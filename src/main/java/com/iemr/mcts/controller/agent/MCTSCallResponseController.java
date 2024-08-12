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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.mcts.services.agent.MctsCallResponseService;
import com.iemr.mcts.utils.response.OutputResponse;

import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("mctsCallResponseController")
public class MCTSCallResponseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	/**
	 * mcts call response service
	 */
	@Autowired
	private MctsCallResponseService mctsCallResponseService;

	/**
	 * api for reading call history
	 * 
	 * @param request
	 * @return String count of allocated calls
	 */
	@CrossOrigin()
	@Operation(summary = "Save MCTS call response")
	@RequestMapping(value = "/put/call/response", method = RequestMethod.POST, headers = "Authorization")
	public String putMctsCallResponse(
			@Param("{\"callDetailID\":\"Integer\", \"questionID\":\"Integer\", \"answer\":\"String \", \"remark\":\"String\"}") @RequestBody String request) {
		logger.info("putMctsCallResponse request " + request);
		OutputResponse response = new OutputResponse();
		try {

			response.setResponse(mctsCallResponseService.saveMctsCallResponse(request));
			logger.info("putMctsCallResponse response " + response.toString());
		} catch (Exception e) {

			response.setError(e);
		}
		return response.toString();
	}

	@CrossOrigin()
	@Operation(summary = "Get MCTS call response")
	@RequestMapping(value = "get/call/response", method = RequestMethod.POST, headers = "Authorization")
	public String getMctsCallResponse(@Param("{\"callDetailID\":\"Integer\"}") @RequestBody String request) {
		logger.info("getMctsCallResponse request " + request);
		OutputResponse response = new OutputResponse();
		try {

			response.setResponse(mctsCallResponseService.getMctsCallResponse(request));
			logger.info("getMctsCallResponse response " + response.toString());
		} catch (Exception e) {

			response.setError(e);
		}
		return response.toString();
	}

	@CrossOrigin()
	@Operation(summary = "Update MCTS call response")
	@RequestMapping(value = "update/call/response", method = RequestMethod.POST, headers = "Authorization")
	public String updateMctsCallResponse(
			@Param("{\"mctsCallResponseID\":\"Integer\", \"answer\":\"String \", \"remark\":\"String\"}") @RequestBody String request) {
		logger.info("updateMctsCallResponse request " + request);
		OutputResponse response = new OutputResponse();
		try {

			response.setResponse(mctsCallResponseService.updateMctsCallResponse(request));
			logger.info("updateMctsCallResponse response " + response.toString());
		} catch (Exception e) {

			response.setError(e);
		}
		return response.toString();
	}

}
