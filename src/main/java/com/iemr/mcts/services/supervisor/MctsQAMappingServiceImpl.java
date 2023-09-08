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
package com.iemr.mcts.services.supervisor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.mcts.data.agent.MctsCallResponseDetail;
import com.iemr.mcts.data.supervisor.MctsOutboundCall;
import com.iemr.mcts.data.supervisor.MctsQAMappingDetail;
import com.iemr.mcts.data.supervisor.MctsQuestionValues;
import com.iemr.mcts.data.supervisor.QuestionnaireDetail;
import com.iemr.mcts.data.utils.MctsCallQAUtils;
import com.iemr.mcts.repository.agent.MctsCallResponseRepository;
import com.iemr.mcts.repository.supervisor.MctsQAMappingRepository;
import com.iemr.mcts.repository.supervisor.MctsQuestionValuesRepository;
import com.iemr.mcts.repository.supervisor.QuestionnaireRepository;
import com.iemr.mcts.utils.exception.IEMRException;
import com.iemr.mcts.utils.mapper.InputMapper;
import com.iemr.mcts.utils.mapper.OutputMapper;

@Service
public class MctsQAMappingServiceImpl implements MctsQAMappingService {

	InputMapper inputMapper = new InputMapper();

	/**
	 * Mcts QA Mapping Repository
	 */
	private MctsQAMappingRepository mctsQAMappingRepository;

	/**
	 * Inject Mcts QA Mapping Repository
	 * 
	 * @param mctsQAMappingRepository
	 */
	@Autowired
	public void setMctsQAMappingRepository(MctsQAMappingRepository mctsQAMappingRepository) {

		this.mctsQAMappingRepository = mctsQAMappingRepository;
	}

	/**
	 * Question Type Repository
	 */
	private QuestionnaireRepository questionnaireRepository;

	/**
	 * Inject Question Type Repository
	 */
	@Autowired
	public void setQuestionnaireRepository(QuestionnaireRepository questionnaireRepository) {

		this.questionnaireRepository = questionnaireRepository;
	}

	/**
	 * mcts call response repository
	 */
	private MctsCallResponseRepository mctsCallResponseRepository;

	/**
	 * Inject mcts call response reposijtory
	 */
	@Autowired
	public void setMctsCallResponseRepository(MctsCallResponseRepository mctsCallResponseRepository) {

		this.mctsCallResponseRepository = mctsCallResponseRepository;
	}

	@Autowired
	MctsQuestionValuesRepository mctsQuestionValuesRepository;

	/**
	 * JSONParser
	 */
	private static JSONParser parser = new JSONParser();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iemr.mcts.services.supervisor.MctsQAMappingService#
	 * createOutBoundQuestions(java.lang.String)
	 */
	@Override
	public String createOutboundQuestions(String request) throws IEMRException {

		MctsQAMappingDetail[] mappingDetails = InputMapper.gson().fromJson(request, MctsQAMappingDetail[].class);

		for (MctsQAMappingDetail detail : mappingDetails) {

			// checking the rank of the adding question
			MctsQAMappingDetail checkRank = mctsQAMappingRepository.checkRank(
					detail.getQuestionnaireDetail().getQuestionRank(), detail.getOutboundCallType(),
					detail.getProviderServiceMapID(), detail.getEffectiveFrom());

			QuestionnaireDetail questionnaireDetail = null;

			if (checkRank != null) {

				int updateRank = mctsQAMappingRepository.updateRank(detail.getQuestionnaireDetail().getQuestionRank(),
						detail.getOutboundCallType(), detail.getProviderServiceMapID(), detail.getEffectiveFrom());

				if (updateRank > 0) {
					questionnaireDetail = questionnaireRepository.save(detail.getQuestionnaireDetail());
				}

			} else {
				// saving questions
				questionnaireDetail = questionnaireRepository.save(detail.getQuestionnaireDetail());

			}

			// saving options of the questions.
			if (questionnaireDetail != null) {
				ArrayList<MctsQuestionValues> mctsQuestionValues = questionnaireDetail.getQuestionOptions();
				for (MctsQuestionValues options : mctsQuestionValues) {
					options.setCreatedBy(questionnaireDetail.getCreatedBy());
					options.setQuestionID(questionnaireDetail.getQuestionID());
					options.setProviderServiceMapID(questionnaireDetail.getProviderServiceMapID());

				}
				mctsQuestionValuesRepository.save(mctsQuestionValues);

				detail.setQuestionID(questionnaireDetail.getQuestionID());
				// saving mapping
				mctsQAMappingRepository.save(detail);
			}
		}

		return "Successfully added questions";
	}

	/*
	 * to get outbound call list on outbound call type (non-Javadoc)
	 * 
	 * @see com.iemr.mcts.services.supervisor.MctsQAMappingService#
	 * getOutboundQuestionList(java.lang.String)
	 */
	@Override
	public String getOutboundQuestionList(String request) throws Exception {

		MctsCallQAUtils mctsCallQAUtils = InputMapper.gson().fromJson(request, MctsCallQAUtils.class);
		MctsOutboundCall mctsOutboundCall = mctsCallQAUtils.getMctsOutboundCall();
		MctsQAMappingDetail mctsQAMappingDetail = mctsCallQAUtils.getMctsQAMappingDetail();
		List<MctsQAMappingDetail> mctsQAMappingDetails = null;
		List<MctsCallResponseDetail> mctsCallResponseDetails = null;
		StringBuilder interactions = new StringBuilder();
		Map<String, Object> result = new HashMap<String, Object>();

		if (mctsQAMappingDetail.getOutboundCallType() != null) {

			// interaction questions needs to be added here
			mctsQAMappingDetails = mctsQAMappingRepository.getQuestionsTypeList(
					mctsQAMappingDetail.getOutboundCallType(), mctsQAMappingDetail.getProviderServiceMapID(),
					mctsQAMappingDetail.getEffectiveFrom());

			if (mctsOutboundCall != null) {

				if (mctsOutboundCall != null) {
					mctsCallResponseDetails = mctsCallResponseRepository
							.getMctsCallResponse(mctsCallQAUtils.getCallDetailID());
				}

				JSONObject json = null;
				if (mctsOutboundCall.getMctsDataReaderDetail() != null) {

					json = (JSONObject) parser.parse(mctsOutboundCall.getMctsDataReaderDetail().toString());
				} else {

					json = (JSONObject) parser.parse(mctsOutboundCall.getChildValidDataHandler().toString());
				}

				List<MctsQAMappingDetail> obInteractions = mctsQAMappingRepository.getOutboundCallInteractions(
						mctsQAMappingDetail.getOutboundCallType(), mctsQAMappingDetail.getProviderServiceMapID(),
						mctsQAMappingDetail.getEffectiveFrom());

				ListIterator<MctsQAMappingDetail> iterator = obInteractions.listIterator();
				while (iterator.hasNext()) {
					MctsQAMappingDetail detail = (MctsQAMappingDetail) iterator.next();

					if (detail.getInteraction() != null) {
						interactions.append(detail.getInteraction() + " ");
						if (detail.getVariableName() != null) {

							if (json.get(detail.getVariableName()) != null) {
								if (detail.getVariableDataType().equalsIgnoreCase("date")) {
									String jsonDate = (String) json.get(detail.getVariableName());
									Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(jsonDate);
									String strDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
									interactions.append(strDate + " ");
								} else {
									interactions.append(json.get(detail.getVariableName()) + " ");
								}
							}
						}
					}
				}
			}

			result.put("interactions", interactions.toString());
			result.put("questions", mctsQAMappingDetails);
			result.put("answers", mctsCallResponseDetails);
		} else {

			mctsQAMappingDetails = mctsQAMappingRepository.getQuestionsList(
					mctsQAMappingDetail.getProviderServiceMapID(), mctsQAMappingDetail.getEffectiveFrom());
			result.put("interactions", " ");
			result.put("questions", mctsQAMappingDetails);
		}

		return OutputMapper.gson().toJson(result);
	}

	@Override
	public String deleteQuestion(String request) throws IEMRException {
		int deleted = 0;
		String response = null;
		MctsQAMappingDetail mappingDetail = InputMapper.gson().fromJson(request, MctsQAMappingDetail.class);

		if (mappingDetail.getQuestionnaireDetail().getQuestionRank() == null) {
			deleted = mctsQAMappingRepository.markDelete(mappingDetail.getQuestionID());
		} else {
			// checking the rank of the adding question
			int degradeRank = mctsQAMappingRepository.degradeRank(
					mappingDetail.getQuestionnaireDetail().getQuestionRank(), mappingDetail.getOutboundCallType(),
					mappingDetail.getProviderServiceMapID(), mappingDetail.getEffectiveFrom());
			if (degradeRank > 0) {
				deleted = mctsQAMappingRepository.markDelete(mappingDetail.getQuestionID());

				if (mappingDetail.getQuestionnaireDetail().getAnswerType().equalsIgnoreCase("Yes/No")
						|| mappingDetail.getQuestionnaireDetail().getAnswerType().equalsIgnoreCase("Multiple")
						|| mappingDetail.getQuestionnaireDetail().getAnswerType().equalsIgnoreCase("DropDown")) {
					deleted = 0;
					deleted = mctsQuestionValuesRepository.deleteQuestionValues(mappingDetail.getQuestionID());
				}
			}

		}

		if (deleted > 0)
			response = "Deleted successfully";

		return response;
	}

	@Override
	public String deleteMultipleQuestions(String request) throws IEMRException {
		MctsQAMappingDetail[] mappingDetail = InputMapper.gson().fromJson(request, MctsQAMappingDetail[].class);
		int index = 0, deleted = 0;
		String response = null;
		for (MctsQAMappingDetail questionDelete : mappingDetail) {

			if (questionDelete.getQuestionnaireDetail().getQuestionRank() == null) {
				deleted = mctsQAMappingRepository.markDelete(questionDelete.getQuestionID());
			} else {
				questionDelete.getQuestionnaireDetail()
						.setQuestionRank(questionDelete.getQuestionnaireDetail().getQuestionRank() - index);
				int degradeRank = mctsQAMappingRepository.degradeRank(
						questionDelete.getQuestionnaireDetail().getQuestionRank(), questionDelete.getOutboundCallType(),
						questionDelete.getProviderServiceMapID(), questionDelete.getEffectiveFrom());
				if (degradeRank > 0) {
					deleted = mctsQAMappingRepository.markDelete(questionDelete.getQuestionID());
					if (questionDelete.getQuestionnaireDetail().getAnswerType().equalsIgnoreCase("Yes/No")
							|| questionDelete.getQuestionnaireDetail().getAnswerType().equalsIgnoreCase("Multiple")
							|| questionDelete.getQuestionnaireDetail().getAnswerType().equalsIgnoreCase("DropDown")) {
						deleted = 0;
						deleted = mctsQuestionValuesRepository.deleteQuestionValues(questionDelete.getQuestionID());
					}
					index++;
				} else {
					deleted = 0;
				}
			}

		}
		if (deleted > 0) {
			response = "Deleted successfully";
		}

		return response;
	}

	@Override
	public String saveInteractions(String request) throws IEMRException {

		MctsQAMappingDetail[] mappingDetails = inputMapper.gson().fromJson(request, MctsQAMappingDetail[].class);
		for (MctsQAMappingDetail mappingDetail : mappingDetails) {

			mctsQAMappingRepository.save(mappingDetail);
		}

		return "Interactions saved successfully";
	}

	@Override
	public String updateInteraction(String request) throws IEMRException {

		MctsQAMappingDetail mappingDetail = inputMapper.gson().fromJson(request, MctsQAMappingDetail.class);
		mctsQAMappingRepository.updateInteraction(mappingDetail.getMctsQAMapID(), mappingDetail.getInteraction(),
				mappingDetail.getVariableName(), mappingDetail.getVariableDataType());
		return "Interaction updated successfully";
	}

	@Override
	public String interactionsList(String request) throws IEMRException {

		MctsQAMappingDetail mappingDetail = inputMapper.gson().fromJson(request, MctsQAMappingDetail.class);
		return mctsQAMappingRepository.findByQuestionID(mappingDetail.getQuestionID()).toString();
	}

	@Override
	public String deleteInteraction(String request) throws IEMRException {

		MctsQAMappingDetail mappingDetail = inputMapper.gson().fromJson(request, MctsQAMappingDetail.class);
		mctsQAMappingRepository.deleteInteraction(mappingDetail.getMctsQAMapID());
		return "Interaction deleted successfully";
	}

	@Override
	public String addDeriveQuestion(String request) throws IEMRException {
		String response = null;
		QuestionnaireDetail questionnaireDetail = InputMapper.gson().fromJson(request, QuestionnaireDetail.class);
		int success = questionnaireRepository.updateDeriveQuestion(questionnaireDetail.getParentQuestionID(),
				questionnaireDetail.getParentAnswer(), questionnaireDetail.getChildQuestionID(),
				questionnaireDetail.getProviderServiceMapID());
		if (success == 1)
			response = "Derived question added successfully";

		return response;
	}

	@Override
	public String getAgentOutboundQuestionList(String request) throws Exception {

		MctsCallQAUtils mctsCallQAUtils = InputMapper.gson().fromJson(request, MctsCallQAUtils.class);
		MctsOutboundCall mctsOutboundCall = mctsCallQAUtils.getMctsOutboundCall();
		MctsQAMappingDetail mctsQAMappingDetail = mctsCallQAUtils.getMctsQAMappingDetail();
		List<MctsQAMappingDetail> mctsQAMappingDetailsParent = null;
		List<MctsCallResponseDetail> mctsCallResponseDetails = null;
		List<MctsQAMappingDetail> mctsQAMappingDetailsChild = null;
		StringBuilder interactions = new StringBuilder();
		Map<String, Object> result = new HashMap<String, Object>();

		if (mctsQAMappingDetail.getOutboundCallType() != null) {

			mctsQAMappingDetailsParent = mctsQAMappingRepository.getParentQuestionsTypeList(
					mctsQAMappingDetail.getOutboundCallType(), mctsQAMappingDetail.getProviderServiceMapID(),
					mctsQAMappingDetail.getEffectiveFrom());

			mctsQAMappingDetailsChild = mctsQAMappingRepository.getChildQuestionsTypeList(
					mctsQAMappingDetail.getOutboundCallType(), mctsQAMappingDetail.getProviderServiceMapID(),
					mctsQAMappingDetail.getEffectiveFrom());

			if (mctsOutboundCall != null) {

				if (mctsOutboundCall != null) {
					mctsCallResponseDetails = mctsCallResponseRepository.getMctsCallResponseForAgent(
							mctsCallQAUtils.getCallDetailID(), mctsQAMappingDetail.getOutboundCallType());
				}

				JSONObject json = null;
				if (mctsOutboundCall.getMctsDataReaderDetail() != null) {

					json = (JSONObject) parser.parse(mctsOutboundCall.getMctsDataReaderDetail().toString());
				} else {

					json = (JSONObject) parser.parse(mctsOutboundCall.getChildValidDataHandler().toString());
				}

				List<MctsQAMappingDetail> obInteractions = mctsQAMappingRepository.getOutboundCallInteractions(
						mctsQAMappingDetail.getOutboundCallType(), mctsQAMappingDetail.getProviderServiceMapID(),
						mctsQAMappingDetail.getEffectiveFrom());

				ListIterator<MctsQAMappingDetail> iterator = obInteractions.listIterator();
				while (iterator.hasNext()) {
					MctsQAMappingDetail detail = (MctsQAMappingDetail) iterator.next();

					if (detail.getInteraction() != null) {
						interactions.append(detail.getInteraction() + " ");
						if (detail.getVariableName() != null) {

							if (json.get(detail.getVariableName()) != null) {
								if (detail.getVariableDataType().equalsIgnoreCase("date")) {
									String jsonDate = (String) json.get(detail.getVariableName());
									Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(jsonDate);
									String strDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
									interactions.append(strDate + " ");
								} else {
									interactions.append(json.get(detail.getVariableName()) + " ");
								}
							}
						}
					}
				}
			}

			result.put("interactions", interactions.toString());
			result.put("parentQuestions", mctsQAMappingDetailsParent);
			result.put("childQuestions", mctsQAMappingDetailsChild);
			result.put("answers", mctsCallResponseDetails);
		} else {

			mctsQAMappingDetailsParent = mctsQAMappingRepository.getQuestionsList(
					mctsQAMappingDetail.getProviderServiceMapID(), mctsQAMappingDetail.getEffectiveFrom());
			result.put("interactions", " ");
			result.put("questions", mctsQAMappingDetailsParent);
		}

		return OutputMapper.gson().toJson(result);
	}

}
