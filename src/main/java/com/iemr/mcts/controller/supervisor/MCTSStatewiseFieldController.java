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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.mcts.services.supervisor.MctsStatewiseFieldsService;
import com.iemr.mcts.utils.response.OutputResponse;

import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/mctsStatewiseFieldsController")
public class MCTSStatewiseFieldController {

	/**
	 * mcta statewise fields controller
	 */
	@Autowired
	private MctsStatewiseFieldsService mctsStatewiseFieldsService;

	@CrossOrigin
	@Operation(summary = "Get variables based on provider service map id and field")
	@RequestMapping(value = "/get/variablenames", method = RequestMethod.POST, headers = "Authorization")
	public String getVariables(
			@Param("{\"providerServiceMapID\":\"Integer\", \"fieldFor\":\"String Name\"}") @RequestBody String request) {

		OutputResponse response = new OutputResponse();

		try {

			response.setResponse(mctsStatewiseFieldsService.getVariables(request));

		} catch (Exception e) {

			response.setError(e);
		}

		return response.toString();
	}
}
