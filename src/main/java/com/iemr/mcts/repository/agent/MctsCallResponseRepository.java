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

import java.util.ArrayList;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.mcts.data.agent.MctsCallResponseDetail;

@Repository
@RestResource(exported = false)
public interface MctsCallResponseRepository extends CrudRepository<MctsCallResponseDetail, Long> {

	@Transactional
	@Modifying
	@Query("update MctsCallResponseDetail res set res.callDetailID = :callDetailID where callDetailID is null "
			+ "and res.motherID = :motherID")
	public int updateMotherCallDetailID(@Param("callDetailID") Long callDetailID, @Param("motherID") Long motherID);

	@Transactional
	@Modifying
	@Query("update MctsCallResponseDetail res set res.callDetailID = :callDetailID where callDetailID is null "
			+ "and res.childID = :childID")
	public int updateChildCallDetailID(@Param("callDetailID") Long callDetailID, @Param("childID") Long childID);

	@Query("select res from MctsCallResponseDetail res join res.questionnaireDetail where res.callDetailID = :callDetailID "
			+ " and res.deleted = false ")
	public ArrayList<MctsCallResponseDetail> getMctsCallResponse(@Param("callDetailID") Long callDetailID);

	@Transactional
	@Modifying
	@Query("update MctsCallResponseDetail res set res.answer = :answer, res.remarks = :remarks, res.deleted = false "
			+ " where res.mctsCallResponseID = :mctsCallResponseID ")
	public int updateMctsCall(@Param("mctsCallResponseID") Long mctsCallResponseID, @Param("answer") String answer,
			@Param("remarks") String remarks);

	@Query("select max(res.mctsCallResponseID) from MctsCallResponseDetail res "
			+ "where res.callDetailID = :callDetailID and res.questionID = :questionID")
	public Long isRecordAvail(@Param("callDetailID") Long callDetailID, @Param("questionID") Integer questionID);

	@Transactional
	@Modifying
	@Query("update MctsCallResponseDetail md set md.deleted = true where md.callDetailID = :callDetailID "
			+ " and md.outboundCallType = :outboundCallType ")
	public int deletePreviousAnswer(@Param("callDetailID") Long callDetailID,
			@Param("outboundCallType") String outboundCallType);

	@Query("select res from MctsCallResponseDetail res join res.questionnaireDetail where res.callDetailID = :callDetailID "
			+ " and res.deleted = false and res.outboundCallType = :outboundCallType ")
	public ArrayList<MctsCallResponseDetail> getMctsCallResponseForAgent(@Param("callDetailID") Long callDetailID,
			@Param("outboundCallType") String outboundCallType);
}
