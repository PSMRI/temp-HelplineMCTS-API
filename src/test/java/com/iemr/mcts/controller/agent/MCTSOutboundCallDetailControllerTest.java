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
package com.iemr.mcts.controller.agent;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import jakarta.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.iemr.mcts.services.agent.MctsOutboundCallDetailService;
import com.iemr.mcts.utils.exception.IEMRException;

@RunWith(MockitoJUnitRunner.class)
public class MCTSOutboundCallDetailControllerTest {

	@InjectMocks
	MCTSOutboundCallDetailController mctsOutboundCallDetailController;
	
	@Mock
	MctsOutboundCallDetailService mctsOutboundCallDetailService;
	
	@Test
	public void getBeneficiaryDetailsTest()
	{
		String str=new String("get beneficiary successfully");
		HttpServletRequest servletRequest=mock(HttpServletRequest.class);
		try {
			doReturn(str).when(mctsOutboundCallDetailService).getBeneficiaryDetails(str,servletRequest);
		} catch (IEMRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String res=mctsOutboundCallDetailController.getBeneficiaryDetails(str,servletRequest);
		assertTrue(res.contains("\"response\":\"get beneficiary successfully\""));
	}
	@Test
	public void getBeneficiaryDetailsExceptionTest()
	{
		String str=new String("get beneficiary successfully");
		HttpServletRequest servletRequest=mock(HttpServletRequest.class);
		try {
			doThrow(IEMRException.class).when(mctsOutboundCallDetailService).getBeneficiaryDetails(str,servletRequest);
		} catch (IEMRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String res=mctsOutboundCallDetailController.getBeneficiaryDetails(str,servletRequest);
		assertFalse(res.contains("\"response\":\"get beneficiary successfully\""));
	}
	@Test
	public void getVariablesTest()
	{
		String str=new String("put call history successfully");
		try {
			doReturn(str).when(mctsOutboundCallDetailService).createCallHistory(Mockito.anyString());
		} catch (IEMRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String res=mctsOutboundCallDetailController.getVariables(Mockito.anyString());
		assertTrue(res.contains("\"response\":\"put call history successfully\""));
		
	}
	@Test
	public void getVariablesExceptionTest()
	{
		try {
			doThrow(IEMRException.class).when(mctsOutboundCallDetailService).createCallHistory(Mockito.anyString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String res=mctsOutboundCallDetailController.getVariables(Mockito.anyString());
		assertFalse(res.contains("\"response\":\"put call history successfully\""));
		
	}
	@Test
	public void sendSmsAdviseTest()
	{
		String str=new String("SMS sent successfully");
		try {
			doReturn(str).when(mctsOutboundCallDetailService).sendSmsAdvice(Mockito.anyString());
		} catch (IEMRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String res=mctsOutboundCallDetailController.sendSmsAdvise(Mockito.anyString());
		assertTrue(res.contains("\"response\":\"SMS sent successfully\""));
		
	}
	@Test
	public void sendSmsAdviseExceptionTest()
	{
		try {
			doThrow(IEMRException.class).when(mctsOutboundCallDetailService).sendSmsAdvice(Mockito.anyString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String res=mctsOutboundCallDetailController.sendSmsAdvise(Mockito.anyString());
		assertFalse(res.contains("\"response\":\"SMS sent successfully\""));
		
	}
	@Test
	public void getSmsAdviseTest()
	{
		String str=new String("get sms advise");
		try {
			doReturn(str).when(mctsOutboundCallDetailService).getSmsAdvice(Mockito.anyString());
		} catch (IEMRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String res=mctsOutboundCallDetailController.getSmsAdvise(Mockito.anyString());
		assertTrue(res.contains("\"response\":\"get sms advise\""));
		
	}
	@Test
	public void getSmsAdviseExceptionTest()
	{
		try {
			doThrow(IEMRException.class).when(mctsOutboundCallDetailService).getSmsAdvice(Mockito.anyString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String res=mctsOutboundCallDetailController.getSmsAdvise(Mockito.anyString());
		assertFalse(res.contains("\"response\":\"get sms advise\""));
		
	}

}
