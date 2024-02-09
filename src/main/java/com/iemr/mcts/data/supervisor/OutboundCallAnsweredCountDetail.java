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

import com.google.gson.annotations.Expose;
import com.iemr.mcts.utils.mapper.OutputMapper;

@Entity
@Table(name = "t_benCall")
public class OutboundCallAnsweredCountDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name = "BenCallID")
	private Long benCallID;

	@Expose
	@Column(name = "CalledServiceID")
	private Long providerServiceMapID;

	@Expose
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Timestamp createdDate;
	
	@Expose
	@Column(name = "ReceivedAgentID")
	private Long agentID;
	
	@Expose
	@Column(name = "IsVerified")
	private Boolean isVerified;
	
	@Expose
	@Column(name = "CallTypeID")
	private Long callTypeID;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "callTypeID", referencedColumnName = "CallTypeID", insertable = false, updatable = false)
	@Expose
	private CallType callType;

	
	private static OutputMapper outputMapper = new OutputMapper();


	/**
	 * Default Constructor
	 */
	public OutboundCallAnsweredCountDetail() {

	}

	@Override
	public String toString() {

		return outputMapper.gson().toJson(this);
	}


	public Long getProviderServiceMapID() {
		return providerServiceMapID;
	}

	public void setProviderServiceMapID(Long providerServiceMapID) {
		this.providerServiceMapID = providerServiceMapID;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the agentID
	 */
	public Long getAgentID() {
		return agentID;
	}

	/**
	 * @param agentID the agentID to set
	 */
	public void setAgentID(Long agentID) {
		this.agentID = agentID;
	}
	
}
