/*
* AMRIT – Accessible Medical Records via Integrated Technology
* Integrated EHR (Electronic Health Records) Solution
*
* Copyright (C) "Piramal Swasthya Management and Research Institute"
*
* This file is part of AMRIT.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.iemr.mcts.controller.supervisor;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.mcts.data.mapper.UploadData;
import com.iemr.mcts.data.supervisor.FileManager;
import com.iemr.mcts.services.supervisor.MctsDataHandlerService;
import com.iemr.mcts.utils.response.OutputResponse;

import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("mctsDataHandlerController")
public class MCTSDataHandlerController {

	/**
	 * MCTS mother/ child data upload
	 * 
	 * @param file
	 * @return string message
	 */
	@CrossOrigin()
	@Operation(summary = "Upload data in MCTS")
	@RequestMapping(value = "/mcts/data/upload", method = RequestMethod.POST, headers = "Authorization")
	public String uploadData(@RequestBody String request, HttpServletRequest servletRequest) {

		OutputResponse response = new OutputResponse();

		try {

			//
			UploadData uploadData = mctsDataHandlerService.mctsDataUpload(request, servletRequest);
			if (uploadData != null && uploadData.getFileManager() != null
					&& uploadData.getFileManager().getFileID() != null) {
				final ExecutorService pool = Executors.newFixedThreadPool(10);
				pool.submit(new Callable<String>() {
					@Override
					public String call() {
						String x = "";
						try {
							final UploadData uploadData1 = mctsDataHandlerService.validateData(uploadData);
							x = mctsDataHandlerService.savemother(uploadData1);
						} catch (Exception e) {
							FileManager file = uploadData.getFileManager();
							file.setStatusReason(e.getMessage());
							file.setFileStatusID(4L);
							mctsDataHandlerService.savefilemanger(uploadData.getFileManager());
						}

						return x;
					}
				});
			}
			if (uploadData != null && uploadData.getMessage().contains("FileID")) {
				uploadData.setMessage("FileID");
			}
			if (uploadData != null) {
				response.setResponse(uploadData.getMessage());
			}

		} catch (IllegalStateException | IOException e) {

			response.setError(e);
		} catch (TikaException e) {

			response.setError(e);
		} catch (Exception e) {
			response.setError(e);
		}

		return response.toString();
	}

	/**
	 * File data upload service
	 */
	@Autowired
	private MctsDataHandlerService mctsDataHandlerService;

	/**
	 * MCTS mother/ child data upload
	 * 
	 * @param file
	 * @return string message
	 */
	@CrossOrigin()
	@Operation(summary = "Update beneficiary data in MCTS")
	@RequestMapping(value = "/update/beneficiary/data", method = RequestMethod.POST, headers = "Authorization")
	public String updateBeneficiary(@RequestParam("request") String request) {

		OutputResponse response = new OutputResponse();

		try {

			response.setResponse(mctsDataHandlerService.updateBeneficiary(request));

		} catch (Exception e) {
			response.setError(e);
		}

		return response.toString();
	}

	@CrossOrigin()
	@Operation(summary = "Upload status in MCTS")
	@RequestMapping(value = "/mcts/data/upload/status", method = RequestMethod.POST, headers = "Authorization")
	public String uploadstatus(
			@Param("{\"providerServiceMapID\":\"Integer - provider service ID\"}") @RequestBody String request) {

		OutputResponse response = new OutputResponse();

		try {

			response.setResponse(mctsDataHandlerService.uploadstatus(request).toString());

		} catch (Exception e) {
			response.setError(e);
		}

		return response.toString();
	}
}
