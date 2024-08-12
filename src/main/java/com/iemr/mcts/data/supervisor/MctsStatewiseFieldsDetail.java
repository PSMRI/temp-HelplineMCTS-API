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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.google.gson.annotations.Expose;
import com.iemr.mcts.utils.mapper.OutputMapper;

@Entity
@Table(name="m_MctsFieldsStatewise")
public class MctsStatewiseFieldsDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name="SlNo")
	private Integer slNo;
	
	@Expose
	@Column(name="StateID")
	private Integer stateID;
	
	@Expose
	@Column(name="DataFields")
	private String dataFields;
	
	@Expose
	@Column(name="FieldsFor")
	private String fieldFor;
	
	@Expose
	@Column(name="ProviderServiceMapID")
	private Long providerServiceMapID;
	
	@Expose
	@Column(name="Deleted")
	private Boolean deleted;

	@Expose
	@Column(name="CreatedBy")
	private String createdBy;
	
	@Expose
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Date createdDate;
	
	@Expose
	@Column(name="ModifiedBy")
	private String modifiedBy;
	
	@Expose
	@Column(name="LastModDate")
	private Date lastModDate;
	
	/**
	 * Default Constructor
	 */
	public MctsStatewiseFieldsDetail(){
		
	}
	
	/**
	 * Parameter Constructor
	 */
	public MctsStatewiseFieldsDetail(String dataFields, String fieldFor){
		
		this.dataFields = dataFields;
		this.fieldFor = fieldFor;
	}
	
	/**
	 * @return the providerServiceMapID
	 */
	public Long getProviderServiceMapID() {
		return providerServiceMapID;
	}

	/**
	 * @return the dataFields
	 */
	public String getDataFields() {
		return dataFields;
	}

	/**
	 * @return the fieldFor
	 */
	public String getFieldFor() {
		return fieldFor;
	}
	
	/**
	 * @param providerServiceMapID the providerServiceMapID to set
	 */
	public void setProviderServiceMapID(Long providerServiceMapID) {
		this.providerServiceMapID = providerServiceMapID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return OutputMapper.gson().toJson(this);
	}
	
	
	
}
