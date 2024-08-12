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
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.mcts.utils.mapper.OutputMapper;

import lombok.Data;

@Entity
@Table(name = "db_reporting.fact_bencall", schema = "db_reporting")
@Data
public class BenCallDetail implements Serializable{
	
	@Id
	@Column(name="Fact_BenCallID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long callReportDetailID;
	
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
	@Column(name = "SubCategory")
	private String subCategory;
	
	@Expose
	@Column(name = "PhoneNo")
	private String phoneNumber;
	
	@Expose
	@Column(name = "Remarks")
	private String remark;
	
	@Expose
	@Column(name = "IsOutbound")
	private Boolean isOutbound;
	
	@Expose
	@Column(name = "CallTypeID")
	private Integer callTypeID;  
	
	@Expose
	@Column(name = "CallTypeName")
	private String callTypeName;  
	
	@Expose
	@Column(name = "CallSubTypeName")
	private String callSubTypeName;  
	
	@Transient
	private Timestamp startDate = new Timestamp(0);

	@Transient
	private Timestamp endDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
	
	@Column(name = "ProviderServiceMapID")
	private Long providerServiceMapID;
	
	
	@Column(name = "CreatedDate")
	private Timestamp createdDate;
	
	@Column(name = "ReceivedAgentID")
	private Integer userID;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "obCallID", referencedColumnName = "OBCallID", insertable = false, updatable = false)
	@Expose
	private OBdetail obdetail;
	
	@Column(name = "CallClosureType")
	@Expose
	private String status;
	
	@Expose
	@Column(name = "IsMother")
	private Boolean isMother;
	
	@Expose
	@Column(name = "IsVerified")
	private Boolean isVerified;
	
	@Expose
	@Column(name = "CallEndTime")
	private Timestamp callEndTime;
	
	@Expose
	@Column(name = "CallDuration")
	private String callDuration;
	

	@Expose
	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegID;
	@Expose
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "beneficiaryRegID", insertable = false, updatable = false,
			referencedColumnName = "BeneficiaryRegID")
	private BeneficiaryDetailsReport benReport;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "BenCallID", referencedColumnName = "BenCallID", insertable = false, updatable = false)
	@Expose
	private List<CallResponseReportDetail> callResponseReportDetail;

	@Transient
	private String outboundCallType;
	
	@Transient
	private Boolean VerifiedData;

	@Transient
	private Timestamp effectiveFrom;
	
	public String checkOutbound()
	{
		String callMode = "OUTGOING";
		if (isOutbound != null)
		{
			callMode = isOutbound ? "OUTGOING" : "INCOMING";
		}
		return callMode;
	}
	
	@Override
	public String toString()
	{
		return OutputMapper.gsonWithoutExposeRestriction().toJson(this);
		
	}
	public BenCallDetail()
	{
		benReport = new BeneficiaryDetailsReport();
	}
	
	

	
}
	