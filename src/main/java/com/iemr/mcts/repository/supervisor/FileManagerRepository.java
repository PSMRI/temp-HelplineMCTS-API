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

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.mcts.data.supervisor.FileManager;


@Repository
@RestResource(exported = false)
public interface FileManagerRepository extends CrudRepository<FileManager, Long>{
	
	/**
	 * query method to check file checksum
	 * @param checkSum
	 * @return
	 */
	@Query("select count(f)>0 from FileManager f where f.md5CheckSum = :checkSum")
	public boolean isUploaded(@Param("checkSum") String checkSum);

	

	/**
	 * query method to get providerServiceMapID
	 * @param fileID
	 * @return
	 */
	@Query("select f.providerServiceMapID from FileManager f where f.fileID = :fileID and f.deleted = false ")
	public Long getProviderServiceMapID(@Param("fileID") Long fileID);
	
	/**
	 * query method to get stateID
	 * @param providerServiceMapID
	 * @return
	 */
	@Query("select provider.stateID from ProviderServiceMapDetail provider where provider.providerServiceMapID = :providerServiceMapID and provider.deleted = false ")
	public Integer getStateID(@Param("providerServiceMapID") Long providerServiceMapID);
	
	/**
	 * query method to get districtID
	 * @param stateID
	 * @return
	 */
	@Query("select district.districtID from District district where district.stateID = :stateID and district.districtName = :districtName and district.deleted = false ")
	public Integer getDistrictID(@Param("stateID") Integer stateID, @Param("districtName") String districtName);
	
	/**
	 * query method to get blockID
	 * @param districtID
	 * @return
	 */
	@Query("select districtBlock.blockID from DistrictBlock districtBlock where districtBlock.districtID = :districtID and districtBlock.blockName = :blockName and districtBlock.deleted = false ")
	public Integer getBlockID(@Param("districtID") Integer districtID, @Param("blockName") String blockName);
	
	/**
	 * query method to get VillageID
	 * @param blockID
	 * @return
	 */
	@Query("select village.districtBranchID from DistrictBranchMapping village where village.blockID = :blockID and village.villageName = :villageName and village.deleted = false ")
	public Integer getVillageID(@Param("blockID") Integer blockID, @Param("villageName") String villageName);



	public List<FileManager> findTop1ByProviderServiceMapIDOrderByFileIDDesc(Long long1);

}
