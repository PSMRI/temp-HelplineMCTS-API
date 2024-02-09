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

import jakarta.servlet.http.HttpServletRequest;

import com.iemr.mcts.utils.exception.IEMRException;

public interface MctsDataTransactionService {

	String updateData(String request, HttpServletRequest servletRequest) throws IEMRException, 
		IllegalAccessException, InvocationTargetException, NoSuchMethodException;

	/**
	 * To associate beneficiary with mcts data 
	 * @param request
	 * @return
	 * @throws IEMRException
	 */
	String associateBeneficiaryID(String request) throws IEMRException;

}
