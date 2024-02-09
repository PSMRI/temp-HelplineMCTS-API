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
@Table(name = "i_benphonemap")
public class BenPhoneMap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BenPhMapID")
	private Long benPhMapID;
	@Column(name = "BenificiaryRegID")
	private Long benificiaryRegID;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "benificiaryRegID")
	private I_Beneficiary i_beneficiary;

	@Expose
	@Column(name = "ParentBenRegID")
	private Long parentBenRegID;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "parentBenRegID")
	private I_Beneficiary i_parentBeneficiary;

	@Expose
	@Column(name = "BenRelationshipID")
	private Short benRelationshipID;

	@Expose
	@Column(name = "PhoneNo")
	private String phoneNo;
	@Column(name = "PhoneTypeID")
	private Short phoneTypeID;
	@Column(name = "Deleted", insertable = false, updatable = true)
	private Boolean deleted;
	@Column(name = "CreatedBy")
	private String createdBy;
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	@Column(name = "NuisanceCallCount")
	private Integer nuisanceCallCount = 0;

	public BenPhoneMap() {
		super();
	}

	/**
	 * @return the benPhMapID
	 */
	public Long getBenPhMapID() {
		return benPhMapID;
	}

	/**
	 * @param benPhMapID the benPhMapID to set
	 */
	public void setBenPhMapID(Long benPhMapID) {
		this.benPhMapID = benPhMapID;
	}

	/**
	 * @return the benificiaryRegID
	 */
	public Long getBenificiaryRegID() {
		return benificiaryRegID;
	}

	/**
	 * @param benificiaryRegID the benificiaryRegID to set
	 */
	public void setBenificiaryRegID(Long benificiaryRegID) {
		this.benificiaryRegID = benificiaryRegID;
	}

	/**
	 * @return the i_beneficiary
	 */
	public I_Beneficiary getI_beneficiary() {
		return i_beneficiary;
	}

	/**
	 * @param i_beneficiary the i_beneficiary to set
	 */
	public void setI_beneficiary(I_Beneficiary i_beneficiary) {
		this.i_beneficiary = i_beneficiary;
	}

	/**
	 * @return the parentBenRegID
	 */
	public Long getParentBenRegID() {
		return parentBenRegID;
	}

	/**
	 * @param parentBenRegID the parentBenRegID to set
	 */
	public void setParentBenRegID(Long parentBenRegID) {
		this.parentBenRegID = parentBenRegID;
	}

	/**
	 * @return the i_parentBeneficiary
	 */
	public I_Beneficiary getI_parentBeneficiary() {
		return i_parentBeneficiary;
	}

	/**
	 * @param i_parentBeneficiary the i_parentBeneficiary to set
	 */
	public void setI_parentBeneficiary(I_Beneficiary i_parentBeneficiary) {
		this.i_parentBeneficiary = i_parentBeneficiary;
	}

	/**
	 * @return the benRelationshipID
	 */
	public Short getBenRelationshipID() {
		return benRelationshipID;
	}

	/**
	 * @param benRelationshipID the benRelationshipID to set
	 */
	public void setBenRelationshipID(Short benRelationshipID) {
		this.benRelationshipID = benRelationshipID;
	}

	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * @return the phoneTypeID
	 */
	public Short getPhoneTypeID() {
		return phoneTypeID;
	}

	/**
	 * @param phoneTypeID the phoneTypeID to set
	 */
	public void setPhoneTypeID(Short phoneTypeID) {
		this.phoneTypeID = phoneTypeID;
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
	 * @return the nuisanceCallCount
	 */
	public Integer getNuisanceCallCount() {
		return nuisanceCallCount;
	}

	/**
	 * @param nuisanceCallCount the nuisanceCallCount to set
	 */
	public void setNuisanceCallCount(Integer nuisanceCallCount) {
		this.nuisanceCallCount = nuisanceCallCount;
	}

	@Override
	public String toString() {
		return OutputMapper.gson().toJson(this);
	}
}
