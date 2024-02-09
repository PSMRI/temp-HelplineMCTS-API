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
package com.iemr.mcts.services.agent;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.mcts.data.supervisor.ChildValidDataHandler;
import com.iemr.mcts.data.supervisor.DBExcelFieldName;
import com.iemr.mcts.data.supervisor.MctsDataReaderDetail;
import com.iemr.mcts.data.supervisor.MctsOutboundCall;
import com.iemr.mcts.data.supervisor.MctsStatewiseFieldsDetail;
import com.iemr.mcts.data.utils.CompareObjectsUtils;
import com.iemr.mcts.data.utils.OutboundCallDatesUpdater;
import com.iemr.mcts.repository.agent.MCTSOutboundCallRepository;
import com.iemr.mcts.repository.agent.MctsOutboundCallDetailRepository;
import com.iemr.mcts.repository.supervisor.ChildValidDataRepository;
import com.iemr.mcts.repository.supervisor.MctsDataHandlerRepository;
import com.iemr.mcts.repository.supervisor.MctsStatewiseFieldsRepository;
import com.iemr.mcts.utils.exception.IEMRException;
import com.iemr.mcts.utils.mapper.InputMapper;

@Service
public class MctsDataTransactionServiceImpl implements MctsDataTransactionService {

	/**
	 * String Change Log
	 */
	private String changeLog = null;

	/**
	 * child fields map
	 */
	Map<String, String> childFields = null;

	/**
	 * mother fields map
	 */
	Map<String, String> motherFields = null;

	/**
	 * mcts data handler repostory -- for valid data
	 */
	private MctsDataHandlerRepository mctsDataHandlerRepository;

	/**
	 * Inject mcts data handler repository -- for valid data
	 */
	@Autowired
	public void setMctsDataHandlerRepository(MctsDataHandlerRepository mctsDataHandlerRepository) {

		this.mctsDataHandlerRepository = mctsDataHandlerRepository;
	}

	/**
	 * Child valid data repository
	 */
	private ChildValidDataRepository childValidDataRepository;

	/**
	 * Inject child valid data repository
	 */
	@Autowired
	public void setChildValidDataRepository(ChildValidDataRepository childValidDataRepository) {

		this.childValidDataRepository = childValidDataRepository;
	}

	/**
	 * mcts statewise fields repository
	 */
	private MctsStatewiseFieldsRepository mctsStatewiseFieldsRepository;

	/**
	 * inject mcts statewise fields repository
	 */
	@Autowired
	public void setMctsStatewiseFieldsRepository(MctsStatewiseFieldsRepository mctsStatewiseFieldsRepository) {

		this.mctsStatewiseFieldsRepository = mctsStatewiseFieldsRepository;
	}

	/**
	 * mcts outbound call repository
	 */
	private MCTSOutboundCallRepository mctsOutboundCallRepository;

	/**
	 * inject mcts outbound call repository
	 */
	@Autowired
	public void setMCTSOutboundCallsRepository(MCTSOutboundCallRepository mctsOutboundCallRepository) {

		this.mctsOutboundCallRepository = mctsOutboundCallRepository;
	}

	/**
	 * repository for call history
	 */
	private MctsOutboundCallDetailRepository mctsOutboundCallDetailRepository;

	/**
	 * Inject repository for call history
	 */
	@Autowired
	public void setMctsOutboundCallDeatilRepository(MctsOutboundCallDetailRepository mctsOutboundCallDetailRepository) {

		this.mctsOutboundCallDetailRepository = mctsOutboundCallDetailRepository;
	}

	/**
	 * Inject OutboundCallDatesUpdater
	 */
	@Autowired
	private OutboundCallDatesUpdater outboundCallDatesUpdater;

	/**
	 * Input mapper
	 */
	private static InputMapper inputMapper = new InputMapper();
	
	/**
	 * Inject MctsIdentityServiceImpl
	 */
	private MctsIdentityServiceImpl mctsIdentityServiceImpl;

	/**
	 * @param mctsIdentityServiceImpl the mctsIdentityServiceImpl to set
	 */
	@Autowired
	public void setMctsIdentityServiceImpl(MctsIdentityServiceImpl mctsIdentityServiceImpl) {
		this.mctsIdentityServiceImpl = mctsIdentityServiceImpl;
	}

	@Override
	public String updateData(String request,HttpServletRequest servletRequest)
			throws IEMRException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		MctsOutboundCall mctsOutboundCall = inputMapper.gson().fromJson(request, MctsOutboundCall.class);

		if (mctsOutboundCall.getChildValidDataHandler() != null) {

			ChildValidDataHandler old = childValidDataRepository
					.findById(mctsOutboundCall.getChildValidDataHandler().getRowID()).get();

			if (!old.toString().equals(mctsOutboundCall.getChildValidDataHandler().toString())) {

				if (childFields == null)
					childFields = this.getFieldsMap(mctsOutboundCall.getProviderServiceMapID(), "Child Data");

				changeLog = CompareObjectsUtils.compareObjects(old, mctsOutboundCall.getChildValidDataHandler(),
						childFields);
				if (!old.getDOB().equals(mctsOutboundCall.getChildValidDataHandler().getDOB())) {

					outboundCallDatesUpdater.updateOutboundCallDates(
							mctsOutboundCall.getChildValidDataHandler().getDOB(),
							mctsOutboundCall.getOutboundCallType(), false,
							mctsOutboundCall.getChildValidDataHandler().getMCTSID_no_Child_ID(),
							mctsOutboundCall.getProviderServiceMapID());
				}
				childValidDataRepository.save(mctsOutboundCall.getChildValidDataHandler());
			}
		} else {

			MctsDataReaderDetail old = mctsDataHandlerRepository
					.findById(mctsOutboundCall.getMctsDataReaderDetail().getMotherValidRecordID()).get();
			if (!old.toString().equals(mctsOutboundCall.getMctsDataReaderDetail().toString())) {

				if (motherFields == null) {
					motherFields = this.getFieldsMap(mctsOutboundCall.getProviderServiceMapID(), "Mother Data");
				}
				changeLog = CompareObjectsUtils.compareObjects(old, mctsOutboundCall.getMctsDataReaderDetail(),
						motherFields);

				if (!old.getLMP_Date().equals(mctsOutboundCall.getMctsDataReaderDetail().getLMP_Date())) {

					mctsOutboundCall.getMctsDataReaderDetail()
							.setEDD(getEDD(mctsOutboundCall.getMctsDataReaderDetail().getLMP_Date()));

					outboundCallDatesUpdater.updateOutboundCallDates(
							mctsOutboundCall.getMctsDataReaderDetail().getLMP_Date(),
							mctsOutboundCall.getOutboundCallType(), true,
							mctsOutboundCall.getMctsDataReaderDetail().getMCTSID_no(),
							mctsOutboundCall.getProviderServiceMapID());

				}
				
				mctsDataHandlerRepository.save(mctsOutboundCall.getMctsDataReaderDetail());

			}
		}
		
		mctsIdentityServiceImpl.updateBeneficiaries(request, servletRequest);

		if (changeLog != null) {

			mctsOutboundCallDetailRepository.appendChangeLog(changeLog, mctsOutboundCall.getCallDetailID());
		}

		return mctsOutboundCall.toString();
	}

	private Map<String, String> getFieldsMap(Long providerServiceMapID, String fieldFor) throws IEMRException {

		MctsStatewiseFieldsDetail fieldsDetail = mctsStatewiseFieldsRepository.getAllFields(providerServiceMapID,
				fieldFor);
		Map<String, String> map = new HashMap<String, String>();
		DBExcelFieldName[] excelFieldNames = InputMapper.gson().fromJson(fieldsDetail.getDataFields(),
				DBExcelFieldName[].class);

		for (DBExcelFieldName fieldName : excelFieldNames) {

			map.put(fieldName.getDbColumnName(), fieldName.getExcelColumnName());
		}

		return map;
	}

	/**
	 * method for edd calc
	 */
	private static Date getEDD(Date lmpDate) {

		LocalDate ld = lmpDate.toLocalDate();
		ld = ld.plusMonths(9);
		ld = ld.plusDays(7);
		return Date.valueOf(ld);
	}

	@Override
	public String associateBeneficiaryID(String request) throws IEMRException {

		MctsOutboundCall mctsOutboundCall = inputMapper.gson().fromJson(request, MctsOutboundCall.class);

		if (mctsOutboundCall.getOutboundCallType().indexOf("PNC") != -1) {

			childValidDataRepository.addBeneficiaryRegID(
					mctsOutboundCall.getChildValidDataHandler().getBeneficiaryRegID(),
					mctsOutboundCall.getChildValidDataHandler().getMCTSID_no_Child_ID());
			mctsOutboundCallRepository.addBeneficiaryRegIDChild(mctsOutboundCall.getChildValidDataHandler().getMCTSID_no_Child_ID(),
					mctsOutboundCall.getChildValidDataHandler().getBeneficiaryRegID());
		} else {

			mctsDataHandlerRepository.addBeneficiaryRegID(
					mctsOutboundCall.getMctsDataReaderDetail().getBeneficiaryRegID(),
					mctsOutboundCall.getMctsDataReaderDetail().getMCTSID_no());
			mctsOutboundCallRepository.addBeneficiaryRegIDMother(mctsOutboundCall.getMctsDataReaderDetail().getMCTSID_no(),
					mctsOutboundCall.getMctsDataReaderDetail().getBeneficiaryRegID());
		}

		return mctsOutboundCall.toString();
	}
}
