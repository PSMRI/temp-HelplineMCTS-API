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
package com.iemr.mcts.data.report;


import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.mcts.utils.mapper.OutputMapper;

import lombok.Data;

@Entity
@Table(name = "db_reporting.fact_childvalidrecord", schema = "db_reporting")
@Data
public class ChildDetail implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Fact_ChildValidRecordID")
	private Long childValidRecordID;
	 
	@Expose
	@Column(name = "My_ID")
	private String myID;
	
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
	@Column(name = "PHC_ID")
	private Long phcID;
	 
	@Expose
	@Column(name = "PHC_Name")
	private String phcName;
	 
	@Expose
	@Column(name = "SubCenter_ID")
	private Long subCenterID;
	 
	@Expose
	@Column(name = "SubCenter_Name")
	private String subCenterName;
	 
	@Expose
	@Column(name = "SubCenter_Name1")
	private String subCenterName1;
	 
	@Expose
	@Column(name = "Village_ID")
	private Long villageID;
	 
	@Expose
	@Column(name = "Village_Name")
	private String villageName;
	
	@Expose
	@Column(name = "Rural_urban")
	private String ruralUrban;
	
	@Expose
	@Column(name = "SNO")
	private String sNo;
	
	@Expose
	@Column(name = "Year")
	private String year;
	
	@Expose
	@Column(name = "City")
	private String city;
	
	@Expose
	@Column(name = "GP_Village")
	private String gpVillage;
	 
	@Expose
	@Column(name = "Address")
	private String address;
	 
	@Expose
	@Column(name = "MCTSID_no_Child_ID")
	private Long MCTSIDnoChild_ID;
	 
	@Expose
	@Column(name = "Child_Name")
	private String childName;
	
	@Expose
	@Column(name = "Mother_Name")
	private String motherName;
	 
	@Expose
	@Column(name = "Mother_ID")
	private Long motherID;
	
	@Expose
	@Column(name = "Phone_No_of")
	private String phoneNoof;
	 
	@Expose
	@Column(name = "Phone_No")
	private String phoneNo;
	
	@Expose
	@Column(name = "DOB")
	private Date dob;
	 
	@Expose
	@Column(name = "Place_of_Birth")
	private String placeofBirth;
	
	@Expose
	@Column(name = "Caste")
	private String caste;
	 
	@Expose
	@Column(name = "ANM_Name")
	private String anmName;
	 
	@Expose
	@Column(name = "ANM_Phone_No")
	private String anmPhoneNo;
	 
	@Expose
	@Column(name = "ASHA_Name")
	private String ashaName;
	 
	@Expose
	@Column(name = "ASHA_Phone_No")
	private String ashaPhoneNo;

	@Expose
	@Column(name = "BCG_Date")
	private Date bcgDate;
	 
	@Expose
	@Column(name = "OPV0_Date")
	private Date opvoDate;
	 
	@Expose
	@Column(name = "Hepatitis_B1_Date")
	private Date hepatitisB1Date;
	 
	@Expose
	@Column(name = "DPT1_Date")
	private Date dpt1Date;
	 
	@Expose
	@Column(name = "OPV1_Date")
	private Date opv1Date;
	 
	@Expose
	@Column(name = "Hepatitis_B2_Date")
	private Date hepatitisB2Date;
	 
	@Expose
	@Column(name = "DPT2_Date")
	private Date dpt2Date;
	 
	@Expose
	@Column(name = "OPV2_Date")
	private Date opv2Date;
	 
	@Expose
	@Column(name = "Hepatitis_B3_Date")
	private Date hepatitisB3Date;
	 
	@Expose
	@Column(name = "DPT3_Date")
	private Date dpt3Date;
	 
	@Expose
	@Column(name = "OPV3_Date")
	private Date opv3Date;
	 
	@Expose
	@Column(name = "Hepatitis_B4_Date")
	private Date hepatitisB4Date;
	 
	@Expose
	@Column(name = "Measles_Date")
	private Date measlesDate;
	 
	@Expose
	@Column(name = "VitA_Dose1_Date")
	private Date vitADose1Date;
	 
	@Expose
	@Column(name = "MR_Date")
	private Date mrDate;
	 
	@Expose
	@Column(name = "DPTBooster_Date")
	private Date dptBoosterDate;
	 
	@Expose
	@Column(name = "OPVBooster_Date")
	private Date opvBoosterDate;
	 
	@Expose
	@Column(name = "VitA_Dose2_Date")
	private Date vitADose2Date;
	 
	@Expose
	@Column(name = "VitA_Dose3_Date")
	private Date vitADose3Date;

	@Expose
	@Column(name = "JE_Date")
	private Date jeDate;
	
	@Expose
	@Column(name = "VitA_Dose9_Date")
	private Date vitADose9Date;
	
	@Expose
	@Column(name = "DT5_Date")
	private Date dt5Date;
	 
	@Expose
	@Column(name = "TT10_Date")
	private Date tt10Date;
	 
	@Expose
	@Column(name = "TT16_Date")
	private Date tt16Date;
	
	@Expose
	@Column(name = "is_Upload")
	private Boolean is_Upload;
	
	@Expose
	@Column(name = "Emamta_Health_ID")
	private Long emamtaHealthID;
	 
	@Expose
	@Column(name = "Emamta_Family_ID")
	private Long emamtaFamilyID;
	
	@Expose
	@Column(name = "CID_NO")
	private Long cidNO;
	 
	@Expose
	@Column(name = "MID_NO")
	private Long midNO;
	
	@Expose
	@Column(name = "Delete_Mother")
	private Boolean deleteMother;
	 
	@Expose
	@Column(name = "Delete_Reason")
	private String deleteReason;
	
	@Expose
	@Column(name = "Deleted_ON")
	private Date deletedON;
	
	@Expose
	@Column(name = "Entry_type")
	private Long entrytype;
		 
	@Expose
	@Column(name = "Registration_Date")
	private Date registrationDate;
	
	@Expose
	@Column(name = "Duplicate_Bit")
	private Boolean duplicateBit;
	
	@Expose
	@Column(name = "FacilityType")
	private String facilityType;
	
	@Expose
	@Column(name = "Gender")
	private String gender;
	
	@Expose
	@Column(name = "VitA_Dose5_Date")
	private Date vitADose5Date;
	 
	@Expose
	@Column(name = "VitA_Dose6_Date")
	private Date vitADose6Date;
	 
	@Expose
	@Column(name = "VitA_Dose7_Date")
	private Date vitADose7Date;
	 
	@Expose
	@Column(name = "VitA_Dose8_Date")
	private Date vitADose8Date;

	@Expose
	@Column(name = "VitA_Dose99_Date")
	private Date vitADose99Date;
	
	@Expose
	@Column(name = "Updated_Date")
	private Date updatedDate;
	
	@Expose
	@Column(name = "SMS_Status")
	private Long smsStatus;
		 
	@Expose
	@Column(name = "Remarks")
	private String remarks;
	
	@Expose
	@Column(name = "ANM_ID")
	private Long anmID;
	
	@Expose
	@Column(name = "ASHA_ID")
	private Long ashaID;
	
	@Column(name = "CreatedDate")
	private Timestamp createdDate;
	
	@Expose
	@Column(name = "Created_By")
	private String createdBy;
	 
	@Expose
	@Column(name = "Updated_By")
	private String updatedBy;
	
	@Expose
	@Column(name = "Measles_2_Date")
	private Date measles2Date;
	 
	@Column(name = "ProviderServiceMapID")
	private Long providerServiceMapID;
	
	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegID;
	
	@OneToOne
	@JoinColumn(name = "beneficiaryRegID", referencedColumnName = "BeneficiaryRegID", insertable = false, updatable = false)
	@Expose
	private IdentityDetail identityDetail;
	
	@Transient
	private Timestamp startDate = new Timestamp(0);
	
	@Transient
	private Timestamp endDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
	
	@Column(name = "IsSelfNo")
	private Boolean isSelfNo;
	
	@Override
	public String toString() {

		return OutputMapper.gsonWithoutExposeRestriction().toJson(this);
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public Long getProviderServiceMapID() {
		return providerServiceMapID;
	}

}
