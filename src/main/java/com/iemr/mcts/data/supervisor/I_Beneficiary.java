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
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.google.gson.annotations.Expose;
import com.iemr.mcts.utils.mapper.OutputMapper;

@Entity
@Table(name = "I_Beneficiary")
public class I_Beneficiary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BeneficiaryRegID")
	@Expose
	private Long beneficiaryRegID;
	
	@Expose
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "i_beneficiary")
	private List<I_BenDemographics> i_bendemographics;
	
	@Expose
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "i_beneficiary")
	private List<BenPhoneMap> benPhoneMaps;
	
	@Column(name = "BeneficiaryID")
	@Expose
	private String beneficiaryID;
	@Column(name = "TitleId")
	@Expose
	private Short titleId;
	@Column(name = "FirstName")
	@Expose
	private String firstName;
	@Column(name = "MiddleName")
	@Expose
	private String middleName;
	@Column(name = "LastName")
	@Expose
	private String lastName;
	
	@Column(name = "StatusID")
	@Expose
	private Short statusID;

	@Column(name = "GenderID")
	@Expose
	private Short genderID;

	@Column(name = "MaritalStatusID")
	@Expose
	private Short maritalStatusID;
	
	@Column(name = "DOB")
	@Expose
	private Date dOB;
	
	@Column(name = "FatherName")
	@Expose
	private String fatherName;
	@Column(name = "SpouseName")
	@Expose
	private String spouseName;
	@Column(name = "AadharNo")
	@Expose
	private String aadharNo;
	@Column(name = "GovtIdentityNo")
	@Expose
	private String govtIdentityNo;
	@Column(name = "GovtIdentityTypeID")
	@Expose
	private Short govtIdentityTypeID;
	@Column(name = "RegisteredServiceID")
	@Expose
	private Short registeredServiceID;
	@Column(name = "EmergencyRegistration")
	@Expose
	private Boolean emergencyRegistration;
	private Boolean deleted;
	
	@Column(name = "CreatedBy")
	@Expose
	private String createdBy;
	private String modifiedBy;
	/**
	 * @return the i_bendemographics
	 */
	public List<I_BenDemographics> getI_bendemographics() {
		return i_bendemographics;
	}
	/**
	 * @param i_bendemographics the i_bendemographics to set
	 */
	public void setI_bendemographics(List<I_BenDemographics> i_bendemographics) {
		this.i_bendemographics = i_bendemographics;
	}
	/**
	 * @return the beneficiaryID
	 */
	public String getBeneficiaryID() {
		return beneficiaryID;
	}
	/**
	 * @param beneficiaryID the beneficiaryID to set
	 */
	public void setBeneficiaryID(String beneficiaryID) {
		this.beneficiaryID = beneficiaryID;
	}
	/**
	 * @return the titleId
	 */
	public Short getTitleId() {
		return titleId;
	}
	/**
	 * @param titleId the titleId to set
	 */
	public void setTitleId(Short titleId) {
		this.titleId = titleId;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the dOB
	 */
	public Date getdOB() {
		return dOB;
	}
	/**
	 * @param dOB the dOB to set
	 */
	public void setdOB(Date dOB) {
		this.dOB = dOB;
	}
	/**
	 * @return the fatherName
	 */
	public String getFatherName() {
		return fatherName;
	}
	/**
	 * @param fatherName the fatherName to set
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	/**
	 * @return the spouseName
	 */
	public String getSpouseName() {
		return spouseName;
	}
	/**
	 * @param spouseName the spouseName to set
	 */
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
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
	 * @return the benPhoneMaps
	 */
	public List<BenPhoneMap> getBenPhoneMaps() {
		return benPhoneMaps;
	}
	/**
	 * @param benPhoneMaps the benPhoneMaps to set
	 */
	public void setBenPhoneMaps(List<BenPhoneMap> benPhoneMaps) {
		this.benPhoneMaps = benPhoneMaps;
	}
	/**
	 * @return the genderID
	 */
	public Short getGenderID() {
		return genderID;
	}
	/**
	 * @param genderID the genderID to set
	 */
	public void setGenderID(Short genderID) {
		this.genderID = genderID;
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
	 * @return the aadharNo
	 */
	public String getAadharNo() {
		return aadharNo;
	}
	/**
	 * @param aadharNo the aadharNo to set
	 */
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	
	/**
	 * @return the registeredServiceID
	 */
	public Short getRegisteredServiceID() {
		return registeredServiceID;
	}
	/**
	 * @param registeredServiceID the registeredServiceID to set
	 */
	public void setRegisteredServiceID(Short registeredServiceID) {
		this.registeredServiceID = registeredServiceID;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return OutputMapper.gson().toJson(this);
	}

	
}