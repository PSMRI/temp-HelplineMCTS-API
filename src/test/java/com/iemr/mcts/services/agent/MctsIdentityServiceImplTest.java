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

import jakarta.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.iemr.mcts.configure.HttpRestModal;
import com.iemr.mcts.data.domain.BeneficiariesDTO;
import com.iemr.mcts.data.mapper.MotherDataMapper;
import com.iemr.mcts.data.mapper.SearchDataMapper;
import com.iemr.mcts.data.supervisor.ChildValidDataHandler;
import com.iemr.mcts.data.supervisor.MctsDataReaderDetail;
import com.iemr.mcts.data.supervisor.MctsOutboundCall;
import com.iemr.mcts.model.beneficiary.BeneficiaryModel;
import com.iemr.mcts.repository.supervisor.MctsDataHandlerRepository;
import com.iemr.mcts.utils.exception.IEMRException;

@RunWith(MockitoJUnitRunner.class)
public class MctsIdentityServiceImplTest {

	@InjectMocks
	MctsIdentityServiceImpl mctsIdentityServiceImpl;
	
	@Mock
	MctsDataHandlerRepository mctsDataHandlerRepository;
	
	@Mock
	MotherDataMapper motherDataMapper;
	
	@Mock
	HttpRestModal httpRestModal;
	
	@Mock
	SearchDataMapper searchDataMapper;
	
	@Test
	public void searchBeneficiaryTest() throws IEMRException
	{
		MctsOutboundCall mctsOutboundCall=new MctsOutboundCall();
		mctsOutboundCall.setOutboundCallType("type");
		mctsOutboundCall.setProviderServiceMapID(new Long("301"));
		HttpServletRequest servletRequest = Mockito.mock(HttpServletRequest.class);
		MctsDataReaderDetail mctsDataReaderDetail=new MctsDataReaderDetail();
		mctsDataReaderDetail.setName("name");
		mctsOutboundCall.setMctsDataReaderDetail(mctsDataReaderDetail);
		BeneficiaryModel beneficiaryModel=new BeneficiaryModel();
		beneficiaryModel.setBeneficiaryRegID(new Long("101"));
		doReturn(beneficiaryModel).when(searchDataMapper).ModalToBeneficiaryModel(Mockito.any());
		doReturn("\"beneficiaryRegID\":101\"").when(httpRestModal).restURLConnect("", "", "");
		/*try {
			mctsIdentityServiceImpl.searchBeneficiary(mctsOutboundCall.toString(), servletRequest);
		} catch (IEMRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	@Test
	public void searchBeneficiaryTest1()
	{
		MctsOutboundCall mctsOutboundCall=new MctsOutboundCall();
		mctsOutboundCall.setOutboundCallType("PNC");
		mctsOutboundCall.setProviderServiceMapID(new Long("301"));
		HttpServletRequest servletRequest = Mockito.mock(HttpServletRequest.class);
		/*try {
			mctsIdentityServiceImpl.searchBeneficiary(mctsOutboundCall.toString(), servletRequest);
		} catch (IEMRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	@Test
	public void createBeneficiariesTest()
	{
		MctsOutboundCall mctsOutboundCall=new MctsOutboundCall();
		mctsOutboundCall.setOutboundCallType("type");
		mctsOutboundCall.setProviderServiceMapID(new Long("301"));
		HttpServletRequest servletRequest = Mockito.mock(HttpServletRequest.class);
		ChildValidDataHandler childValidDataHandler=new ChildValidDataHandler();
		childValidDataHandler.setChild_EID(new Long("101"));
		mctsOutboundCall.setChildValidDataHandler(childValidDataHandler);
		BeneficiariesDTO beneficiariesDTO=new BeneficiariesDTO();
		beneficiariesDTO.setBenId(new Long("901"));
	}
}
