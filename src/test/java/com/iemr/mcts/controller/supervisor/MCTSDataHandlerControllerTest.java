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
package com.iemr.mcts.controller.supervisor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.tika.exception.TikaException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import com.iemr.mcts.services.supervisor.MctsDataHandlerService;
import com.iemr.mcts.utils.exception.IEMRException;

@RunWith(MockitoJUnitRunner.class)
public class MCTSDataHandlerControllerTest {

	@InjectMocks
	MCTSDataHandlerController mctsDataHandlerController;
	
	@Mock
	MctsDataHandlerService mctsDataHandlerService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void uploadDataTest() throws IOException, TikaException, IEMRException
	{
        MockMultipartFile file = new MockMultipartFile("file", "orig", null, "bar".getBytes());
        String str=new String("success");
        HttpServletRequest request=mock(HttpServletRequest.class);
        doReturn(str).when(mctsDataHandlerService).mctsDataUpload(str,request);
        String res=mctsDataHandlerController.uploadData(str,  request);
        assertTrue(res.contains("\"response\":\"success\""));
	}
	@Test
	public void uploadDataEmptyTest() throws IOException, TikaException, IEMRException
	{
        String str=new String(" ");
        HttpServletRequest request=mock(HttpServletRequest.class);
        doReturn(str).when(mctsDataHandlerService).mctsDataUpload(str,request);
        String res=mctsDataHandlerController.uploadData(str,  request);
        assertFalse(res.contains("\"response\":\"success\""));
	}
	
	@Test
	public void updateBeneficiaryTest() throws IEMRException
	{
		String str=new String("success");
		doReturn(str).when(mctsDataHandlerService).updateBeneficiary(Mockito.anyString());
        String res=mctsDataHandlerController.updateBeneficiary(Mockito.anyString());
        assertTrue(res.contains("\"response\":\"success\""));
	}
	@Test
	public void updateBeneficiaryExceptionTest() 
	{
		try {
			doThrow(Exception.class).when(mctsDataHandlerService).updateBeneficiary(Mockito.anyString());
		} catch (IEMRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String res=mctsDataHandlerController.updateBeneficiary(Mockito.anyString());
        assertFalse(res.contains("\"response\":\"success\""));
	}

}
