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
import java.sql.Timestamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.iemr.mcts.data.supervisor.MctsOutboundCall;

public class CallClosureDetail {

	@SerializedName("mctsOutboundCall")
	@Expose
	private MctsOutboundCall mctsOutboundCall;

	@SerializedName("isAnswered")
	@Expose
	private Boolean isAnswered;

	@SerializedName("noFurtherCallRequired")
	@Expose
	private Boolean noFurtherCallRequired;

	@SerializedName("callDetailID")
	@Expose
	private Long callDetailID;

	@SerializedName("reasonNoFurtherCallRequired")
	@Expose
	private String reasonNoFurtherCallRequired;

	@SerializedName("additionalCallRequired")
	@Expose
	private Boolean additionalCallRequired;

	@SerializedName("stickyAgentRequired")
	@Expose
	private Boolean stickyAgentRequired;

	@SerializedName("isTransfered")
	@Expose
	private Boolean isTransfered;

	@SerializedName("isTransferedTo104")
	@Expose
	private Boolean isTransferedTo104;

	@SerializedName("isCompleted")
	@Expose
	private Boolean isCompleted;

	@SerializedName("prefferedCallDate")
	@Expose
	private Date prefferedCallDate;

	@SerializedName("providerServiceMapID")
	@Expose
	private Long providerServiceMapID;

	@SerializedName("userID")
	@Expose
	private Integer userID;

	@SerializedName("createdBy")
	@Expose
	private String createdBy;

	@SerializedName("remarks")
	@Expose
	private String remarks;

	@SerializedName("callType") /// need to comment
	@Expose
	private String callType;

	@SerializedName("callTypeID")
	@Expose
	private Integer callTypeID;

	@SerializedName("childCongenitalAnomaliesDetails")
	@Expose
	private ChildCongenitalAnomaliesDetail[] childCongenitalAnomaliesDetails;

	@SerializedName("isVerified")
	@Expose
	private Boolean isVerified;

	@Expose
	@SerializedName("CallTime")
	private Timestamp callTime;

	@Expose
	@SerializedName("CallEndTime")
	private Timestamp callEndTime;
	
	@Expose
	@SerializedName("IsOutbound")
	private Boolean isOutbound = true;

	public Boolean getIsOutbound() {
		return isOutbound;
	}

	public void setIsOutbound(Boolean isOutbound) {
		this.isOutbound = isOutbound;
	}

	/**
	 * @return the mctsOutboundCall
	 */
	public MctsOutboundCall getMctsOutboundCall() {
		return mctsOutboundCall;
	}

	/**
	 * @param mctsOutboundCall the mctsOutboundCall to set
	 */
	public void setMctsOutboundCall(MctsOutboundCall mctsOutboundCall) {
		this.mctsOutboundCall = mctsOutboundCall;
	}

	/**
	 * @return the isAnswered
	 */
	public Boolean getIsAnswered() {
		return isAnswered;
	}

	/**
	 * @param isAnswered the isAnswered to set
	 */
	public void setIsAnswered(Boolean isAnswered) {
		this.isAnswered = isAnswered;
	}

	/**
	 * @return the noFurtherCallRequired
	 */
	public Boolean getNoFurtherCallRequired() {
		return noFurtherCallRequired;
	}

	/**
	 * @param noFurtherCallRequired the noFurtherCallRequired to set
	 */
	public void setNoFurtherCallRequired(Boolean noFurtherCallRequired) {
		this.noFurtherCallRequired = noFurtherCallRequired;
	}

	/**
	 * @return the reasonNoFurtherCallRequired
	 */
	public String getReasonNoFurtherCallRequired() {
		return reasonNoFurtherCallRequired;
	}

	/**
	 * @param reasonNoFurtherCallRequired the reasonNoFurtherCallRequired to set
	 */
	public void setReasonNoFurtherCallRequired(String reasonNoFurtherCallRequired) {
		this.reasonNoFurtherCallRequired = reasonNoFurtherCallRequired;
	}

	/**
	 * @return the additionalCallRequired
	 */
	public Boolean getAdditionalCallRequired() {
		return additionalCallRequired;
	}

	/**
	 * @param additionalCallRequired the additionalCallRequired to set
	 */
	public void setAdditionalCallRequired(Boolean additionalCallRequired) {
		this.additionalCallRequired = additionalCallRequired;
	}

	/**
	 * @return the stickyAgentRequired
	 */
	public Boolean getStickyAgentRequired() {
		return stickyAgentRequired;
	}

	/**
	 * @param stickyAgentRequired the stickyAgentRequired to set
	 */
	public void setStickyAgentRequired(Boolean stickyAgentRequired) {
		this.stickyAgentRequired = stickyAgentRequired;
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
	 * @return the userID
	 */
	public Integer getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(Integer userID) {
		this.userID = userID;
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
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the isCompleted
	 */
	public Boolean getIsCompleted() {
		return isCompleted;
	}

	/**
	 * @param isCompleted the isCompleted to set
	 */
	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	/**
	 * @return the prefferedCallDate
	 */
	public Date getPrefferedCallDate() {
		return prefferedCallDate;
	}

	/**
	 * @param prefferedCallDate the prefferedCallDate to set
	 */
	public void setPrefferedCallDate(Date prefferedCallDate) {
		this.prefferedCallDate = prefferedCallDate;
	}

	/**
	 * @return the childCongenitalAnomaliesDetails
	 */
	public ChildCongenitalAnomaliesDetail[] getChildCongenitalAnomaliesDetails() {
		return childCongenitalAnomaliesDetails;
	}

	/**
	 * @param childCongenitalAnomaliesDetails the childCongenitalAnomaliesDetails to
	 *                                        set
	 */
	public void setChildCongenitalAnomaliesDetails(ChildCongenitalAnomaliesDetail[] childCongenitalAnomaliesDetails) {
		this.childCongenitalAnomaliesDetails = childCongenitalAnomaliesDetails;
	}

	/**
	 * @return the callDetailID
	 */
	public Long getCallDetailID() {
		return callDetailID;
	}

	/**
	 * @param callDetailID the callDetailID to set
	 */
	public void setCallDetailID(Long callDetailID) {
		this.callDetailID = callDetailID;
	}

	/**
	 * @return the callType
	 */
	public String getCallType() {
		return callType;
	}

	/**
	 * @param callType the callType to set
	 */
	public void setCallType(String callType) {
		this.callType = callType;
	}

	/**
	 * @return the isTransfered
	 */
	public Boolean getIsTransfered() {
		return isTransfered;
	}

	/**
	 * @param isTransfered the isTransfered to set
	 */
	public void setIsTransfered(Boolean isTransfered) {
		this.isTransfered = isTransfered;
	}

	/**
	 * @return the callTypeID
	 */
	public Integer getCallTypeID() {
		return callTypeID;
	}

	/**
	 * @param callTypeID the callTypeID to set
	 */
	public void setCallTypeID(Integer callTypeID) {
		this.callTypeID = callTypeID;
	}

	/**
	 * @return the isTransferedTo104
	 */
	public Boolean getIsTransferedTo104() {
		return isTransferedTo104;
	}

	/**
	 * @param isTransferedTo104 the isTransferedTo104 to set
	 */
	public void setIsTransferedTo104(Boolean isTransferedTo104) {
		this.isTransferedTo104 = isTransferedTo104;
	}

	/**
	 * @return the isVerified
	 */
	public Boolean getIsVerified() {
		return isVerified;
	}

	/**
	 * @param isVerified the isVerified to set
	 */
	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	/**
	 * @return the callTime
	 */
	public Timestamp getCallTime() {
		return callTime;
	}

	/**
	 * @param callTime the callTime to set
	 */
	public void setCallTime(Timestamp callTime) {
		this.callTime = callTime;
	}

	/**
	 * @return the callEndTime
	 */
	public Timestamp getCallEndTime() {
		return callEndTime;
	}

	/**
	 * @param callEndTime the callEndTime to set
	 */
	public void setCallEndTime(Timestamp callEndTime) {
		this.callEndTime = callEndTime;
	}

}
