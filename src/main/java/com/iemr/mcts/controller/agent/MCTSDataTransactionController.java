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
package com.iemr.mcts.controller.agent;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.mcts.services.agent.MctsDataTransactionService;
import com.iemr.mcts.utils.response.OutputResponse;

import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping(value = "/mctsdatatransactioncontroller")
public class MCTSDataTransactionController {

	/**
	 * private MctsDataTransactionService
	 */
	@Autowired
	private MctsDataTransactionService mctsDataTransactionService;

	@CrossOrigin
	@Operation(summary = "Update MCTS data")
	@RequestMapping(value = "/edit/mctsdata", method = RequestMethod.POST, headers = "Authorization")
	public String updateData(@RequestBody String request, HttpServletRequest servletRequest) {

		OutputResponse response = new OutputResponse();
		try {

			response.setResponse(mctsDataTransactionService.updateData(request, servletRequest));
		} catch (Exception e) {

			response.setError(e);
		}
		return response.toString();
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin
	@Operation(summary = "Associate beneficiary id")
	@RequestMapping(value = "/associate/beneficiaryID", method = RequestMethod.POST, headers = "Authorization")
	public String associateBeneficiaryID(
			@Param("{\"mCTSID_no\":\"Integer\", \"beneficiaryRegID\":\"String \"}") @RequestBody String request) {

		OutputResponse response = new OutputResponse();
		try {

			response.setResponse(mctsDataTransactionService.associateBeneficiaryID(request));
		} catch (Exception e) {

			response.setError(e);
		}
		return response.toString();
	}
}
