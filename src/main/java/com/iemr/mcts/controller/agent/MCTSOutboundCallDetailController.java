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

import com.iemr.mcts.services.agent.MctsOutboundCallDetailService;
import com.iemr.mcts.utils.response.OutputResponse;

import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("mctsOutboundCallDetailController")
public class MCTSOutboundCallDetailController {

	/**
	 * outbound call service for history
	 */
	@Autowired
	private MctsOutboundCallDetailService mctsOutboundCallDetailService;

	/**
	 * api for reading beneficiary on call transfer
	 * 
	 * @param request
	 * @return String count of allocated calls
	 */
	@CrossOrigin()
	@Operation(summary = "Get beneficiary details")
	@RequestMapping(value = "/get/benificiary/details", method = RequestMethod.POST, headers = "Authorization")
	public String getBeneficiaryDetails(@Param("{\"beneficiaryRegID\":\"Integer \"}") @RequestBody String request,
			HttpServletRequest servletRequest) {

		OutputResponse response = new OutputResponse();
		try {

			response.setResponse(mctsOutboundCallDetailService.getBeneficiaryDetails(request, servletRequest));
		} catch (Exception e) {

			response.setError(e);
		}
		return response.toString();
	}

	/**
	 * to save call after call is connected
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin
	@Operation(summary = "Create call history")
	@RequestMapping(value = "/put/call/history", method = RequestMethod.POST, headers = "Authorization")
	public String getVariables(
			@Param("{\"czentrixCallID\":\"String\", \"allocatedUserID\":\"Integer \"}") @RequestBody String request) {

		OutputResponse response = new OutputResponse();

		try {

			response.setResponse(mctsOutboundCallDetailService.createCallHistory(request));

		} catch (Exception e) {

			response.setError(e);
		}

		return response.toString();
	}

	/**
	 * to save call after call is connected
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin
	@Operation(summary = "Get change log")
	@RequestMapping(value = "/get/change/log", method = RequestMethod.POST, headers = "Authorization")
	public String getChangeLog(@Param("{\"childID or mothetID\":\"String\"}") @RequestBody String request) {

		OutputResponse response = new OutputResponse();

		try {

			response.setResponse(mctsOutboundCallDetailService.getChangeLog(request));

		} catch (Exception e) {

			response.setError(e);
		}

		return response.toString();
	}

	/**
	 * to send sms advice
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin
	@Operation(summary = "Send SMS advice")
	@RequestMapping(value = "/put/sms/advice", method = RequestMethod.POST, headers = "Authorization")
	public String sendSmsAdvise(
			@Param("{\"smsAdvice\":\"String\", \"callDetailID\":\"Integer\"}") @RequestBody String request) {

		OutputResponse response = new OutputResponse();

		try {

			response.setResponse(mctsOutboundCallDetailService.sendSmsAdvice(request));

		} catch (Exception e) {

			response.setError(e);
		}

		return response.toString();
	}

	/**
	 * to save get advice - updation will be done if required
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin
	@Operation(summary = "Get SMS advice")
	// @RequestMapping(value = "/get/sms/advise", method = RequestMethod.POST,
	// headers = "Authorization")
	public String getSmsAdvise(@RequestBody String request) {

		OutputResponse response = new OutputResponse();

		try {

			response.setResponse(mctsOutboundCallDetailService.getSmsAdvice(request));

		} catch (Exception e) {

			response.setError(e);
		}

		return response.toString();
	}

	/**
	 * case sheet
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin
	@Operation(summary = "Get case record")
	@RequestMapping(value = "/case/sheet", method = RequestMethod.POST, headers = "Authorization")
	public String caseSheet(@Param("{\"callDetailID\":\"Integer\"}") @RequestBody String request,
			HttpServletRequest servletRequest) {

		OutputResponse response = new OutputResponse();
		try {

			response.setResponse(mctsOutboundCallDetailService.caseSheet(request, servletRequest));
		} catch (Exception e) {

			response.setError(e);
		}
		return response.toString();
	}
}
