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

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.mcts.data.supervisor.MctsStatewiseFieldsDetail;

@Repository
@RestResource(exported = false)
public interface MctsStatewiseFieldsRepository extends CrudRepository<MctsStatewiseFieldsDetail, Integer> {

	/*@Query("select ms from MctsStatewiseFieldsDetail ms where ms.providerServiceMapID = :providerServiceMapID "
			+ "and ms.fieldFor in (select fileTypeName from FileTypeDetails where fileTypeId = :fileTypeId)")
	public MctsStatewiseFieldsDetail getStatewiseFields(
			@Param("providerServiceMapID") Long providerServiceMapID,
			@Param("fileTypeId") Long fileTypeId);*/
	
	@Query("select ms from MctsStatewiseFieldsDetail ms where ms.providerServiceMapID = :providerServiceMapID and ms.fieldFor = :fieldFor")
	MctsStatewiseFieldsDetail getAllFields(@Param("providerServiceMapID") Long providerServiceMapID, 
				@Param("fieldFor") String fieldFor);
}
