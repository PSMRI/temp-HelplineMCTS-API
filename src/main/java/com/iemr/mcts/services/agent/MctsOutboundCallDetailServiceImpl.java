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

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.mcts.data.agent.CallClosureDetail;
import com.iemr.mcts.data.agent.ChangeLogUtil;
import com.iemr.mcts.data.agent.ChildCongenitalAnomaliesDetail;
import com.iemr.mcts.data.agent.MctsCallResponseDetail;
import com.iemr.mcts.data.agent.MctsOutboundCallDetail;
import com.iemr.mcts.data.supervisor.CallConfigurationDetail;
import com.iemr.mcts.data.supervisor.ChildValidDataHandler;
import com.iemr.mcts.data.supervisor.DBExcelFieldName;
import com.iemr.mcts.data.supervisor.MctsDataReaderDetail;
import com.iemr.mcts.data.supervisor.MctsOutboundCall;
import com.iemr.mcts.data.supervisor.MctsStatewiseFieldsDetail;
import com.iemr.mcts.data.utils.CompareObjectsUtils;
import com.iemr.mcts.data.utils.OutboundCallDatesUpdater;
import com.iemr.mcts.repository.agent.ChildCongenitalAnomaliesRepository;
import com.iemr.mcts.repository.agent.MCTSOutboundCallRepository;
import com.iemr.mcts.repository.agent.MctsCallResponseRepository;
import com.iemr.mcts.repository.agent.MctsOutboundCallDetailRepository;
import com.iemr.mcts.repository.supervisor.CallConfigurationRepository;
import com.iemr.mcts.repository.supervisor.ChildValidDataRepository;
import com.iemr.mcts.repository.supervisor.MctsDataHandlerRepository;
import com.iemr.mcts.repository.supervisor.MctsStatewiseFieldsRepository;
import com.iemr.mcts.utils.exception.IEMRException;
import com.iemr.mcts.utils.mapper.InputMapper;
import com.iemr.mcts.utils.mapper.OutputMapper;

@Service
public class MctsOutboundCallDetailServiceImpl implements MctsOutboundCallDetailService {

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
	 * repository for call history
	 */
	private MctsOutboundCallDetailRepository mctsOutboundCallDetailRepository;

	/**
	 * Inject HttpRestModal
	 */
	@Autowired
	private MctsIdentityServiceImpl mctsIdentityServiceImpl;

	/**
	 * Inject repository for call history
	 */
	@Autowired
	public void setMctsOutboundCallDeatilRepository(MctsOutboundCallDetailRepository mctsOutboundCallDetailRepository) {

		this.mctsOutboundCallDetailRepository = mctsOutboundCallDetailRepository;
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
	 * mcts call response repository
	 */
	private MctsCallResponseRepository mctsCallResponseRepository;

	/**
	 * Outbond call service
	 */
	private MctsOutbondCallService mctsOutbondCallService;

	/**
	 * Inject outbond call service
	 */
	@Autowired
	public void setMctsOutbondCallService(MctsOutbondCallService mctsOutbondCallService) {

		this.mctsOutbondCallService = mctsOutbondCallService;
	}

	/**
	 * Inject mcts call response reposijtory
	 */
	@Autowired
	public void setMctsCallResponseRepository(MctsCallResponseRepository mctsCallResponseRepository) {

		this.mctsCallResponseRepository = mctsCallResponseRepository;
	}

	/**
	 * Call configuration Repository declare
	 */
	private CallConfigurationRepository callConfigurationRepository;

	/**
	 * inject Call configuration Repository
	 * 
	 * @param callConfigurationRepository
	 */
	@Autowired
	public void setCallConfigurationRepository(CallConfigurationRepository callConfigurationRepository) {

		this.callConfigurationRepository = callConfigurationRepository;
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
	 * Child Congenital Anomalies Service
	 */
	private ChildCongenitalAnomaliesRepository childCongenitalAnomaliesRepository;

	/**
	 * Injecct Child Congenital Anomalies Service
	 */
	@Autowired
	public void setChildCongenitalAnomaliesRepository(
			ChildCongenitalAnomaliesRepository childCongenitalAnomaliesRepository) {

		this.childCongenitalAnomaliesRepository = childCongenitalAnomaliesRepository;
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
	 * Inject OutboundCallDatesUpdater
	 */
	@Autowired
	private OutboundCallDatesUpdater outboundCallDatesUpdater;

	/**
	 * Input mapper
	 */
	private static InputMapper inputMapper = new InputMapper();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iemr.mcts.services.agent.MctsOutboundCallDeatilService#
	 * saveCallHistory(java.lang.String)
	 */
	@Override
	public String saveCallClosure(String request) throws IEMRException, Exception {

		CallClosureDetail callClosureDetail = inputMapper.gson().fromJson(request, CallClosureDetail.class);

		MctsOutboundCall mctsOutboundCall = callClosureDetail.getMctsOutboundCall();
		MctsOutboundCallDetail detail = new MctsOutboundCallDetail();

		// if call answered is true
		if (callClosureDetail.getIsAnswered()) {

			// detail.setCallStatus("Answered"); call types need to be read from master
			mctsOutboundCall.setCallStatus("Completed");

			// to update the values added in questioner
			if (mctsOutboundCall.getChildValidDataHandler() != null) {

				ChildValidDataHandler old = childValidDataRepository
						.findOne(mctsOutboundCall.getChildValidDataHandler().getRowID());

				if (!old.toString().equals(mctsOutboundCall.getChildValidDataHandler().toString())) {

					if (childFields == null)
						childFields = this.getFieldsMap(callClosureDetail.getProviderServiceMapID(), "Child Data");

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
						.findOne(mctsOutboundCall.getMctsDataReaderDetail().getMotherValidRecordID());
				if (!old.toString().equals(mctsOutboundCall.getMctsDataReaderDetail().toString())) {

					if (motherFields == null) {
						motherFields = this.getFieldsMap(callClosureDetail.getProviderServiceMapID(), "Mother Data");
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

			// if call is transfered
			if (callClosureDetail.getIsTransfered() != null) {
				if (callClosureDetail.getIsTransfered()) {

					mctsOutboundCall.setCallStatus("In Progress");
				}
			}

			if (callClosureDetail.getIsTransferedTo104() != null) {
				if (callClosureDetail.getIsTransferedTo104()) {

					mctsOutboundCall.setCallStatus("In Progress");
				}
			}

			// if additional call require
			if (callClosureDetail.getAdditionalCallRequired()) {

				mctsOutboundCall.setCallStatus("In Progress");
				mctsOutboundCallRepository.updatePrefferedCallDate(mctsOutboundCall.getObCallID(),
						callClosureDetail.getPrefferedCallDate());
			}

			// no further call require
			if (callClosureDetail.getNoFurtherCallRequired()) {
				// closing other available calls if no further call required is
				// marked
				mctsOutboundCall.setCallStatus("Completed");
				if (mctsOutboundCall.getChildID() != null) {
					mctsOutboundCallRepository.noFurtherCallRequired(mctsOutboundCall.getObCallID(), null,
							mctsOutboundCall.getChildID());
				} else {

					mctsOutboundCallRepository.noFurtherCallRequired(mctsOutboundCall.getObCallID(),
							mctsOutboundCall.getMotherID(), null);
				}
			}

			// if agent to be marked as sticky agent
			if (callClosureDetail.getStickyAgentRequired()) {

				// associating sticky agent with the beneficiary

				if (mctsOutboundCall.getChildID() != null) {

					mctsOutboundCallRepository.stickyChildAgentAllocation(mctsOutboundCall.getObCallID(),
							mctsOutboundCall.getChildID(), callClosureDetail.getUserID());
				} else {

					mctsOutboundCallRepository.stickyMotherAgentAllocation(mctsOutboundCall.getObCallID(),
							mctsOutboundCall.getMotherID(), callClosureDetail.getUserID());
				}
			}

			// to update the status of current call
			mctsOutboundCallRepository.updateCallClosureDetails(mctsOutboundCall.getObCallID(),
					mctsOutboundCall.getNoOfTrials(), mctsOutboundCall.getCallStatus());

			// updating status wheather call is verified or not
			mctsOutboundCallDetailRepository.updateIsVerified(callClosureDetail.getIsVerified(),
					callClosureDetail.getCallDetailID());

		} else { // if call not answered

			// code for allocating call to next time with the configured date
			List<CallConfigurationDetail> callConfigurationDetails = callConfigurationRepository
					.getCallConfiguration(callClosureDetail.getProviderServiceMapID());
			CallConfigurationDetail callConfigurationDetail = callConfigurationDetails.get(0);

			if (mctsOutboundCall.getNoOfTrials() >= callConfigurationDetail.getNoOfAttempts()) {

				mctsOutboundCall.setCallStatus("Completed");
			} else {

				mctsOutboundCallRepository.updatePrefferedCallDate(mctsOutboundCall.getObCallID(), Date.valueOf(
						LocalDate.now().plusDays(Integer.parseInt(callConfigurationDetail.getNextAttemptPeriod()))));
				callClosureDetail.setRemarks("Call has been queued for next time. " + callClosureDetail.getRemarks());
			}

			mctsOutboundCallRepository.updateCallClosureDetails(mctsOutboundCall.getObCallID(),
					mctsOutboundCall.getNoOfTrials() + 1, mctsOutboundCall.getCallStatus());
		}

		Timestamp callEndTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
		Timestamp callStartTime = mctsOutboundCallDetailRepository
				.getCallStartTime(callClosureDetail.getCallDetailID());

		String callDuration = this.calculateCallDuration(callStartTime, callEndTime);

		mctsOutboundCallDetailRepository.updateCallHistory(callClosureDetail.getCallTypeID(),
				Timestamp.valueOf(LocalDateTime.now()), callClosureDetail.getRemarks(),
				callClosureDetail.getCallDetailID(), mctsOutboundCall.getBeneficiaryRegID(), callEndTime, callDuration, callClosureDetail.getIsOutbound() );

		if (changeLog != null) {

			mctsOutboundCallDetailRepository.appendChangeLog(changeLog, mctsOutboundCall.getCallDetailID());
		}

		if (callClosureDetail.getChildCongenitalAnomaliesDetails() != null) {

			for (ChildCongenitalAnomaliesDetail childCongenitalAnomaliesDetail : callClosureDetail
					.getChildCongenitalAnomaliesDetails()) {

				if (mctsOutboundCall.getChildValidDataHandler() != null) {

					childCongenitalAnomaliesDetail
							.setBeneficiaryRegID(mctsOutboundCall.getChildValidDataHandler().getBeneficiaryRegID());
					childCongenitalAnomaliesDetail.setChildID(mctsOutboundCall.getChildID());
				} else {

					childCongenitalAnomaliesDetail
							.setBeneficiaryRegID(mctsOutboundCall.getMctsDataReaderDetail().getBeneficiaryRegID());
					childCongenitalAnomaliesDetail.setMotherID(mctsOutboundCall.getMotherID());
				}

				childCongenitalAnomaliesDetail.setProviderServiceMapID(mctsOutboundCall.getProviderServiceMapID());
				childCongenitalAnomaliesDetail.setCallDetailID(callClosureDetail.getCallDetailID());
				childCongenitalAnomaliesDetail.setCreatedBy(callClosureDetail.getCreatedBy());
				childCongenitalAnomaliesRepository.save(childCongenitalAnomaliesDetail);
			}
		}

		return "Call closed successfully";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iemr.mcts.services.agent.MctsOutboundCallDeatilService#getCallHistory
	 * (java.lang.String)
	 */
	@Override
	public String getCallHistory(String request) throws IEMRException {

		List<MctsOutboundCallDetail> mctsOutboundCallDetails = new ArrayList<MctsOutboundCallDetail>();
		MctsOutboundCall callDetail = inputMapper.gson().fromJson(request, MctsOutboundCall.class);

		if (callDetail.getChildID() != null) {

			mctsOutboundCallDetails = mctsOutboundCallDetailRepository.getChildCallHistory(callDetail.getChildID());
		} else {

			mctsOutboundCallDetails = mctsOutboundCallDetailRepository.getMotherCallHistory(callDetail.getMotherID());
		}

		return mctsOutboundCallDetails.toString();
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

	@Override
	public String getBeneficiaryDetails(String request, HttpServletRequest servletRequest) throws IEMRException {

		MctsOutboundCallDetail mctsOutboundCallDetail = InputMapper.gson().fromJson(request,
				MctsOutboundCallDetail.class);
		MctsOutboundCall benCall;
		MctsOutboundCall mctsOutboundCall = mctsOutboundCallRepository
				.findOnClientID(mctsOutboundCallDetail.getCallId());
		if (mctsOutboundCall != null) {

			if (mctsOutboundCall.getChildValidDataHandler() != null) {

				if (mctsOutboundCall.getChildValidDataHandler().getBeneficiaryRegID() != null) {

					String req = mctsIdentityServiceImpl.getBeneficiaryID(OutputMapper.gson().toJson(mctsOutboundCall),
							servletRequest);
					benCall = InputMapper.gson().fromJson(req, MctsOutboundCall.class);
					mctsOutboundCall.getChildValidDataHandler()
							.setBeneficiaryID(benCall.getChildValidDataHandler().getBeneficiaryID());
				}

			} else {

				if (mctsOutboundCall.getMctsDataReaderDetail().getBeneficiaryRegID() != null) {

					String req = mctsIdentityServiceImpl.getBeneficiaryID(OutputMapper.gson().toJson(mctsOutboundCall),
							servletRequest);
					benCall = InputMapper.gson().fromJson(req, MctsOutboundCall.class);
					mctsOutboundCall.getMctsDataReaderDetail()
							.setBeneficiaryID(benCall.getMctsDataReaderDetail().getBeneficiaryID());
				}
			}

			return mctsOutboundCall.toString();
		}

		else
			return "No beneficiary found with this caller id";
	}

	@Override
	public String createCallHistory(String request) throws IEMRException {

		MctsOutboundCallDetail mctsOutboundCallDetail = InputMapper.gson().fromJson(request,
				MctsOutboundCallDetail.class);

		mctsOutboundCallDetail.setCallTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));

		// Setting default call type Id
		if (mctsOutboundCallDetail.getCallTypeID() == null) {
			mctsOutboundCallDetail.setCallTypeID(mctsOutboundCallDetailRepository.getCallTypeId());
		}

		MctsOutboundCallDetail call = mctsOutboundCallDetailRepository.isAvailable(mctsOutboundCallDetail.getCallId(),
				mctsOutboundCallDetail.getAllocatedUserID());
		if (call == null) {
			call = mctsOutboundCallDetailRepository.save(mctsOutboundCallDetail);
		}
		return call.toString();
	}

	@Override
	public String sendSmsAdvice(String request) throws IEMRException {

		MctsOutboundCallDetail mctsOutboundCallDetail = InputMapper.gson().fromJson(request,
				MctsOutboundCallDetail.class);

		MctsOutboundCallDetail detail = mctsOutboundCallDetailRepository
				.checkAdviseAvailable(mctsOutboundCallDetail.getCallDetailID());

		if (detail.getSmsAdvice() != null) {

			mctsOutboundCallDetailRepository.appendSmsAdvise(mctsOutboundCallDetail.getSmsAdvice(),
					mctsOutboundCallDetail.getCallDetailID());

		} else {

			mctsOutboundCallDetailRepository.updateSmsAdvice(mctsOutboundCallDetail.getSmsAdvice(),
					mctsOutboundCallDetail.getSmsPh(), mctsOutboundCallDetail.getCallDetailID());

		}

		return "SMS sent successfully";
	}

	@Override
	public String getSmsAdvice(String request) throws IEMRException {

		return null;
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
	public String getChangeLog(String request) throws IEMRException {

		String changlog = this.getChangeLogs(request);
		List<MctsOutboundCallDetail> callDetails = Arrays
				.asList(OutputMapper.gson().fromJson(changlog, MctsOutboundCallDetail[].class));
		ListIterator<MctsOutboundCallDetail> iterator = callDetails.listIterator();
		List<ChangeLogUtil> changeLogUtils = new ArrayList<ChangeLogUtil>();
		MctsOutboundCallDetail callDetail;

		ChangeLogUtil changeLogUtil = null;
		while (iterator.hasNext()) {

			callDetail = iterator.next();
			if (callDetail.getChangeLog() != null && !callDetail.getChangeLog().isEmpty()) {
				for (String change : callDetail.getChangeLog().split("\\|")) {

					if (change.trim().length() > 3 && change.indexOf("beneficiary") == -1) {
						changeLogUtil = new ChangeLogUtil();
						changeLogUtil.setChangeLog(change.trim());
						changeLogUtil.setModifiedDate(callDetail.getCreatedDate());
						changeLogUtil.setMofidifedBy(callDetail.getCreatedBy());
						changeLogUtils.add(changeLogUtil);
					}
				}
			}
		}

		return changeLogUtils.toString();
	}

	@Override
	public String caseSheet(String request, HttpServletRequest servletRequest) throws IEMRException {

		Map<String, Object> results = new HashMap<String, Object>();
		MctsOutboundCallDetail mctsOutboundCallDetail = InputMapper.gson().fromJson(request,
				MctsOutboundCallDetail.class);
		List<ChildCongenitalAnomaliesDetail> childCongenitalAnomaliesDetails;
		mctsOutboundCallDetail = mctsOutboundCallDetailRepository
				.findByCallDetailID(mctsOutboundCallDetail.getCallDetailID());
		MctsOutboundCall mctsOutboundCall = mctsOutboundCallRepository
				.findOnClientID(mctsOutboundCallDetail.getCallId());

		String updObj = mctsOutbondCallService.getUpdatedObject(OutputMapper.gson().toJson(mctsOutboundCall),
				servletRequest);
		mctsOutboundCall = InputMapper.gson().fromJson(updObj, MctsOutboundCall.class);
		ArrayList<MctsCallResponseDetail> callResponseDetails = mctsCallResponseRepository
				.getMctsCallResponse(mctsOutboundCallDetail.getCallDetailID());
		results.put("mctsOutboundCallDetail", mctsOutboundCallDetail);
		results.put("mctsOutboundCall", mctsOutboundCall);
		results.put("callResponseDetails", callResponseDetails);
		if (mctsOutboundCall.getOutboundCallType().indexOf("PNC") != -1) {

			if (mctsOutboundCall.getChildID() != null) {

				childCongenitalAnomaliesDetails = childCongenitalAnomaliesRepository
						.findByChildID(mctsOutboundCall.getChildID());
			} else {

				childCongenitalAnomaliesDetails = childCongenitalAnomaliesRepository
						.findByMotherID(mctsOutboundCall.getMotherID());
			}

			results.put("childCongenitalAnomaliesDetails", childCongenitalAnomaliesDetails);
		}

		return OutputMapper.gson().toJson(results);
	}

	@Override
	public String getChangeLogs(String request) throws IEMRException {

		List<MctsOutboundCallDetail> mctsOutboundCallDetails = new ArrayList<MctsOutboundCallDetail>();
		MctsOutboundCall callDetail = inputMapper.gson().fromJson(request, MctsOutboundCall.class);

		if (callDetail.getChildID() != null) {

			mctsOutboundCallDetails = mctsOutboundCallDetailRepository.getChildChangeLogs(callDetail.getChildID());
		} else {

			mctsOutboundCallDetails = mctsOutboundCallDetailRepository.getMotherChangeLogs(callDetail.getMotherID());
		}

		return mctsOutboundCallDetails.toString();
	}

	private String calculateCallDuration(Timestamp CallStartTime, Timestamp CallEndTime) {

		long diff = CallEndTime.getTime() - CallStartTime.getTime();
		long durationInSeconds = diff / 1000;

		long SECONDS_IN_A_MINUTE = 60;
		long MINUTES_IN_AN_HOUR = 60;
		long HOURS_IN_A_DAY = 24;

		long sec = (durationInSeconds >= SECONDS_IN_A_MINUTE) ? durationInSeconds % SECONDS_IN_A_MINUTE
				: durationInSeconds;
		long min = (durationInSeconds /= SECONDS_IN_A_MINUTE) >= MINUTES_IN_AN_HOUR
				? durationInSeconds % MINUTES_IN_AN_HOUR
				: durationInSeconds;
		long hrs = (durationInSeconds /= MINUTES_IN_AN_HOUR) >= HOURS_IN_A_DAY ? durationInSeconds % HOURS_IN_A_DAY
				: durationInSeconds;

		StringBuffer sb = new StringBuffer();
		String EMPTY_STRING = "";
		sb.append(hrs > 0 ? hrs + (hrs > 1 ? " hours " : " hour ") : EMPTY_STRING);
		sb.append(min > 0 ? min + (min > 1 ? " mins " : " min ") : EMPTY_STRING);
		sb.append(sec > 0 ? sec + (sec > 1 ? " secs " : " sec ") : EMPTY_STRING);

		String callDuration = sb.toString();

		return callDuration;

	}

}
