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

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Comparator;
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
import com.iemr.mcts.data.agent.MctsOutboundCallDetail;
import com.iemr.mcts.utils.mapper.OutputMapper;

@Entity
@Table(name = "t_MCTSOutboundCalls")

public class MctsOutboundCall implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name = "OBCallID")
	private Long obCallID;

	@Expose
	@Column(name = "MotherID", insertable = true, updatable = true)
	private Long motherID;

	@Expose
	@Column(name = "ChildID")
	private Long childID;

	@Expose
	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegID;

	@Expose
	@Column(name = "AllocatedUserID", insertable = false, updatable = true)
	private Integer allocatedUserID;

	@Expose
	@Column(name = "ProviderServiceMapID")
	private Long providerServiceMapID;

	@Expose
	@Column(name = "OutboundCallType")
	private String outboundCallType;

	@Expose
	@Column(name = "CallDateFrom")
	private Date callDateFrom;

	@Expose
	@Column(name = "CallDateTo")
	private Date callDateTo;

	@Expose
	@Column(name = "PrefferedCallDate")
	private Date prefferedCallDate;

	@Expose
	@Column(name = "CallStatus")
	private String callStatus;

	@Expose
	@Column(name = "NoOfTrials")
	private Integer noOfTrials;

	@Expose
	@Column(name = "AllocationStatus")
	private String allocationStatus;

	@Expose
	@Column(name = "Deleted")
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
	@Transient
	private Long callDetailID;

	@Expose
	@Column(name = "DisplayOBCallType")
	private String displayOBCallType;

	@Expose
	@Transient
	private Timestamp lastCallTime;

	@Expose
	@Transient
	private String lastCallRemark;

	/**
	 * map id on with mother data reader detail
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "motherID", referencedColumnName = "MCTSID_no", insertable = false, updatable = false)
	@Expose
	private MctsDataReaderDetail mctsDataReaderDetail;

	/**
	 * map id on with mother data reader detail
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "childID", referencedColumnName = "MCTSID_no_Child_ID", insertable = false, updatable = false)
	@Expose
	private ChildValidDataHandler childValidDataHandler;

	/**
	 * map id on with valid mother data
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "allocatedUserID", referencedColumnName = "userID", insertable = true, updatable = false)
	@Expose
	private M_User user;

	/**
	 * mcts outbound detail to store and get history
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mctsOutboundCall")
	private List<MctsOutboundCallDetail> mctsOutboundCallDeatils;

	@Expose
	@Transient
	private Integer vanID;

	/**
	 * OutputMapper
	 */
	private static OutputMapper outputMapper = new OutputMapper();

	/**
	 * @return the obCallID
	 */
	public Long getObCallID() {
		return obCallID;
	}

	/**
	 * @param obCallID the obCallID to set
	 */
	public void setObCallID(Long obCallID) {
		this.obCallID = obCallID;
	}

	/**
	 * @return the childID
	 */
	public Long getChildID() {
		return childID;
	}

	/**
	 * @param childID the childID to set
	 */
	public void setChildID(Long childID) {
		this.childID = childID;
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

	/**
	 * @return the allocatedUserID
	 */
	public Integer getAllocatedUserID() {
		return allocatedUserID;
	}

	/**
	 * @param allocatedUserID the allocatedUserID to set
	 */
	public void setAllocatedUserID(Integer allocatedUserID) {
		this.allocatedUserID = allocatedUserID;
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
	 * @return the callStatus
	 */
	public String getCallStatus() {
		return callStatus;
	}

	/**
	 * @param callStatus the callStatus to set
	 */
	public void setCallStatus(String callStatus) {
		this.callStatus = callStatus;
	}

	/**
	 * @return the noOfTrials
	 */
	public Integer getNoOfTrials() {
		return noOfTrials;
	}

	/**
	 * @param noOfTrials the noOfTrials to set
	 */
	public void setNoOfTrials(Integer noOfTrials) {
		this.noOfTrials = noOfTrials;
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
	 * @return the motherID
	 */
	public Long getMotherID() {
		return motherID;
	}

	/**
	 * @param motherID the motherID to set
	 */
	public void setMotherID(Long motherID) {
		this.motherID = motherID;
	}

	/**
	 * @return the callDateFrom
	 */
	public Date getCallDateFrom() {
		return callDateFrom;
	}

	/**
	 * @param callDateFrom the callDateFrom to set
	 */
	public void setCallDateFrom(Date callDateFrom) {
		this.callDateFrom = callDateFrom;
	}

	/**
	 * @return the callDateTo
	 */
	public Date getCallDateTo() {
		return callDateTo;
	}

	/**
	 * @param callDateTo the callDateTo to set
	 */
	public void setCallDateTo(Date callDateTo) {
		this.callDateTo = callDateTo;
	}

	/**
	 * @return the allocationStatus
	 */
	public String getAllocationStatus() {
		return allocationStatus;
	}

	/**
	 * @param allocationStatus the allocationStatus to set
	 */
	public void setAllocationStatus(String allocationStatus) {
		this.allocationStatus = allocationStatus;
	}

	/**
	 * @return the mctsOutboundCallDeatils
	 */
	public List<MctsOutboundCallDetail> getMctsOutboundCallDeatils() {
		return mctsOutboundCallDeatils;
	}

	/**
	 * @param mctsOutboundCallDeatils the mctsOutboundCallDeatils to set
	 */
	public void setMctsOutboundCallDeatils(List<MctsOutboundCallDetail> mctsOutboundCallDeatils) {
		this.mctsOutboundCallDeatils = mctsOutboundCallDeatils;
	}

	/**
	 * @return the user
	 */
	public M_User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(M_User user) {
		this.user = user;
	}

	/**
	 * @return the mctsDataReaderDetail
	 */
	public MctsDataReaderDetail getMctsDataReaderDetail() {
		return mctsDataReaderDetail;
	}

	/**
	 * @param mctsDataReaderDetail the mctsDataReaderDetail to set
	 */
	public void setMctsDataReaderDetail(MctsDataReaderDetail mctsDataReaderDetail) {
		this.mctsDataReaderDetail = mctsDataReaderDetail;
	}

	/**
	 * @return the childValidDataHandler
	 */
	public ChildValidDataHandler getChildValidDataHandler() {
		return childValidDataHandler;
	}

	/**
	 * @param childValidDataHandler the childValidDataHandler to set
	 */
	public void setChildValidDataHandler(ChildValidDataHandler childValidDataHandler) {
		this.childValidDataHandler = childValidDataHandler;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((callDateFrom == null) ? 0 : callDateFrom.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MctsOutboundCall other = (MctsOutboundCall) obj;
		if (callDateFrom == null) {
			if (other.callDateFrom != null)
				return false;
		} else if (!callDateFrom.equals(other.callDateFrom))
			return false;
		return true;
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

	public static Comparator<MctsOutboundCall> getSortCompoByCallFromDate() {

		Comparator<MctsOutboundCall> comp = new Comparator<MctsOutboundCall>() {

			@Override
			public int compare(MctsOutboundCall o1, MctsOutboundCall o2) {

				if (o1.getCallDateFrom() != null && o2.getCallDateFrom() != null)
					return o1.getCallDateFrom().compareTo(o2.getCallDateFrom());

				else
					return 0;
			}
		};

		return comp;
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

	/**
	 * @return the lastCallTime
	 */
	public Timestamp getLastCallTime() {
		return lastCallTime;
	}

	/**
	 * @param lastCallTime the lastCallTime to set
	 */
	public void setLastCallTime(Timestamp lastCallTime) {
		this.lastCallTime = lastCallTime;
	}

	/**
	 * @return the lastCallRemark
	 */
	public String getLastCallRemark() {
		return lastCallRemark;
	}

	/**
	 * @param lastCallRemark the lastCallRemark to set
	 */
	public void setLastCallRemark(String lastCallRemark) {
		this.lastCallRemark = lastCallRemark;
	}

	/**
	 * @return the vanID
	 */
	public Integer getVanID() {
		return vanID;
	}

	/**
	 * @param vanID the vanID to set
	 */
	public void setVanID(Integer vanID) {
		this.vanID = vanID;
	}
}
