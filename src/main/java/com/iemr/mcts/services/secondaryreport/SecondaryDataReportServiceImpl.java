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
package com.iemr.mcts.services.secondaryreport;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.mcts.data.report.BenCallDetail;
import com.iemr.mcts.data.report.FeedbacksDetail;
import com.iemr.mcts.data.report.HighRiskCall;
import com.iemr.mcts.data.report.MotherDataReportDetail;
import com.iemr.mcts.data.supervisor.MctsQAMappingDetail;
import com.iemr.mcts.mapper.report.MctsReportmapper;
import com.iemr.mcts.model.excel.Criteria;
import com.iemr.mcts.model.excel.ExcelHelper;
import com.iemr.mcts.repository.supervisor.MctsQAMappingRepository;
import com.iemr.mcts.secondary.repository.report.MCTSSecondaryReportRepo;
import com.iemr.mcts.utils.exception.IEMRException;
import com.iemr.mcts.utils.mapper.InputMapper;

@Service
public class SecondaryDataReportServiceImpl implements SecondaryDataReportService {

	@Autowired
	MctsReportmapper mapper;

	@Autowired
	MctsQAMappingRepository mctsQAMappingRepository;

	@Autowired
	MCTSSecondaryReportRepo mctsSecondaryReportRepo;

	@Override
	public ByteArrayInputStream getDataReport(String request, String fname) throws IEMRException {
		String[] motherHeaders = { "Sno", "State ID", "District ID", "District Name", "Taluka ID", "Taluka Name",
				"Health Block ID", "Health Block Name", "PHC ID", "PHC Name", "Sub Center ID", "Sub Center Name",
				"Village ID", "Village Name", "Yr", "GP Village", "Address", "ID No", "Name", "Husband Name",
				"Phone No Of Whom", "Whom Phone No", "Birth Date", "JSY Beneficiary", "Caste", "Sub Center Name1",
				"ANM Name", "ANM Phone", "Asha Name", "Asha Phone", "Delivery Lnk Facility", "Facility Name",
				"LMP Date", "ANC1 Date", "ANC2 Date", "ANC3 Date", "ANC4 Date", "TT1 Date", "TT2 Date",
				"TT Booster Date", "Ifa100 Given Date", "Anemia", "ANC Complication", "Rti Sti", "Delivery Date",
				"Delivery Placehome Type", "Delivery Place Public", "Delivery Place Private", "Delivery Type",
				"Delivery Complications", "Discharge Date", "JSY Paid Date", "Abortion", "PNC Home Visit",
				"PNC Complication", "Ppc Method", "PNC Checkup", "Outcome Nos", "Child1 Name", "Child1 Gender",
				"Child1 Weight", "Child1 Breast Feeding", "Child2 Name", "Child2 Gender", "Child2 Weight",
				"Child2 Breast Feeding", "Child3 Name", "Child3 Gender", "Child3 Weight", "Child3 Breast Feeding",
				"Child4 Name", "Child4 Gender", "Child4 Weight", "Child4 Breast Feeding", "Age", "Mother Reg Date",
				"Last Update Date", "Remarks", "ANM ID", "Asha ID", "Call Answered", "No Call Reason",
				"No Phone Reason", "Created By", "Updated By", "Aadhar No", "Bpl Apl", "E ID", "E ID Time", "Entrytype",
				"MDDS State ID", "MDDS District ID", "MDDS Taluka ID", "MDDS Village ID" };
		String[] childHeaders = { "Sno", "State ID", "District ID", "District Name", "Taluka ID", "Taluka Name",
				"Health Block ID", "Health Block Name", "PHC ID", "PHC Name", "Sub Center ID", "Sub Center Name",
				"City Maholla", "Village ID", "Village Name", "Yr", "GP Village", "Address", "ID No", "Name",
				"Mother Name", "Mother ID", "Phone Noof Whom", "Whom Phone No", "Birth Date", "Place Of Birth",
				"Blood Group", "Caste", "Sub Center Name1", "ANM Name", "ANM Phone No", "Asha Name", "Asha Phone No",
				"Bcg Date", "Opvo Date", "Hepatitis B2 Date", "Dpt2 Date", "Opv2 Date", "Hepatitis B3 Date",
				"Dpt3 Date", "Opv3 Date", "Hepatitis B4 Date", "Measles Date", "Vit A Dose1 Date", "Mr Date",
				"Dpt Booster Date", "Opv Booster Date", "Vit A Dose2 Date", "Vit A Dose3 Date", "Je Date",
				"Vit A Dose9 Date", "Dt5 Date", "TT10 Date", "TT16 Date", "Child Registration Date", "Gender",
				"Vit A Dose5 Date", "Vit A Dose6 Date", "Vit A Dose7 Date", "Vit A Dose8 Date", "Updated Date",
				"Remarks", "ANM ID", "Asha ID", "Created By", "Updated By", "Measles2 Date", "Weight Of Child",
				"Child Aadhaar No", "Child E ID", "Child E ID Time", "Father Name", "Birth Certificate No", "Entrytype",
				"MDDS State ID", "MDDS District ID", "MDDS Taluka ID", "MDDS Village ID" };
		String[] criteriaColumns = { "Start_Date", "End_Date", "Type" };
		ByteArrayInputStream response = null;
		List<Objects[]> result = null;
		try {
			MotherDataReportDetail motherDataReportDetail = InputMapper.gson().fromJson(request,
					MotherDataReportDetail.class);
			Criteria c = new Criteria();
			c.setStart_Date(motherDataReportDetail.getStartDate().toString());
			c.setEnd_Date(motherDataReportDetail.getEndDate().toString());
			c.setType(motherDataReportDetail.getIsMother().toString());

			if (motherDataReportDetail.getIsMother()) {
				result = mctsSecondaryReportRepo.getDataReportMother(motherDataReportDetail.getStartDate(),
						motherDataReportDetail.getEndDate(), motherDataReportDetail.getProviderServiceMapID());
			} else {
				result = mctsSecondaryReportRepo.getDataReportChild(motherDataReportDetail.getStartDate(),
						motherDataReportDetail.getEndDate(), motherDataReportDetail.getProviderServiceMapID());
			}

			if (result != null && result.size() > 0) {
				if (motherDataReportDetail.getIsMother())
					response = ExcelHelper.tutorialsToExcel(motherHeaders, result, c, criteriaColumns);
				else
					response = ExcelHelper.tutorialsToExcel(childHeaders, result, c, criteriaColumns);
			} else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;

	}

	@Override
	public ByteArrayInputStream getCallDetailReport(String request, String filename) throws Exception {
		String[] headers = { "Sno", "User ID", "Campaign ID", "Call Time", "Mother District", "Child District",
				"Phone Number", "Mother ID", "Child ID", "Status", "Remarks", "Count", "Count Verified" };
		String[] criteriaColumns = { "Start_Date", "End_Date", "Agent_ID" };
		ByteArrayInputStream response = null;
		List<Objects[]> result = null;
		try {
			BenCallDetail benCallDetail = InputMapper.gson().fromJson(request, BenCallDetail.class);
			Criteria c = new Criteria();
			c.setStart_Date(benCallDetail.getStartDate().toString());
			c.setEnd_Date(benCallDetail.getEndDate().toString());
			c.setAgent_ID(benCallDetail.getUserID().toString());
			result = mctsSecondaryReportRepo.getCallDetailReport(benCallDetail.getStartDate(),
					benCallDetail.getEndDate(), benCallDetail.getUserID() > 0 ? benCallDetail.getUserID() : null,
					benCallDetail.getProviderServiceMapID());

			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result, c, criteriaColumns);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}

	@Override
	public ByteArrayInputStream getCallDetailReportUnique(String request, String filename) throws Exception {
		String[] headers = { "Sno", "User ID", "Call Time", "Mother ID", "Status", "Remarks"

		};
		String[] criteriaColumns = { "Start_Date", "End_Date", "Agent_ID" };
		ByteArrayInputStream response = null;
		List<Objects[]> result = null;
		try {
			BenCallDetail benCallDetail = InputMapper.gson().fromJson(request, BenCallDetail.class);
			Criteria c = new Criteria();
			c.setStart_Date(benCallDetail.getStartDate().toString());
			c.setEnd_Date(benCallDetail.getEndDate().toString());
			c.setAgent_ID(benCallDetail.getUserID().toString());
			result = mctsSecondaryReportRepo.getCallDetailReportUnique(benCallDetail.getStartDate(),
					benCallDetail.getEndDate(), benCallDetail.getUserID() > 0 ? benCallDetail.getUserID() : null,
					benCallDetail.getProviderServiceMapID());

			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result, c, criteriaColumns);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}

	@Override
	public ByteArrayInputStream getComplaintReport(String request, String filename) throws Exception {
		String[] headers = { "SNo", "Mother ID / Child ID", "Beneficiary Caller Name", "Beneficiary Phone Number",
				"Another Contact Number", "District Name", "Block", "Village", "Call Type", "Display Call Type",
				"Type Of Complaint", "Nature Of Complaint", "Institute Name", "Designation", "Complaint Against",
				"Date Of Complaint", "Brief of Complaint", "Details of Complaint" };
		String[] criteriaColumns = { "Start_Date", "End_Date", "Type", "Agent_ID" };
		ByteArrayInputStream response = null;
		List<Objects[]> result = null;
		try {
			FeedbacksDetail complaintReportDetails = InputMapper.gson().fromJson(request, FeedbacksDetail.class);
			Criteria c = new Criteria();
			c.setStart_Date(complaintReportDetails.getStartDate().toString());
			c.setEnd_Date(complaintReportDetails.getEndDate().toString());
			c.setType(complaintReportDetails.getIsMother() != null ? complaintReportDetails.getIsMother().toString()
					: null);
			c.setAgent_ID(
					complaintReportDetails.getUserID() != null ? complaintReportDetails.getUserID().toString() : null);

			List<Objects[]> motherComplaintResult = null;
			List<Objects[]> childComplaintResult = null;

			if (complaintReportDetails.getIsMother() != null
					&& Boolean.TRUE.equals(complaintReportDetails.getIsMother())) {
				result = mctsSecondaryReportRepo.getComplaintReportMother(complaintReportDetails.getStartDate(),
						complaintReportDetails.getEndDate(),

						((complaintReportDetails.getUserID() != null && complaintReportDetails.getUserID() > 0)
								? complaintReportDetails.getUserID()
								: null),
						complaintReportDetails.getProviderServiceMapID());
			} else if (complaintReportDetails.getIsMother() != null
					&& Boolean.FALSE.equals(complaintReportDetails.getIsMother())) {
				result = mctsSecondaryReportRepo.getComplaintReportChild(complaintReportDetails.getStartDate(),
						complaintReportDetails.getEndDate(),
						((complaintReportDetails.getUserID() != null && complaintReportDetails.getUserID() > 0)
								? complaintReportDetails.getUserID()
								: null),
						complaintReportDetails.getProviderServiceMapID());
			} else {
				motherComplaintResult = mctsSecondaryReportRepo.getComplaintReportMother(
						complaintReportDetails.getStartDate(), complaintReportDetails.getEndDate(),
						((complaintReportDetails.getUserID() != null && complaintReportDetails.getUserID() > 0)
								? complaintReportDetails.getUserID()
								: null),
						complaintReportDetails.getProviderServiceMapID());
				childComplaintResult = mctsSecondaryReportRepo.getComplaintReportChild(
						complaintReportDetails.getStartDate(), complaintReportDetails.getEndDate(),
						((complaintReportDetails.getUserID() != null && complaintReportDetails.getUserID() > 0)
								? complaintReportDetails.getUserID()
								: null),
						complaintReportDetails.getProviderServiceMapID());

				result = ListUtils.union(motherComplaintResult, childComplaintResult);

				if (result != null && result.size() > 0)
					response = ExcelHelper.tutorialsToExcel(headers, result, c, criteriaColumns);
				else
					throw new IEMRException("No data found");
			}
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}

	@Override
	public ByteArrayInputStream getInvalidRecordReport(String request, String fname) throws IEMRException {
		String[] motherHeaders = { "SNo", "M C T S ID", "Name", "Husband  Name", "Phone No Of Whom", "Whom Phone No",
				"Birth Date", "Age", "Caste", "Aadhar no", "State ID", "District ID", "District Name", "Taluka Name",
				"Taluka ID", "Block ID", "Block Name", "Sub Center ID", "Sub Center Name", "Village ID", "Village Name",
				"City ID", "City Name", "Sub Center Name1", "Anm ID", "ANM Name", "ANM Phone", "Asha ID", "Asha Name",
				"Asha Phone", "PHC ID", "PHC Name", "Sub Phc ID", "Sub Phc  Name", "Yr", "GP Village", "Address",
				"Entry type", "LMP Date", "ANC1 Date", "ANC2 Date", "ANC3 Date", "ANC4 Date", "Anc Complication",
				"TT1 Date", "TT2 Date", "TT Booster Date", "Ifa100 Given Date", "Edd", "Anemia", "Rti Sti",
				"Delivery Lnk Facility", "Facility Name", "Delivery Date", "Delivery Place home Type",
				"Delivery Place Public", "Delivery Place Private", "Delivery Type", "Delivery Complications",
				"Discharge Date", "Abortion", "PNC Home Visit", "PNC Complication", "Ppc Method", "PNC Checkup",
				"Outcome Nos", "Child1 ID", "Child1 Name", "Child1 Gender", "Child1 Weight", "Child1 Breast Feeding",
				"Child2 ID", "Child2 Name", "Child2 Gender", "Child2 Weight", "Child2 Breast Feeding", "Child3 ID",
				"Child3 Name", "Child3 Gender", "Child3 Weight", "Child3 Breast Feeding", "Child4 ID", "Child4 Name",
				"Child4 Gender", "Child4 Weight", "Child4 Breast Feeding", "Mother Reg Date", "Last Update Date", "EID",
				"EID Time", "Cpsms  Flag", "Jsy  Beneficiary", "Jsy Paid Date", "Bank Name", "Bank Branch Name",
				"Acc No", "Ifsc Code", "Remarks", "Referred By Telecaller", "Referred Date", "No of Try",
				"Call Answered", "Status", "High Risk", "Call Verified", "Associate", "Call  Date", "Due Services",
				"Due Services Response", "Overdue Services", "Overdue Services Response", "Given Services",
				"Given Services Response", "Miscarriage", "Baby Died", "Call No", "Questions Asked",
				"Asnwer Given by Benificary", "Source", "No Call Reason", "No Phone Reason", "Created By", "Updated By",
				"Bpl Apl", "MDDS State ID", "MDDS District ID", "MDDS Taluka ID", "MDDS Village ID", "Is  Valid",
				"Is  Error", "File ID", "Error Reason", "Is Allocated", "Deleted", "Dateof Upload", "Updated By",
				"In Valid Reason" };
		String[] childHeaders = { "SNo", "M C T S ID no  Child  ID", "Child  Name", "Father  Name", "Mother  Name",
				"Mother  ID", "Birth Date", "Place of  Birth", "Gender", "Caste", "Child  Aadhaar  No",
				"Weight of  Child", "Child  E ID", "Child  E ID  Time", "Emamta  Health  ID", "Emamta  Family  ID",
				"Phone  No of", "Phone  No", "Registration  Date", "Updated  Date", "City", "State  ID", "District  ID",
				"District  Name", "Taluka  Name", "Taluka  ID", "Block  ID", "Block  Name", "Phc  ID", "Phc  Name",
				"Sub Center  ID", "Sub Center  Name", "Sub Center  Name1", "Village  ID", "Village  Name", "Address",
				"Year", "Anm  ID", "Anm  Name", "Anm  Phone  No", "Asha  ID", "Asha  Name", "Asha  Phone  No",
				"Entry type", "B C G  Date", "O P V0  Date", "Hepatitis  B1  Date", "Dpt1  Date", "Opv1  Date",
				"Hepatitis  B2  Date", "Dpt2  Date", "Opv2  Date", "Hepatitis  B3  Date", "Dpt3  Date", "Opv3  Date",
				"Hepatitis  B4  Date", "Measles  Date", "Vit A  Dose1  Date", "M R  Date", "Dptbooster  Date",
				"Opvbooster  Date", "Vit A  Dose2  Date", "Vit A  Dose3  Date", "Vit A  Dose99  Date",
				"Vit A  Dose5  Date", "Vit A  Dose6  Date", "Vit A  Dose7  Date", "Vit A  Dose8  Date",
				"Vit A  Dose9  Date", "Je  Date", "Dt5  Date", "Tt10  Date", "Tt16  Date", "Measles 2  Date",
				"Penta Valent1  Date", "Penta Valent2  Date", "Penta Valent3  Date", "Is  Upload", "Status", "Remarks",
				"Delete  Mother", "Delete  Reason", "Deleted  O N", "Sms  Status", "Mdds  State ID",
				"Mdds  District  ID", "Mdds  Taluka  ID", "Mdds  Village  ID", "Birth Certificate No", "Rural urban",
				"S N O", "Lead  ID", "My  ID", "Cid  N O", "Mid  N O", "Duplicate  Bit", "Facility Type",
				"Due Services", "Over Due Services", "Given Services", "Source", "Is Allocated", "Is  Valid",
				"Is  Error", "Error  Reason", "File ID", "Deleted", "Dateof Upload", "Updated By"

		};
		String[] criteriaColumns = { "Start_Date", "End_Date", "Type" };
		ByteArrayInputStream response = null;
		List<Objects[]> result = null;
		try {
			MotherDataReportDetail motherInvalidRecordReportDetail = InputMapper.gson().fromJson(request,
					MotherDataReportDetail.class);
			Criteria c = new Criteria();
			c.setStart_Date(motherInvalidRecordReportDetail.getStartDate().toString());
			c.setEnd_Date(motherInvalidRecordReportDetail.getEndDate().toString());
			c.setType(motherInvalidRecordReportDetail.getIsMother().toString());

			if (motherInvalidRecordReportDetail.getIsMother()) {
				result = mctsSecondaryReportRepo.getInvalidRecordReportMother(
						motherInvalidRecordReportDetail.getStartDate(), motherInvalidRecordReportDetail.getEndDate(),
						motherInvalidRecordReportDetail.getProviderServiceMapID());
			} else {
				result = mctsSecondaryReportRepo.getInvalidRecordReportChild(
						motherInvalidRecordReportDetail.getStartDate(), motherInvalidRecordReportDetail.getEndDate(),
						motherInvalidRecordReportDetail.getProviderServiceMapID());
			}

			if (result != null && result.size() > 0) {
				if (motherInvalidRecordReportDetail.getIsMother())
					response = ExcelHelper.tutorialsToExcel(motherHeaders, result, c, criteriaColumns);
				else
					response = ExcelHelper.tutorialsToExcel(childHeaders, result, c, criteriaColumns);
			} else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;

	}

	@Override
	public ByteArrayInputStream getCallAnsweredReport(String request, String filename) throws Exception {
		String[] initialHeaders = { "SNo", "Mother ID", "Mother Name", "Child ID", "Child Name", "Phone No", "Anm Name",
				"Asha Name", "Phc Name", "Address", "District Name", "Block Name", "Sub Center Name",
				"Outbound Call Type", "Display Call Type", "Call Date And Time", "Remark" };
		String[] criteriaColumns = { "Start_Date", "End_Date", "Agent_ID" };
		ByteArrayInputStream response = null;
		List<Objects[]> result = null;
		try {
			BenCallDetail benCallDetail = InputMapper.gson().fromJson(request, BenCallDetail.class);
			Criteria c = new Criteria();
			c.setStart_Date(benCallDetail.getStartDate().toString());
			c.setEnd_Date(benCallDetail.getEndDate().toString());
			c.setAgent_ID(benCallDetail.getUserID().toString());

			List<MctsQAMappingDetail> mctsQAMappingDetails = mctsQAMappingRepository.getQuestionsForReport(
					benCallDetail.getOutboundCallType(), benCallDetail.getProviderServiceMapID(),
					benCallDetail.getEffectiveFrom());

			String[] headers = new String[mctsQAMappingDetails.size() + 17];
			int i = 0;
			for (i = 0; i < initialHeaders.length; i++) {
				headers[i] = initialHeaders[i];
			}

			for (MctsQAMappingDetail mctsQAMapping : mctsQAMappingDetails) {
				headers[i] = mctsQAMapping.getQuestionnaireDetail().getQuestion();
				i++;
			}

			result = mctsSecondaryReportRepo.getCallAnsweredReport(benCallDetail.getStartDate(),
					benCallDetail.getEndDate(), benCallDetail.getEffectiveFrom(), benCallDetail.getOutboundCallType(),
					benCallDetail.getVerifiedData().toString(),
					benCallDetail.getUserID() > 0 ? benCallDetail.getUserID() : null,
					benCallDetail.getProviderServiceMapID());

			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result, c, criteriaColumns);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;

	}

	@Override
	public ByteArrayInputStream getCallNotAnsweredReport(String request, String filename) throws Exception {
		String[] headers = { "Sno", "Mother ID", "Mother Name", "Child ID", "Child Name", "Phone No", "Anm Name",
				"Asha Name", "Phc Name", "Outbound Call Type", "Display Call Type", "Call Date And Time", "Address",
				"District Name", "Block Name", "Sub Center Name", "Remark", "Reason" };
		String[] criteriaColumns = { "Start_Date", "End_Date", "Agent_ID" };
		ByteArrayInputStream response = null;
		List<Objects[]> result = null;
		try {
			BenCallDetail benCallDetail = InputMapper.gson().fromJson(request, BenCallDetail.class);
			Criteria c = new Criteria();
			c.setStart_Date(benCallDetail.getStartDate().toString());
			c.setEnd_Date(benCallDetail.getEndDate().toString());
			c.setAgent_ID(benCallDetail.getUserID().toString());
			result = mctsSecondaryReportRepo.getCallNotAnsweredReport(benCallDetail.getStartDate(),
					benCallDetail.getEndDate(), benCallDetail.getUserID() > 0 ? benCallDetail.getUserID() : null,
					benCallDetail.getProviderServiceMapID());

			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result, c, criteriaColumns);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}

	@Override
	public ByteArrayInputStream getHighRiskReport(String request, String filename) throws Exception {
		String[] headers = { "Sno", "Mcts ID No", "Name", "Husband Name", "Age", "District Name", "Taluka Name",
				"Block Name", "Phc ID", "Phc Name", "Sub Phc ID", "Sub Phc Name", "Phone No Of Whom", "Whom Phone No",
				"Birth Date", "Lmp Date", "Edd", "High Risk Reason" };
		String[] criteriaColumns = { "Start_Date", "End_Date", "Agent_ID" };
		ByteArrayInputStream response = null;
		List<Objects[]> result = null;
		try {
			HighRiskCall getHighRiskReport = InputMapper.gson().fromJson(request, HighRiskCall.class);
			Criteria c = new Criteria();
			c.setStart_Date(getHighRiskReport.getStartDate().toString());
			c.setEnd_Date(getHighRiskReport.getEndDate().toString());
			c.setAgent_ID(getHighRiskReport.getUserID().toString());

			result = mctsSecondaryReportRepo.getHighRiskReport(getHighRiskReport.getStartDate(),
					getHighRiskReport.getEndDate(),
					getHighRiskReport.getUserID() > 0 ? getHighRiskReport.getUserID() : null,
					getHighRiskReport.getProviderServiceMapID());

			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result, c, criteriaColumns);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;

	}

	@Override
	public ByteArrayInputStream getDailyReport(String request, String filename) throws Exception {
		String[] headers = { "Sno", "Date", "District ID", "Districts", "TotalCalls", "TotalUniqueCalls",
				"TotalSelfNoCalls", "TotalOtherNoCalls", "TotalAnsweredCalls", "TotalVerifiedCalls",
				"TotalUnVerifiedCalls" };
		String[] criteriaColumns = { "Start_Date", "End_Date", "Type", "Agent_ID" };
		ByteArrayInputStream response = null;
		List<Objects[]> result = null;
		try {
			BenCallDetail getDailyReport = InputMapper.gson().fromJson(request, BenCallDetail.class);
			Criteria c = new Criteria();
			c.setStart_Date(getDailyReport.getStartDate().toString());
			c.setEnd_Date(getDailyReport.getEndDate().toString());
			c.setType(getDailyReport.getIsMother().toString());
			c.setAgent_ID(getDailyReport.getUserID().toString());

			result = mctsSecondaryReportRepo.getDailyReport(getDailyReport.getStartDate(), getDailyReport.getEndDate(),
					getDailyReport.getIsMother() != null ? getDailyReport.getIsMother().toString() : null,
					getDailyReport.getUserID() > 0 ? getDailyReport.getUserID() : null,
					getDailyReport.getProviderServiceMapID());
			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result, c, criteriaColumns);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}

	@Override
	public ByteArrayInputStream getNHMReport(String request, String filename) throws Exception {
		String[] headers = { "Sno", "Date", "District", "Beneficiary ID", "Name", "Health Block", "Phc",
				"Sub Center Name", "Facility Name", "Category", "Display Call Type", "Phone Number", "Call Duration",
				"Remark" };
		String[] criteriaColumns = { "Start_Date", "End_Date", "Agent_ID" };
		ByteArrayInputStream response = null;
		List<Objects[]> result = null;
		try {
			BenCallDetail getNHMReport = InputMapper.gson().fromJson(request, BenCallDetail.class);
			Criteria c = new Criteria();
			c.setStart_Date(getNHMReport.getStartDate().toString());
			c.setEnd_Date(getNHMReport.getEndDate().toString());
			c.setAgent_ID(getNHMReport.getUserID().toString());

			result = mctsSecondaryReportRepo.getNHMReport(getNHMReport.getStartDate(), getNHMReport.getEndDate(),
					getNHMReport.getUserID() > 0 ? getNHMReport.getUserID() : null,
					getNHMReport.getProviderServiceMapID());

			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result, c, criteriaColumns);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}

	@Override
	public ByteArrayInputStream getCongenitalAnomaliesReport(String request, String filename) throws Exception {
		String[] headers = { "SNo", "Mother ID", "Mother Name", "Child ID", "Child Name", "Dob", "Address", "Anm Name",
				"Asha Name", "Phone No", "Congenital Anomalies", "Remarks" };
		String[] criteriaColumns = { "Start_Date", "End_Date", "Agent_ID" };
		ByteArrayInputStream response = null;
		List<Objects[]> result = null;
		try {
			BenCallDetail getCongenitalAnomaliesReport = InputMapper.gson().fromJson(request, BenCallDetail.class);
			Criteria c = new Criteria();
			c.setStart_Date(getCongenitalAnomaliesReport.getStartDate().toString());
			c.setEnd_Date(getCongenitalAnomaliesReport.getEndDate().toString());
			c.setAgent_ID(getCongenitalAnomaliesReport.getUserID().toString());

			result = mctsSecondaryReportRepo.getCongenitalAnomaliesReport(getCongenitalAnomaliesReport.getStartDate(),
					getCongenitalAnomaliesReport.getEndDate(),
					getCongenitalAnomaliesReport.getUserID() > 0 ? getCongenitalAnomaliesReport.getUserID() : null,
					getCongenitalAnomaliesReport.getProviderServiceMapID());

			if (result != null && result.size() > 0)
				response = ExcelHelper.tutorialsToExcel(headers, result, c, criteriaColumns);
			else
				throw new IEMRException("No data found");
		} catch (IEMRException e) {
			throw new IEMRException(e.getMessage());
		}
		return response;
	}
}
