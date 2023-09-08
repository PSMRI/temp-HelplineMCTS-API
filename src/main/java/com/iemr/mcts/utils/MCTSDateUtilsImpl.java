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
package com.iemr.mcts.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.mcts.data.supervisor.CallConfigurationDetail;
import com.iemr.mcts.data.supervisor.MctsOutboundCall;
import com.iemr.mcts.repository.supervisor.CallConfigurationRepository;

@Service
public class MCTSDateUtilsImpl implements MCTSDateUtils {

	/**
	 * call configuration repository
	 */
	private CallConfigurationRepository callConfigurationRepository;

	/**
	 * Inject call configuration repository
	 * 
	 * @param callConfigurationRepository
	 */
	@Autowired
	public void setCallConfigurationRepository(CallConfigurationRepository callConfigurationRepository) {

		this.callConfigurationRepository = callConfigurationRepository;
	}

	/*
	 * (non-Javadoc) method to collect all outbound type calls and respective dates
	 * 
	 * @see com.iemr.mcts.utils.MCTSDateUtils#getCallTypesDates(java.sql.Date,
	 * java.lang.Long) this method is to get no of ANC's and PNC's with respective
	 * dates
	 */
	@Override
	public Map<String, MctsOutboundCall> getCallTypesDates(Date lmpDate, Date eddDate, Long providerServiceMapID) {

		Map<String, MctsOutboundCall> callsDatesMap = new HashMap<String, MctsOutboundCall>();
		MctsOutboundCall mctsOutboundCall;
		List<CallConfigurationDetail> callConfigurationDetails = this.callConfigurationRepository
				.getMotherConfigTerms(providerServiceMapID);

		for (CallConfigurationDetail callConfigurationDetail : callConfigurationDetails) {

			LocalDate ld;
			if (callConfigurationDetail.getOutboundCallType().indexOf("A") != -1) {

				ld = lmpDate.toLocalDate();
			} else {
				ld = eddDate.toLocalDate();
			}

			if (callConfigurationDetail.getFromTerm() != null) {
				String fromTerm = callConfigurationDetail.getFromTerm();
				String toTerm = callConfigurationDetail.getToTerm();

				mctsOutboundCall = new MctsOutboundCall();
				mctsOutboundCall.setCallDateFrom(this.getManipulatedDate(fromTerm, ld));
				mctsOutboundCall.setCallDateTo(this.getManipulatedDate(toTerm, ld));
				mctsOutboundCall.setDisplayOBCallType(callConfigurationDetail.getDisplayOBCallType());
				callsDatesMap.put(callConfigurationDetail.getOutboundCallType(), mctsOutboundCall);
			}
		}

		return callsDatesMap;
	}

	/*
	 * (non-Javadoc) method to collect all outbound type calls and respective dates
	 * for child data
	 * 
	 * @see com.iemr.mcts.utils.MCTSDateUtils#getCallTypesDates(java.sql.Date,
	 * java.lang.Long) this method is to get no of ANC's and PNC's with respective
	 * dates
	 */
	@Override
	public Map<String, MctsOutboundCall> getChildCallTypesDates(Date birthDate, Long providerServiceMapID) {

		Map<String, MctsOutboundCall> callsDatesMap = new HashMap<String, MctsOutboundCall>();
		MctsOutboundCall mctsOutboundCall;
		List<CallConfigurationDetail> callConfigurationDetails = this.callConfigurationRepository
				.getChildConfigTerms(providerServiceMapID);

		for (CallConfigurationDetail callConfigurationDetail : callConfigurationDetails) {

			LocalDate ld;
			if (callConfigurationDetail.getOutboundCallType().indexOf("P") != -1) {

				ld = birthDate.toLocalDate();

				if (callConfigurationDetail.getFromTerm() != null) {
					String fromTerm = callConfigurationDetail.getFromTerm();
					String toTerm = callConfigurationDetail.getToTerm();

					mctsOutboundCall = new MctsOutboundCall();
					mctsOutboundCall.setCallDateFrom(this.getManipulatedDate(fromTerm, ld));
					mctsOutboundCall.setCallDateTo(this.getManipulatedDate(toTerm, ld));
					mctsOutboundCall.setDisplayOBCallType(callConfigurationDetail.getDisplayOBCallType());
					callsDatesMap.put(callConfigurationDetail.getOutboundCallType(), mctsOutboundCall);
				}
			}
		}

		return callsDatesMap;
	}

	private Date getManipulatedDate(String term, LocalDate localDate) {

		LocalDate ld = localDate;
		String termType = term.substring(term.length() - 1, term.length());

		if (termType.equals("M")) {
			int months = Integer.parseInt(String.valueOf(term.substring(0, term.length() - 1)));
			ld = ld.plusMonths(months);
		} else {
			int weeks = Integer.parseInt(String.valueOf(term.substring(0, term.length() - 1)));
			ld = ld.plusWeeks(weeks);
		}
		return Date.valueOf(ld);
	}
}
