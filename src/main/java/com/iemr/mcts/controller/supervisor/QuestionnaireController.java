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

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.mcts.services.supervisor.MctsQAMappingService;
import com.iemr.mcts.services.supervisor.QuestionnaireService;
import com.iemr.mcts.utils.response.OutputResponse;

import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/questionnaireController")
public class QuestionnaireController {

	Logger logger = LoggerFactory.getLogger(QuestionnaireController.class);

	/**
	 * Question Type Repository
	 */
	@Autowired
	private QuestionnaireService questionnaireService;

	/**
	 * Mcts QA Mapping Service
	 */
	@Autowired
	private MctsQAMappingService mctsQAMappingService;

	/**
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin()
	@Operation(summary = "Update interaction")
	@RequestMapping(value = "/edit/interaction", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String editInteraction(
			@Param("{\"mctsQAMapID\":\"Integer\", \"interaction\":\"String Value\", \"variableName\":\"String-Name \", \"variableDataType\":\"String- Name\"}") @RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {
			response.setResponse(mctsQAMappingService.updateInteraction(request));
		} catch (Exception e) {
			logger.error("eidt-interaction failed with error " + e.getMessage(), e);
			response.setError(e);
		}

		return response.toString();
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin()
	@Operation(summary = "Delete interaction")
	@RequestMapping(value = "/delete/interaction", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String deleteInteraction(@Param("{\"mctsQAMapID\":\"Integer\")}") @RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {
			response.setResponse(mctsQAMappingService.deleteInteraction(request));
		} catch (Exception e) {
			logger.error("eidt-interaction failed with error " + e.getMessage(), e);
			response.setError(e);
		}

		return response.toString();
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin()
	@Operation(summary = "Fetch interaction list")
	@RequestMapping(value = "/get/interaction/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String interactionsList(@Param("{\"questionID\":\"Integer\"}") @RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {
			response.setResponse(mctsQAMappingService.interactionsList(request));
		} catch (Exception e) {
			logger.error("interaction-list failed with error " + e.getMessage(), e);
			response.setError(e);
		}

		return response.toString();
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin()
	@Operation(summary = "Edit questionnaire")
	@RequestMapping(value = "/edit/questionnaire", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String editQuestionnaire(
			@Param("{\"questionID\":\"Integer\", \"question\":\"String Value\", \"answerType\":\"String-Name \", "
					+ "\"triggerFeedback\":\"Boolean- Value\", \"triggerFeedbackFor\":\"String-Value\", \"showText\":\"Boolean- Value\", \"showTextFor\":\"String-Value\"}") @RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {
			String res = questionnaireService.updateQuestionnaire(request);
			if (res != null)
				response.setResponse(res);
			else
				response.setError(5000, "Question not updated successfully");
		} catch (Exception e) {
			logger.error("put-questionnaire failed with error " + e.getMessage(), e);
			response.setError(e);
		}

		return response.toString();
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin()
	@Operation(summary = "Save interactions")
	@RequestMapping(value = "/put/interactions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String addInteractions(@RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {
			response.setResponse(mctsQAMappingService.saveInteractions(request));
		} catch (Exception e) {
			logger.error("save-interactions failed with error " + e.getMessage(), e);
			response.setError(e);
		}

		return response.toString();
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin()
	@Operation(summary = "Create outbound questions")
	@RequestMapping(value = "/put/outboundcall/questions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String createOutBoundQuestions(@RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {

			response.setResponse(mctsQAMappingService.createOutboundQuestions(request));
		} catch (Exception e) {
			logger.error("put-outboundcall-questions failed with error " + e.getMessage(), e);
			response.setError(e);
		}

		return response.toString();
	}

	/**
	 * getting list of questions on outbound call type
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin()
	@Operation(summary = "Get outbound question list")
	@RequestMapping(value = "/get/questionnaireList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String outboundQuestionList(@RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {

			response.setResponse(mctsQAMappingService.getOutboundQuestionList(request));
		} catch (Exception e) {
			logger.error("get-outbound question list " + e.getMessage(), e);
			response.setError(e);
		}

		return response.toString();
	}

	/**
	 * getting list of questions on outbound call type
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin()
	@Operation(summary = "Delete question")
	@RequestMapping(value = "/delete/question", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String updateQuestion(@Param("{\"questionID\":\"Integer\"}") @RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {
			String res = mctsQAMappingService.deleteQuestion(request);
			if (res != null)
				response.setResponse(res);
			else
				response.setError(5000, "Question not deleted successfully");
		} catch (Exception e) {
			logger.error("get-outbound question list " + e.getMessage(), e);
			response.setError(e);
		}

		return response.toString();
	}

	@CrossOrigin()
	@Operation(summary = "Add derived question")
	@RequestMapping(value = "derived/addDeriveQuestion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String deriveQuestion(@Param("{\"questionID\":\"Integer\"}") @RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {

			response.setResponse(mctsQAMappingService.addDeriveQuestion(request));
			String res = mctsQAMappingService.addDeriveQuestion(request);
			if (res != null)
				response.setResponse(res);
			else
				response.setError(5000, "Question not mapped successfully");
		} catch (Exception e) {
			response.setError(e);
		}

		return response.toString();
	}

	@CrossOrigin()
	@Operation(summary = "Get agent outbound question list")
	@RequestMapping(value = "/get/agentQuestionnaireList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String outboundQuestionListAgent(@RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {

			response.setResponse(mctsQAMappingService.getAgentOutboundQuestionList(request));
		} catch (Exception e) {
			logger.error("get-outbound question list " + e.getMessage(), e);
			response.setError(e);
		}

		return response.toString();
	}

	@CrossOrigin()
	@Operation(summary = "Delete multiple questions")
	@RequestMapping(value = "/delete/multipleQuestion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String deleteMultipleQuestions(@RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {

			String res = mctsQAMappingService.deleteMultipleQuestions(request);
			if (res != null)
				response.setResponse(res);
			else
				response.setError(5000, "Questions not deleted successfully");
		} catch (Exception e) {
			logger.error("get-outbound question list " + e.getMessage(), e);
			response.setError(e);
		}

		return response.toString();
	}

}
