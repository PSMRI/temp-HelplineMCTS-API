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

@Repository
@RestResource(exported = false)
public interface CallAnsweredReportRepository extends CrudRepository<BenCallDetail, Integer> {

	@Query("SELECT benCallDetail " + "FROM BenCallDetail benCallDetail " + "inner join benCallDetail.obdetail obdet "
			+ "inner join obdet.motherDetail mother  "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID "
			+ "and benCallDetail.callTypeID = :callTypeID "
			+ "and benCallDetail.userID = :userID and benCallDetail.isMother = true ")
	List<BenCallDetail> getCallAnsweredMotherByAgentID(@Param("providerServiceMapID") Long providerServiceMapID,
			@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("callTypeID") Integer callTypeID, @Param("userID") Integer userID);

	@Query("SELECT distinct benCallDetail " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.obdetail obdet " + "inner join obdet.motherDetail mother  "
			+ "left join benCallDetail.callResponseReportDetail callres "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID "
			+ "and benCallDetail.callTypeID = :callTypeID "
			+ "and benCallDetail.userID = :userID and benCallDetail.isMother = true "
			+ "and benCallDetail.isVerified = :VerifiedData and obdet.outboundCallType = :outboundCallType")
	List<BenCallDetail> getCallAnsweredMotherByAgentIDNew(@Param("providerServiceMapID") Long providerServiceMapID,
			@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("callTypeID") Integer callTypeID, @Param("userID") Integer userID,
			@Param("outboundCallType") String outboundCallType, @Param("VerifiedData") Boolean VerifiedData);

	@Query("SELECT distinct benCallDetail " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.obdetail obdet " + "inner join obdet.motherDetail mother  "
			+ "left join benCallDetail.callResponseReportDetail callres "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID "
			+ "and benCallDetail.callTypeID = :callTypeID "
			+ "and benCallDetail.userID = :userID and benCallDetail.isMother = true "
			+ " and obdet.outboundCallType = :outboundCallType")
	List<BenCallDetail> getCallAnsweredMotherByAgentIDNewAllData(
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, @Param("callTypeID") Integer callTypeID,
			@Param("userID") Integer userID, @Param("outboundCallType") String outboundCallType);

	@Query("SELECT benCallDetail " + "FROM BenCallDetail benCallDetail " + "inner join benCallDetail.obdetail obdet "
			+ "inner join obdet.motherDetail mother "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.callTypeID = :callTypeID "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = true ")
	List<BenCallDetail> getCallAnsweredMother(@Param("providerServiceMapID") Long providerServiceMapID,
			@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("callTypeID") Integer callTypeID);

	@Query("SELECT distinct benCallDetail " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.obdetail obdet " + "inner join obdet.motherDetail mother "
			+ "left join benCallDetail.callResponseReportDetail callres "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.callTypeID = :callTypeID "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = true "
			+ "and benCallDetail.isVerified = :VerifiedData and obdet.outboundCallType = :outboundCallType")
	List<BenCallDetail> getCallAnsweredMotherNew(@Param("providerServiceMapID") Long providerServiceMapID,
			@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("callTypeID") Integer callTypeID, @Param("outboundCallType") String outboundCallType,
			@Param("VerifiedData") Boolean VerifiedData);

	@Query("SELECT distinct benCallDetail " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.obdetail obdet " + "inner join obdet.motherDetail mother "
			+ "left join benCallDetail.callResponseReportDetail callres "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.callTypeID = :callTypeID "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = true "
			+ "and obdet.outboundCallType = :outboundCallType")
	List<BenCallDetail> getCallAnsweredMotherNewAllData(@Param("providerServiceMapID") Long providerServiceMapID,
			@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("callTypeID") Integer callTypeID, @Param("outboundCallType") String outboundCallType);

	@Query("SELECT benCallDetail " + "FROM BenCallDetail benCallDetail " + "inner join benCallDetail.obdetail obdet "
			+ "inner join obdet.childDetail child  "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID "
			+ "and benCallDetail.callTypeID = :callTypeID "
			+ "and benCallDetail.userID = :userID and benCallDetail.isMother = false ")
	List<BenCallDetail> getCallAnsweredChildByAgentID(@Param("providerServiceMapID") Long providerServiceMapID,
			@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("callTypeID") Integer callTypeID, @Param("userID") Integer userID);

	@Query("SELECT distinct benCallDetail " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.obdetail obdet " + "inner join obdet.childDetail child  "
			+ "left join benCallDetail.callResponseReportDetail callres "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID "
			+ "and benCallDetail.callTypeID = :callTypeID "
			+ "and benCallDetail.userID = :userID and benCallDetail.isMother = false "
			+ "and benCallDetail.isVerified = :VerifiedData and obdet.outboundCallType = :outboundCallType ")
	List<BenCallDetail> getCallAnsweredChildByAgentIDNew(@Param("providerServiceMapID") Long providerServiceMapID,
			@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("callTypeID") Integer callTypeID, @Param("userID") Integer userID,
			@Param("outboundCallType") String outboundCallType, @Param("VerifiedData") Boolean VerifiedData);

	@Query("SELECT distinct benCallDetail " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.obdetail obdet " + "inner join obdet.childDetail child  "
			+ "left join benCallDetail.callResponseReportDetail callres "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID "
			+ "and benCallDetail.callTypeID = :callTypeID "
			+ "and benCallDetail.userID = :userID and benCallDetail.isMother = false "
			+ "and obdet.outboundCallType = :outboundCallType ")
	List<BenCallDetail> getCallAnsweredChildByAgentIDNewAllData(
			@Param("providerServiceMapID") Long providerServiceMapID, @Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, @Param("callTypeID") Integer callTypeID,
			@Param("userID") Integer userID, @Param("outboundCallType") String outboundCallType);

	@Query("SELECT benCallDetail " + "FROM BenCallDetail benCallDetail " + "inner join benCallDetail.obdetail obdet "
			+ "inner join obdet.childDetail child  "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.callTypeID = :callTypeID "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = false ")
	List<BenCallDetail> getCallAnsweredChild(@Param("providerServiceMapID") Long providerServiceMapID,
			@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("callTypeID") Integer callTypeID);

	@Query("SELECT distinct benCallDetail " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.obdetail obdet " + "inner join obdet.childDetail child  "
			+ "left join benCallDetail.callResponseReportDetail callres "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.callTypeID = :callTypeID "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = false "
			+ "and benCallDetail.isVerified = :VerifiedData and obdet.outboundCallType = :outboundCallType")
	List<BenCallDetail> getCallAnsweredChildNew(@Param("providerServiceMapID") Long providerServiceMapID,
			@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("callTypeID") Integer callTypeID, @Param("outboundCallType") String outboundCallType,
			@Param("VerifiedData") Boolean VerifiedData);

	@Query("SELECT distinct benCallDetail " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.obdetail obdet " + "inner join obdet.childDetail child  "
			+ "left join benCallDetail.callResponseReportDetail callres "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.callTypeID = :callTypeID "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = false "
			+ "and obdet.outboundCallType = :outboundCallType")
	List<BenCallDetail> getCallAnsweredChildNewAllData(@Param("providerServiceMapID") Long providerServiceMapID,
			@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("callTypeID") Integer callTypeID, @Param("outboundCallType") String outboundCallType);

	@Query("SELECT benCallDetail " + "FROM BenCallDetail benCallDetail " + "inner join benCallDetail.obdetail obdet "
			+ "inner join obdet.motherDetail mother  "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID "
			+ "and benCallDetail.callTypeID != :callTypeID "
			+ "and benCallDetail.userID = :userID and benCallDetail.isMother = true ")
	List<BenCallDetail> getCallNotAnsweredMotherByAgentID(@Param("providerServiceMapID") Long providerServiceMapID,
			@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("callTypeID") Integer callTypeID, @Param("userID") Integer userID);

	@Query("SELECT benCallDetail " + "FROM BenCallDetail benCallDetail " + "inner join benCallDetail.obdetail obdet "
			+ "inner join obdet.motherDetail mother  "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.callTypeID != :callTypeID "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = true ")
	List<BenCallDetail> getCallNotAnsweredMother(@Param("providerServiceMapID") Long providerServiceMapID,
			@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("callTypeID") Integer callTypeID);

	@Query("SELECT distinct benCallDetail " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.obdetail obdet " + "inner join obdet.childDetail child  "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID "
			+ "and benCallDetail.callTypeID != :callTypeID "
			+ "and benCallDetail.userID = :userID and benCallDetail.isMother = false ")
	List<BenCallDetail> getCallNotAnsweredChildByAgentID(@Param("providerServiceMapID") Long providerServiceMapID,
			@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("callTypeID") Integer callTypeID, @Param("userID") Integer userID);

	@Query("SELECT distinct benCallDetail " + "FROM BenCallDetail benCallDetail "
			+ "inner join benCallDetail.obdetail obdet " + "inner join obdet.childDetail child  "
			+ "where benCallDetail.createdDate >= :startDate and benCallDetail.createdDate <= :endDate "
			+ "and benCallDetail.callTypeID != :callTypeID "
			+ "and benCallDetail.providerServiceMapID = :providerServiceMapID and benCallDetail.isMother = false ")
	List<BenCallDetail> getCallNotAnsweredChild(@Param("providerServiceMapID") Long providerServiceMapID,
			@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate,
			@Param("callTypeID") Integer callTypeID);

	@Query(value = "SELECT benCallType.callTypeID " + "FROM db_iemr.m_calltype benCallType "
			+ "where benCallType.callGroupType = 'Answered' and benCallType.callType = 'Answered' "
			+ "and benCallType.providerServiceMapID = :providerServiceMapID ", nativeQuery = true)
	Integer getCallTypeID(@Param("providerServiceMapID") Integer providerServiceMapID);
}
