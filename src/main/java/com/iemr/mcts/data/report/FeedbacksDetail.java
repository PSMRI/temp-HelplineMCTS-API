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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.mcts.utils.mapper.OutputMapper;

import lombok.Data;

@Entity
@Data
@Table(name = "db_reporting.fact_feedback", schema = "db_reporting")
public class FeedbacksDetail implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Fact_FeedbackID")
	private Long feedbackID;

	@Expose
	@Column(name = "FeedbackTypeID")
	private Integer feedbackTypeID;

	@Expose
	@Column(name = "FeedbackNatureID")
	private Integer feedbackNatureID;

	@Expose
	@Column(name = "DesignationID")
	private Integer designationID;
	
	@Expose
	@Column(name = "FeedbackTypeName")
	private String typeOfComplain = "";

	@Expose
	@Column(name = "FeedbackNatureName")
	private String natureOfComplaint;

	@Expose
	@Column(name = "InstitutionName")
	private String instituteName;

	@Expose
	@Column(name = "DesignationName")
	private String designation = "";

	@Expose
	@Column(name = "FeedbackAgainst")
	private String complaintAgainst = "";
	
	@Expose
	@Column(name = "Feedback")
	private String briefofComplaint = "";

	@Transient
	private Timestamp startDate = new Timestamp(0);

	@Transient
	private Timestamp endDate = new Timestamp(Calendar.getInstance().getTimeInMillis());

	@Expose
	@Column(name = "ProviderServiceMapID")
	private Long providerServiceMapID;

	@Expose
	@Column(name = "BenCallID")
	private Long benCallID;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "benCallID", referencedColumnName = "BenCallID", insertable = false, updatable = false)
	@Expose
	private BenCallDetail benCallDetails;
	
	@Expose
	@Transient
	private Boolean isMother;
	
	@Expose
	@Column(name = "CreatedDate")
	private Timestamp createdDate;
	
	@Transient
	private Integer userID;

	@Override
	public String toString()
	{
		return OutputMapper.gsonWithoutExposeRestriction().toJson(this);
	}

}
