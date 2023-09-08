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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.mcts.data.supervisor.MctsQAMappingDetail;
import com.iemr.mcts.data.supervisor.MctsQuestionValues;
import com.iemr.mcts.data.supervisor.QuestionnaireDetail;
import com.iemr.mcts.repository.supervisor.MctsQAMappingRepository;
import com.iemr.mcts.repository.supervisor.MctsQuestionValuesRepository;
import com.iemr.mcts.repository.supervisor.QuestionnaireRepository;
import com.iemr.mcts.utils.exception.IEMRException;
import com.iemr.mcts.utils.mapper.InputMapper;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

	private InputMapper inputMapper = new InputMapper();

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
	 * Mcts QA Mapping Detail
	 */
	private MctsQAMappingRepository mctsQAMappingRepository;

	/**
	 * Inject Mcts QA Mapping Detail
	 */
	@Autowired
	public void setMctsQAMappingRepository(MctsQAMappingRepository mctsQAMappingRepository) {

		this.mctsQAMappingRepository = mctsQAMappingRepository;
	}

	@Autowired
	MctsQuestionValuesRepository mctsQuestionValuesRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iemr.mcts.services.supervisor.QuestionnaireService#createQuestionnaire(
	 * java.lang.String)
	 */
	@Override
	public String createQuestionnaire(String request) throws IEMRException {

		QuestionnaireDetail[] questionnaireDetails = inputMapper.gson().fromJson(request, QuestionnaireDetail[].class);

		for (QuestionnaireDetail detail : questionnaireDetails) {

			QuestionnaireDetail questionnaireDetail = questionnaireRepository.save(detail);
		}

		return "Inserted question successfully;";
	}

	@Override
	public String updateQuestionnaire(String request) throws IEMRException {
		int updatedQuestion = 0, updateOption = 0, updateVriable = 0;
		String response = null;
		MctsQAMappingDetail qaDetail = InputMapper.gson().fromJson(request, MctsQAMappingDetail.class);
		QuestionnaireDetail detail = qaDetail.getQuestionnaireDetail();
		updatedQuestion = questionnaireRepository.updateQuestionnaire(detail.getQuestionID(), detail.getQuestion(),
				detail.getAnswerType(), detail.getTriggerFeedback(), detail.getTriggerFeedbackFor(),
				detail.getShowText(), detail.getShowTextFor(), detail.getQuestionRank(), detail.getIsMandatory(),
				detail.getInteraction());

		if (detail.getAnswerType().equalsIgnoreCase("Text") || detail.getAnswerType().equalsIgnoreCase("Date")
				|| detail.getAnswerType().equalsIgnoreCase("Time")
				|| detail.getAnswerType().equalsIgnoreCase("Date & Time")) {
			updateOption = 1;
		} else {
			List<MctsQuestionValues> mctsQuestionValues = detail.getMctsQuestionValues();
			for (MctsQuestionValues options : mctsQuestionValues) {
				System.out.println(options);
				if (options.getQuestionValuesID() != null) {
					updateOption = mctsQuestionValuesRepository.updateQuestionValues(options.getDropDownOptions(),
							options.getDeleted(), detail.getQuestionID(), options.getQuestionValuesID());
				} else {
					options.setCreatedBy(detail.getCreatedBy());
					options.setProviderServiceMapID(detail.getProviderServiceMapID());
					options.setQuestionID(detail.getQuestionID());
					mctsQuestionValuesRepository.save(options);
				}

			}
		}

		updateVriable = mctsQAMappingRepository.editVariableName(qaDetail.getVariableName(),
				qaDetail.getVariableDataType(), qaDetail.getMctsQAMapID());
		if (updatedQuestion > 0 && updateOption > 0 && updateVriable > 0)
			response = "Question updated successfully";

		return response;
	}

}
