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

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iemr.mcts.data.agent.MctsOutboundCallDetail;
import com.iemr.mcts.data.supervisor.CallType;

@Repository
@RestResource(exported = false)
public interface MctsOutboundCallDetailRepository extends CrudRepository<MctsOutboundCallDetail, Long> {

	@Query("select cd from MctsOutboundCallDetail cd join cd.callType where cd.obCallID in "
			+ " (select c.obCallID from MctsOutboundCall c where c.motherID = :motherID) order by cd.createdDate desc")
	public ArrayList<MctsOutboundCallDetail> getMotherCallHistory(@Param("motherID") Long motherID);
	
	@Query("select cd from MctsOutboundCallDetail cd join cd.callType where cd.obCallID in "
			+ " (select c.obCallID from MctsOutboundCall c where c.childID = :childID) order by cd.createdDate desc")
	public ArrayList<MctsOutboundCallDetail> getChildCallHistory(@Param("childID") Long childID);
	
	@Query("select cd from MctsOutboundCallDetail cd where cd.callId = :callId "
			+ "and cd.allocatedUserID = :allocatedUserID")
	public MctsOutboundCallDetail isAvailable(@Param("callId") String callId, @Param("allocatedUserID") Integer allocatedUserID);
	
	@Transactional
	@Modifying
	@Query("update MctsOutboundCallDetail cd set cd.callTypeID = :callTypeID, cd.lastModDate = :lastModDate, "
			+ "cd.remark = :remark,cd.beneficiaryRegID = :beneficiaryRegID, cd.callEndTime = :callEndTime, cd.callDuration = :callDuration ,cd.isOutbound = :isOutbound where cd.callDetailID = :callDetailID")
	public int updateCallHistory(@Param("callTypeID") Integer callTypeID, @Param("lastModDate") Timestamp lastModDate,
			@Param("remark") String remark, @Param("callDetailID") Long callDetailID, @Param("beneficiaryRegID") Long beneficiaryRegID,
			@Param("callEndTime") Timestamp callEndTime, @Param("callDuration") String callDuration,@Param("isOutbound") Boolean isOutbound);
	
	@Transactional
	@Modifying
	@Query("update MctsOutboundCallDetail cd set cd.smsAdvice = :smsAdvice, cd.smsPh = :smsPh "
			+ " where cd.callDetailID = :callDetailID")
	public int updateSmsAdvice(@Param("smsAdvice") String smsAdvice, @Param("smsPh") String smsPh, 
			@Param("callDetailID") Long callDetailID);
	
	@Query("select cd from MctsOutboundCallDetail cd join cd.callType where cd.callDetailID = :callDetailID")
	public MctsOutboundCallDetail findByCallDetailID(@Param("callDetailID") Long callDetailID);
	
	@Query("select cd from MctsOutboundCallDetail cd where cd.callDetailID = :callDetailID")
	public MctsOutboundCallDetail checkAdviseAvailable(@Param("callDetailID") Long callDetailID);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE db_iemr.t_bencall set ChangeLog = concat(COALESCE(ChangeLog, ''), :changeLog, ' ') "
			+ "where BenCallID = :callDetailID ", nativeQuery=true)
	public int appendChangeLog(@Param("changeLog") String changeLog, @Param("callDetailID") Long callDetailID);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE db_iemr.t_mctsoutboundcalldeatil set SMS_Advice = concat(COALESCE(SMS_Advice, ''), ', ', :smsAdvice, ' ') "
			+ "where CallDetailID = :callDetailID ", nativeQuery=true)
	public int appendSmsAdvise(@Param("smsAdvice") String smsAdvice, @Param("callDetailID") Long callDetailID);
	
	@Query("select cd from MctsOutboundCallDetail cd where cd.obCallID in "
			+ " (select c.obCallID from MctsOutboundCall c where c.motherID = :motherID) order by cd.createdDate")
	public ArrayList<MctsOutboundCallDetail> getMotherChangeLogs(@Param("motherID") Long motherID);
	
	@Query("select cd from MctsOutboundCallDetail cd where cd.obCallID in "
			+ " (select c.obCallID from MctsOutboundCall c where c.childID = :childID) order by cd.createdDate")
	public ArrayList<MctsOutboundCallDetail> getChildChangeLogs(@Param("childID") Long childID);

	
	@Transactional
	@Modifying
	@Query("update MctsOutboundCallDetail cd set cd.isVerified = :isVerified where cd.callDetailID = :callDetailID")
	public int updateIsVerified(@Param("isVerified") Boolean isVerified, @Param("callDetailID") Long callDetailID);
	
	@Query("select cd.callTime from MctsOutboundCallDetail cd where cd.callDetailID = :callDetailID")
	public Timestamp getCallStartTime(@Param("callDetailID") Long callDetailID);

	@Query(value="select CallTypeID from db_iemr.m_callType where CallType='default null callType' order by CreatedDate DESC LIMIT 1",nativeQuery=true)
	public Integer getCallTypeId();

}
