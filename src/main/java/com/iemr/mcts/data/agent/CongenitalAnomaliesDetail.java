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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.google.gson.annotations.Expose;
import com.iemr.mcts.utils.mapper.OutputMapper;

@Entity
@Table(name="m_congenitalanomalies")
public class CongenitalAnomaliesDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name="CongenitalAnomaliesID")
	private Integer congenitalAnomaliesID;
	
	@Expose 
	@Column(name="CongenitalAnomalies")
	private String congenitalAnomalies;
	
	@Expose 
	@Column(name="CongenitalAnomaliesDesc")
	private String congenitalAnomaliesDesc;
	
	@Expose 
	@Column(name="ServiceID")
	private Integer serviceID;
	 
	@Expose
	@Column(name = "Deleted")
	private Boolean deleted;

	@Expose
	@Column(name = "CreatedBy")
	private String createdBy;

	@Expose
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Date createdDate;

	@Expose
	@Column(name = "ModifiedBy")
	private Boolean modifiedBy;

	@Expose
	@Column(name = "LastModDate")
	private Date lastModDate;
	
	/**
	 * Default Constructor
	 */
	public CongenitalAnomaliesDetail(){
		
	}
	
	/**
	 * Default Constructor
	 */
	public CongenitalAnomaliesDetail(Integer congenitalAnomaliesID, String congenitalAnomalies){
		
		this.congenitalAnomaliesID = congenitalAnomaliesID;
		this.congenitalAnomalies = congenitalAnomalies;
	}

	/**
	 * @return the congenitalAnomaliesID
	 */
	public Integer getCongenitalAnomaliesID() {
		return congenitalAnomaliesID;
	}

	/**
	 * @param congenitalAnomaliesID the congenitalAnomaliesID to set
	 */
	public void setCongenitalAnomaliesID(Integer congenitalAnomaliesID) {
		this.congenitalAnomaliesID = congenitalAnomaliesID;
	}

	/**
	 * @return the congenitalAnomalies
	 */
	public String getCongenitalAnomalies() {
		return congenitalAnomalies;
	}

	/**
	 * @param congenitalAnomalies the congenitalAnomalies to set
	 */
	public void setCongenitalAnomalies(String congenitalAnomalies) {
		this.congenitalAnomalies = congenitalAnomalies;
	}

	/**
	 * @return the congenitalAnomaliesDesc
	 */
	public String getCongenitalAnomaliesDesc() {
		return congenitalAnomaliesDesc;
	}

	/**
	 * @param congenitalAnomaliesDesc the congenitalAnomaliesDesc to set
	 */
	public void setCongenitalAnomaliesDesc(String congenitalAnomaliesDesc) {
		this.congenitalAnomaliesDesc = congenitalAnomaliesDesc;
	}

	/**
	 * @return the serviceID
	 */
	public Integer getServiceID() {
		return serviceID;
	}

	/**
	 * @param serviceID the serviceID to set
	 */
	public void setServiceID(Integer serviceID) {
		this.serviceID = serviceID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return OutputMapper.gson().toJson(this);
	}
}
