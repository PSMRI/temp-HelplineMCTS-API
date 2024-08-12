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
package com.iemr.mcts.data.agent;

import java.sql.Timestamp;

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

@Entity
@Table(name = "t_feedback")
public class FeedbackDetail
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FeedbackID")
	@Expose
	private Long feedbackID;

	@Column(name = "DesignationID")
	@Expose
	private Integer designationID;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DesignationID", insertable = false, updatable = false)
	@Expose
	private Designation designation;

	@Column(name = "FeedbackTypeID")
	@Expose
	private Integer feedbackTypeID;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FeedbackTypeID", insertable = false, updatable = false)
	@Expose
	private FeedbackType feedbackType;
	
	@Expose
	@Column(name = "FeedbackNatureID")
	private Integer feedbackNatureID;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FeedbackNatureID", insertable = false, updatable = false)
	@Expose
	private FeedBacknatureDetail feedBacknatureDetail;

	@Column(name = "BeneficiaryRegID")
	@Expose
	private Long beneficiaryRegID;

	@Column(name = "ProviderServiceMapID")
	@Expose
	private Long ProviderServiceMapID;
	
	@Column(name = "Deleted", insertable = false, updatable = true)
	@Expose
	private Boolean deleted;
	
	@Column(name = "CreatedBy")
	@Expose
	private String createdBy;
	
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	@Expose
	private Timestamp createdDate;
	
	@Column(name = "ModifiedBy")
	@Expose
	private String modifiedBy;
	
	@Column(name = "LastModDate", insertable = false, updatable = false)
	@Expose
	private Timestamp lastModDate;

	@Column(name = "FeedbackStatusID")
	@Expose
	private Integer feedbackStatusID;

	@Column(name = "Feedback")
	@Expose
	private String feedback;

	@Transient
	@Expose
	private Integer subServiceID;

	public FeedbackDetail()
	{

	}

	/**
	 * @return the beneficiaryRegID
	 */
	public Long getBeneficiaryRegID() {
		return beneficiaryRegID;
	}

	/**
	 * @param beneficiaryRegID the beneficiaryRegID to set
	 */
	public void setBeneficiaryRegID(Long beneficiaryRegID) {
		this.beneficiaryRegID = beneficiaryRegID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return OutputMapper.gson().toJson(this);
	}

	
}
