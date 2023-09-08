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
package com.iemr.mcts.services.report;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.iemr.mcts.data.report.BenCallDetail;
import com.iemr.mcts.data.report.CallAnsweredDetail;
import com.iemr.mcts.data.report.CallResponseReportDetail;
import com.iemr.mcts.data.supervisor.MctsQAMappingDetail;
import com.iemr.mcts.mapper.report.MctsReportmapper;
import com.iemr.mcts.repository.report.CallAnsweredReportRepository;
import com.iemr.mcts.repository.supervisor.MctsQAMappingRepository;
import com.iemr.mcts.utils.mapper.InputMapper;

@Service
public class CallAnsweredReportServiceImpl implements CallAnsweredReportService {

	Logger logger = LoggerFactory.getLogger(CallAnsweredReportServiceImpl.class);

	private InputMapper inputMapper = new InputMapper();

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
	 * CallAnsweredCountRepository repo
	 */
	private CallAnsweredReportRepository callAnsweredReportRepository;

	/**
	 * CallAnsweredCountRepository repo
	 */

	@Autowired
	public void setCallAnsweredCountRepository(CallAnsweredReportRepository callAnsweredCountRepository) {
		this.callAnsweredReportRepository = callAnsweredCountRepository;
	}

	@Autowired
	MctsReportmapper mapper;

	@Override
	public String getCallAnsweredReport(String request) throws Exception {

		logger.info("CallAnsweredCountService.getCallAnsweredCount - start");

		BenCallDetail benCallDetail = InputMapper.gson().fromJson(request, BenCallDetail.class);

		List<BenCallDetail> benMotherCallList = new ArrayList<BenCallDetail>();
		List<BenCallDetail> benChildCallList = new ArrayList<BenCallDetail>();
		List<Map<String, String>> reportMap = new ArrayList<Map<String, String>>();
		Map<String, String> ReportResponse = new LinkedHashMap<String, String>();
		ReportResponse.put("motherID", "");
		ReportResponse.put("motherName", "");
		ReportResponse.put("childID", "");
		ReportResponse.put("childName", "");
		ReportResponse.put("phoneNo", "");
		ReportResponse.put("anmName", "");
		ReportResponse.put("ashaName", "");
		ReportResponse.put("phcName", "");
		ReportResponse.put("address", "");
		ReportResponse.put("districtName", "");
		ReportResponse.put("blockName", "");
		ReportResponse.put("subCenterName", "");
		ReportResponse.put("outboundCallType", "");
		ReportResponse.put("displayCallType", "");
		ReportResponse.put("callDateAndTime", "");
		ReportResponse.put("remark", "");


		Integer callTypeID = callAnsweredReportRepository
				.getCallTypeID(benCallDetail.getProviderServiceMapID().intValue());

		if (benCallDetail.getCallTypeName().equalsIgnoreCase("Answered")) {

			// fetching the questions for report
			List<MctsQAMappingDetail> mctsQAMappingDetails = mctsQAMappingRepository.getQuestionsForReport(
					benCallDetail.getOutboundCallType(), benCallDetail.getProviderServiceMapID(),
					benCallDetail.getEffectiveFrom());

			for (MctsQAMappingDetail reportQuestions : mctsQAMappingDetails) {
				ReportResponse.put(reportQuestions.getQuestionnaireDetail().getQuestion(), "");
			}

			if(benCallDetail.getVerifiedData() == true) {
				if (benCallDetail.getUserID() != null && benCallDetail.getUserID() > 0) {
					benMotherCallList = callAnsweredReportRepository.getCallAnsweredMotherByAgentIDNew(
							benCallDetail.getProviderServiceMapID(), benCallDetail.getStartDate(),
							benCallDetail.getEndDate(), callTypeID, benCallDetail.getUserID(),
							benCallDetail.getOutboundCallType(), benCallDetail.getVerifiedData());
					benChildCallList = callAnsweredReportRepository.getCallAnsweredChildByAgentIDNew(
							benCallDetail.getProviderServiceMapID(), benCallDetail.getStartDate(),
							benCallDetail.getEndDate(), callTypeID, benCallDetail.getUserID(),
							benCallDetail.getOutboundCallType(), benCallDetail.getVerifiedData());
				} else {

					benMotherCallList = callAnsweredReportRepository.getCallAnsweredMotherNew(
							benCallDetail.getProviderServiceMapID(), benCallDetail.getStartDate(),
							benCallDetail.getEndDate(), callTypeID, benCallDetail.getOutboundCallType(),
							benCallDetail.getVerifiedData());

					benChildCallList = callAnsweredReportRepository.getCallAnsweredChildNew(
							benCallDetail.getProviderServiceMapID(), benCallDetail.getStartDate(),
							benCallDetail.getEndDate(), callTypeID, benCallDetail.getOutboundCallType(),
							benCallDetail.getVerifiedData());
				}
			}else {
				if (benCallDetail.getUserID() != null && benCallDetail.getUserID() > 0) {
					benMotherCallList = callAnsweredReportRepository.getCallAnsweredMotherByAgentIDNewAllData(
							benCallDetail.getProviderServiceMapID(), benCallDetail.getStartDate(),
							benCallDetail.getEndDate(), callTypeID, benCallDetail.getUserID(),
							benCallDetail.getOutboundCallType());
					benChildCallList = callAnsweredReportRepository.getCallAnsweredChildByAgentIDNewAllData(
							benCallDetail.getProviderServiceMapID(), benCallDetail.getStartDate(),
							benCallDetail.getEndDate(), callTypeID, benCallDetail.getUserID(),
							benCallDetail.getOutboundCallType());
				} else {

					benMotherCallList = callAnsweredReportRepository.getCallAnsweredMotherNewAllData(
							benCallDetail.getProviderServiceMapID(), benCallDetail.getStartDate(),
							benCallDetail.getEndDate(), callTypeID, benCallDetail.getOutboundCallType());

					benChildCallList = callAnsweredReportRepository.getCallAnsweredChildNewAllData(
							benCallDetail.getProviderServiceMapID(), benCallDetail.getStartDate(),
							benCallDetail.getEndDate(), callTypeID, benCallDetail.getOutboundCallType());
				}
			}
			
		}
		for (BenCallDetail reportDetails : benMotherCallList) {
			Map<String, String> reportResponse = insertInMapMother(reportDetails, ReportResponse);
			reportMap.add(reportResponse);

		}
		for (BenCallDetail reportDetails : benChildCallList) {
			Map<String, String> reportResponse = insertInMapChild(reportDetails, ReportResponse);
			reportMap.add(reportResponse);

		}

		logger.info("CallAnsweredCountService.getCallAnsweredCount - end");

		return new Gson().toJson(reportMap);

	}

	public Map<String, String> insertInMapChild(BenCallDetail reportDetails, Map<String, String> ReportResponse) {
		Map<String, String> reportResponseNew = new LinkedHashMap<String, String>(ReportResponse);
		for (Map.Entry<String, String> entry : reportResponseNew.entrySet()) {

			if (entry.getKey().equalsIgnoreCase("motherID")) {
				reportResponseNew.put("motherID",
						reportDetails.getObdetail().getChildDetail().getMotherID() == null ? ""
								: String.valueOf(reportDetails.getObdetail().getChildDetail().getMotherID()));
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("motherName")) {
				reportResponseNew.put("motherName",
						reportDetails.getObdetail().getChildDetail().getMotherName() == null ? ""
								: String.valueOf(reportDetails.getObdetail().getChildDetail().getMotherName()));
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("childID")) {
				reportResponseNew.put("childID",
						reportDetails.getObdetail().getChildDetail().getMCTSIDnoChild_ID() == null ? ""
								: String.valueOf(reportDetails.getObdetail().getChildDetail().getMCTSIDnoChild_ID()));
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("childName")) {
				reportResponseNew.put("childName",
						reportDetails.getObdetail().getChildDetail().getChildName() == null ? ""
								: String.valueOf(reportDetails.getObdetail().getChildDetail().getChildName()));
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("phoneNo")) {
				reportResponseNew.put("phoneNo",
						reportDetails.getPhoneNumber() == null ? "" : String.valueOf(reportDetails.getPhoneNumber()));
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("remark")) {
				reportResponseNew.put("remark", reportDetails.getRemark() == null ? "" : reportDetails.getRemark());
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("anmName")) {
				reportResponseNew.put("anmName", reportDetails.getObdetail().getChildDetail().getAnmName() == null ? ""
						: reportDetails.getObdetail().getChildDetail().getAnmName());
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("ashaName")) {
				reportResponseNew.put("ashaName",
						reportDetails.getObdetail().getChildDetail().getAshaName() == null ? ""
								: reportDetails.getObdetail().getChildDetail().getAshaName());
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("phcName")) {
				reportResponseNew.put("phcName", reportDetails.getObdetail().getChildDetail().getPhcName() == null ? ""
						: reportDetails.getObdetail().getChildDetail().getPhcName());
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("outboundCallType")) {
				reportResponseNew.put("outboundCallType", reportDetails.getObdetail().getOutboundCallType() == null ? ""
						: reportDetails.getObdetail().getOutboundCallType());
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("displayCallType")) {
				reportResponseNew.put("displayCallType", reportDetails.getObdetail().getDisplayOBCallType() == null ? ""
						: reportDetails.getObdetail().getDisplayOBCallType());
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("callDateAndTime")) {
				reportResponseNew.put("callDateAndTime",
						reportDetails.getDate() == null ? "" : String.valueOf(reportDetails.getDate()));
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("address")) {
				reportResponseNew.put("address", reportDetails.getObdetail().getChildDetail().getAddress() == null ? ""
						: String.valueOf(reportDetails.getObdetail().getChildDetail().getAddress()));
				continue;
			}			
			if (entry.getKey().equalsIgnoreCase("districtName")) {
				reportResponseNew.put("districtName", reportDetails.getObdetail().getChildDetail().getDistrictName() == null ? ""
						: String.valueOf(reportDetails.getObdetail().getChildDetail().getDistrictName()));
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("blockName")) {
				reportResponseNew.put("blockName", reportDetails.getObdetail().getChildDetail().getBlockName() == null ? ""
						: String.valueOf(reportDetails.getObdetail().getChildDetail().getBlockName()));
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("subCenterName")) {
				reportResponseNew.put("subCenterName", reportDetails.getObdetail().getChildDetail().getSubCenterName() == null ? ""
						: String.valueOf(reportDetails.getObdetail().getChildDetail().getSubCenterName()));
				continue;
			}
			
			List<CallResponseReportDetail> callResponse = reportDetails.getCallResponseReportDetail();

			for (CallResponseReportDetail reportCallResponse : callResponse) {
				if (entry.getKey().equalsIgnoreCase(reportCallResponse.getQuestion())) {
					reportResponseNew.put(reportCallResponse.getQuestion(), reportCallResponse.getAnswer());
					break;
				}
					
			}

		}
		
		return reportResponseNew;

	}

	public Map<String, String> insertInMapMother(BenCallDetail reportDetails, Map<String, String> ReportResponse) {
		Map<String, String> reportResponseNew = new LinkedHashMap<String, String>(ReportResponse);
		for (Map.Entry<String, String> entry : reportResponseNew.entrySet()) {

			if (entry.getKey().equalsIgnoreCase("motherID")) {
				reportResponseNew.put("motherID", reportDetails.getObdetail().getMotherID() == null ? ""
						: String.valueOf(reportDetails.getObdetail().getMotherDetail().getMctsIDNo()));
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("motherName")) {
				reportResponseNew.put("motherName", reportDetails.getObdetail().getMotherDetail().getName() == null ? ""
						: String.valueOf(reportDetails.getObdetail().getMotherDetail().getName()));
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("phoneNo")) {
				reportResponseNew.put("phoneNo",
						reportDetails.getObdetail().getMotherDetail().getPhoneNoOfWhom() == null ? ""
								: String.valueOf(reportDetails.getObdetail().getMotherDetail().getPhoneNoOfWhom()));
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("remark")) {
				reportResponseNew.put("remark", reportDetails.getRemark() == null ? "" : reportDetails.getRemark());
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("anmName")) {
				reportResponseNew.put("anmName", reportDetails.getObdetail().getMotherDetail().getAnmName() == null ? ""
						: reportDetails.getObdetail().getMotherDetail().getAnmName());
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("ashaName")) {
				reportResponseNew.put("ashaName",
						reportDetails.getObdetail().getMotherDetail().getAshaName() == null ? ""
								: reportDetails.getObdetail().getMotherDetail().getAshaName());
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("phcName")) {
				reportResponseNew.put("phcName", reportDetails.getObdetail().getMotherDetail().getPHCName() == null ? ""
						: reportDetails.getObdetail().getMotherDetail().getPHCName());
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("outboundCallType")) {
				reportResponseNew.put("outboundCallType", reportDetails.getObdetail().getOutboundCallType() == null ? ""
						: reportDetails.getObdetail().getOutboundCallType());
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("displayCallType")) {
				reportResponseNew.put("displayCallType", reportDetails.getObdetail().getDisplayOBCallType() == null ? ""
						: reportDetails.getObdetail().getDisplayOBCallType());
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("callDateAndTime")) {
				reportResponseNew.put("callDateAndTime",
						reportDetails.getDate() == null ? "" : String.valueOf(reportDetails.getDate()));
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("address")) {
				reportResponseNew.put("address", reportDetails.getObdetail().getMotherDetail().getAddress() == null ? ""
						: String.valueOf(reportDetails.getObdetail().getMotherDetail().getAddress()));
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("districtName")) {
				reportResponseNew.put("districtName", reportDetails.getObdetail().getMotherDetail().getDistrictName() == null ? ""
						: String.valueOf(reportDetails.getObdetail().getMotherDetail().getDistrictName()));
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("blockName")) {
				reportResponseNew.put("blockName", reportDetails.getObdetail().getMotherDetail().getBlockName() == null ? ""
						: String.valueOf(reportDetails.getObdetail().getMotherDetail().getBlockName()));
				continue;
			}
			if (entry.getKey().equalsIgnoreCase("subCenterName")) {
				reportResponseNew.put("subCenterName", reportDetails.getObdetail().getMotherDetail().getSubCenterName() == null ? ""
						: String.valueOf(reportDetails.getObdetail().getMotherDetail().getSubCenterName()));
				continue;
			}
			
			List<CallResponseReportDetail> callResponse = reportDetails.getCallResponseReportDetail();

			for (CallResponseReportDetail reportCallResponse : callResponse) {
				if (entry.getKey().equalsIgnoreCase(reportCallResponse.getQuestion())) {
					reportResponseNew.put(reportCallResponse.getQuestion(), reportCallResponse.getAnswer());
					break;
				}									
			}

		}
		return reportResponseNew;

	}

	public String getCallNotAnsweredReport(String request) throws Exception {

		BenCallDetail benCallDetail = InputMapper.gson().fromJson(request, BenCallDetail.class);
		List<CallAnsweredDetail> callAnsweredDetailList = new ArrayList<CallAnsweredDetail>();
		List<BenCallDetail> benMotherCallList = new ArrayList<BenCallDetail>();
		List<BenCallDetail> benChildCallList = new ArrayList<BenCallDetail>();

		Integer callTypeID = callAnsweredReportRepository
				.getCallTypeID(benCallDetail.getProviderServiceMapID().intValue());

		if (benCallDetail.getUserID() != null && benCallDetail.getUserID() > 0) {
			benMotherCallList = callAnsweredReportRepository.getCallNotAnsweredMotherByAgentID(
					benCallDetail.getProviderServiceMapID(), benCallDetail.getStartDate(), benCallDetail.getEndDate(),
					callTypeID, benCallDetail.getUserID());
			benChildCallList = callAnsweredReportRepository.getCallNotAnsweredChildByAgentID(
					benCallDetail.getProviderServiceMapID(), benCallDetail.getStartDate(), benCallDetail.getEndDate(),
					callTypeID, benCallDetail.getUserID());
		} else {
			benMotherCallList = callAnsweredReportRepository.getCallNotAnsweredMother(
					benCallDetail.getProviderServiceMapID(), benCallDetail.getStartDate(), benCallDetail.getEndDate(),
					callTypeID);
			benChildCallList = callAnsweredReportRepository.getCallNotAnsweredChild(
					benCallDetail.getProviderServiceMapID(), benCallDetail.getStartDate(), benCallDetail.getEndDate(),
					callTypeID);
		}

		for (BenCallDetail bencall : benMotherCallList) {

			CallAnsweredDetail report = mapper.mapMotherAnsweredReport(bencall);
			callAnsweredDetailList.add(report);
		}

		for (BenCallDetail bencall : benChildCallList) {
			CallAnsweredDetail report = mapper.mapChildAnsweredCall(bencall);
			callAnsweredDetailList.add(report);
		}
		return callAnsweredDetailList.toString();

	}
}
