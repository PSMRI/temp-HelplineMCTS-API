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
package com.iemr.mcts.repository.report;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.mcts.data.report.BenCallDetail;
import com.iemr.mcts.data.report.Districts;

@Repository
@RestResource(exported = false)
public interface DailyReportRepository extends CrudRepository<BenCallDetail, Integer> {

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = true ")
	Integer getTotalCallMother(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.obdetail obdet " + "inner join obdet.motherDetail mother "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = true and mother.isSelfNo = true "
			+ "and benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and ben.district = :district")
	Integer getTotalSelfNoCallMother(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.obdetail obdet " + "inner join obdet.motherDetail mother "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = true and mother.isSelfNo = false "
			+ "and benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and ben.district = :district")
	Integer getTotalOtherNoCallMother(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and benCallDetail.callTypeID = :callTypeID and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = true ")
	Integer getTotalCallAnsweredMother(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("callTypeID") Integer callTypeID,
			@Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = true and ben.district = :district "
			+ "and benCallDetail.isVerified = true "
			+ "and benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate ")
	Integer getTotalVerifiedCallMother(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = true "
			+ "and benCallDetail.isVerified = false "
			+ "and benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and ben.district = :district ")
	Integer getTotalNotVerifiedCallMother(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("district") String district);

	@Query("SELECT COUNT(DISTINCT benCallDetail.phoneNumber) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = true and ben.district = :district "
			+ "and benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate ")
	Integer getTotalUniqueNoCallMother(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = false ")
	Integer getTotalCallChild(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.obdetail obdet " + "inner join obdet.childDetail child "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and child.isSelfNo = true "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = false and ben.district = :district")
	Integer getTotalSelfNoCallChild(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.obdetail obdet " + "inner join obdet.childDetail child "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and child.isSelfNo = false "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = false and ben.district = :district")
	Integer getTotalOtherNoCallChild(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and benCallDetail.callTypeID = :callTypeID and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = false ")
	Integer getTotalCallAnsweredChild(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("callTypeID") Integer callTypeID,
			@Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.obdetail obdet " + "inner join obdet.childDetail child "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.isVerified = true and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = false ")
	Integer getTotalVerifiedCallChild(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.obdetail obdet " + "inner join obdet.childDetail child "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.isVerified = false and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = false ")
	Integer getTotalNotVerifiedCallChild(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("district") String district);

	@Query("SELECT COUNT(DISTINCT benCallDetail.phoneNumber) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = false ")
	Integer getTotalUniqueNoCallChild(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.userID = :userID and benCallDetail.isMother = true  and ben.district = :district")
	Integer getTotalCallMotherByAgentID(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("userID") Integer userID,
			@Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.obdetail obdet " + "inner join obdet.motherDetail mother  "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and mother.isSelfNo = true and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.userID = :userID and benCallDetail.isMother = true ")
	Integer getTotalSelfNoCallMotherByAgentID(@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, @Param("providerServiceMapID") Long providerServiceMapID,
			@Param("userID") Integer userID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.obdetail obdet " + "inner join obdet.motherDetail mother  "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and mother.isSelfNo = false and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.userID = :userID and benCallDetail.isMother = true ")
	Integer getTotalOtherNoCallMotherByAgentID(@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, @Param("providerServiceMapID") Long providerServiceMapID,
			@Param("userID") Integer userID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and benCallDetail.callTypeID = :callTypeID and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.userID = :userID and benCallDetail.isMother = true ")
	Integer getTotalCallAnsweredMotherByAgentID(@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, @Param("providerServiceMapID") Long providerServiceMapID,
			@Param("userID") Integer userID, @Param("callTypeID") Integer callTypeID,
			@Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.isVerified = true and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.userID = :userID and benCallDetail.isMother = true ")
	Integer getTotalVerifiedCallMotherByAgentID(@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, @Param("providerServiceMapID") Long providerServiceMapID,
			@Param("userID") Integer userID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.isVerified = false and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.userID = :userID and benCallDetail.isMother = true ")
	Integer getTotalNotVerifiedCallMotherByAgentID(@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, @Param("providerServiceMapID") Long providerServiceMapID,
			@Param("userID") Integer userID, @Param("district") String district);

	@Query("SELECT COUNT(DISTINCT benCallDetail.phoneNumber) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.userID = :userID and benCallDetail.isMother = true ")
	Integer getTotalUniqueNoCallMotherByAgentID(@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, @Param("providerServiceMapID") Long providerServiceMapID,
			@Param("userID") Integer userID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.userID = :userID and benCallDetail.isMother = false ")
	Integer getTotalCallChildByAgentID(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("userID") Integer userID,
			@Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.obdetail obdet " + "inner join obdet.childDetail child "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and child.isSelfNo = true and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.userID = :userID and benCallDetail.isMother = false ")
	Integer getTotalSelfNoCallChildByAgentID(@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, @Param("providerServiceMapID") Long providerServiceMapID,
			@Param("userID") Integer userID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.obdetail obdet " + "inner join obdet.childDetail child "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and child.isSelfNo = false and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.userID = :userID and benCallDetail.isMother = false ")
	Integer getTotalOtherNoCallChildByAgentID(@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, @Param("providerServiceMapID") Long providerServiceMapID,
			@Param("userID") Integer userID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and benCallDetail.callTypeID = :callTypeID and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.userID = :userID and benCallDetail.isMother = false ")
	Integer getTotalCallAnsweredChildByAgentID(@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, @Param("providerServiceMapID") Long providerServiceMapID,
			@Param("userID") Integer userID, @Param("callTypeID") Integer callTypeID,
			@Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and ben.district = :district "
			+ "and benCallDetail.isVerified = true "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.userID = :userID and benCallDetail.isMother = false ")
	Integer getTotalVerifiedCallChildByAgentID(@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, @Param("providerServiceMapID") Long providerServiceMapID,
			@Param("userID") Integer userID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and ben.district = :district "
			+ "and benCallDetail.isVerified = false "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.userID = :userID and benCallDetail.isMother = false ")
	Integer getTotalNotVerifiedCallChildByAgentID(@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, @Param("providerServiceMapID") Long providerServiceMapID,
			@Param("userID") Integer userID, @Param("district") String district);

	@Query("SELECT COUNT(DISTINCT benCallDetail.phoneNumber) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.userID = :userID and benCallDetail.isMother = false ")
	Integer getTotalUniqueNoCallChildByAgentID(@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, @Param("providerServiceMapID") Long providerServiceMapID,
			@Param("userID") Integer userID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and ben.district = :district ")
	Integer getTotalCall(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and benCallDetail.callTypeID = :callTypeID "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and ben.district = :district ")
	Integer getTotalCallAnswered(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("callTypeID") Integer callTypeID,
			@Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.providerServiceMapID = :providerServiceMapID "
			+ "and benCallDetail.isVerified = true "
			+ "and benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and ben.district = :district")
	Integer getTotalVerifiedCall(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.providerServiceMapID = :providerServiceMapID "
			+ "and benCallDetail.isVerified = false "
			+ "and benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and ben.district = :district")
	Integer getTotalNotVerifiedCall(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("district") String district);

	@Query("SELECT COUNT(DISTINCT benCallDetail.phoneNumber) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.providerServiceMapID = :providerServiceMapID "
			+ "and benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and ben.district = :district ")
	Integer getTotalUniqueNoCall(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.userID = :userID ")
	Integer getTotalCallByAgentID(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("userID") Integer userID,
			@Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and benCallDetail.callTypeID = :callTypeID and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.userID = :userID ")
	Integer getTotalCallAnsweredByAgentID(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("userID") Integer userID,
			@Param("callTypeID") Integer callTypeID, @Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.isVerified = true and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.userID = :userID ")
	Integer getTotalVerifiedCallByAgentID(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("userID") Integer userID,
			@Param("district") String district);

	@Query("SELECT COUNT(benCallDetail.benCallID) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and ben.district = :district "
			+ "and benCallDetail.isVerified = false "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.userID = :userID ")
	Integer getTotalNotVerifiedCallByAgentID(@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, @Param("providerServiceMapID") Long providerServiceMapID,
			@Param("userID") Integer userID, @Param("district") String district);

	@Query("SELECT COUNT(DISTINCT benCallDetail.phoneNumber) " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.benReport ben "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate and ben.district = :district "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.userID = :userID ")
	Integer getTotalUniqueNoCallByAgentID(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("userID") Integer userID,
			@Param("district") String district);

	@Query(value = "SELECT benCallType.callTypeID " + "FROM db_iemr.m_calltype benCallType "
			+ "where benCallType.callGroupType = 'Answered' and benCallType.callType = 'Answered' "
			+ "and benCallType.providerServiceMapID = :providerServiceMapID ", nativeQuery = true)
	Integer getCallTypeID(@Param("providerServiceMapID") Integer providerServiceMapID);

	@Query("select provider.stateID from ProviderServiceMapping provider "
			+ "where provider.providerServiceMapID=:providerServiceMapID ")
	public Integer getStateByProvider(@Param("providerServiceMapID") Integer providerServiceMapID);

	@Query("select district from Districts district "
			+ "where district.stateID=:stateID order by district.districts asc")
	public List<Districts> getDistrictByStateID(@Param("stateID") Integer stateID);

}
