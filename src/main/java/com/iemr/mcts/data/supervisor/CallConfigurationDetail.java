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
package com.iemr.mcts.data.supervisor;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.mcts.utils.mapper.OutputMapper;

@Entity
@Table(name = "m_MCTSCallConfiguration")
public class CallConfigurationDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name = "MCTSCallConfigID")
	private Integer mctsCallConfigID;

	@Expose
	@Column(name = "ProviderServiceMapID")
	private Long providerServiceMapID;

	@Expose
	@Column(name = "OutboundCallType")
	private String outboundCallType;

	@Expose
	@Column(name = "NoOfAttempts")
	private Integer noOfAttempts;

	@Expose
	@Column(name = "NextAttemptPeriod")
	private String nextAttemptPeriod;

	@Expose
	@Column(name = "FromTerm")
	private String fromTerm;

	@Expose
	@Column(name = "ToTerm")
	private String toTerm;

	@Expose
	@Column(name = "NoOfAgents")
	private Long noOfAgents;

	@Expose
	@Column(name = "TotalCallsPerDay")
	private Long totalCallsPerDay;

	@Expose
	@Column(name = "EffectiveFrom")
	private Date effectiveFrom;

	@Expose
	@Column(name = "EffectiveUpto")
	private Date effectiveUpto;

	@Expose
	@Column(name = "Deleted", insertable = false, updatable = true)
	private Boolean deleted;

	@Expose
	@Column(name = "CreatedBy")
	private String createdBy;

	@Expose
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Timestamp createdDate;

	@Expose
	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Expose
	@Column(name = "LastModDate")
	private Date lastModDate;

	@Expose
	@Column(name = "DisplayOBCallType")
	private String displayOBCallType;

	@Transient
	private Timestamp endDate;

	private static OutputMapper outputMapper = new OutputMapper();

	/**
	 * Default Constructor
	 */
	public CallConfigurationDetail() {

	}

	/**
	 * Constructor for outboundcalltype fromTerm and toTerm
	 */
	public CallConfigurationDetail(String outboundCallType, String fromTerm, String toTerm) {

		this.outboundCallType = outboundCallType;
		this.fromTerm = fromTerm;
		this.toTerm = toTerm;
	}

	/**
	 * Constructor for outboundcalltype fromTerm and toTerm
	 */
	public CallConfigurationDetail(Integer noOfAttempts, String nextAttemptPeriod) {

		this.noOfAttempts = noOfAttempts;
		this.nextAttemptPeriod = nextAttemptPeriod;
	}

	/**
	 * @return the mctsCallConfigID
	 */
	public Integer getMctsCallConfigID() {
		return mctsCallConfigID;
	}

	/**
	 * @param mctsCallConfigID the mctsCallConfigID to set
	 */
	public void setMctsCallConfigID(Integer mctsCallConfigID) {
		this.mctsCallConfigID = mctsCallConfigID;
	}

	/**
	 * @return the providerServiceMapID
	 */
	public Long getProviderServiceMapID() {
		return providerServiceMapID;
	}

	/**
	 * @param providerServiceMapID the providerServiceMapID to set
	 */
	public void setProviderServiceMapID(Long providerServiceMapID) {
		this.providerServiceMapID = providerServiceMapID;
	}

	/**
	 * @return the outboundCallType
	 */
	public String getOutboundCallType() {
		return outboundCallType;
	}

	/**
	 * @param outboundCallType the outboundCallType to set
	 */
	public void setOutboundCallType(String outboundCallType) {
		this.outboundCallType = outboundCallType;
	}

	/**
	 * @return the fromTerm
	 */
	public String getFromTerm() {
		return fromTerm;
	}

	/**
	 * @param fromTerm the fromTerm to set
	 */
	public void setFromTerm(String fromTerm) {
		this.fromTerm = fromTerm;
	}

	/**
	 * @return the toTerm
	 */
	public String getToTerm() {
		return toTerm;
	}

	/**
	 * @param toTerm the toTerm to set
	 */
	public void setToTerm(String toTerm) {
		this.toTerm = toTerm;
	}

	/**
	 * @return the effectiveFrom
	 */
	public Date getEffectiveFrom() {
		return effectiveFrom;
	}

	/**
	 * @param effectiveFrom the effectiveFrom to set
	 */
	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	/**
	 * @return the effectiveUpto
	 */
	public Date getEffectiveUpto() {
		return effectiveUpto;
	}

	/**
	 * @param effectiveUpto the effectiveUpto to set
	 */
	public void setEffectiveUpto(Date effectiveUpto) {
		this.effectiveUpto = effectiveUpto;
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
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Timestamp createdDate) {
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
	 * @return the noOfAttempts
	 */
	public Integer getNoOfAttempts() {
		return noOfAttempts;
	}

	/**
	 * @param noOfAttempts the noOfAttempts to set
	 */
	public void setNoOfAttempts(Integer noOfAttempts) {
		this.noOfAttempts = noOfAttempts;
	}

	/**
	 * @return the nextAttemptPeriod
	 */
	public String getNextAttemptPeriod() {
		return nextAttemptPeriod;
	}

	/**
	 * @param nextAttemptPeriod the nextAttemptPeriod to set
	 */
	public void setNextAttemptPeriod(String nextAttemptPeriod) {
		this.nextAttemptPeriod = nextAttemptPeriod;
	}

	/**
	 * @return the outputMapper
	 */
	public static OutputMapper getOutputMapper() {
		return outputMapper;
	}

	/**
	 * @param outputMapper the outputMapper to set
	 */
	public static void setOutputMapper(OutputMapper outputMapper) {
		CallConfigurationDetail.outputMapper = outputMapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return outputMapper.gson().toJson(this);
	}

	/**
	 * @return the displayOBCallType
	 */
	public String getDisplayOBCallType() {
		return displayOBCallType;
	}

	/**
	 * @param displayOBCallType the displayOBCallType to set
	 */
	public void setDisplayOBCallType(String displayOBCallType) {
		this.displayOBCallType = displayOBCallType;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

}
