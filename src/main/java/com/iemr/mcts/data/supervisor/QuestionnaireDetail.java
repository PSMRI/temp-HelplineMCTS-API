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
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.mcts.data.agent.MctsCallResponseDetail;
import com.iemr.mcts.utils.mapper.OutputMapper;

/**
 * 
 * @author WA875423
 *
 */
@Entity
@Table(name = "m_questionnaire")
public class QuestionnaireDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name = "QuestionID")
	private Integer questionID;

	@Expose
	@Column(name = "Question")
	private String question;

	@Expose
	@Column(name = "QuestionDesc")
	private String questionDesc;

	@Expose
	@Column(name = "QuestionTypeID")
	private Integer questionTypeID;

	@Expose
	@Column(name = "AnswerType")
	private String answerType;

	@Expose
	@Column(name = "TriggerFeedback")
	private Boolean triggerFeedback;
	
	@Expose
	@Column(name = "ShowText")
	private Boolean showText;

	@Expose
	@Column(name = "TriggerFeedbackFor")
	private String triggerFeedbackFor;
	
	@Expose
	@Column(name = "ShowTextFor")
	private String showTextFor;
	
	@Expose
	@Column(name = "Interaction")
	private String interaction;

	@Expose
	@Column(name = "ProviderServiceMapID")
	private Long providerServiceMapID;

	@Expose
	@Column(name = "Deleted")
	private Boolean deleted;

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
	private Boolean modifiedBy;

	@Expose
	@Column(name = "LastModDate")
	private Date lastModDate;
	
	@Expose
	@Column(name = "isChild")
	private Boolean isChild = false;


	@Transient
	@Expose
	private ArrayList<MctsQuestionValues> questionOptions;
	
	@Expose
	@Column(name = "QuestionRank")
	private Integer questionRank;
	
	@Expose
	@Column(name = "isMandatory")
	private Boolean isMandatory;
	
	@Expose
	@Column(name = "parentQuestionID")
	private Integer parentQuestionID;
	
	@Expose
	@Column(name = "parentAnswer")
	private String parentAnswer;	
	
	@Transient
	@Expose
	private Integer childQuestionID;
	
	
	
	@Expose
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(insertable = false, updatable = false,name = "questionID", referencedColumnName="questionID")
	private List<MctsQuestionValues> mctsQuestionValues ;

	/**
	 * mcts qa mapping
	 */
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "questionnaireDetail")
	private MctsQAMappingDetail mctsQAMappingDetail;
	
	/**
	 * mcts call qa mapping
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "questionnaireDetail") // for now not required
	private List<MctsCallResponseDetail> mctsCallQAMappingDetails;

	/**
	 * Default Constructor
	 */
	public QuestionnaireDetail() {
	}

	/**
	 * constructor with parameters
	 */
	public QuestionnaireDetail(Integer questionID, String question, String questionDesc) {

		this.questionID = questionID;
		this.question = question;
		this.questionDesc = questionDesc;
	}

	/**
	 * @return the questionID
	 */
	public Integer getQuestionID() {
		return questionID;
	}

	/**
	 * @param questionID
	 *            the questionID to set
	 */
	public void setQuestionID(Integer questionID) {
		this.questionID = questionID;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the questionDesc
	 */
	public String getQuestionDesc() {
		return questionDesc;
	}

	/**
	 * @param questionDesc
	 *            the questionDesc to set
	 */
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	/**
	 * @return the questionTypeID
	 */
	public Integer getQuestionTypeID() {
		return questionTypeID;
	}

	/**
	 * @param questionTypeID
	 *            the questionTypeID to set
	 */
	public void setQuestionTypeID(Integer questionTypeID) {
		this.questionTypeID = questionTypeID;
	}

	/**
	 * @return the answerType
	 */
	public String getAnswerType() {
		return answerType;
	}

	/**
	 * @param answerType
	 *            the answerType to set
	 */
	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}

	/**
	 * @return the triggerFeedback
	 */
	public Boolean getTriggerFeedback() {
		return triggerFeedback;
	}

	/**
	 * @param triggerFeedback
	 *            the triggerFeedback to set
	 */
	public void setTriggerFeedback(Boolean triggerFeedback) {
		this.triggerFeedback = triggerFeedback;
	}

	/**
	 * @return the triggerFeedbackFor
	 */
	public String getTriggerFeedbackFor() {
		return triggerFeedbackFor;
	}

	/**
	 * @param triggerFeedbackFor
	 *            the triggerFeedbackFor to set
	 */
	public void setTriggerFeedbackFor(String triggerFeedbackFor) {
		this.triggerFeedbackFor = triggerFeedbackFor;
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
	 * @return the deleted
	 */
	public Boolean getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted
	 *            the deleted to set
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the processed
	 */
	public String getProcessed() {
		return processed;
	}

	/**
	 * @param processed
	 *            the processed to set
	 */
	public void setProcessed(String processed) {
		this.processed = processed;
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
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedBy
	 */
	public Boolean getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy
	 *            the modifiedBy to set
	 */
	public void setModifiedBy(Boolean modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the lastModDate
	 */
	public Date getLastModDate() {
		return lastModDate;
	}

	/**
	 * @param lastModDate
	 *            the lastModDate to set
	 */
	public void setLastModDate(Date lastModDate) {
		this.lastModDate = lastModDate;
	}
	
	/**
	 * @return the mctsQAMappingDetail
	 */
	public MctsQAMappingDetail getMctsQAMappingDetail() {
		return mctsQAMappingDetail;
	}

	/**
	 * @param mctsQAMappingDetail the mctsQAMappingDetail to set
	 */
	public void setMctsQAMappingDetail(MctsQAMappingDetail mctsQAMappingDetail) {
		this.mctsQAMappingDetail = mctsQAMappingDetail;
	}

	/**
	 * @return the showText
	 */
	public Boolean getShowText() {
		return showText;
	}

	/**
	 * @param showText the showText to set
	 */
	public void setShowText(Boolean showText) {
		this.showText = showText;
	}

	/**
	 * @return the showTextFor
	 */
	public String getShowTextFor() {
		return showTextFor;
	}

	/**
	 * @param showTextFor the showTextFor to set
	 */
	public void setShowTextFor(String showTextFor) {
		this.showTextFor = showTextFor;
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

	public ArrayList<MctsQuestionValues> getQuestionOptions() {
		return questionOptions;
	}

	public void setQuestionOptions(ArrayList<MctsQuestionValues> questionOptions) {
		this.questionOptions = questionOptions;
	}

	public Integer getQuestionRank() {
		return questionRank;
	}

	public void setQuestionRank(Integer questionRank) {
		this.questionRank = questionRank;
	}

	public Boolean getIsMandatory() {
		return isMandatory;
	}

	public void setIsMandatory(Boolean isMandatory) {
		this.isMandatory = isMandatory;
	}
	
	public List<MctsQuestionValues> getMctsQuestionValues() {
		return mctsQuestionValues;
	}

	public void setMctsQuestionValues(List<MctsQuestionValues> mctsQuestionValues) {
		this.mctsQuestionValues = mctsQuestionValues;
	}

	public Integer getParentQuestionID() {
		return parentQuestionID;
	}

	public void setParentQuestionID(Integer parentQuestionID) {
		this.parentQuestionID = parentQuestionID;
	}

	public String getParentAnswer() {
		return parentAnswer;
	}

	public void setParentAnswer(String parentAnswer) {
		this.parentAnswer = parentAnswer;
	}

	public Integer getChildQuestionID() {
		return childQuestionID;
	}

	public void setChildQuestionID(Integer childQuestionID) {
		this.childQuestionID = childQuestionID;
	}

	public String getInteraction() {
		return interaction;
	}

	public void setInteraction(String interaction) {
		this.interaction = interaction;
	}
	
	public Boolean getIsChild() {
		return isChild;
	}

	public void setIsChild(Boolean isChild) {
		this.isChild = isChild;
	}

}
