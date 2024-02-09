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

import lombok.Data;

@Entity
@Data
@Table(name = "t_MCTSOutboundCalls")
public class OutboundWorklist implements Serializable {

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
	private Timestamp callDateFrom;

	@Column(name = "CallDateTo")
	private Timestamp callDateTo;

	@Expose
	@Column(name = "NoOfTrials")
	private Integer noOfTrials;

	@Expose
	@Column(name = "DisplayOBCallType")
	private String displayOBCallType;

	@Expose
	@Column(name = "AllocationStatus")
	private String allocationStatus;

	@Column(name = "CallStatus")
	private String callStatus;

	@Expose
	@Transient
	private Timestamp lastCallTime;

	@Expose
	@Transient
	private String lastCallRemark;

	@Expose
	@Transient
	private MctsDataReaderDetail mctsDataReaderDetail;

	@Expose
	@Transient
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

	public OutboundWorklist(Long obCallID, Long beneficiaryRegID, Integer allocatedUserID, Long providerServiceMapID,
			String outboundCallType, Timestamp callDateFrom, Integer noOfTrials, String displayOBCallType,
			Long motherID, MctsDataReaderDetail mctsDataReaderDetail) {
		this.obCallID = obCallID;
		this.beneficiaryRegID = beneficiaryRegID;
		this.allocatedUserID = allocatedUserID;
		this.providerServiceMapID = providerServiceMapID;
		this.outboundCallType = outboundCallType;
		this.callDateFrom = callDateFrom;
		this.noOfTrials = noOfTrials;
		this.displayOBCallType = displayOBCallType;
		this.motherID = motherID;
		this.mctsDataReaderDetail = mctsDataReaderDetail;
	}

	public OutboundWorklist(Long beneficiaryRegID, String outboundCallType, Timestamp callDateFrom, Integer noOfTrials,
			String displayOBCallType, Long obCallID, Long motherID, Long childID,
			ChildValidDataHandler childValidDataHandler) {
		this.beneficiaryRegID = beneficiaryRegID;
		this.outboundCallType = outboundCallType;
		this.callDateFrom = callDateFrom;
		this.noOfTrials = noOfTrials;
		this.displayOBCallType = displayOBCallType;
		this.obCallID = obCallID;
		this.motherID = motherID;
		this.childID = childID;
		this.childValidDataHandler = childValidDataHandler;
	}

	public OutboundWorklist(Long obCallID, String outboundCallType, String displayOBCallType, Timestamp callDateFrom,
			String allocationStatus, MctsDataReaderDetail mctsDataReaderDetail) {
		this.obCallID = obCallID;
		this.outboundCallType = outboundCallType;
		this.callDateFrom = callDateFrom;
		this.displayOBCallType = displayOBCallType;
		this.allocationStatus = allocationStatus;
		this.mctsDataReaderDetail = mctsDataReaderDetail;
	}

	public OutboundWorklist(Long obCallID, String outboundCallType, String displayOBCallType, Timestamp callDateFrom,
			String allocationStatus, ChildValidDataHandler childValidDataHandler) {
		this.obCallID = obCallID;
		this.outboundCallType = outboundCallType;
		this.callDateFrom = callDateFrom;
		this.displayOBCallType = displayOBCallType;
		this.allocationStatus = allocationStatus;
		this.childValidDataHandler = childValidDataHandler;
	}

	public OutboundWorklist(Long obCallID, Integer allocatedUserID, java.util.Date callDateFrom) {
		this.obCallID = obCallID;
		this.allocatedUserID = allocatedUserID;
		this.callDateFrom = new Timestamp(callDateFrom.getTime());
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
		OutboundWorklist other = (OutboundWorklist) obj;
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

	public static Comparator<OutboundWorklist> getSortCompoByCallFromDate() {

		Comparator<OutboundWorklist> comp = new Comparator<OutboundWorklist>() {

			@Override
			public int compare(OutboundWorklist o1, OutboundWorklist o2) {

				if (o1.getCallDateFrom() != null && o2.getCallDateFrom() != null)
					return o1.getCallDateFrom().compareTo(o2.getCallDateFrom());

				else
					return 0;
			}
		};

		return comp;
	}

}
