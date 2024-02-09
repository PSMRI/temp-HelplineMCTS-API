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
import java.text.SimpleDateFormat;

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
@Table(name = "db_reporting.fact_motherinvalidrecord", schema = "db_reporting")
public class MotherInvalidRecordDetail implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long MotherInValidRecordID;
	
	@Transient
	private SimpleDateFormat BIRTH_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

	@Expose
	@Column(name = "MCTSID_no", insertable = false, updatable = false)
	private Long MCTSID;

	@Expose
	private String Name;

	@Expose
	private String Husband_Name;

	@Expose
	private String PhoneNo_Of_Whom;

	@Expose
	private String Whom_PhoneNo;
	
	@Expose
	@Column(name = "Birth_Date")
	private Timestamp birthDate;

	@Expose
	private Integer Age;

	@Expose
	private String Caste;

	@Expose
	private String Aadhar_no;

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
	private Long SubCenter_ID;

	@Expose
	private String SubCenter_Name;

	@Expose
	private Long Village_ID;

	@Expose
	private String Village_Name;

	@Expose
	private Long City_ID;

	@Expose
	private String City_Name;

	@Expose
	private String SubCenter_Name1;

	@Expose
	@Column(name = "ANM_ID")
	private Long Anm_ID;

	@Expose
	@Column(name = "ANM_Name")
	private String Anm_Name;

	@Expose
	@Column(name = "ANM_Ph")
	private String Anm_Ph;

	@Expose
	@Column(name = "ASHA_ID")
	private Long Asha_ID;

	@Expose
	@Column(name = "ASHA_Name")
	private String Asha_Name;

	@Expose
	@Column(name = "ASHA_Ph")
	private String Asha_Ph;

	@Expose
	@Column(name = "PHC_ID")
	private Long Phc_ID;

	@Expose
	@Column(name = "PHC_Name")
	private String Phc_Name;

	@Expose
	@Column(name = "SUBPHC_ID")
	private Long SubPhc_ID;

	@Expose
	@Column(name = "SUBPHC_Name")
	private String SubPhc_Name;

	@Expose
	private String Yr;

	@Expose
	private String Gp_Village;

	@Expose
	private String Address;
	
	@Expose
	private Long Entry_type;

	@Expose
	@Column(name = "LMP_Date")
	private Timestamp lmpDate;

	@Expose
	@Column(name = "ANC1_Date")
	private Date Anc1_Date;

	@Expose
	@Column(name = "ANC2_Date")
	private Date Anc2_Date;

	@Expose
	@Column(name = "ANC3_Date")
	private Date Anc3_Date;

	@Expose
	@Column(name = "ANC4_Date")
	private Date Anc4_Date;

	@Expose
	@Column(name = "ANC_Complication")
	private String Anc_Complication;

	@Expose
	@Column(name = "TT1_Date")
	private Date Tt1_Date;

	@Expose
	@Column(name = "TT2_Date")
	private Date Tt2_Date;

	@Expose
	@Column(name = "TTBooster_Date")
	private Date TtBooster_Date;

	@Expose
	@Column(name = "IFA100_Given_Date")
	private Date Ifa100_Given_Date;

	@Expose
	@Column(name = "EDD")
	private Date edd;

	@Expose
	private String Anemia;

	@Expose
	@Column(name = "RTI_STI")
	private String Rti_Sti;

	@Expose
	private String Delivery_Lnk_Facility;

	@Expose
	private String Facility_Name;

	@Expose
	private Date Delivery_Date;

	@Expose
	private String Delivery_Place_home_type;

	@Expose
	private String Delivery_Place_Public;

	@Expose
	private String Delivery_Place_Private;

	@Expose
	private String Delivery_type;

	@Expose
	private String Delivery_Complications;

	@Expose
	private Date Discharge_Date;

	@Expose
	private String Abortion;

	@Expose
	@Column(name = "PNC_Home_Visit")
	private String Pnc_Home_Visit;

	@Expose
	@Column(name = "PNC_Complication")
	private String Pnc_Complication;

	@Expose
	@Column(name = "PPC_Method")
	private String Ppc_Method;

	@Expose
	@Column(name = "PNC_Checkup")
	private String Pnc_Checkup;

	@Expose
	private Long Outcome_Nos;

	@Expose
	private Long Child1_ID;

	@Expose
	private String Child1_Name;

	@Expose
	private String Child1_Gender;

	@Expose
	private Float Child1_Weight;

	@Expose
	private String Child1_BreastFeeding;

	@Expose
	private Long Child2_ID;

	@Expose
	private String Child2_Name;

	@Expose
	private String Child2_Gender;

	@Expose
	private Float Child2_weight;

	@Expose
	private String Child2_Breastfeeding;

	@Expose
	private Long Child3_ID;

	@Expose
	private String Child3_Name;

	@Expose
	private String Child3_Gender;

	@Expose
	private Float Child3_Weight;

	@Expose
	private String Child3_Breastfeeding;

	@Expose
	private Long Child4_ID;

	@Expose
	private String Child4_Name;

	@Expose
	private String Child4_Gender;

	@Expose
	private Float Child4_Weight;

	@Expose
	private String Child4_Breastfeeding;

	@Expose
	private Date Mother_Reg_Date;

	@Expose
	private Date Last_Update_Date;

	@Expose
	@Column(name = "EID")
	private String eid;

	@Expose
	@Column(name = "EID_time")
	private String eid_time;

	@Expose
	@Column(name = "CPSMS_Flag")
	private Boolean Cpsms_Flag;

	@Expose
	@Column(name = "JSY_Beneficiary")
	private String Jsy_Beneficiary;

	@Expose
	@Column(name = "JSY_Paid_Date")
	private Date Jsy_Paid_Date;

	@Expose
	private String Bank_Name;

	@Expose
	private String Bank_Branch_Name;

	@Expose
	private String Acc_No;

	@Expose
	@Column(name = "IFSC_Code")
	private String Ifsc_Code;

	@Expose
	private String Remarks;

	@Expose
	private Boolean Referred_By_Telecaller;

	@Expose
	private Date Referred_Date;

	@Expose
	private Long No_of_Try;

	@Expose
	private Boolean Call_Answered;

	@Expose
	private String Status;

	@Expose
	private Boolean High_Risk;

	@Expose
	private Boolean Call_Verified;

	@Expose
	private String Associate;

	@Expose
	private Date Call_Date;

	@Expose
	private String Due_Services;

	@Expose
	private String Due_Services_Response;

	@Expose
	private String Overdue_Services;

	@Expose
	private String Overdue_Services_Response;

	@Expose
	private String Given_Services;

	@Expose
	private String Given_Services_Response;

	@Expose
	private String Miscarriage;

	@Expose
	private String Baby_Died;

	@Expose
	private String Call_No;

	@Expose
	private String Questions_Asked;

	@Expose
	private String Asnwer_Given_by_Benificary;

	@Expose
	private String Source;

	@Expose
	private String NoCall_Reason;

	@Expose
	private String NoPhone_Reason;

	@Expose
	private String Created_By;

	@Expose
	private String Updated_By;

	@Expose
	@Column(name = "BPL_APL")
	private String Bpl_Apl;

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
	private Boolean Is_Valid;

	@Expose
	private Boolean Is_Error;
	
	@Expose
	@Column(name = "FileID", insertable = false, updatable = false)
	private Long FileID;

	@Expose
	private String Error_Reason;

	@Expose
	private Boolean IsAllocated;

	@Expose
	private Boolean Deleted;
	
	@Expose
	@Column(name = "CreatedDate")
	private Timestamp DateofUpload;

	@Expose
	@Column(name = "CreatedBy")
	private String UpdatedBy;
	
	@Expose
	private String InValid_Reason;
	
	@Column(name = "ProviderServiceMapID")
	private Long providerServiceMapID;
	
	@Transient
	private Timestamp startDate ;

	@Transient
	private Timestamp endDate ;
	
	@Override
	public String toString() {
		return new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).serializeNulls()
				.excludeFieldsWithoutExposeAnnotation().setDateFormat("dd-MM-yyyy").create().toJson(this);

	}
}
