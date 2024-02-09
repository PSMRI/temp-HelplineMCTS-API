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

public interface MctsOutbondCallService {

	/**
	 * this method is to get worklist of an agent
	 * @param request
	 * @return response
	 * @throws IEMRException 
	 */
	String getOutbondCalls(String request) throws IEMRException;
	
	/**
	 * this method is to allocate/re-allocate calls to agents
	 * @param request
	 * @return
	 * @throws IEMRException 
	 */
	String allocateCalls(String request) throws IEMRException;

	/**
	 * this method provide us the list of calls which needs to allocate
	 * @param request
	 * @return
	 * @throws IEMRException 
	 */
	String getUnallocatedCalls(String request) throws IEMRException;

	/**
	 * this method provide us the list of calls which needs to re-allocate
	 * @param request
	 * @return
	 * @throws IEMRException 
	 */
	String getReallocateCalls(String request) throws IEMRException;

	/**
	 * this method moves allocated call to bucket
	 * @param request
	 * @return message of call moving
	 * @throws IEMRException 
	 */
	String moveCallsToBucket(String request) throws IEMRException;

	/**
	 * api for getting next call date
	 * @param request
	 * @return String next call date
	 * @throws IEMRException 
	 */
	String getNextANC_PNC(String request) throws IEMRException;

	String getUpdatedObject(String request, HttpServletRequest servletRequest) throws IEMRException;

	String getHighRiskReason() throws IEMRException;

	String getMotherWorklist(String request) throws IEMRException;

	String getChildWorklist(String request) throws IEMRException;

}
