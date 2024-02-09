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
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.mcts.data.supervisor.OutboundCallAnsweredCountDetail;
import com.iemr.mcts.services.agent.CallCountService;
import com.iemr.mcts.utils.response.OutputResponse;

import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/callCountController")
public class CallCountController {

	Logger logger = LoggerFactory.getLogger(CallCountController.class);

	/**
	 * CallCountService service
	 */
	@Autowired
	private CallCountService callCountService;

	@CrossOrigin
	@Operation(summary = "Get count of answered calls")
	@RequestMapping(value = "/getCallAnsweredCount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getCallAnsweredCount(
			@Param("{\"providerServiceMapID\":\"Integer \", \"agentID\":\"Integer\"}") @RequestBody OutboundCallAnsweredCountDetail outboundCallAnsweredCountDetail,
			HttpServletRequest httpRequest) {
		OutputResponse response = new OutputResponse();
		logger.info("getCallAnsweredCount request " + outboundCallAnsweredCountDetail);
		try {
			response.setResponse(callCountService.getCallAnsweredCount(outboundCallAnsweredCountDetail));
		} catch (Exception e) {
			logger.error("getCallAnsweredCount failed with error " + e.getMessage(), e);
			response.setError(e);
		}
		logger.info("getCallAnsweredCount response " + response.toString());
		return response.toString();
	}

	@CrossOrigin
	@Operation(summary = "Get count of verified calls")
	@RequestMapping(value = "/getCallVerifiedCount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String getCallVerifiedCount(
			@Param("{\"providerServiceMapID\":\"Integer \", \"agentID\":\"Integer\"}") @RequestBody OutboundCallAnsweredCountDetail outboundCallAnsweredCountDetail,
			HttpServletRequest httpRequest) {
		OutputResponse response = new OutputResponse();
		logger.info("getCallVerifiedCount request " + outboundCallAnsweredCountDetail);
		try {
			response.setResponse(callCountService.getCallVerifiedCount(outboundCallAnsweredCountDetail));
		} catch (Exception e) {
			logger.error("getCallVerifiedCount failed with error " + e.getMessage(), e);
			response.setError(e);
		}
		logger.info("getCallVerifiedCount response " + response.toString());
		return response.toString();
	}

}
