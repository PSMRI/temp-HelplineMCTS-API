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

import java.sql.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.google.gson.annotations.Expose;
import com.iemr.mcts.utils.mapper.OutputMapper;

@Entity
@Table(name="m_feedbacknature")
public class FeedBacknatureDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name = "FeedbackNatureID")
	private Integer feedbackNatureID;
	
	@Expose
	@Column(name = "FeedbackNature")
	private String feedbackNature;
	
	@Expose
	@Column(name = "FeedbackNatureDesc")
	private String feedbackNatureDesc;
	
	@Expose
	@Column(name = "FeedbackTypeID")
	private Integer feedbackTypeID;
	
	@Expose
	@Column(name = "Deleted",insertable=false,updatable=true)
	private Boolean deleted;
	
	@Expose
	@Column(name = "CreatedBy")
	private String createdBy;
	
	@Expose
	@Column(name = "CreatedDate",insertable=false,updatable=false)
	private Date createdDate;
	
	@Expose
	@Column(name = "ModifiedBy")
	private String modifiedBy; 
	
	@Expose
	@Column(name = "LastModDate",insertable=false,updatable=false)
	private Date lastModDate; 
	
	@Expose
	@Column(name = "FeedbackTypeName")
	private String feedbackTypeName;
	
	@OneToMany(mappedBy = "feedBacknatureDetail", fetch = FetchType.LAZY)
	@Expose
	private Set<FeedbackDetail> feedbackDetails;

	/**
	 * @return the feedbackNatureID
	 */
	public Integer getFeedbackNatureID() {
		return feedbackNatureID;
	}

	/**
	 * @param feedbackNatureID the feedbackNatureID to set
	 */
	public void setFeedbackNatureID(Integer feedbackNatureID) {
		this.feedbackNatureID = feedbackNatureID;
	}

	/**
	 * @return the feedbackNature
	 */
	public String getFeedbackNature() {
		return feedbackNature;
	}

	/**
	 * @param feedbackNature the feedbackNature to set
	 */
	public void setFeedbackNature(String feedbackNature) {
		this.feedbackNature = feedbackNature;
	}

	/**
	 * @return the feedbackNatureDesc
	 */
	public String getFeedbackNatureDesc() {
		return feedbackNatureDesc;
	}

	/**
	 * @param feedbackNatureDesc the feedbackNatureDesc to set
	 */
	public void setFeedbackNatureDesc(String feedbackNatureDesc) {
		this.feedbackNatureDesc = feedbackNatureDesc;
	}

	/**
	 * @return the feedbackTypeID
	 */
	public Integer getFeedbackTypeID() {
		return feedbackTypeID;
	}

	/**
	 * @param feedbackTypeID the feedbackTypeID to set
	 */
	public void setFeedbackTypeID(Integer feedbackTypeID) {
		this.feedbackTypeID = feedbackTypeID;
	}

	/**
	 * @return the deleted
	 */
	public Boolean getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the lastModDate
	 */
	public Date getLastModDate() {
		return lastModDate;
	}

	/**
	 * @param lastModDate the lastModDate to set
	 */
	public void setLastModDate(Date lastModDate) {
		this.lastModDate = lastModDate;
	}

	/**
	 * @return the feedbackTypeName
	 */
	public String getFeedbackTypeName() {
		return feedbackTypeName;
	}

	/**
	 * @param feedbackTypeName the feedbackTypeName to set
	 */
	public void setFeedbackTypeName(String feedbackTypeName) {
		this.feedbackTypeName = feedbackTypeName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return OutputMapper.gson().toJson(this);
	}

	
}
