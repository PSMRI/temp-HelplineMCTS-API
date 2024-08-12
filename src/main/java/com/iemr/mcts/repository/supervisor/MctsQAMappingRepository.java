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

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.mcts.data.supervisor.MctsQAMappingDetail;

@Repository
@RestResource(exported = false)
public interface MctsQAMappingRepository extends CrudRepository<MctsQAMappingDetail, Long> {

	@Query("select mcts from MctsQAMappingDetail mcts join mcts.questionnaireDetail"
			+ " where mcts.providerServiceMapID = :providerServiceMapID and mcts.deleted=false "
			+ " and mcts.effectiveFrom = :effectiveFrom and mcts.interaction is null")
	public ArrayList<MctsQAMappingDetail> getQuestionsList(@Param("providerServiceMapID") Long providerServiceMapID,
			@Param("effectiveFrom") Date effectiveFrom);

	@Query("select distinct mcts from MctsQAMappingDetail mcts inner join mcts.questionnaireDetail mctsq "
			+ " left join mctsq.mctsQuestionValues mctsv "
			+ " where mcts.outboundCallType = :outboundCallType and mcts.providerServiceMapID = :providerServiceMapID "
			+ " and mcts.deleted=false and mcts.effectiveFrom = :effectiveFrom and mcts.interaction is null "
			+ " order by mctsq.questionRank asc")
	public ArrayList<MctsQAMappingDetail> getQuestionsTypeList(@Param("outboundCallType") String outboundCallType,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("effectiveFrom") Date effectiveFrom);

	@Query("select mcts from MctsQAMappingDetail mcts "
			+ " where mcts.outboundCallType = :outboundCallType and mcts.providerServiceMapID = :providerServiceMapID "
			+ " and mcts.deleted=false and mcts.effectiveFrom = :effectiveFrom")
	public ArrayList<MctsQAMappingDetail> getOutboundCallInteractions(
			@Param("outboundCallType") String outboundCallType,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("effectiveFrom") Date effectiveFrom);

	@Query("select mcts from MctsQAMappingDetail mcts where mcts.questionID = :questionID "
			+ "and mcts.deleted=false and interaction is not null")
	ArrayList<MctsQAMappingDetail> findByQuestionID(@Param("questionID") Integer questionID);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update MctsQAMappingDetail mq set mq.deleted = true where mq.mctsQAMapID = :mctsQAMapID")
	public int deleteInteraction(@Param("mctsQAMapID") Long mctsQAMapID);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update MctsQAMappingDetail mq set mq.deleted = true where mq.questionID = :questionID")
	public int markDelete(@Param("questionID") Integer questionID);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update MctsQAMappingDetail mq set mq.variableName = :variableName, "
			+ "mq.variableDataType = :variableDataType where mq.mctsQAMapID = :mctsQAMapID")
	public int editVariableName(@Param("variableName") String variableName,
			@Param("variableDataType") String variableDataType, @Param("mctsQAMapID") Long mctsQAMapID);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update MctsQAMappingDetail mq set mq.interaction = :interaction, mq.variableName = :variableName, "
			+ "mq.variableDataType = :variableDataType where mq.mctsQAMapID = :mctsQAMapID")
	public int updateInteraction(@Param("mctsQAMapID") Long mctsQAMapID, @Param("interaction") String interaction,
			@Param("variableName") String variableName, @Param("variableDataType") String variableDataType);

	
//	  getting parent question list for agent screen
	  
	  @Query("select distinct mcts from MctsQAMappingDetail mcts left join mcts.questionnaireDetail mctsq left join mctsq.mctsQuestionValues mctsv"
	  +
	  " where mcts.outboundCallType = :outboundCallType and mcts.providerServiceMapID = :providerServiceMapID "
	  +
	  " and mcts.deleted=false and mcts.effectiveFrom = :effectiveFrom and mcts.interaction is null "
	  + " and mctsq.isChild is false and mctsq.parentQuestionID is null order by mctsq.questionRank asc "
	  ) public ArrayList<MctsQAMappingDetail>
	  getParentQuestionsTypeList(@Param("outboundCallType") String
	  outboundCallType,	  
	  @Param("providerServiceMapID") Long
	  providerServiceMapID, @Param("effectiveFrom") Date effectiveFrom);
	  
	  
	  //getting child question list for agent screen
	  
	  @Query("select distinct mcts from MctsQAMappingDetail mcts left join mcts.questionnaireDetail mctsq left join mctsq.mctsQuestionValues mctsv"
	  +
	  " where mcts.outboundCallType = :outboundCallType and mcts.providerServiceMapID = :providerServiceMapID "
	  +
	  " and mcts.deleted=false and mcts.effectiveFrom = :effectiveFrom and mcts.interaction is null "
	  + " and mctsq.parentQuestionID is not null and mctsq.isChild is true ") 
	  public ArrayList<MctsQAMappingDetail> getChildQuestionsTypeList(@Param("outboundCallType") String outboundCallType,	  
	   @Param("providerServiceMapID") Long providerServiceMapID,
	   @Param("effectiveFrom") Date effectiveFrom);
	 
	/*
	 * checking the rank of the question.
	 */
	@Query("select mcts from MctsQAMappingDetail mcts left join mcts.questionnaireDetail mctsq "
			+ "where mcts.outboundCallType = :outboundCallType and mcts.providerServiceMapID = :providerServiceMapID"
			+ " and mcts.deleted=false and mcts.effectiveFrom = :effectiveFrom and mctsq.questionRank = :questionRank")
	public MctsQAMappingDetail checkRank(@Param("questionRank") Integer questionRank,
			@Param("outboundCallType") String outboundCallType,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("effectiveFrom") Date effectiveFrom);

//	
//	"update MctsQAMappingDetail mq inner join mq.questionnaireDetail mctsq set mctsq.questionRank = :questionRank+1 "
//	+ "where mctsq.questionRank >= questionRank and mq.outboundCallType = :outboundCallType and mq.providerServiceMapID = :providerServiceMapID"
//	+ "and mq.deleted=false and mq.effectiveFrom = :effectiveFrom"

	/*
	 * update the existing rank to +1.
	 */
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update m_mctsqamapping a inner join m_questionnaire b on a.QuestionID = b.QuestionID set b.QuestionRank = b.QuestionRank+1 "
			+ "where b.QuestionRank >= :questionRank and a.ProviderServiceMapID = :providerServiceMapID and a.OutboundCallType = :outboundCallType and a.EffectiveFrom = :effectiveFrom", nativeQuery = true)
	public int updateRank(@Param("questionRank") Integer questionRank,
			@Param("outboundCallType") String outboundCallType,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("effectiveFrom") Date effectiveFrom);

	/*
	 * degrade the rank of next questions.
	 */
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update m_mctsqamapping a inner join m_questionnaire b on a.QuestionID = b.QuestionID set b.QuestionRank = b.QuestionRank-1 "
			+ "where b.QuestionRank >= :questionRank and a.ProviderServiceMapID = :providerServiceMapID and a.OutboundCallType = :outboundCallType and a.EffectiveFrom = :effectiveFrom", nativeQuery = true)
	public int degradeRank(@Param("questionRank") Integer questionRank,
			@Param("outboundCallType") String outboundCallType,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("effectiveFrom") Date effectiveFrom);
	
	/*
	 * get questions for report
	 * benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate
	 */
	@Query("select distinct mcts from MctsQAMappingDetail mcts inner join mcts.questionnaireDetail mctsq "
			+ " where mcts.outboundCallType = :outboundCallType and mcts.providerServiceMapID = :providerServiceMapID "
			+ " and mcts.deleted=false and mcts.effectiveFrom = :effectiveFrom "
			+ " and mcts.interaction is null order by mctsq.questionRank asc")
	public ArrayList<MctsQAMappingDetail> getQuestionsForReport(@Param("outboundCallType") String outboundCallType,
			@Param("providerServiceMapID") Long providerServiceMapID,@Param("effectiveFrom") Timestamp effectiveFrom);
}
