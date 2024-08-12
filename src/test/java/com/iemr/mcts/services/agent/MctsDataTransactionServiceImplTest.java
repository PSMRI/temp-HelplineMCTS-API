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

import static org.mockito.Mockito.doReturn;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.iemr.mcts.data.supervisor.DBExcelFieldName;
import com.iemr.mcts.data.supervisor.MctsDataReaderDetail;
import com.iemr.mcts.data.supervisor.MctsOutboundCall;
import com.iemr.mcts.data.supervisor.MctsStatewiseFieldsDetail;
import com.iemr.mcts.repository.supervisor.MctsDataHandlerRepository;
import com.iemr.mcts.repository.supervisor.MctsStatewiseFieldsRepository;
import com.iemr.mcts.utils.exception.IEMRException;

@RunWith(MockitoJUnitRunner.class)
public class MctsDataTransactionServiceImplTest {

	@InjectMocks
	MctsDataTransactionServiceImpl mctsDataTransactionServiceImpl;
	
	@Mock
	MctsDataHandlerRepository mctsDataHandlerRepository;
	
	@Mock
	MctsStatewiseFieldsRepository mctsStatewiseFieldsRepository;
	
	@Test
	public void updateDataTest()
	{
		MctsOutboundCall mctsOutboundCall=new MctsOutboundCall();
		mctsOutboundCall.setOutboundCallType("type");
		mctsOutboundCall.setProviderServiceMapID(new Long("301"));
		MctsDataReaderDetail mctsDataReaderDetail=new MctsDataReaderDetail();
		mctsDataReaderDetail.setMotherValidRecordID(new Long("101"));
		mctsOutboundCall.setMctsDataReaderDetail(mctsDataReaderDetail);
		MctsDataReaderDetail mctsDataReaderDetail1=new MctsDataReaderDetail();
		
		MctsStatewiseFieldsDetail mctsStatewiseFieldsDetail=new MctsStatewiseFieldsDetail();
		mctsStatewiseFieldsDetail.setProviderServiceMapID(new Long("501"));
		
		doReturn(mctsStatewiseFieldsDetail).when(mctsStatewiseFieldsRepository).getAllFields(Mockito.anyLong(),Mockito.anyString());
		
		doReturn(mctsDataReaderDetail1).when(mctsDataHandlerRepository).findById(Mockito.anyLong());
		
		List<DBExcelFieldName> list=Lists.newArrayList();
		DBExcelFieldName dBExcelFieldName=new DBExcelFieldName();
		dBExcelFieldName.setDbColumnName("db");
		list.add(dBExcelFieldName);
		
	/*	try {
			String response=mctsDataTransactionServiceImpl.updateData(mctsOutboundCall.toString());
			System.out.println(response);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | IEMRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	@Test
	public void updateData1Test()
	{
		MctsOutboundCall mctsOutboundCall=new MctsOutboundCall();
		mctsOutboundCall.setOutboundCallType("type");
		MctsDataReaderDetail mctsDataReaderDetail=new MctsDataReaderDetail();
		mctsOutboundCall.setMctsDataReaderDetail(mctsDataReaderDetail);
		doReturn(mctsDataReaderDetail).when(mctsDataHandlerRepository).findById(Mockito.anyLong());
		/*try {
			//String response=mctsDataTransactionServiceImpl.updateData(mctsOutboundCall.toString());
			//System.out.println(response);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | IEMRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
