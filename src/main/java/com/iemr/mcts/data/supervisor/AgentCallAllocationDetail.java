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

import java.util.List;

import jakarta.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.iemr.mcts.utils.mapper.OutputMapper;

public class AgentCallAllocationDetail {

	@SerializedName("allocateNo")
	@Expose
	private Integer allocateNo;
	
	@SerializedName("providerServiceMapID")
	@Expose
	private Long providerServiceMapID;
	
	@SerializedName("userID")
	@Expose
	private List<Integer> userID;
	
	@SerializedName("mctsOutboundCalls")
	@Expose
	private MctsOutboundCall[] mctsOutboundCalls;
	
	@SerializedName("createdBy")
	@Expose
	private String createdBy;

	/**
	 * @return the allocateNo
	 */
	public Integer getAllocateNo() {
		return allocateNo;
	}

	/**
	 * @param allocateNo the allocateNo to set
	 */
	public void setAllocateNo(Integer allocateNo) {
		this.allocateNo = allocateNo;
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
	public List<Integer> getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(List<Integer> userID) {
		this.userID = userID;
	}
	
	/**
	 * @return the mctsOutboundCalls
	 */
	public MctsOutboundCall[] getMctsOutboundCalls() {
		return mctsOutboundCalls;
	}

	/**
	 * @param mctsOutboundCalls the mctsOutboundCalls to set
	 */
	public void setMctsOutboundCalls(MctsOutboundCall[] mctsOutboundCalls) {
		this.mctsOutboundCalls = mctsOutboundCalls;
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
	 * custom OutMapper for json String
	 */
	@Transient
	private static OutputMapper outputMapper = new OutputMapper();

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return outputMapper.gson().toJson(this);
	}
	
}
