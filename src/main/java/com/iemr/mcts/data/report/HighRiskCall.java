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
package com.iemr.mcts.data.report;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.mcts.utils.mapper.OutputMapper;

import lombok.Data;

@Entity
@Table(name = "db_reporting.fact_bencall", schema = "db_reporting")
@Data
public class HighRiskCall implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BenCallID") 
	private Long callDetailID;

	@Column(name = "CallReceivedUserID") 
	private Integer allocatedUserID;
	
	@Column(name = "ReceivedAgentID")
	private Integer userID;
	
	@Expose
	@Column(name = "PhoneNo")
	private String phoneNo;

	@Column(name = "ProviderServiceMapID")
	private Long providerServiceMapID;

	@Expose
	@Column(name = "SubCategory")
	private String outboundCallType;

	@Expose
	@Column(name = "Remarks")
	private String remark;

	@Column(name = "CreatedDate")
	private Timestamp createdDate;

	@Expose
	@Column(name = "CallTypeName")
	private String callTypeName;  
	
	@Expose
	@Column(name = "CallSubTypeName")
	private String callSubTypeName;
	
	@Column(name = "OBCallID")
	private Timestamp obCallID;
	
	@Column(name = "IsMother")
	private Boolean isMother;

	@Transient
	private Timestamp startDate = new Timestamp(0);

	@Transient
	private Timestamp endDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
	
	@Transient
	private OutputMapper outputMapper = new OutputMapper();
	
	@Override
	public String toString()
	{
		return outputMapper.gson().toJson(this);
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "obCallID", referencedColumnName = "OBCallID", insertable = false, updatable = false)
	@Expose
	private OutboundHighRisk OutboundHighRisk;
}