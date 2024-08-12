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

import java.sql.Blob;

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
@Table(name = "i_Bendemographics")
public class I_BenDemographics {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long benDemographicsID;

	private Long beneficiaryRegID;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "beneficiaryRegID")
	private I_Beneficiary i_beneficiary;
	private Integer educationID;

	private Integer occupationID;
	
	private Integer healthCareWorkerID;

	private Integer incomeStatusID;

	private Blob isPhoto;
	private String photoFilePath;
	private Long isBiometric;
	private String biometricFilePath;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String addressLine4;
	private String addressLine5;
	private Integer cityID;

	@Expose
	@JoinColumn(name = "StateID")
	private Integer stateID;

	private Integer countryID;

	private Integer districtID;
	
	private Integer blockID;
	
	private Integer DistrictBranchID;
	
	private String pinCode;
	private Integer isAddPresent;
	private Integer isAddPermanent;
	private Long deleted;
	private String createdBy;
	private String modifiedBy;

	public Long getBenDemographicsID() {
		return this.benDemographicsID;
	}

	public void setBenDemographicsID(long benDemographicsID) {
		this.benDemographicsID = Long.valueOf(benDemographicsID);
	}

	public Long getBeneficiaryRegID() {
		return this.beneficiaryRegID;
	}

	public void setBeneficiaryRegID(long beneficiaryRegID) {
		this.beneficiaryRegID = Long.valueOf(beneficiaryRegID);
	}

	public Integer getEducationID() {
		return this.educationID;
	}

	public void setEducationID(int educationID) {
		this.educationID = Integer.valueOf(educationID);
	}

	public Integer getOccupationID() {
		return this.occupationID;
	}

	public void setOccupationID(int occupationID) {
		this.occupationID = Integer.valueOf(occupationID);
	}

	public Integer getIncomeStatusID() {
		return this.incomeStatusID;
	}

	public void setIncomeStatusID(Integer incomeStatusID) {
		this.incomeStatusID = incomeStatusID;
	}

	public Blob getIsPhoto() {
		return this.isPhoto;
	}

	public void setIsPhoto(Blob isPhoto) {
		this.isPhoto = isPhoto;
	}

	public String getPhotoFilePath() {
		return this.photoFilePath;
	}

	public void setPhotoFilePath(String photoFilePath) {
		this.photoFilePath = photoFilePath;
	}

	public Long getIsBiometric() {
		return this.isBiometric;
	}

	public void setIsBiometric(long isBiometric) {
		this.isBiometric = Long.valueOf(isBiometric);
	}

	public String getBiometricFilePath() {
		return this.biometricFilePath;
	}

	public void setBiometricFilePath(String biometricFilePath) {
		this.biometricFilePath = biometricFilePath;
	}

	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return this.addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getAddressLine4() {
		return this.addressLine4;
	}

	public void setAddressLine4(String addressLine4) {
		this.addressLine4 = addressLine4;
	}

	public String getAddressLine5() {
		return this.addressLine5;
	}

	public void setAddressLine5(String addressLine5) {
		this.addressLine5 = addressLine5;
	}

	public Integer getCityID() {
		return this.cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}

	public Integer getStateID() {
		return this.stateID;
	}

	public void setStateID(Integer stateID) {
		this.stateID = stateID;
	}

	public Integer getCountryID() {
		return this.countryID;
	}

	public void setCountryID(Integer countryID) {
		this.countryID = countryID;
	}

	public String getPinCode() {
		return this.pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public Integer getIsAddPresent() {
		return this.isAddPresent;
	}

	public void setIsAddPresent(Integer isAddPresent) {
		this.isAddPresent = isAddPresent;
	}

	public Integer getIsAddPermanent() {
		return this.isAddPermanent;
	}

	public void setIsAddPermanent(Integer isAddPermanent) {
		this.isAddPermanent = isAddPermanent;
	}

	public Long getDeleted() {
		return this.deleted;
	}

	public void setDeleted(long deleted) {
		this.deleted = Long.valueOf(deleted);
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Integer getDistrictID() {
		return districtID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return OutputMapper.gson().toJson(this);
	}
	
}
