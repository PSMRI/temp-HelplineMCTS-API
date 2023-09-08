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

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.mcts.data.report.BenCallDetail;
import com.iemr.mcts.data.report.DailyReportDetail;
import com.iemr.mcts.data.report.Districts;
import com.iemr.mcts.repository.report.DailyReportRepository;
import com.iemr.mcts.utils.mapper.InputMapper;

@Service
public class DailyReportServiceImpl implements DailyReportService {

	Logger logger = LoggerFactory.getLogger(HighRiskReportServiceImpl.class);

	private InputMapper inputMapper = new InputMapper();

	private DailyReportRepository dailyReportRepository;

	/**
	 * @param dailyReportRepository the dailyReportRepository to set
	 */
	@Autowired
	public void setDailyReportRepository(DailyReportRepository dailyReportRepository) {
		this.dailyReportRepository = dailyReportRepository;
	}

	@Override
	public String getDailyReport(String request) throws Exception {
		logger.info("DailyReportServiceImpl.getDailyReport - start");

		BenCallDetail benCallDetail = inputMapper.gson().fromJson(request, BenCallDetail.class);

		DailyReportDetail report = null;
		List<DailyReportDetail> list = new ArrayList<DailyReportDetail>();

		Integer totalCalls = 0;
		Integer totalUniqueCalls = 0;
		Integer totalSelfNoCalls = 0;
		Integer totalOtherNoCalls = 0;
		Integer totalAnsweredCalls = 0;
		Integer totalVerifiedCalls = 0;
		Integer totalUnVerifiedCalls = 0;

		Integer callTypeID = dailyReportRepository.getCallTypeID(benCallDetail.getProviderServiceMapID().intValue());
		Integer stateID = dailyReportRepository.getStateByProvider(benCallDetail.getProviderServiceMapID().intValue());
		List<Districts> list1 = dailyReportRepository.getDistrictByStateID(stateID);

		Timestamp stamp = benCallDetail.getStartDate();
		Timestamp stamp1 = benCallDetail.getEndDate();

		java.util.Date current = new Date(stamp.getTime());
		Date end = new Date(stamp1.getTime());

		java.util.Date current1 = new Date(stamp1.getTime());

		while (current.before(end)) {

			java.util.Date date = current;
			Timestamp beginOfDate = new Timestamp(date.getTime());

			java.util.Date endDate = current;
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");

			String str2 = sdf2.format(endDate.getTime());

			endDate = sdf2.parse(str2);

			String endDateTime = sdf2.format(endDate);
			Timestamp endOfDate = Timestamp.valueOf(endDateTime);

			for (Districts district : list1) {
				report = new DailyReportDetail();
				if (benCallDetail.getUserID() != null && benCallDetail.getUserID() > 0) {
					if (benCallDetail.getIsMother() != null && Boolean.TRUE.equals(benCallDetail.getIsMother())) {
						totalCalls = dailyReportRepository.getTotalCallMotherByAgentID(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
								district.getDistrict());
						totalUniqueCalls = dailyReportRepository.getTotalUniqueNoCallMotherByAgentID(beginOfDate,
								endOfDate, benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
								district.getDistrict());
						totalSelfNoCalls = dailyReportRepository.getTotalSelfNoCallMotherByAgentID(beginOfDate,
								endOfDate, benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
								district.getDistrict());
						totalOtherNoCalls = dailyReportRepository.getTotalOtherNoCallMotherByAgentID(beginOfDate,
								endOfDate, benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
								district.getDistrict());
						totalAnsweredCalls = dailyReportRepository.getTotalCallAnsweredMotherByAgentID(beginOfDate,
								endOfDate, benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
								callTypeID, district.getDistrict());
						totalVerifiedCalls = dailyReportRepository.getTotalVerifiedCallMotherByAgentID(beginOfDate,
								endOfDate, benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
								district.getDistrict());
						totalUnVerifiedCalls = dailyReportRepository.getTotalNotVerifiedCallMotherByAgentID(beginOfDate,
								endOfDate, benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
								district.getDistrict());
					} else if (benCallDetail.getIsMother() != null
							&& Boolean.FALSE.equals(benCallDetail.getIsMother())) {
						totalCalls = dailyReportRepository.getTotalCallChildByAgentID(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
								district.getDistrict());
						totalUniqueCalls = dailyReportRepository.getTotalUniqueNoCallChildByAgentID(beginOfDate,
								endOfDate, benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
								district.getDistrict());
						totalSelfNoCalls = dailyReportRepository.getTotalSelfNoCallChildByAgentID(beginOfDate,
								endOfDate, benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
								district.getDistrict());
						totalOtherNoCalls = dailyReportRepository.getTotalOtherNoCallChildByAgentID(beginOfDate,
								endOfDate, benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
								district.getDistrict());
						totalAnsweredCalls = dailyReportRepository.getTotalCallAnsweredChildByAgentID(beginOfDate,
								endOfDate, benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
								callTypeID, district.getDistrict());
						totalVerifiedCalls = dailyReportRepository.getTotalVerifiedCallChildByAgentID(beginOfDate,
								endOfDate, benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
								district.getDistrict());
						totalUnVerifiedCalls = dailyReportRepository.getTotalNotVerifiedCallChildByAgentID(beginOfDate,
								endOfDate, benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
								district.getDistrict());
					} else {
						totalCalls = dailyReportRepository.getTotalCallByAgentID(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
								district.getDistrict());
						totalUniqueCalls = dailyReportRepository.getTotalUniqueNoCallByAgentID(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
								district.getDistrict());

						totalSelfNoCalls = dailyReportRepository.getTotalSelfNoCallMotherByAgentID(beginOfDate,
								endOfDate, benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
								district.getDistrict())
								+ dailyReportRepository.getTotalSelfNoCallChildByAgentID(beginOfDate, endOfDate,
										benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
										district.getDistrict());
						totalOtherNoCalls = dailyReportRepository.getTotalOtherNoCallMotherByAgentID(beginOfDate,
								endOfDate, benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
								district.getDistrict())
								+ dailyReportRepository.getTotalOtherNoCallChildByAgentID(beginOfDate, endOfDate,
										benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
										district.getDistrict());

						totalAnsweredCalls = dailyReportRepository.getTotalCallAnsweredByAgentID(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(), callTypeID,
								district.getDistrict());
						totalVerifiedCalls = dailyReportRepository.getTotalVerifiedCallByAgentID(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
								district.getDistrict());
						totalUnVerifiedCalls = dailyReportRepository.getTotalNotVerifiedCallByAgentID(beginOfDate,
								endOfDate, benCallDetail.getProviderServiceMapID(), benCallDetail.getUserID(),
								district.getDistrict());

					}
				} else {

					if (benCallDetail.getIsMother() != null && Boolean.TRUE.equals(benCallDetail.getIsMother())) {
						totalCalls = dailyReportRepository.getTotalCallMother(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), district.getDistrict());
						totalUniqueCalls = dailyReportRepository.getTotalUniqueNoCallMother(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), district.getDistrict());
						totalSelfNoCalls = dailyReportRepository.getTotalSelfNoCallMother(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), district.getDistrict());
						totalOtherNoCalls = dailyReportRepository.getTotalOtherNoCallMother(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), district.getDistrict());
						totalAnsweredCalls = dailyReportRepository.getTotalCallAnsweredMother(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), callTypeID, district.getDistrict());
						totalVerifiedCalls = dailyReportRepository.getTotalVerifiedCallMother(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), district.getDistrict());
						totalUnVerifiedCalls = dailyReportRepository.getTotalNotVerifiedCallMother(beginOfDate,
								endOfDate, benCallDetail.getProviderServiceMapID(), district.getDistrict());
					} else if (benCallDetail.getIsMother() != null
							&& Boolean.FALSE.equals(benCallDetail.getIsMother())) {
						totalCalls = dailyReportRepository.getTotalCallChild(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), district.getDistrict());
						totalUniqueCalls = dailyReportRepository.getTotalUniqueNoCallChild(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), district.getDistrict());
						totalSelfNoCalls = dailyReportRepository.getTotalSelfNoCallChild(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), district.getDistrict());
						totalOtherNoCalls = dailyReportRepository.getTotalOtherNoCallChild(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), district.getDistrict());
						totalAnsweredCalls = dailyReportRepository.getTotalCallAnsweredChild(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), callTypeID, district.getDistrict());
						totalVerifiedCalls = dailyReportRepository.getTotalVerifiedCallChild(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), district.getDistrict());
						totalUnVerifiedCalls = dailyReportRepository.getTotalNotVerifiedCallChild(beginOfDate,
								endOfDate, benCallDetail.getProviderServiceMapID(), district.getDistrict());
					} else {
						totalCalls = dailyReportRepository.getTotalCall(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), district.getDistrict());
						totalUniqueCalls = dailyReportRepository.getTotalUniqueNoCall(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), district.getDistrict());

						totalSelfNoCalls = dailyReportRepository.getTotalSelfNoCallMother(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), district.getDistrict())
								+ dailyReportRepository.getTotalSelfNoCallChild(beginOfDate, endOfDate,
										benCallDetail.getProviderServiceMapID(), district.getDistrict());
						totalOtherNoCalls = dailyReportRepository.getTotalOtherNoCallMother(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), district.getDistrict())
								+ dailyReportRepository.getTotalOtherNoCallChild(beginOfDate, endOfDate,
										benCallDetail.getProviderServiceMapID(), district.getDistrict());

						totalAnsweredCalls = dailyReportRepository.getTotalCallAnswered(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), callTypeID, district.getDistrict());
						totalVerifiedCalls = dailyReportRepository.getTotalVerifiedCall(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), district.getDistrict());
						totalUnVerifiedCalls = dailyReportRepository.getTotalNotVerifiedCall(beginOfDate, endOfDate,
								benCallDetail.getProviderServiceMapID(), district.getDistrict());

					}

				}

				report.setDate(beginOfDate);
				report.setDistricts(district.getDistrict());
				report.setTotalCalls(totalCalls);
				report.setTotalUniqueCalls(totalUniqueCalls);
				report.setTotalSelfNoCalls(totalSelfNoCalls);
				report.setTotalOtherNoCalls(totalOtherNoCalls);
				report.setTotalVerifiedCalls(totalVerifiedCalls);
				report.setTotalUnVerifiedCalls(totalUnVerifiedCalls);
				report.setTotalAnsweredCalls(totalAnsweredCalls);
				list.add(report);

			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(current);
			calendar.add(Calendar.DATE, 1);
			current = calendar.getTime();
		}

		logger.info("DailyReportServiceImpl.getDailyReport - end");
		return list.toString();
	}

}
