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
package com.iemr.mcts.secondary.repository.report;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.mcts.secondary.data.report.SecondaryCallReport;
@Repository
@RestResource(exported = false)
public interface MCTSSecondaryReportRepo extends CrudRepository<SecondaryCallReport, Long> {

	@Query(value="call Pr_MCTSDataReport_Mother(:startDateTime,:endDateTime,:providerServiceMapID)", nativeQuery=true)
	public List<Objects[]> getDataReportMother(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime,
			@Param("providerServiceMapID") Long providerServiceMapID);
	
	@Query(value="call Pr_MCTSDataReport_Child(:startDateTime,:endDateTime,:providerServiceMapID)", nativeQuery=true)
	public List<Objects[]> getDataReportChild(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime,
			@Param("providerServiceMapID") Long providerServiceMapID);
	
	@Query(value="call Pr_MCTSCallDetailsReport(:startDateTime,:endDateTime,:agentId,:providerServiceMapID)", nativeQuery=true)
	public List<Objects[]> getCallDetailReport(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime, @Param("agentId") Integer agentId,
			@Param("providerServiceMapID") Long providerServiceMapID);
	
	@Query(value="call Pr_MCTSCallDetailsUniqueReport(:startDateTime,:endDateTime,:agentId,:providerServiceMapID)", nativeQuery=true)
	public List<Objects[]> getCallDetailReportUnique(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime, @Param("agentId") Integer agentId,
			@Param("providerServiceMapID") Long providerServiceMapID);
	
	@Query(value="call Pr_MCTSComplaintReport_Mother(:startDateTime,:endDateTime,:agentID,:providerServiceMapID)", nativeQuery=true)
	public List<Objects[]> getComplaintReportMother(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime, @Param("agentID") Integer agentID,
			@Param("providerServiceMapID") Long providerServiceMapID);
	
	@Query(value="call Pr_MCTSComplaintReport_Child(:startDateTime,:endDateTime,:agentID,:providerServiceMapID)", nativeQuery=true)
	public List<Objects[]> getComplaintReportChild(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime, @Param("agentID") Integer agentID,
			@Param("providerServiceMapID") Long providerServiceMapID);
	
	@Query(value="call Pr_MCTSInvalidRecords_Mother(:startDateTime,:endDateTime,:providerServiceMapID)", nativeQuery=true)
	public List<Objects[]> getInvalidRecordReportMother(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime,
			@Param("providerServiceMapID") Long providerServiceMapID);
	
	@Query(value="call Pr_MCTSInvalidRecords_Child(:startDateTime,:endDateTime,:providerServiceMapID)", nativeQuery=true)
	public List<Objects[]> getInvalidRecordReportChild(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime,
			@Param("providerServiceMapID") Long providerServiceMapID);
	
	@Query(value="call Pr_MCTSCallAnsweredReport_Pivot(:startDateTime,:endDateTime,:effectiveFrom,:outboundCallType,:verifiedData,:agentId,:providerServiceMapID)", nativeQuery=true)
	public List<Objects[]> getCallAnsweredReport(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime,  @Param("effectiveFrom") Timestamp effectiveFrom, 
			@Param("outboundCallType") String outboundCallType, @Param("verifiedData") String verifiedData, @Param("agentId") Integer agentId,
			@Param("providerServiceMapID") Long providerServiceMapID);
	
	@Query(value="call Pr_MCTSCallNotAnsweredReport(:startDateTime,:endDateTime,:agentId,:providerServiceMapID)", nativeQuery=true)
	public List<Objects[]> getCallNotAnsweredReport(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime,  @Param("agentId") Integer agentId,
			@Param("providerServiceMapID") Long providerServiceMapID);
	
	@Query(value="call Pr_MCTSHighRiskReport(:startDateTime,:endDateTime,:agentID,:providerServiceMapID)", nativeQuery=true)
	public List<Objects[]> getHighRiskReport(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime, @Param("agentID") Integer agentID,
			@Param("providerServiceMapID") Long providerServiceMapID);
	
	@Query(value="call Pr_MCTSDailyReport(:startDateTime,:endDateTime,:isMother,:agentID,:providerServiceMapID)", nativeQuery=true)
	public List<Objects[]> getDailyReport(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime,@Param("isMother") String isMother, @Param("agentID") Integer agentID,
			@Param("providerServiceMapID") Long providerServiceMapID);
	
	@Query(value="call Pr_MCTSNHMReport(:startDateTime,:endDateTime,:agentID,:providerServiceMapID)", nativeQuery=true)
	public List<Objects[]> getNHMReport(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime, @Param("agentID") Integer agentID,
			@Param("providerServiceMapID") Long providerServiceMapID);
	
	@Query(value="call Pr_MCTSCongenitalAnomalies(:startDateTime,:endDateTime,:agentID,:providerServiceMapID)", nativeQuery=true)
	public List<Objects[]> getCongenitalAnomaliesReport(@Param("startDateTime") Timestamp startDateTime,
			@Param("endDateTime") Timestamp endDateTime, @Param("agentID") Integer agentID,
			@Param("providerServiceMapID") Long providerServiceMapID);
}
