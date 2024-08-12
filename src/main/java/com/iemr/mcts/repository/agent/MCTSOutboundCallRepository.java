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
package com.iemr.mcts.repository.agent;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.mcts.data.agent.MctsOutboundCallDetail;
import com.iemr.mcts.data.supervisor.AllocatedCalls;
import com.iemr.mcts.data.supervisor.MctsOutboundCall;

@Repository
@RestResource(exported = false)
public interface MCTSOutboundCallRepository extends CrudRepository<MctsOutboundCall, Long> {
	


	@Query("select mctscalls, u from MctsOutboundCall mctscalls join mctscalls.mctsDataReaderDetail, M_User u "
			+ "where mctscalls.allocatedUserID in (select userID from mctscalls.user u where :firstName is null or u.firstName = :firstName "
			+ " and :lastName is null or u.lastName = :lastName) and :fromDate is null or mctscalls.callDateFrom >= :fromDate"
			+ " and :toDate is null or mctscalls.callDateTo >= :toDate and mctscalls.callStatus!='Completed' "
			+ " and mctscalls.providerServiceMapID = :providerServiceMapID")
	public ArrayList<MctsOutboundCall> getUsersAllocatedCalls(@Param("firstName") String firstName,
			@Param("lastName") String lastName, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate,
			@Param("providerServiceMapID") Long providerServiceMapID);


	@Query(value="call PR_FetchMotherOutboundWorklist(:allocatedUserID)", nativeQuery=true)
	public  ArrayList<MctsOutboundCall> getMotherAllocatedCalls(@Param("allocatedUserID") Integer allocatedUserID);
	

	@Query(value="call PR_FetchChildOutboundWorklist(:allocatedUserID)", nativeQuery=true)
	public ArrayList<MctsOutboundCall> getChildAllocatedCalls(@Param("allocatedUserID") Integer allocatedUserID);

	@Transactional
	@Modifying
	@Query("update MctsOutboundCall call set call.allocationStatus = 'allocated', call.allocatedUserID = :userID where call.obCallID = :obCallID")
	public int allocateCall(@Param("obCallID") Long obCallID, @Param("userID") Integer userID);
	
	@Transactional
	@Modifying
	@Query("update MctsOutboundCall call set call.allocationStatus = 'allocated', call.allocatedUserID = :userID where call.obCallID in :obCallID")
	public int allocateCallList(@Param("obCallID") List<Long> obCallID, @Param("userID") Integer userID);
	
	@Transactional
	@Modifying
	@Query("update MctsOutboundCall call set call.allocationStatus = 'unallocated', call.allocatedUserID = null where call.obCallID in :obCallID")
	public int moveToBucket(@Param("obCallID") List<Long> obCallID);
	
	@Transactional
	@Modifying
	@Query("update MctsOutboundCall call set call.noOfTrials = :noOfTrials, "
			+ " call.callStatus = :callStatus where call.obCallID = :id")
	public int updateCallClosureDetails(@Param("id") Long id, @Param("noOfTrials") Integer noOfTrials,
			@Param("callStatus") String callStatus);
	
	@Transactional
	@Modifying
	@Query("update MctsOutboundCall call set call.allocationStatus = 'allocated', call.allocatedUserID = :userID "
			+ " where call.motherID = :motherID and call.callDateTo>current_date() and call.obCallID!=:id")
	public int stickyMotherAgentAllocation(@Param("id") Long id, @Param("motherID") Long motherID, 
									 @Param("userID") Integer userID);
	
	@Transactional
	@Modifying
	@Query("update MctsOutboundCall call set call.allocationStatus = 'allocated', call.allocatedUserID = :userID "
			+ " where call.childID = :childID and call.callDateTo>current_date() and call.obCallID!=:id")
	public int stickyChildAgentAllocation(@Param("id") Long id, @Param("childID") Long motherID, 
									 @Param("userID") Integer userID);
	
	@Transactional
	@Modifying
	@Query("update MctsOutboundCall call set call.prefferedCallDate = :prefferedCallDate where call.obCallID = :id")
	public int updatePrefferedCallDate(@Param("id") Long id, @Param("prefferedCallDate") Date prefferedCallDate);
	
	@Transactional
	@Modifying
	@Query("update MctsOutboundCall call set call.beneficiaryRegID = :beneficiaryRegID where call.obCallID = :id")
	public int addBeneficiaryRegID(@Param("id") Long id, @Param("beneficiaryRegID") Long beneficiaryRegID);
	
	@Transactional
	@Modifying
	@Query("update MctsOutboundCall call set call.callStatus = 'Completed' "
			+ "where call.motherID = :motherID or call.childID = :childID "
			+ "and call.obCallID!=:id")
	public int noFurtherCallRequired(@Param("id") Long id, @Param("motherID") Long motherID, 
			@Param("childID") Long childID);

	@Query("select call from MctsOutboundCall call, MctsOutboundCallDetail md "
			+ "where call.obCallID = md.obCallID and md.callId = :clientID group by call.obCallID")
	public MctsOutboundCall findOnClientID(@Param("clientID") String clientID);
	
	public MctsOutboundCall findByMotherIDAndOutboundCallType(Long motherID, 
			String outboundCallType);
	
	public MctsOutboundCall findByChildIDAndOutboundCallType(Long childID, 
			String outboundCallType);
	
	@Transactional
	@Modifying
	@Query("update MctsOutboundCall call set call.callDateFrom = :callDateFrom, call.callDateTo = :callDateTo "
			+ "where call.childID = :childID and call.outboundCallType != :outboundCallType") // and call.callDateFrom>(select callDateFrom from MctsOutboundCall "
			//+ "where outboundCallType = :outboundCallType and childID = :childID)")
	public int updateChildOutboundCallDates(@Param("callDateFrom") Date callDateFrom, @Param("callDateTo") Date callDateTo,
			@Param("outboundCallType") String outboundCallType, @Param("childID") Long childID);
	
	@Transactional
	@Modifying
	@Query("update MctsOutboundCall call set call.callDateFrom = :callDateFrom, call.callDateTo = :callDateTo "
			+ "where call.motherID = :motherID and call.outboundCallType = :outboundCallType") // and call.callDateFrom>(select callDateFrom from MctsOutboundCall " 
			//+ "where outboundCallType = :outboundCallType and motherID = :motherID)")
	public int updateMotherOutboundCallDates(@Param("callDateFrom") Date callDateFrom, @Param("callDateTo") Date callDateTo,
			@Param("outboundCallType") String outboundCallType, @Param("motherID") Long motherID);
	
	@Transactional
	@Modifying
	@Query("update MctsOutboundCall call set call.callDateFrom = :callDateFrom, call.callDateTo = :callDateTo, call.callStatus = 'NA' "
			+ "where call.motherID = :motherID and call.outboundCallType = :outboundCallType")
	public int updateMotherOutboundCallDateAndStatus(@Param("callDateFrom") Date callDateFrom, @Param("callDateTo") Date callDateTo,
			@Param("outboundCallType") String outboundCallType, @Param("motherID") Long motherID);
	
	@Transactional
	@Modifying
	@Query("update MctsOutboundCall call set call.callDateFrom = :callDateFrom, call.callDateTo = :callDateTo, call.callStatus = 'NA' "
			+ "where call.childID = :childID and call.outboundCallType != :outboundCallType")
	public int updateChildOutboundCallDateAndStatus(@Param("callDateFrom") Date callDateFrom, @Param("callDateTo") Date callDateTo,
			@Param("outboundCallType") String outboundCallType, @Param("childID") Long childID);
	
	@Query("select call from MctsOutboundCall call join call.mctsDataReaderDetail where call.obCallID = :obCallID")
	public MctsOutboundCall getUpdatedMotherRecord(@Param("obCallID") Long obCallID);
	
	@Query("select call from MctsOutboundCall call join call.childValidDataHandler where call.obCallID = :obCallID")
	public MctsOutboundCall getUpdatedChildRecord(@Param("obCallID") Long obCallID);
	
	@Transactional
	@Modifying
	@Query("update MctsOutboundCall call set call.beneficiaryRegID = :beneficiaryRegID where call.motherID = :motherID")
	public int addBeneficiaryRegIDMother(@Param("motherID") Long motherID, @Param("beneficiaryRegID") Long beneficiaryRegID);
	
	@Transactional
	@Modifying
	@Query("update MctsOutboundCall call set call.beneficiaryRegID = :beneficiaryRegID where call.childID = :childID")
	public int addBeneficiaryRegIDChild(@Param("childID") Long childID, @Param("beneficiaryRegID") Long beneficiaryRegID);
	
	@Query("select mctscalls from MctsOutboundCall mctscalls "
			+ "where mctscalls.callDateFrom =:futureDate and mctscalls.beneficiaryRegID !=null and mctscalls.allocationStatus='allocated' ")
	public ArrayList<MctsOutboundCall> getFutureCallList(@Param("futureDate") Date futureDate);
	

	
	@Query(value="call PR_FetchMotherOutboundWorklist(:allocatedUserID)", nativeQuery=true)
	public  List<Objects[]> getAgentAllocatedMotherList(@Param("allocatedUserID") Integer allocatedUserID);

	@Query(value="call PR_FetchChildOutboundWorklist(:allocatedUserID)", nativeQuery=true)
	public List<Objects[]> getAgentAllocatedChildList(@Param("allocatedUserID") Integer allocatedUserID);
	
	@Query(value="call PR_FetchMotherChildOutboundWorklist(:allocatedUserID)", nativeQuery=true)
	public  List<Objects[]> getAgentAllocatedMotherChildList(@Param("allocatedUserID") Integer allocatedUserID);

	@Query("select new MctsOutboundCallDetail(call.beneficiaryRegID, call.obCallID, call.createdDate, call.remark) from MctsOutboundCallDetail call "
			+ "where call.beneficiaryRegID in :regIDList order by call.callDetailID desc ")
	public ArrayList<MctsOutboundCallDetail> getLastCallDetailByRegID(@Param("regIDList") List<Long> regIDList);
	
	@Query("select new MctsOutboundCallDetail(call.beneficiaryRegID, call.obCallID, call.createdDate, call.remark) from MctsOutboundCallDetail call "
			+ "where call.obCallID in :obIDList order by call.callDetailID desc ")
	public ArrayList<MctsOutboundCallDetail> getLastCallDetailByOBCallID(@Param("obIDList") List<Long> obIDList);
	

	@Query(value="call PR_FetchMCTS_1(:providerServiceMapID)", nativeQuery=true)
	public List<Objects[]> getMotherUnallocatedCalls(@Param("providerServiceMapID") Long providerServiceMapID);

	@Query(value="call PR_FetchMCTS_3(:providerServiceMapID)", nativeQuery=true)
	public List<Objects[]> getChildUnallocatedCalls(@Param("providerServiceMapID") Long providerServiceMapID);

	@Query(value="call PR_FetchMCTS_2(:providerServiceMapID, :fromDate, :toDate)", nativeQuery=true)
	public List<Objects[]> getMotherUnAllocatedCalls(@Param("fromDate") Date fromDate,
		@Param("toDate") Date toDate, @Param("providerServiceMapID") Long providerServiceMapID);
	
	@Query(value="call PR_FetchMCTS_4(:providerServiceMapID, :fromDate, :toDate)", nativeQuery=true)
	public List<Objects[]> getChildUnAllocatedCalls(@Param("fromDate") Date fromDate,
		@Param("toDate") Date toDate, @Param("providerServiceMapID") Long providerServiceMapID);
	
	@Query("select new AllocatedCalls(mctscalls.obCallID,mctscalls.allocatedUserID,mctscalls.callDateFrom) from AllocatedCalls mctscalls join mctscalls.mctsDataReaderDetail "
			+ "where mctscalls.allocatedUserID = :userID "
			+ " and (:fromDate between mctscalls.callDateFrom and mctscalls.callDateTo and :toDate between mctscalls.callDateFrom and mctscalls.callDateTo)"
			+ " and mctscalls.callStatus!='Completed'")
	public ArrayList<AllocatedCalls> getUsersMotherAllocatedCalls(@Param("userID") Integer userID,
			@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
	
	@Query("select new AllocatedCalls(mctscalls.obCallID,mctscalls.allocatedUserID,mctscalls.callDateFrom) from AllocatedCalls mctscalls join mctscalls.mctsDataReaderDetail "
			+ "where mctscalls.callStatus!='Completed' and  mctscalls.allocatedUserID = :userID ")
	public ArrayList<AllocatedCalls> getUsersMotherAllocatedCalls(@Param("userID") Integer userID);
	
	@Query("select new AllocatedCalls(mctscalls.obCallID,mctscalls.allocatedUserID,mctscalls.callDateFrom) from AllocatedCalls mctscalls join mctscalls.childValidDataHandler "
			+ "where mctscalls.allocatedUserID = :userID "
			+ " and (:fromDate between mctscalls.callDateFrom and mctscalls.callDateTo and :toDate between mctscalls.callDateFrom and mctscalls.callDateTo)"
			+ " and mctscalls.callStatus!='Completed'")
	public ArrayList<AllocatedCalls> getUsersChildAllocatedCalls(@Param("userID") Integer userID,
			@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
	
	@Query("select new AllocatedCalls(mctscalls.obCallID,mctscalls.allocatedUserID,mctscalls.callDateFrom) from AllocatedCalls mctscalls join mctscalls.childValidDataHandler "
			+ "where mctscalls.callStatus!='Completed' and  mctscalls.allocatedUserID = :userID ")
	public ArrayList<AllocatedCalls> getUsersChildAllocatedCalls(@Param("userID") Integer userID);
}
