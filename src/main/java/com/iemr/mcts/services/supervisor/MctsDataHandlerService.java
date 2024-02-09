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

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.tika.exception.TikaException;

import com.iemr.mcts.data.mapper.UploadData;
import com.iemr.mcts.data.supervisor.FileManager;
import com.iemr.mcts.utils.exception.IEMRException;

public interface MctsDataHandlerService {

	String savemother(UploadData uploadData);

	/**
	 * service method to upload mcts data
	 * 
	 * @param request
	 * @param servletRequest
	 * @param inputStream
	 * @return
	 * @throws IEMRException
	 */
	UploadData mctsDataUpload(String request, HttpServletRequest servletRequest)
			throws IOException, TikaException, IEMRException;

	/**
	 * this to update beneficiary details
	 * 
	 * @param request
	 * @return
	 * @throws IEMRException
	 */
	String updateBeneficiary(String request) throws IEMRException;

	UploadData validateData(UploadData uploadData);

	FileManager uploadstatus(String request) throws IEMRException;

	FileManager savefilemanger(FileManager fileManager);

}
