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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.annotations.Expose;

import lombok.Data;


@Entity
@Data
@Table(name = "db_reporting.fact_childinvalidrecord", schema = "db_reporting")
public class ChildInvalidRecordDetail implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Fact_ChildInValidRecordID")
	private Long Fact_ChildInValidRecordID;
	
	@Expose
	@Column(name = "MCTSID_no_Child_ID", insertable = false, updatable = false)
	private Long MCTSID_no_Child_ID;
	 
	@Expose
	private String Child_Name;
	 
	@Expose
	private String Father_Name;
	 
	@Expose
	private String Mother_Name;
	 
	@Expose
	private Long Mother_ID;
	
	@Expose
	@Column(name = "DOB")
	private Timestamp birthDate;
	 
	@Expose
	private String Place_of_Birth;
	 
	@Expose
	private String Gender;
	 
	@Expose
	private String Caste;
	 
	@Expose
	private String Child_Aadhaar_No;
	 
	@Expose
	private Float Weight_of_Child;
	 
	@Expose
	private Long Child_EID;
	 
	@Expose
	private Date Child_EID_Time;
	 
	@Expose
	private Long Emamta_Health_ID;
	 
	@Expose
	private Long Emamta_Family_ID;
	 
	@Expose
	private String Phone_No_of;
	 
	@Expose
	private String Phone_No;
	 
	@Expose
	private Date Registration_Date;
	 
	@Expose
	private Date Updated_Date;
	 
	@Expose
	private String City;
	 
	@Expose
	private Long State_ID;
	 
	@Expose
	private Long District_ID;
	 
	@Expose
	private String District_Name;
	 
	@Expose
	private String Taluka_Name;
	 
	@Expose
	private Long Taluka_ID;
	 
	@Expose
	private Long Block_ID;
	 
	@Expose
	private String Block_Name;
	 
	@Expose
	@Column(name = "PHC_ID")
	private Long Phc_ID;
	 
	@Expose
	@Column(name = "PHC_Name")
	private String Phc_Name;
	 
	@Expose
	private Long SubCenter_ID;
	 
	@Expose
	private String SubCenter_Name;
	 
	@Expose
	private String SubCenter_Name1;
	 
	@Expose
	private Long Village_ID;
	 
	@Expose
	private String Village_Name;
	 

	 
	@Expose
	private String Address;
	 
	@Expose
	private String Year;
	 
	@Expose
	@Column(name = "ANM_ID")
	private Long Anm_ID;
	 
	@Expose
	@Column(name = "ANM_Name")
	private String Anm_Name;
	 
	@Expose
	@Column(name = "ANM_Phone_No")
	private String Anm_Phone_No;
	 
	@Expose
	@Column(name = "ASHA_ID")
	private Long Asha_ID;
	 
	@Expose
	@Column(name = "ASHA_Name")
	private String Asha_Name;
	 
	@Expose
	@Column(name = "ASHA_Phone_No")
	private String Asha_Phone_No;
	 
	@Expose
	private Long Entry_type;
	 
	@Expose
	private Date BCG_Date;
	 
	@Expose
	private Date OPV0_Date;
	 
	@Expose
	private Date Hepatitis_B1_Date;
	 
	@Expose
	@Column(name = "DPT1_Date")
	private Date Dpt1_Date;
	 
	@Expose
	@Column(name = "OPV1_Date")
	private Date Opv1_Date;
	 
	@Expose
	private Date Hepatitis_B2_Date;
	 
	@Expose
	@Column(name = "DPT2_Date")
	private Date Dpt2_Date;
	 
	@Expose
	@Column(name = "OPV2_Date")
	private Date Opv2_Date;
	 
	@Expose
	private Date Hepatitis_B3_Date;
	 
	@Expose
	@Column(name = "DPT3_Date")
	private Date Dpt3_Date;
	 
	@Expose
	@Column(name = "OPV3_Date")
	private Date Opv3_Date;
	 
	@Expose
	private Date Hepatitis_B4_Date;
	 
	@Expose
	private Date Measles_Date;
	 
	@Expose
	private Date VitA_Dose1_Date;
	 
	@Expose
	private Date MR_Date;
	 
	@Expose
	@Column(name = "DPTBooster_Date")
	private Date Dptbooster_Date;
	 
	@Expose
	@Column(name = "OPVBooster_Date")
	private Date Opvbooster_Date;
	 
	@Expose
	private Date VitA_Dose2_Date;
	 
	@Expose
	private Date VitA_Dose3_Date;
	 
	@Expose
	private Date VitA_Dose99_Date;
	 
	@Expose
	private Date VitA_Dose5_Date;
	 
	@Expose
	private Date VitA_Dose6_Date;
	 
	@Expose
	private Date VitA_Dose7_Date;
	 
	@Expose
	private Date VitA_Dose8_Date;
	 
	@Expose
	private Date VitA_Dose9_Date;
	 
	@Expose
	@Column(name = "JE_Date")
	private Date Je_Date;
	 
	@Expose
	@Column(name = "DT5_Date")
	private Date Dt5_Date;
	 
	@Expose
	@Column(name = "TT10_Date")
	private Date Tt10_Date;
	 
	@Expose
	@Column(name = "TT16_Date")
	private Date Tt16_Date;
	 
	@Expose
	private Date Measles_2_Date;
	 
	@Expose
	private Date PentaValent1_Date;
	 
	@Expose
	private Date PentaValent2_Date;
	 
	@Expose
	private Date PentaValent3_Date;
	 
	@Expose
	private Boolean is_Upload;
	 
	@Expose
	private Long Status;
	 
	@Expose
	private String Remarks;
	 
	@Expose
	private Boolean Delete_Mother;
	 
	@Expose
	private String Delete_Reason;
	 
	@Expose
	private Date Deleted_ON;
	 
	@Expose
	@Column(name = "SMS_Status")
	private Long Sms_Status;
	 
	@Expose
	@Column(name = "MDDS_StateID")
	private Long Mdds_StateID;
	 
	@Expose
	@Column(name = "MDDS_District_ID")
	private Long Mdds_District_ID;
	 
	@Expose
	@Column(name = "MDDS_Taluka_ID")
	private Long Mdds_Taluka_ID;
	 
	@Expose
	@Column(name = "MDDS_Village_ID")
	private Long Mdds_Village_ID;
	 
	@Expose
	private String BirthCertificateNo;
	 
	@Expose
	private String Rural_urban;
	 
	@Expose
	private String SNO;
	 
	@Expose
	private String Lead_ID;
	 
	@Expose
	private String My_ID;
	 
	@Expose
	private Long Cid_NO;
	 
	@Expose
	private Long Mid_NO;
	 
	@Expose
	private Boolean Duplicate_Bit;
	 
	@Expose
	private String FacilityType;
	 
	@Expose
	private String DueServices;
	 
	@Expose
	private String OverDueServices;
	 
	@Expose
	private String GivenServices;
	 
	@Expose
	private String Source;
	
	@Expose
	private Boolean IsAllocated;
	 
	@Expose
	private Boolean Is_Valid;
	 
	@Expose
	private Boolean Is_Error;
	 
	@Expose
	private String Error_Reason;
	 
	@Expose
	@Column(name = "FileID")
	private Long FileID;
	 
	@Expose
	private Boolean Deleted;
	
	@Expose
	@Column(name = "CreatedDate")
	private Timestamp DateofUpload;
	 
	@Expose
	@Column(name = "CreatedBy")
	private String UpdatedBy;
	
	@Column(name = "ProviderServiceMapID")
	private Long providerServiceMapID;
	
	@Transient
	private Timestamp startDate ;

	@Transient
	private Timestamp endDate;
	
	@Override
	public String toString() {
		return new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).serializeNulls()
				.excludeFieldsWithoutExposeAnnotation().setDateFormat("dd-MM-yyyy").create().toJson(this);

	}

}
