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
@Table(name = "m_MctsQAMapping")
public class MctsQAMappingDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name = "MctsQAMapID")
	private Long mctsQAMapID;

	@Expose
	@Column(name = "QuestionID", insertable = true, updatable = false)
	private Integer questionID;

	@Expose
	@Column(name = "Interaction")
	private String interaction;

	@Expose
	@Column(name = "VariableName")
	private String variableName;

	@Expose
	@Column(name = "VariableDataType")
	private String variableDataType;

	@Expose
	@Column(name = "ProviderServiceMapID")
	private Long providerServiceMapID;

	@Expose
	@Column(name = "OutboundCallType")
	private String outboundCallType;

	@Expose
	@Column(name = "EffectiveFrom")
	private Date effectiveFrom;

	@Expose
	@Column(name = "EffectiveUpto")
	private Date effectiveUpto;

	@Expose
	@Column(name = "Deleted", insertable = true, updatable = true)
	private boolean deleted;

	@Expose
	@Column(name = "Processed", insertable = false, updatable = true)
	private String processed;

	@Expose
	@Column(name = "CreatedBy")
	private String createdBy;

	@Expose
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Date createdDate;

	@Expose
	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Expose
	@Column(name = "LastModDate")
	private Date lastModDate;

	@Expose
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "questionID", insertable = false, updatable = false)
	private QuestionnaireDetail questionnaireDetail;

	@Expose
	@Column(name = "DisplayOBCallType")
	private String displayOBCallType;

	/**
	 * Default Constructor
	 */
	public MctsQAMappingDetail() {

	}

	/**
	 * @return the questionID
	 */
	public Integer getQuestionID() {
		return questionID;
	}

	/**
	 * @param questionID the questionID to set
	 */
	public void setQuestionID(Integer questionID) {
		this.questionID = questionID;
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
	 * @return the questionnaireDetail
	 */
	public QuestionnaireDetail getQuestionnaireDetail() {
		return questionnaireDetail;
	}

	/**
	 * @param questionnaireDetail the questionnaireDetail to set
	 */
	public void setQuestionnaireDetail(QuestionnaireDetail questionnaireDetail) {
		this.questionnaireDetail = questionnaireDetail;
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
	 * @return the variableName
	 */
	public String getVariableName() {
		return variableName;
	}

	/**
	 * @param variableName the variableName to set
	 */
	public void setVariableName(String variableName) {
		this.variableName = variableName;
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
	 * @return the variableDataType
	 */
	public String getVariableDataType() {
		return variableDataType;
	}

	/**
	 * @param variableDataType the variableDataType to set
	 */
	public void setVariableDataType(String variableDataType) {
		this.variableDataType = variableDataType;
	}

	/**
	 * @return the interaction
	 */
	public String getInteraction() {
		return interaction;
	}

	/**
	 * @param interaction the interaction to set
	 */
	public void setInteraction(String interaction) {
		this.interaction = interaction;
	}

	/**
	 * @return the mctsQAMapID
	 */
	public Long getMctsQAMapID() {
		return mctsQAMapID;
	}

	/**
	 * @param mctsQAMapID the mctsQAMapID to set
	 */
	public void setMctsQAMapID(Long mctsQAMapID) {
		this.mctsQAMapID = mctsQAMapID;
	}

	private static OutputMapper outputMapper = new OutputMapper();

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

}
