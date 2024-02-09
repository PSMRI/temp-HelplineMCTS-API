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

import jakarta.servlet.http.HttpServletRequest;

import com.iemr.mcts.utils.exception.IEMRException;

public interface MctsOutboundCallDetailService {

	/**
	 * this method saves the call history with comments
	 * @param request
	 * @return
	 * @throws IEMRException 
	 * @throws Exception 
	 */
	String saveCallClosure(String request) throws IEMRException, Exception;

	/**
	 * this method define to get previous history of the call
	 * @param request
	 * @return
	 * @throws IEMRException 
	 */
	String getCallHistory(String request) throws IEMRException;

	/**
	 * method to get beneficiary details for call history
	 * @param request
	 * @return
	 * @throws IEMRException 
	 */
	String getBeneficiaryDetails(String request,  HttpServletRequest servletRequest) throws IEMRException;

	/**
	 * method to save history when call is connected
	 * @param request
	 * @return
	 * @throws IEMRException 
	 */
	String createCallHistory(String request) throws IEMRException;

	/**
	 * api to send sms
	 * @param request
	 * @return
	 * @throws IEMRException 
	 */
	String sendSmsAdvice(String request) throws IEMRException;

	/**
	 * api to get sms
	 * @param request
	 * @return
	 * @throws IEMRException
	 */
	String getSmsAdvice(String request) throws IEMRException;

	String getChangeLog(String request) throws IEMRException;

	String caseSheet(String request, HttpServletRequest servletRequest) throws IEMRException;

	String getChangeLogs(String request) throws IEMRException;
}
