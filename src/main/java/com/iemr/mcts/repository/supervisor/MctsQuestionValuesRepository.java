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

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.mcts.data.supervisor.MctsQuestionValues;

@Repository
@RestResource(exported = false)
public interface MctsQuestionValuesRepository extends CrudRepository<MctsQuestionValues, Long> {

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update MctsQuestionValues m set m.dropDownOptions = :questionValue, m.deleted = :deletedStatus "
			+ "where m.questionID = :questionID and m.questionValuesID = :questionValueID ")
	public int updateQuestionValues(@Param("questionValue") String questionValue,@Param("deletedStatus") Boolean deletedStatus,
			@Param("questionID") Integer questionID, @Param("questionValueID") Integer questionValueID);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update MctsQuestionValues m set m.deleted = true "
			+ "where m.questionID = :questionID ")
	public int deleteQuestionValues(@Param("questionID") Integer questionID);
}
