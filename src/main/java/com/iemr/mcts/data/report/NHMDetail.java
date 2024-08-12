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

import java.sql.Timestamp;
import java.util.Calendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.mcts.utils.mapper.OutputMapper;

import lombok.Data;

@Entity
@Table(name = "db_reporting.fact_bencall", schema = "db_reporting")
@Data
public class NHMDetail {

	
	@Id
	@Column(name="Fact_BenCallID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nhmReportDetailID;
	
	@Expose
	@Column(name = "BenCallID")
	private String benCallID;
	
	@Expose
	@Column(name = "OBCallID")
	private Long obCallID;
	
	@Column(name = "CallTime")
	@Expose
	private Timestamp date;
	
	@Expose
	@Column(name = "Category")
	private String category;
	
	@Expose
	@Column(name = "PhoneNo")
	private String phoneNumber;
	
	@Expose
	@Column(name = "Remarks")
	private String remark;
	
	@Transient
	private Timestamp startDate = new Timestamp(0);

	@Transient
	private Timestamp endDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
	
	@Transient
	private OutputMapper outputMapper = new OutputMapper();
	
	@Column(name = "ProviderServiceMapID")
	private Long providerServiceMapID;
	
	@Column(name = "CreatedDate")
	private Timestamp createdDate;

	@Override
	public String toString()
	{
		return outputMapper.gson().toJson(this);
	}
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OBCallID", referencedColumnName = "OBCallID", insertable = false, updatable = false)
	@Expose
	private OBdetail obdetail;
	
	
	
	
}
