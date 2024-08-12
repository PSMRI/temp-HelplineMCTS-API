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
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import com.google.gson.annotations.Expose;
import com.iemr.mcts.data.supervisor.CallType;
import com.iemr.mcts.data.supervisor.M_User;
import com.iemr.mcts.data.supervisor.MctsOutboundCall;
import com.iemr.mcts.utils.mapper.OutputMapper;

@Entity
@Table(name = "t_bencall")    // t_mctsoutboundcall
public class MctsOutboundCallDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name = "BenCallID") // CallDetailID
	private Long callDetailID;

	@Expose
	@Column(name = "OBCallID", insertable = true, updatable = true) 
	private Long obCallID;

	@Expose
	@Column(name = "CallReceivedUserID", insertable = true, updatable = true)  //AllocatedUserID
	private Integer allocatedUserID;
	
	@Expose
	@Column(name = "ReceivedAgentID")
	private String agentID;
	
	@Expose
	@Column(name = "PhoneNo")
	private String phoneNo;

	@Expose
	@Column(name = "CalledServiceID")
	private Long providerServiceMapID;

	@Expose
	@Column(name = "SubCategory")
	private String outboundCallType;

	@Expose
	@Column(name = "CallID")  //callId
	private String callId;

	@Expose
	@Column(name = "IsOutbound")
	private Boolean isOutbound = true;
	
	@Expose
	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegID;

	@Expose
	@Column(name = "ReceivedRoleName")
	private String receivedRoleName; 

	@Expose
	@Column(name = "CallTypeID")
	private Integer callTypeID;

	@Expose
	@Column(name = "ChangeLog")
	private String changeLog;

	@Expose
	@Column(name = "Remarks")
	private String remark;

	@Expose
	@Column(name = "SMS_Advice")
	private String smsAdvice;

	@Expose
	@Column(name = "SMS_Ph")
	private String smsPh;

	@Expose
	@Column(name = "Deleted", insertable=false, updatable=true)
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
	private Timestamp lastModDate;
	
	@Column(name = "IsMother")
	private Boolean isMother;
	
	@Expose
	@Column(name = "CallTime")
	private Timestamp callTime;
	
	@Expose
	@Column(name = "IsVerified")
	private Boolean isVerified;
	
	@Expose
	@Column(name = "CallEndTime")
	private Timestamp callEndTime;

	@Expose
	@Column(name = "CallDuration")
	private String callDuration;
	/**
	 * map id on with valid mother data
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CallReceivedUserID", referencedColumnName = "userID", insertable = false, updatable = false)
	@Expose
	private M_User user;

	/**
	 * 
	 */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "obCallID", insertable = false, updatable = false)
	@Expose
	private MctsOutboundCall mctsOutboundCall;
	
	/**
	 * 
	 */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "callTypeID", insertable = false, updatable = false)
	@Expose
	private CallType callType;

	private static OutputMapper outputMapper = new OutputMapper();

	/**
	 * mcts outbound call response
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mctsOutboundCallDetail")
	private List<MctsCallResponseDetail> mctsCallResponseDetails;

	/**
	 * Default Constructor
	 */
	public MctsOutboundCallDetail() {

	}

	/**
	 * Overloaded parameter Constructor
	 */
	public MctsOutboundCallDetail(Long callDetailID, String outboundCallType, String callId,
			String changeLog, String remark, String smsAdvice, java.util.Date createdDate, String createdBy) {

		this.callDetailID = callDetailID;
		this.outboundCallType = outboundCallType;
		this.callId = callId;
		this.changeLog = changeLog;
		this.remark = remark;
		this.smsAdvice = smsAdvice;
		this.createdDate = new Timestamp(createdDate.getTime());
		this.createdBy = createdBy;
	}

	/**
	 * @return the obCallID
	 */
	public Long getObCallID() {
		return obCallID;
	}

	/**
	 * @param obCallID
	 *            the obCallID to set
	 */
	public void setObCallID(Long obCallID) {
		this.obCallID = obCallID;
	}

	/**
	 * @return the user
	 */
	public M_User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(M_User user) {
		this.user = user;
	}

	/**
	 * @return the mctsOutboundCall
	 */
	public MctsOutboundCall getMctsOutboundCall() {
		return mctsOutboundCall;
	}

	/**
	 * @param mctsOutboundCall
	 *            the mctsOutboundCall to set
	 */
	public void setMctsOutboundCall(MctsOutboundCall mctsOutboundCall) {
		this.mctsOutboundCall = mctsOutboundCall;
	}

	/**
	 * @return the callDetailID
	 */
	public Long getCallDetailID() {
		return callDetailID;
	}

	/**
	 * @param callDetailID
	 *            the callDetailID to set
	 */
	public void setCallDetailID(Long callDetailID) {
		this.callDetailID = callDetailID;
	}

	/**
	 * @return the allocatedUserID
	 */
	public Integer getAllocatedUserID() {
		return allocatedUserID;
	}

	/**
	 * @param allocatedUserID
	 *            the allocatedUserID to set
	 */
	public void setAllocatedUserID(Integer allocatedUserID) {
		this.allocatedUserID = allocatedUserID;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the providerServiceMapID
	 */
	public Long getProviderServiceMapID() {
		return providerServiceMapID;
	}

	/**
	 * @param providerServiceMapID
	 *            the providerServiceMapID to set
	 */
	public void setProviderServiceMapID(Long providerServiceMapID) {
		this.providerServiceMapID = providerServiceMapID;
	}

	/**
	 * @return the czentrixCallID
	 */
	public String getCallId() {
		return callId;
	}

	/**
	 * @param callId
	 *            the czentrixCallID to set
	 */
	public void setCallId(String callId) {
		this.callId = callId;
	}

	/**
	 * @return the changeLog
	 */
	public String getChangeLog() {
		return changeLog;
	}

	/**
	 * @param changeLog
	 *            the changeLog to set
	 */
	public void setChangeLog(String changeLog) {
		this.changeLog = changeLog;
	}

	/**
	 * @return the smsAdvice
	 */
	public String getSmsAdvice() {
		return smsAdvice;
	}

	/**
	 * @param smsAdvice
	 *            the smsAdvice to set
	 */
	public void setSmsAdvice(String smsAdvice) {
		this.smsAdvice = smsAdvice;
	}

	/**
	 * @return the smsPh
	 */
	public String getSmsPh() {
		return smsPh;
	}

	/**
	 * @param smsPh
	 *            the smsPh to set
	 */
	public void setSmsPh(String smsPh) {
		this.smsPh = smsPh;
	}

	/**
	 * @return the mctsCallResponseDetails
	 */
	public List<MctsCallResponseDetail> getMctsCallResponseDetails() {
		return mctsCallResponseDetails;
	}

	/**
	 * @param mctsCallResponseDetails
	 *            the mctsCallResponseDetails to set
	 */
	public void setMctsCallResponseDetails(List<MctsCallResponseDetail> mctsCallResponseDetails) {
		this.mctsCallResponseDetails = mctsCallResponseDetails;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return outputMapper.gson().toJson(this);
	}

	private static Date convertUtilToSql(java.util.Date uDate) {
		
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
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

	public MctsOutboundCallDetail(Long beneficiaryRegID,Long obCallID,java.util.Date createdDate,String remark)
	{
		this.beneficiaryRegID=beneficiaryRegID;
		this.obCallID=obCallID;
		this.createdDate = new Timestamp(createdDate.getTime());
		this.remark=remark;
	}

	public Long getBeneficiaryRegID() {
		return beneficiaryRegID;
	}

	public void setBeneficiaryRegID(Long beneficiaryRegID) {
		this.beneficiaryRegID = beneficiaryRegID;
	}
}