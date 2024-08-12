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

import com.iemr.mcts.data.supervisor.ChildValidDataHandler;

@Repository
@RestResource(exported = false)
public interface ChildValidDataRepository extends CrudRepository<ChildValidDataHandler, Long> {

	@Query("select count(*) from ChildValidDataHandler c where c.FileID = :fileID")
	public long validCount(@Param("fileID") Long fileID);
	
	@Query("SELECT count(c)>0 FROM ChildValidDataHandler c WHERE c.MCTSID_no_Child_ID = :MCTSID_no_Child_ID")
	public boolean ifExists(@Param("MCTSID_no_Child_ID") Long MCTSID_no_Child_ID);
	
	@Query("SELECT count(c)>0 FROM ChildValidDataHandler c WHERE c.Mother_ID = :Mother_ID")
	public boolean ifMotherPNCExists(@Param("Mother_ID") Long Mother_ID);

	@Modifying
	@Transactional
	@Query("update ChildValidDataHandler c set c.BeneficiaryRegID = :beneficiaryRegID WHERE c.MCTSID_no_Child_ID = :MCTSID_no_Child_ID")
	public int addBeneficiaryRegID(@Param("beneficiaryRegID")Long beneficiaryRegID, @Param("MCTSID_no_Child_ID")Long mctsid_no_Child_ID);
	
	@Modifying
	@Transactional
	@Query("update ChildValidDataHandler c set c.Child_Name = :Child_Name, c.Phone_No = :Phone_No, c.Gender = :Gender, c.State_ID = :State_ID, "
			+ "c.State_Name = :State_Name, c.District_ID = :District_ID, c.District_Name= :District_Name "
			+ "WHERE c.MCTSID_no_Child_ID = :MCTSID_no_Child_ID ")
	public int updateChildRecord(@Param("Child_Name")String Child_Name, @Param("Phone_No")String Phone_No, 
			@Param("Gender")String Gender, @Param("State_ID") Long State_ID, @Param("State_Name") String State_Name, @Param("District_ID") Long District_ID, 
			@Param("District_Name") String District_Name, @Param("MCTSID_no_Child_ID")Long mctsid_no_Child_ID);
	
}
