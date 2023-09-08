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
package com.iemr.mcts.repository.supervisor;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.mcts.data.supervisor.CallConfigurationDetail;

@Repository
@RestResource(exported = false)
public interface CallConfigurationRepository extends CrudRepository<CallConfigurationDetail, Integer> {

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE CallConfigurationDetail c SET c.fromTerm = :fromTerm, c.toTerm = :toTerm WHERE c.mctsCallConfigID = :id")
	public void setConfigTerms(@Param("fromTerm") String fromTerm,
							   @Param("toTerm") String toTerm,
							   @Param("id") Integer id);
	
	@Query("select count(c) from CallConfigurationDetail c where c.effectiveFrom in (select max(effectiveFrom) from CallConfigurationDetail "
            + " where providerServiceMapID = :providerServiceMapID and deleted = false and effectiveFrom <= current_date()) "
            + "and providerServiceMapID = :providerServiceMapID and c.deleted = false ")
	public int getConfigCount(@Param("providerServiceMapID") Long providerServiceMapID);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE CallConfigurationDetail c SET c.fromTerm = :fromTerm, c.toTerm = :toTerm, c.effectiveFrom = :effectiveFrom, c.effectiveUpto = :effectiveUpto, "
			+ "c.noOfAttempts = :noOfAttempts, c.nextAttemptPeriod = :nextAttemptPeriod "
			+ "WHERE :effectiveFrom is not null and :effectiveUpto is not null "
			+ "and :fromTerm is not null and :toTerm is not null and :noOfAttempts is not null and :nextAttemptPeriod is not null "
			+ "and c.mctsCallConfigID = :id")
	public void updateConfiguration(@Param("fromTerm") String fromTerm,
									@Param("toTerm") String toTerm,
									@Param("effectiveFrom") Date effectiveFrom,
								    @Param("effectiveUpto") Date effectiveUpto,
								    @Param("noOfAttempts") Integer noOfAttempts,
								    @Param("nextAttemptPeriod") String nextAttemptPeriod,
								    @Param("id") Integer id);
	
	@Query("select c from CallConfigurationDetail c "
			+ "where c.effectiveFrom in (select max(effectiveFrom) from CallConfigurationDetail "
            + "where providerServiceMapID = :providerServiceMapID and deleted = false and effectiveFrom <= current_date()) "
            + "and providerServiceMapID = :providerServiceMapID and c.deleted = false ")
	public ArrayList<CallConfigurationDetail> getConfigTerms(@Param("providerServiceMapID") Long providerServiceMapID);
	
	@Query("select c from CallConfigurationDetail c where c.providerServiceMapID = :providerServiceMapID and c.deleted = false ")
	public ArrayList<CallConfigurationDetail> getConfigurations(@Param("providerServiceMapID") Long providerServiceMapID);
	
	@Query("select new CallConfigurationDetail(c.noOfAttempts, c.nextAttemptPeriod) from CallConfigurationDetail c "
			+ "where c.effectiveFrom in (select max(effectiveFrom) from CallConfigurationDetail "
            + "where providerServiceMapID = :providerServiceMapID and deleted = false and effectiveFrom <= current_date()) "
            + "and providerServiceMapID = :providerServiceMapID and c.deleted = false ")
	public  ArrayList<CallConfigurationDetail> getCallConfiguration(@Param("providerServiceMapID") Long providerServiceMapID);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE CallConfigurationDetail c SET c.deleted = true  WHERE c.mctsCallConfigID = :mctsCallConfigID")
	public void deleteConfiguration(@Param("mctsCallConfigID") Integer mctsCallConfigID);
	
	@Query("select c from CallConfigurationDetail c where c.providerServiceMapID = :providerServiceMapID and c.effectiveFrom <= :effectiveUpto "
			+ "and c.createdDate > :createdDate and c.deleted = false ")
	public ArrayList<CallConfigurationDetail> checkConfigurations(@Param("providerServiceMapID") Long providerServiceMapID, @Param("effectiveUpto") Date effectiveUpto,
			@Param("createdDate") Timestamp createdDate);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE CallConfigurationDetail c SET c.fromTerm = :fromTerm, c.toTerm = :toTerm, c.effectiveFrom = :effectiveFrom, c.effectiveUpto = :effectiveUpto, "
			+ "c.noOfAttempts = :noOfAttempts, c.nextAttemptPeriod = :nextAttemptPeriod, c.displayOBCallType = :displayOBCallType "
			+ "WHERE :effectiveFrom is not null and :effectiveUpto is not null "
			+ "and :fromTerm is not null and :toTerm is not null and :noOfAttempts is not null and :nextAttemptPeriod is not null "
			+ "and c.mctsCallConfigID = :id")
	public void updateConfigDisplayCallType(@Param("fromTerm") String fromTerm,
									@Param("toTerm") String toTerm,
									@Param("effectiveFrom") Date effectiveFrom,
								    @Param("effectiveUpto") Date effectiveUpto,
								    @Param("noOfAttempts") Integer noOfAttempts,
								    @Param("nextAttemptPeriod") String nextAttemptPeriod,
								    @Param("id") Integer id,
								    @Param("displayOBCallType") String displayOBCallType);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE MctsQAMappingDetail mcts SET mcts.deleted = true where mcts.providerServiceMapID = :providerServiceMapID and mcts.effectiveFrom = :effectiveFrom ")
	public void deleteQuestionsList( @Param("providerServiceMapID") Long providerServiceMapID, @Param("effectiveFrom") Date effectiveFrom);
	
	@Query("select c from CallConfigurationDetail c where c.providerServiceMapID = :providerServiceMapID "
			+ "and c.effectiveFrom <= :endDate and c.deleted = false ")
	public ArrayList<CallConfigurationDetail> getConfigurationsForReport(@Param("providerServiceMapID") Long providerServiceMapID,
			@Param("endDate") Timestamp endDate);
	
	
	/*
	 * mother data should only have ANC calls;
	 */
	@Query("select c from CallConfigurationDetail c "
			+ "where c.effectiveFrom in (select max(effectiveFrom) from CallConfigurationDetail "
            + "where providerServiceMapID = :providerServiceMapID and deleted = false and effectiveFrom <= current_date()) "
            + "and providerServiceMapID = :providerServiceMapID and c.deleted = false and c.outboundCallType like 'ANC%'")
	public ArrayList<CallConfigurationDetail> getMotherConfigTerms(@Param("providerServiceMapID") Long providerServiceMapID);
	
	/*
	 * Child data should only have PNC calls;
	 */
	@Query("select c from CallConfigurationDetail c "
			+ "where c.effectiveFrom in (select max(effectiveFrom) from CallConfigurationDetail "
            + "where providerServiceMapID = :providerServiceMapID and deleted = false and effectiveFrom <= current_date()) "
            + "and providerServiceMapID = :providerServiceMapID and c.deleted = false and c.outboundCallType like 'PNC%'")
	public ArrayList<CallConfigurationDetail> getChildConfigTerms(@Param("providerServiceMapID") Long providerServiceMapID);
}
