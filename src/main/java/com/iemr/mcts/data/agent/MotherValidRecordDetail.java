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

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.google.gson.annotations.Expose;
import com.iemr.mcts.utils.mapper.OutputMapper;

@SuppressWarnings("serial")
@Entity
@Table(name = "t_MotherValidRecord")
public class MotherValidRecordDetail implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MotherValidRecordID")
	@Expose
	private Long motherValidRecordID;

	@Column(name = "RowID")
	@Expose
	private Long rowID;

	@Column(name = "Date_of_Entry")
	@Expose
	private Date dateOfEntry;

	@Expose
	@Column(name = "Entry_type")
	private Long entryTpe;

	@Expose
	@Column(name = "MCTSID_no", insertable = true, updatable = false)
	private Long mctsIDNo;

	@Expose
	@Column(name = "Name")
	private String name;

	@Expose
	@Column(name = "Husband_Name")
	private String husbandName;

	@Expose
	@Column(name = "PhoneNo_Of_Whom")
	private String phoneNoOfWhom;

	@Expose
	@Column(name = "Whom_PhoneNo")
	private String whomPhoneNo;

	@Expose
	@Column(name = "Birth_Date")
	private Date birthDate;

	@Expose
	@Column(name = "Age")
	private Long age;

	@Expose
	@Column(name = "Blood_Group")
	private String bloodGroup;

	@Expose
	@Column(name = "Caste")
	private String caste;

	@Expose
	@Column(name = "Aadhar_no")
	private String aadharNo;

	@Expose
	@Column(name = "State_ID")
	private Long stateID;

	@Expose
	@Column(name = "State_Name")
	private String stateName;

	@Expose
	@Column(name = "District_ID")
	private Long districtID;

	@Expose
	@Column(name = "District_Name")
	private String districtName;

	@Expose
	@Column(name = "Taluka_Name")
	private String talukaName;

	@Expose
	@Column(name = "Taluka_ID")
	private Long talukaID;

	@Expose
	@Column(name = "Block_ID")
	private Long blockID;

	@Expose
	@Column(name = "Block_Name")
	private String blockName;

	@Expose
	@Column(name = "SubCenter_Name")
	private String subCenterName;

	@Column(name = "Village_ID")
	private Long villageID;

	@Column(name = "Village_Name")
	private String VillageName;
	@Column(name = "City_ID")
	private Long CityID;

	@Expose
	@Column(name = "City_Name")
	private String cityName;

	@Expose
	@Column(name = "ANM_Name")
	private String anmName;

	@Expose
	@Column(name = "ANM_Ph")
	private String anmPh;

	@Expose
	@Column(name = "ASHA_Name")
	private String ashaName;

	@Expose
	@Column(name = "ASHA_Ph")
	private String ashaPh;
	@Column(name = "PHC_ID")
	private String PHCID;
	@Column(name = "PHC_Name")
	private String PHCName;
	@Column(name = "SUBPHC_ID")
	private String SUBPHCID;
	@Column(name = "SUBPHC_Name")
	private String SUBPHCName;
	@Column(name = "YR")
	private Character yR;
	@Column(name = "GP_Village")
	private String GPVillage;
	@Column(name = "Address")
	private String address;

	@Expose
	@Column(name = "LMP_Date")
	private Date lmpDate;

	@Expose
	@Column(name = "ANC1_Date")
	private Date anc1Date;

	@Expose
	@Column(name = "ANC2_Date")
	private Date anc2Date;

	@Expose
	@Column(name = "ANC3_Date")
	private Date anc3Date;

	@Expose
	@Column(name = "ANC4_Date")
	private Date anc4Date;

	@Expose
	@Column(name = "ANC_Complication")
	private Boolean ancComplication;

	@Expose
	@Column(name = "TT1_Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd\'T\'HH:mm:ss.SSS")
	private Date TT1Date;

	@Expose
	@Column(name = "TT2_Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd\'T\'HH:mm:ss.SSS")
	private Date TT2Date;

	@Expose
	@Column(name = "TTBooster_Date")
	private Date TTBoosterDate;

	@Expose
	@Column(name = "IFA100_Given_Date")
	private Date IFA100GivenDate;

	@Expose
	@Column(name = "EDD")
	private Date edd;

	@Expose
	@Column(name = "Anemia")
	private Boolean anemia;

	@Expose
	@Column(name = "RTI_STI")
	private Character RTISTI;

	@Expose
	@Column(name = "Delivery_Lnk_Facility")
	private String DeliveryLnkFacility;

	@Expose
	@Column(name = "Facility_Name")
	private String FacilityName;

	@Expose
	@Column(name = "Delivery_Date")
	private Date deliveryDate;

	@Expose
	@Column(name = "Delivery_Place_home_type")
	private String DeliveryPlacehomeType;

	@Expose
	@Column(name = "Delivery_Place_Public")
	private String DeliveryPlacePublic;

	@Expose
	@Column(name = "Delivery_Place_Private")
	private String DeliveryPlacePrivate;

	@Expose
	@Column(name = "Delivery_type")
	private String DeliveryType;

	@Expose
	@Column(name = "Delivery_Complications")
	private String DeliveryComplications;
	@Column(name = "Discharge_Date")
	private Date DischargeDate;
	@Column(name = "Abortion")
	private String abortion;
	@Column(name = "PNC_Home_Visit")
	private String PNCHomeVisit;
	@Column(name = "PNC_Complication")
	private String PNCComplication;
	@Column(name = "PPC_Method")
	private String PPCMethod;
	@Column(name = "PNC_Checkup")
	private Boolean PNCCheckup;
	@Column(name = "Outcome_Nos")
	private Long OutcomeNos;
	@Column(name = "Child1_ID")
	private Long child1ID;
	@Column(name = "Child1_Name")
	private String Child1Name;
	@Column(name = "Child1_Gender")
	private String Child1Gender;
	@Column(name = "Child1_Weight")
	private Long Child1Weight;
	@Column(name = "Child1_BreastFeeding")
	private Boolean Child1BreastFeeding;
	@Column(name = "Child2_ID")
	private Long child2ID;
	@Column(name = "Child2_Name")
	private String Child2Name;
	@Column(name = "Child2_Gender")
	private String Child2Gender;
	@Column(name = "Child2_weight")
	private Long Child2Weight;
	@Column(name = "Child2_BreastFeeding")
	private Boolean Child2BreastFeeding;
	@Column(name = "Child3_ID")
	private Long child3ID;
	@Column(name = "Child3_Name")
	private String Child3Name;
	@Column(name = "Child3_Gender")
	private String Child3Gender;
	@Column(name = "Child3_Weight")
	private Long Child3Weight;
	@Column(name = "Child3_BreastFeeding")
	private Boolean Child3BreastFeeding;
	@Column(name = "Child4_ID")
	private Long child4ID;
	@Column(name = "Child4_Name")
	private String Child4Name;
	@Column(name = "Child4_Gender")
	private String Child4Gender;
	@Column(name = "Child4_Weight")
	private Long Child4Weight;
	@Column(name = "Child4_BreastFeeding")
	private Boolean Child4BreastFeeding;
	@Column(name = "Mother_Reg_Date")
	private Date MotherRegDate;
	@Column(name = "Last_Update_Date")
	private Date LastUpdateDate;
	@Column(name = "EID")
	private String eID;
	@Column(name = "EID_time")
	private Date EIDTime;
	@Column(name = "CPSMS_Flag")
	private Boolean CPSMSFlag;
	@Column(name = "JSY_Beneficiary")
	private Character JSYBeneficiary;
	@Column(name = "JSY_Paid_Date")
	private Date JSYPaidDate;
	@Column(name = "Bank_Name")
	private String BankName;
	@Column(name = "Bank_Branch_Name")
	private String BankBranchName;
	@Column(name = "Acc_No")
	private String AccNo;
	@Column(name = "IFSC_Code")
	private String IFSCCode;
	@Column(name = "Remarks")
	private String remarks;
	@Column(name = "Referred_By_Telecaller")
	private Boolean ReferredByTelecaller;
	@Column(name = "Referred_Date")
	private Date ReferredDate;
	@Column(name = "No_of_Try")
	private Long NoofTry;
	@Column(name = "Call_Answered")
	private Boolean CallAnswered;
	@Column(name = "Status")
	private String status;

	@Expose
	@Column(name = "High_Risk")
	private Boolean highRisk;

	@Expose
	@Column(name = "High_Risk_Reason")
	private String highRiskReason;

	@Column(name = "Call_Verified")
	private Boolean CallVerified;
	@Column(name = "Associate")
	private String associate;
	@Column(name = "Call_Date")
	private Date CallDate;
	@Column(name = "Due_Services")
	private String DueServices;
	@Column(name = "Due_Services_Response")
	private String DueServicesResponse;
	@Column(name = "Overdue_Services")
	private String OverdueServices;
	@Column(name = "Overdue_Services_Response")
	private String OverdueServicesResponse;
	@Column(name = "Given_Services")
	private String GivenServices;
	@Column(name = "Given_Services_Response")
	private String GivenServicesResponse;
	@Column(name = "Miscarriage")
	private Boolean miscarriage;
	@Column(name = "Baby_Died")
	private Boolean BabyDied;
	@Column(name = "Call_No")
	private String CallNo;
	@Column(name = "Questions_Asked")
	private String QuestionsAsked;
	@Column(name = "Asnwer_Given_by_Benificary")
	private String AsnwerGivenByBenificary;
	@Column(name = "Source")
	private String source;
	@Column(name = "NoCall_Reason")
	private String NoCallReason;
	@Column(name = "NoPhone_Reason")
	private String NoPhoneReason;
	@Column(name = "Created_By")
	private String CreatedBy;
	@Column(name = "Updated_By")
	private String UpdatedBy;
	@Column(name = "BPL_APL")
	private String BPLAPL;
	@Column(name = "MDDS_StateID")
	private String MDDSStateID;
	@Column(name = "MDDS_District_ID")
	private String MDDSDistrictID;
	@Column(name = "MDDS_Taluka_ID")
	private String MDDSTalukaID;
	@Column(name = "MDDS_Village_ID")
	private String MDDSVillageID;
	@Column(name = "Is_Valid")
	private Boolean IsValid;
	@Column(name = "Is_Error")
	private Boolean IsError;
	@Column(name = "Error_Reason")
	private String ErrorReason;

	@Column(name = "FileID")
	private Long fileID;
	@Column(name = "Deleted")
	private Boolean deleted;
	@Column(name = "CreatedBy")
	private String createdby;
	@Column(name = "CreatedDate")
	private Date createdDate;
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	@Column(name = "LastModDate")
	private Date lastModDate;

	@Expose
	@Column(name = "IsAllocated")
	private Boolean isAllocated;

	public MotherValidRecordDetail() {

	}

	public MotherValidRecordDetail(Long rowID, Long mctsIDNo) {

		this.rowID = rowID;
		this.mctsIDNo = mctsIDNo;
	}

	/**
	 * @param mCTSIDNo
	 * @param name
	 * @param husbandName
	 * @param phoneNoOfWhom
	 * @param whomPhoneNo
	 * @param districtName
	 * @param blockName
	 * @param subCenterName
	 * @param aNMName
	 * @param aSHAName
	 * @param pHCName
	 * @param gPVillage
	 * @param address
	 * @param lmpDate
	 * @param child1id
	 * @param child1Nam
	 */

	public MotherValidRecordDetail(Long mctsIDNo, String name, String husbandName, String districtName,
			String blockName, String pHCName, String subCenterName, String gPVillage, String address,
			String whomPhoneNo, String phoneNoOfWhom, String aSHAName, String aNMName, Long child1id, String child1Name,
			Date lmpDate) {

		this.mctsIDNo = mctsIDNo;
		this.name = name;
		this.husbandName = husbandName;
		this.phoneNoOfWhom = phoneNoOfWhom;
		this.whomPhoneNo = whomPhoneNo;
		this.districtName = districtName;
		this.blockName = blockName;
		this.subCenterName = subCenterName;
		this.anmName = aNMName;
		this.ashaName = aSHAName;
		this.PHCName = pHCName;
		this.GPVillage = gPVillage;
		this.address = address;
		this.child1ID = child1id;
		this.Child1Name = child1Name;
		this.lmpDate = lmpDate;
	}

	public MotherValidRecordDetail(Long motherValidRecordID, Long mctsIDNo, String name, String husbandName,
			String phoneNoOfWhom, String whomPhoneNo, Long stateID, String stateName, java.util.Date lmpDate,
			java.util.Date edd, Boolean highRisk, Boolean isAllocated, Boolean deleted) {

		this.motherValidRecordID = motherValidRecordID;
		this.mctsIDNo = mctsIDNo;
		this.name = name;
		this.husbandName = husbandName;
		this.phoneNoOfWhom = phoneNoOfWhom;
		this.whomPhoneNo = whomPhoneNo;
		this.stateID = stateID;
		this.stateName = stateName;
		this.lmpDate = this.convertUtilDatetoSqlDate(lmpDate);
		this.edd = this.convertUtilDatetoSqlDate(edd);
		this.highRisk = highRisk;
		this.isAllocated = isAllocated;
		this.deleted = deleted;
	}

	/**
	 * @return the motherValidRecordID
	 */
	public Long getMotherValidRecordID() {
		return motherValidRecordID;
	}

	/**
	 * @param motherValidRecordID the motherValidRecordID to set
	 */
	public void setMotherValidRecordID(Long motherValidRecordID) {
		this.motherValidRecordID = motherValidRecordID;
	}

	/**
	 * @return the edd
	 */
	public Date getEdd() {
		return edd;
	}

	/**
	 * @param edd the edd to set
	 */
	public void setEdd(Date edd) {
		this.edd = edd;
	}

	/**
	 * @return the rowID
	 */
	public Long getRowID() {
		return rowID;
	}

	/**
	 * @param rowID the rowID to set
	 */
	public void setRowID(Long rowID) {
		this.rowID = rowID;
	}

	/**
	 * @return the dateOfEntry
	 */
	public Date getDateOfEntry() {
		return dateOfEntry;
	}

	/**
	 * @param dateOfEntry the dateOfEntry to set
	 */
	public void setDateOfEntry(Date dateOfEntry) {
		this.dateOfEntry = dateOfEntry;
	}

	/**
	 * @return the entryTpe
	 */
	public Long getEntryTpe() {
		return entryTpe;
	}

	/**
	 * @param entryTpe the entryTpe to set
	 */
	public void setEntryTpe(Long entryTpe) {
		this.entryTpe = entryTpe;
	}

	/**
	 * @return the mctsIDNo
	 */
	public Long getMctsIDNo() {
		return mctsIDNo;
	}

	/**
	 * @param mctsIDNo the mctsIDNo to set
	 */
	public void setMctsIDNo(Long mctsIDNo) {
		this.mctsIDNo = mctsIDNo;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the husbandName
	 */
	public String getHusbandName() {
		return husbandName;
	}

	/**
	 * @param husbandName the husbandName to set
	 */
	public void setHusbandName(String husbandName) {
		this.husbandName = husbandName;
	}

	/**
	 * @return the phoneNoOfWhom
	 */
	public String getPhoneNoOfWhom() {
		return phoneNoOfWhom;
	}

	/**
	 * @param phoneNoOfWhom the phoneNoOfWhom to set
	 */
	public void setPhoneNoOfWhom(String phoneNoOfWhom) {
		this.phoneNoOfWhom = phoneNoOfWhom;
	}

	/**
	 * @return the whomPhoneNo
	 */
	public String getWhomPhoneNo() {
		return whomPhoneNo;
	}

	/**
	 * @param whomPhoneNo the whomPhoneNo to set
	 */
	public void setWhomPhoneNo(String whomPhoneNo) {
		this.whomPhoneNo = whomPhoneNo;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the age
	 */
	public Long getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Long age) {
		this.age = age;
	}

	/**
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * @param bloodGroup the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	/**
	 * @return the caste
	 */
	public String getCaste() {
		return caste;
	}

	/**
	 * @param caste the caste to set
	 */
	public void setCaste(String caste) {
		this.caste = caste;
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
	 * @return the stateID
	 */
	public Long getStateID() {
		return stateID;
	}

	/**
	 * @param stateID the stateID to set
	 */
	public void setStateID(Long stateID) {
		this.stateID = stateID;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @return the districtID
	 */
	public Long getDistrictID() {
		return districtID;
	}

	/**
	 * @param districtID the districtID to set
	 */
	public void setDistrictID(Long districtID) {
		this.districtID = districtID;
	}

	/**
	 * @return the districtName
	 */
	public String getDistrictName() {
		return districtName;
	}

	/**
	 * @param districtName the districtName to set
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	/**
	 * @return the villageName
	 */
	public String getVillageName() {
		return VillageName;
	}

	/**
	 * @param villageName the villageName to set
	 */
	public void setVillageName(String villageName) {
		VillageName = villageName;
	}

	/**
	 * @return the cityID
	 */
	public Long getCityID() {
		return CityID;
	}

	/**
	 * @param cityID the cityID to set
	 */
	public void setCityID(Long cityID) {
		CityID = cityID;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the anmName
	 */
	public String getAnmName() {
		return anmName;
	}

	/**
	 * @param anmName the anmName to set
	 */
	public void setAnmName(String anmName) {
		this.anmName = anmName;
	}

	/**
	 * @return the anmPh
	 */
	public String getAnmPh() {
		return anmPh;
	}

	/**
	 * @param anmPh the anmPh to set
	 */
	public void setAnmPh(String anmPh) {
		this.anmPh = anmPh;
	}

	/**
	 * @return the ashaName
	 */
	public String getAshaName() {
		return ashaName;
	}

	/**
	 * @param ashaName the ashaName to set
	 */
	public void setAshaName(String ashaName) {
		this.ashaName = ashaName;
	}

	/**
	 * @return the ashaPh
	 */
	public String getAshaPh() {
		return ashaPh;
	}

	/**
	 * @param ashaPh the ashaPh to set
	 */
	public void setAshaPh(String ashaPh) {
		this.ashaPh = ashaPh;
	}

	/**
	 * @return the pHCID
	 */
	public String getPHCID() {
		return PHCID;
	}

	/**
	 * @param pHCID the pHCID to set
	 */
	public void setPHCID(String pHCID) {
		PHCID = pHCID;
	}

	/**
	 * @return the pHCName
	 */
	public String getPHCName() {
		return PHCName;
	}

	/**
	 * @param pHCName the pHCName to set
	 */
	public void setPHCName(String pHCName) {
		PHCName = pHCName;
	}

	/**
	 * @return the sUBPHCID
	 */
	public String getSUBPHCID() {
		return SUBPHCID;
	}

	/**
	 * @param sUBPHCID the sUBPHCID to set
	 */
	public void setSUBPHCID(String sUBPHCID) {
		SUBPHCID = sUBPHCID;
	}

	/**
	 * @return the sUBPHCName
	 */
	public String getSUBPHCName() {
		return SUBPHCName;
	}

	/**
	 * @param sUBPHCName the sUBPHCName to set
	 */
	public void setSUBPHCName(String sUBPHCName) {
		SUBPHCName = sUBPHCName;
	}

	/**
	 * @return the yR
	 */
	public Character getyR() {
		return yR;
	}

	/**
	 * @param yR the yR to set
	 */
	public void setyR(Character yR) {
		this.yR = yR;
	}

	/**
	 * @return the gPVillage
	 */
	public String getGPVillage() {
		return GPVillage;
	}

	/**
	 * @param gPVillage the gPVillage to set
	 */
	public void setGPVillage(String gPVillage) {
		GPVillage = gPVillage;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the lmpDate
	 */
	public Date getLmpDate() {
		return lmpDate;
	}

	/**
	 * @param lmpDate the lmpDate to set
	 */
	public void setLmpDate(Date lmpDate) {
		this.lmpDate = lmpDate;
	}

	/**
	 * @return the anc1Date
	 */
	public Date getAnc1Date() {
		return anc1Date;
	}

	/**
	 * @param anc1Date the anc1Date to set
	 */
	public void setAnc1Date(Date anc1Date) {
		this.anc1Date = anc1Date;
	}

	/**
	 * @return the anc2Date
	 */
	public Date getAnc2Date() {
		return anc2Date;
	}

	/**
	 * @param anc2Date the anc2Date to set
	 */
	public void setAnc2Date(Date anc2Date) {
		this.anc2Date = anc2Date;
	}

	/**
	 * @return the anc3Date
	 */
	public Date getAnc3Date() {
		return anc3Date;
	}

	/**
	 * @param anc3Date the anc3Date to set
	 */
	public void setAnc3Date(Date anc3Date) {
		this.anc3Date = anc3Date;
	}

	/**
	 * @return the anc4Date
	 */
	public Date getAnc4Date() {
		return anc4Date;
	}

	/**
	 * @param anc4Date the anc4Date to set
	 */
	public void setAnc4Date(Date anc4Date) {
		this.anc4Date = anc4Date;
	}

	/**
	 * @return the ancComplication
	 */
	public Boolean getAncComplication() {
		return ancComplication;
	}

	/**
	 * @param ancComplication the ancComplication to set
	 */
	public void setAncComplication(Boolean ancComplication) {
		this.ancComplication = ancComplication;
	}

	/**
	 * @return the tT1Date
	 */
	public Date getTT1Date() {
		return TT1Date;
	}

	/**
	 * @param tT1Date the tT1Date to set
	 */
	public void setTT1Date(Date tT1Date) {
		TT1Date = tT1Date;
	}

	/**
	 * @return the tT2Date
	 */
	public Date getTT2Date() {
		return TT2Date;
	}

	/**
	 * @param tT2Date the tT2Date to set
	 */
	public void setTT2Date(Date tT2Date) {
		TT2Date = tT2Date;
	}

	/**
	 * @return the tTBoosterDate
	 */
	public Date getTTBoosterDate() {
		return TTBoosterDate;
	}

	/**
	 * @param tTBoosterDate the tTBoosterDate to set
	 */
	public void setTTBoosterDate(Date tTBoosterDate) {
		TTBoosterDate = tTBoosterDate;
	}

	/**
	 * @return the iFA100GivenDate
	 */
	public Date getIFA100GivenDate() {
		return IFA100GivenDate;
	}

	/**
	 * @param iFA100GivenDate the iFA100GivenDate to set
	 */
	public void setIFA100GivenDate(Date iFA100GivenDate) {
		IFA100GivenDate = iFA100GivenDate;
	}

	/**
	 * @return the eDD
	 */
	public Date geteDD() {
		return edd;
	}

	/**
	 * @param eDD the eDD to set
	 */
	public void seteDD(Date edd) {
		this.edd = edd;
	}

	/**
	 * @return the anemia
	 */
	public Boolean getAnemia() {
		return anemia;
	}

	/**
	 * @param anemia the anemia to set
	 */
	public void setAnemia(Boolean anemia) {
		this.anemia = anemia;
	}

	/**
	 * @return the rTISTI
	 */
	public Character getRTISTI() {
		return RTISTI;
	}

	/**
	 * @param rTISTI the rTISTI to set
	 */
	public void setRTISTI(Character rTISTI) {
		RTISTI = rTISTI;
	}

	/**
	 * @return the deliveryLnkFacility
	 */
	public String getDeliveryLnkFacility() {
		return DeliveryLnkFacility;
	}

	/**
	 * @param deliveryLnkFacility the deliveryLnkFacility to set
	 */
	public void setDeliveryLnkFacility(String deliveryLnkFacility) {
		DeliveryLnkFacility = deliveryLnkFacility;
	}

	/**
	 * @return the facilityName
	 */
	public String getFacilityName() {
		return FacilityName;
	}

	/**
	 * @param facilityName the facilityName to set
	 */
	public void setFacilityName(String facilityName) {
		FacilityName = facilityName;
	}

	/**
	 * @return the deliveryDate
	 */
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * @return the deliveryPlacehomeType
	 */
	public String getDeliveryPlacehomeType() {
		return DeliveryPlacehomeType;
	}

	/**
	 * @param deliveryPlacehomeType the deliveryPlacehomeType to set
	 */
	public void setDeliveryPlacehomeType(String deliveryPlacehomeType) {
		DeliveryPlacehomeType = deliveryPlacehomeType;
	}

	/**
	 * @return the deliveryPlacePublic
	 */
	public String getDeliveryPlacePublic() {
		return DeliveryPlacePublic;
	}

	/**
	 * @param deliveryPlacePublic the deliveryPlacePublic to set
	 */
	public void setDeliveryPlacePublic(String deliveryPlacePublic) {
		DeliveryPlacePublic = deliveryPlacePublic;
	}

	/**
	 * @return the deliveryPlacePrivate
	 */
	public String getDeliveryPlacePrivate() {
		return DeliveryPlacePrivate;
	}

	/**
	 * @param deliveryPlacePrivate the deliveryPlacePrivate to set
	 */
	public void setDeliveryPlacePrivate(String deliveryPlacePrivate) {
		DeliveryPlacePrivate = deliveryPlacePrivate;
	}

	/**
	 * @return the deliveryType
	 */
	public String getDeliveryType() {
		return DeliveryType;
	}

	/**
	 * @param deliveryType the deliveryType to set
	 */
	public void setDeliveryType(String deliveryType) {
		DeliveryType = deliveryType;
	}

	/**
	 * @return the deliveryComplications
	 */
	public String getDeliveryComplications() {
		return DeliveryComplications;
	}

	/**
	 * @param deliveryComplications the deliveryComplications to set
	 */
	public void setDeliveryComplications(String deliveryComplications) {
		DeliveryComplications = deliveryComplications;
	}

	/**
	 * @return the dischargeDate
	 */
	public Date getDischargeDate() {
		return DischargeDate;
	}

	/**
	 * @param dischargeDate the dischargeDate to set
	 */
	public void setDischargeDate(Date dischargeDate) {
		DischargeDate = dischargeDate;
	}

	/**
	 * @return the abortion
	 */
	public String getAbortion() {
		return abortion;
	}

	/**
	 * @param abortion the abortion to set
	 */
	public void setAbortion(String abortion) {
		this.abortion = abortion;
	}

	/**
	 * @return the pNCHomeVisit
	 */
	public String getPNCHomeVisit() {
		return PNCHomeVisit;
	}

	/**
	 * @param pNCHomeVisit the pNCHomeVisit to set
	 */
	public void setPNCHomeVisit(String pNCHomeVisit) {
		PNCHomeVisit = pNCHomeVisit;
	}

	/**
	 * @return the pNCComplication
	 */
	public String getPNCComplication() {
		return PNCComplication;
	}

	/**
	 * @param pNCComplication the pNCComplication to set
	 */
	public void setPNCComplication(String pNCComplication) {
		PNCComplication = pNCComplication;
	}

	/**
	 * @return the pPCMethod
	 */
	public String getPPCMethod() {
		return PPCMethod;
	}

	/**
	 * @param pPCMethod the pPCMethod to set
	 */
	public void setPPCMethod(String pPCMethod) {
		PPCMethod = pPCMethod;
	}

	/**
	 * @return the pNCCheckup
	 */
	public Boolean getPNCCheckup() {
		return PNCCheckup;
	}

	/**
	 * @param pNCCheckup the pNCCheckup to set
	 */
	public void setPNCCheckup(Boolean pNCCheckup) {
		PNCCheckup = pNCCheckup;
	}

	/**
	 * @return the outcomeNos
	 */
	public Long getOutcomeNos() {
		return OutcomeNos;
	}

	/**
	 * @param outcomeNos the outcomeNos to set
	 */
	public void setOutcomeNos(Long outcomeNos) {
		OutcomeNos = outcomeNos;
	}

	/**
	 * @return the child1ID
	 */
	public Long getChild1ID() {
		return child1ID;
	}

	/**
	 * @param child1id the child1ID to set
	 */
	public void setChild1ID(Long child1id) {
		child1ID = child1id;
	}

	/**
	 * @return the child1Name
	 */
	public String getChild1Name() {
		return Child1Name;
	}

	/**
	 * @param child1Name the child1Name to set
	 */
	public void setChild1Name(String child1Name) {
		Child1Name = child1Name;
	}

	/**
	 * @return the child1Gender
	 */
	public String getChild1Gender() {
		return Child1Gender;
	}

	/**
	 * @param child1Gender the child1Gender to set
	 */
	public void setChild1Gender(String child1Gender) {
		Child1Gender = child1Gender;
	}

	/**
	 * @return the child1Weight
	 */
	public Long getChild1Weight() {
		return Child1Weight;
	}

	/**
	 * @param child1Weight the child1Weight to set
	 */
	public void setChild1Weight(Long child1Weight) {
		Child1Weight = child1Weight;
	}

	/**
	 * @return the child1BreastFeeding
	 */
	public Boolean getChild1BreastFeeding() {
		return Child1BreastFeeding;
	}

	/**
	 * @param child1BreastFeeding the child1BreastFeeding to set
	 */
	public void setChild1BreastFeeding(Boolean child1BreastFeeding) {
		Child1BreastFeeding = child1BreastFeeding;
	}

	/**
	 * @return the child2ID
	 */
	public Long getChild2ID() {
		return child2ID;
	}

	/**
	 * @param child2id the child2ID to set
	 */
	public void setChild2ID(Long child2id) {
		child2ID = child2id;
	}

	/**
	 * @return the child2Name
	 */
	public String getChild2Name() {
		return Child2Name;
	}

	/**
	 * @param child2Name the child2Name to set
	 */
	public void setChild2Name(String child2Name) {
		Child2Name = child2Name;
	}

	/**
	 * @return the child2Gender
	 */
	public String getChild2Gender() {
		return Child2Gender;
	}

	/**
	 * @param child2Gender the child2Gender to set
	 */
	public void setChild2Gender(String child2Gender) {
		Child2Gender = child2Gender;
	}

	/**
	 * @return the child2Weight
	 */
	public Long getChild2Weight() {
		return Child2Weight;
	}

	/**
	 * @param child2Weight the child2Weight to set
	 */
	public void setChild2Weight(Long child2Weight) {
		Child2Weight = child2Weight;
	}

	/**
	 * @return the child2BreastFeeding
	 */
	public Boolean getChild2BreastFeeding() {
		return Child2BreastFeeding;
	}

	/**
	 * @param child2BreastFeeding the child2BreastFeeding to set
	 */
	public void setChild2BreastFeeding(Boolean child2BreastFeeding) {
		Child2BreastFeeding = child2BreastFeeding;
	}

	/**
	 * @return the child3ID
	 */
	public Long getChild3ID() {
		return child3ID;
	}

	/**
	 * @param child3id the child3ID to set
	 */
	public void setChild3ID(Long child3id) {
		child3ID = child3id;
	}

	/**
	 * @return the child3Name
	 */
	public String getChild3Name() {
		return Child3Name;
	}

	/**
	 * @param child3Name the child3Name to set
	 */
	public void setChild3Name(String child3Name) {
		Child3Name = child3Name;
	}

	/**
	 * @return the child3Gender
	 */
	public String getChild3Gender() {
		return Child3Gender;
	}

	/**
	 * @param child3Gender the child3Gender to set
	 */
	public void setChild3Gender(String child3Gender) {
		Child3Gender = child3Gender;
	}

	/**
	 * @return the child3Weight
	 */
	public Long getChild3Weight() {
		return Child3Weight;
	}

	/**
	 * @param child3Weight the child3Weight to set
	 */
	public void setChild3Weight(Long child3Weight) {
		Child3Weight = child3Weight;
	}

	/**
	 * @return the child3BreastFeeding
	 */
	public Boolean getChild3BreastFeeding() {
		return Child3BreastFeeding;
	}

	/**
	 * @param child3BreastFeeding the child3BreastFeeding to set
	 */
	public void setChild3BreastFeeding(Boolean child3BreastFeeding) {
		Child3BreastFeeding = child3BreastFeeding;
	}

	/**
	 * @return the child4ID
	 */
	public Long getChild4ID() {
		return child4ID;
	}

	/**
	 * @param child4id the child4ID to set
	 */
	public void setChild4ID(Long child4id) {
		child4ID = child4id;
	}

	/**
	 * @return the child4Name
	 */
	public String getChild4Name() {
		return Child4Name;
	}

	/**
	 * @param child4Name the child4Name to set
	 */
	public void setChild4Name(String child4Name) {
		Child4Name = child4Name;
	}

	/**
	 * @return the child4Gender
	 */
	public String getChild4Gender() {
		return Child4Gender;
	}

	/**
	 * @param child4Gender the child4Gender to set
	 */
	public void setChild4Gender(String child4Gender) {
		Child4Gender = child4Gender;
	}

	/**
	 * @return the child4Weight
	 */
	public Long getChild4Weight() {
		return Child4Weight;
	}

	/**
	 * @param child4Weight the child4Weight to set
	 */
	public void setChild4Weight(Long child4Weight) {
		Child4Weight = child4Weight;
	}

	/**
	 * @return the child4BreastFeeding
	 */
	public Boolean getChild4BreastFeeding() {
		return Child4BreastFeeding;
	}

	/**
	 * @param child4BreastFeeding the child4BreastFeeding to set
	 */
	public void setChild4BreastFeeding(Boolean child4BreastFeeding) {
		Child4BreastFeeding = child4BreastFeeding;
	}

	/**
	 * @return the motherRegDate
	 */
	public Date getMotherRegDate() {
		return MotherRegDate;
	}

	/**
	 * @param motherRegDate the motherRegDate to set
	 */
	public void setMotherRegDate(Date motherRegDate) {
		MotherRegDate = motherRegDate;
	}

	/**
	 * @return the lastUpdateDate
	 */
	public Date getLastUpdateDate() {
		return LastUpdateDate;
	}

	/**
	 * @param lastUpdateDate the lastUpdateDate to set
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		LastUpdateDate = lastUpdateDate;
	}

	/**
	 * @return the eID
	 */
	public String geteID() {
		return eID;
	}

	/**
	 * @param eID the eID to set
	 */
	public void seteID(String eID) {
		this.eID = eID;
	}

	/**
	 * @return the eIDTime
	 */
	public Date getEIDTime() {
		return EIDTime;
	}

	/**
	 * @param eIDTime the eIDTime to set
	 */
	public void setEIDTime(Date eIDTime) {
		EIDTime = eIDTime;
	}

	/**
	 * @return the cPSMSFlag
	 */
	public Boolean getCPSMSFlag() {
		return CPSMSFlag;
	}

	/**
	 * @param cPSMSFlag the cPSMSFlag to set
	 */
	public void setCPSMSFlag(Boolean cPSMSFlag) {
		CPSMSFlag = cPSMSFlag;
	}

	/**
	 * @return the jSYBeneficiary
	 */
	public Character getJSYBeneficiary() {
		return JSYBeneficiary;
	}

	/**
	 * @param jSYBeneficiary the jSYBeneficiary to set
	 */
	public void setJSYBeneficiary(Character jSYBeneficiary) {
		JSYBeneficiary = jSYBeneficiary;
	}

	/**
	 * @return the jSYPaidDate
	 */
	public Date getJSYPaidDate() {
		return JSYPaidDate;
	}

	/**
	 * @param jSYPaidDate the jSYPaidDate to set
	 */
	public void setJSYPaidDate(Date jSYPaidDate) {
		JSYPaidDate = jSYPaidDate;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return BankName;
	}

	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		BankName = bankName;
	}

	/**
	 * @return the bankBranchName
	 */
	public String getBankBranchName() {
		return BankBranchName;
	}

	/**
	 * @param bankBranchName the bankBranchName to set
	 */
	public void setBankBranchName(String bankBranchName) {
		BankBranchName = bankBranchName;
	}

	/**
	 * @return the accNo
	 */
	public String getAccNo() {
		return AccNo;
	}

	/**
	 * @param accNo the accNo to set
	 */
	public void setAccNo(String accNo) {
		AccNo = accNo;
	}

	/**
	 * @return the iFSCCode
	 */
	public String getIFSCCode() {
		return IFSCCode;
	}

	/**
	 * @param iFSCCode the iFSCCode to set
	 */
	public void setIFSCCode(String iFSCCode) {
		IFSCCode = iFSCCode;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the referredByTelecaller
	 */
	public Boolean getReferredByTelecaller() {
		return ReferredByTelecaller;
	}

	/**
	 * @param referredByTelecaller the referredByTelecaller to set
	 */
	public void setReferredByTelecaller(Boolean referredByTelecaller) {
		ReferredByTelecaller = referredByTelecaller;
	}

	/**
	 * @return the referredDate
	 */
	public Date getReferredDate() {
		return ReferredDate;
	}

	/**
	 * @param referredDate the referredDate to set
	 */
	public void setReferredDate(Date referredDate) {
		ReferredDate = referredDate;
	}

	/**
	 * @return the noofTry
	 */
	public Long getNoofTry() {
		return NoofTry;
	}

	/**
	 * @param noofTry the noofTry to set
	 */
	public void setNoofTry(Long noofTry) {
		NoofTry = noofTry;
	}

	/**
	 * @return the callAnswered
	 */
	public Boolean getCallAnswered() {
		return CallAnswered;
	}

	/**
	 * @param callAnswered the callAnswered to set
	 */
	public void setCallAnswered(Boolean callAnswered) {
		CallAnswered = callAnswered;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the highRisk
	 */
	public Boolean getHighRisk() {
		return highRisk;
	}

	/**
	 * @param highRisk the highRisk to set
	 */
	public void setHighRisk(Boolean highRisk) {
		this.highRisk = highRisk;
	}

	/**
	 * @return the callVerified
	 */
	public Boolean getCallVerified() {
		return CallVerified;
	}

	/**
	 * @param callVerified the callVerified to set
	 */
	public void setCallVerified(Boolean callVerified) {
		CallVerified = callVerified;
	}

	/**
	 * @return the associate
	 */
	public String getAssociate() {
		return associate;
	}

	/**
	 * @param associate the associate to set
	 */
	public void setAssociate(String associate) {
		this.associate = associate;
	}

	/**
	 * @return the callDate
	 */
	public Date getCallDate() {
		return CallDate;
	}

	/**
	 * @param callDate the callDate to set
	 */
	public void setCallDate(Date callDate) {
		CallDate = callDate;
	}

	/**
	 * @return the dueServices
	 */
	public String getDueServices() {
		return DueServices;
	}

	/**
	 * @param dueServices the dueServices to set
	 */
	public void setDueServices(String dueServices) {
		DueServices = dueServices;
	}

	/**
	 * @return the dueServicesResponse
	 */
	public String getDueServicesResponse() {
		return DueServicesResponse;
	}

	/**
	 * @param dueServicesResponse the dueServicesResponse to set
	 */
	public void setDueServicesResponse(String dueServicesResponse) {
		DueServicesResponse = dueServicesResponse;
	}

	/**
	 * @return the overdueServices
	 */
	public String getOverdueServices() {
		return OverdueServices;
	}

	/**
	 * @param overdueServices the overdueServices to set
	 */
	public void setOverdueServices(String overdueServices) {
		OverdueServices = overdueServices;
	}

	/**
	 * @return the overdueServicesResponse
	 */
	public String getOverdueServicesResponse() {
		return OverdueServicesResponse;
	}

	/**
	 * @param overdueServicesResponse the overdueServicesResponse to set
	 */
	public void setOverdueServicesResponse(String overdueServicesResponse) {
		OverdueServicesResponse = overdueServicesResponse;
	}

	/**
	 * @return the givenServices
	 */
	public String getGivenServices() {
		return GivenServices;
	}

	/**
	 * @param givenServices the givenServices to set
	 */
	public void setGivenServices(String givenServices) {
		GivenServices = givenServices;
	}

	/**
	 * @return the givenServicesResponse
	 */
	public String getGivenServicesResponse() {
		return GivenServicesResponse;
	}

	/**
	 * @param givenServicesResponse the givenServicesResponse to set
	 */
	public void setGivenServicesResponse(String givenServicesResponse) {
		GivenServicesResponse = givenServicesResponse;
	}

	/**
	 * @return the miscarriage
	 */
	public Boolean getMiscarriage() {
		return miscarriage;
	}

	/**
	 * @param miscarriage the miscarriage to set
	 */
	public void setMiscarriage(Boolean miscarriage) {
		this.miscarriage = miscarriage;
	}

	/**
	 * @return the babyDied
	 */
	public Boolean getBabyDied() {
		return BabyDied;
	}

	/**
	 * @param babyDied the babyDied to set
	 */
	public void setBabyDied(Boolean babyDied) {
		BabyDied = babyDied;
	}

	/**
	 * @return the callNo
	 */
	public String getCallNo() {
		return CallNo;
	}

	/**
	 * @param callNo the callNo to set
	 */
	public void setCallNo(String callNo) {
		CallNo = callNo;
	}

	/**
	 * @return the questionsAsked
	 */
	public String getQuestionsAsked() {
		return QuestionsAsked;
	}

	/**
	 * @param questionsAsked the questionsAsked to set
	 */
	public void setQuestionsAsked(String questionsAsked) {
		QuestionsAsked = questionsAsked;
	}

	/**
	 * @return the asnwerGivenByBenificary
	 */
	public String getAsnwerGivenByBenificary() {
		return AsnwerGivenByBenificary;
	}

	/**
	 * @param asnwerGivenByBenificary the asnwerGivenByBenificary to set
	 */
	public void setAsnwerGivenByBenificary(String asnwerGivenByBenificary) {
		AsnwerGivenByBenificary = asnwerGivenByBenificary;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the noCallReason
	 */
	public String getNoCallReason() {
		return NoCallReason;
	}

	/**
	 * @param noCallReason the noCallReason to set
	 */
	public void setNoCallReason(String noCallReason) {
		NoCallReason = noCallReason;
	}

	/**
	 * @return the noPhoneReason
	 */
	public String getNoPhoneReason() {
		return NoPhoneReason;
	}

	/**
	 * @param noPhoneReason the noPhoneReason to set
	 */
	public void setNoPhoneReason(String noPhoneReason) {
		NoPhoneReason = noPhoneReason;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return CreatedBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return UpdatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
	}

	/**
	 * @return the bPLAPL
	 */
	public String getBPLAPL() {
		return BPLAPL;
	}

	/**
	 * @param bPLAPL the bPLAPL to set
	 */
	public void setBPLAPL(String bPLAPL) {
		BPLAPL = bPLAPL;
	}

	/**
	 * @return the mDDSStateID
	 */
	public String getMDDSStateID() {
		return MDDSStateID;
	}

	/**
	 * @param mDDSStateID the mDDSStateID to set
	 */
	public void setMDDSStateID(String mDDSStateID) {
		MDDSStateID = mDDSStateID;
	}

	/**
	 * @return the mDDSDistrictID
	 */
	public String getMDDSDistrictID() {
		return MDDSDistrictID;
	}

	/**
	 * @param mDDSDistrictID the mDDSDistrictID to set
	 */
	public void setMDDSDistrictID(String mDDSDistrictID) {
		MDDSDistrictID = mDDSDistrictID;
	}

	/**
	 * @return the mDDSTalukaID
	 */
	public String getMDDSTalukaID() {
		return MDDSTalukaID;
	}

	/**
	 * @param mDDSTalukaID the mDDSTalukaID to set
	 */
	public void setMDDSTalukaID(String mDDSTalukaID) {
		MDDSTalukaID = mDDSTalukaID;
	}

	/**
	 * @return the mDDSVillageID
	 */
	public String getMDDSVillageID() {
		return MDDSVillageID;
	}

	/**
	 * @param mDDSVillageID the mDDSVillageID to set
	 */
	public void setMDDSVillageID(String mDDSVillageID) {
		MDDSVillageID = mDDSVillageID;
	}

	/**
	 * @return the isValid
	 */
	public Boolean getIsValid() {
		return IsValid;
	}

	/**
	 * @param isValid the isValid to set
	 */
	public void setIsValid(Boolean isValid) {
		IsValid = isValid;
	}

	/**
	 * @return the isError
	 */
	public Boolean getIsError() {
		return IsError;
	}

	/**
	 * @param isError the isError to set
	 */
	public void setIsError(Boolean isError) {
		IsError = isError;
	}

	/**
	 * @return the errorReason
	 */
	public String getErrorReason() {
		return ErrorReason;
	}

	/**
	 * @param errorReason the errorReason to set
	 */
	public void setErrorReason(String errorReason) {
		ErrorReason = errorReason;
	}

	/**
	 * @return the fileID
	 */
	public Long getFileID() {
		return fileID;
	}

	/**
	 * @param fileID the fileID to set
	 */
	public void setFileID(Long fileID) {
		this.fileID = fileID;
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
	 * @return the createdby
	 */
	public String getCreatedby() {
		return createdby;
	}

	/**
	 * @param createdby the createdby to set
	 */
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
	 * @return the lastModDate
	 */
	public Date getLastModDate() {
		return lastModDate;
	}

	/**
	 * @param lastModDate the lastModDate to set
	 */
	public void setLastModDate(Date lastModDate) {
		this.lastModDate = lastModDate;
	}

	/**
	 * @return the isAllocated
	 */
	public Boolean getIsAllocated() {
		return isAllocated;
	}

	/**
	 * @param isAllocated the isAllocated to set
	 */
	public void setIsAllocated(Boolean isAllocated) {
		this.isAllocated = isAllocated;
	}

	/**
	 * @return the outputMapper
	 */
	public OutputMapper getOutputMapper() {
		return outputMapper;
	}

	/**
	 * @param outputMapper the outputMapper to set
	 */
	public void setOutputMapper(OutputMapper outputMapper) {
		this.outputMapper = outputMapper;
	}

	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString() {

		return outputMapper.gson().toJson(this);
	}

	/**
	 * 
	 * @param utilDate
	 * @return
	 */
	private Date convertUtilDatetoSqlDate(java.util.Date utilDate) {

		return new Date(utilDate.getTime());
	}

}
