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
import java.util.Calendar;

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
@Table(name = "db_reporting.fact_mothervalidrecord", schema = "db_reporting")
@Data
public class MotherDataReportDetail implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Fact_MotherValidRecordID")
	private Long motherValidRecordID;

	@Transient
	private SimpleDateFormat BIRTH_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
	
	@Expose
	@Column(name = "State_ID")
	private Integer stateID;

	@Expose
	@Column(name = "District_ID")
	private Integer districtID;

	@Expose
	@Column(name = "District_Name")
	private String districtName;

	@Expose
	@Column(name = "Taluka_ID")
	private Long talukaID;

	@Expose
	@Column(name = "Taluka_Name")
	private String talukaName;

	@Expose
	@Column(name = "Block_ID")
	private Long healthBlockID;

	@Expose
	@Column(name = "Block_Name")
	private String healthBlockName;

	@Expose
	@Column(name = "PHC_ID")
	private String PhcID;

	@Expose
	@Column(name = "PHC_Name")
	private String PhcName;

	@Expose
	@Column(name = "SubCenter_ID")
	private Integer subCenterID;

	@Expose
	@Column(name = "SubCenter_Name")
	private String subCenterName;

	@Expose
	@Column(name = "Village_ID")
	private Integer villageID;

	@Expose
	@Column(name = "Village_Name")
	private String villageName;

	@Expose
	@Column(name = "YR")
	private Character yr;

	@Expose
	@Column(name = "GP_Village")
	private String GpVillage;

	@Expose
	@Column(name = "Address")
	private String address;

	@Expose
	@Column(name = "MCTSID_no")
	private Long IDNo;

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
	@Column(name = "JSY_Beneficiary")
	private Character JsyBeneficiary;

	@Expose
	@Column(name = "Caste")
	private String caste;

	@Expose
	@Column(name = "SubCenter_Name1")
	private String subCenterName1;

	@Expose
	@Column(name = "ANM_Name")
	private String anmName;

	@Expose
	@Column(name = "ANM_Ph")
	private String anmPhone;

	@Expose
	@Column(name = "ASHA_Name")
	private String ashaName;

	@Expose
	@Column(name = "ASHA_Ph")
	private String ashaPhone;

	@Expose
	@Column(name = "Delivery_Lnk_Facility")
	private String deliveryLnkFacility;

	@Expose
	@Column(name = "Facility_Name")
	private String facilityName;

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
	@Column(name = "TT1_Date")
	private Date Tt1Date;

	@Expose
	@Column(name = "TT2_Date")
	private Date Tt2Date;

	@Expose
	@Column(name = "TTBooster_Date")
	private Date TtBoosterDate;

	@Expose
	@Column(name = "IFA100_Given_Date")
	private Date Ifa100GivenDate;

	@Expose
	@Column(name = "Anemia")
	private Boolean anemia;

	@Expose
	@Column(name = "ANC_Complication")
	private String ancComplication;

	@Expose
	@Column(name = "RTI_STI")
	private Character RtiSti;

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

	@Expose
	@Column(name = "Discharge_Date")
	private Date DischargeDate;

	@Expose
	@Column(name = "JSY_Paid_Date")
	private Date JsyPaidDate;

	@Expose
	@Column(name = "Abortion")
	private String abortion;

	@Expose
	@Column(name = "PNC_Home_Visit")
	private String PncHomeVisit;

	@Expose
	@Column(name = "PNC_Complication")
	private String PncComplication;

	@Expose
	@Column(name = "PPC_Method")
	private String PpcMethod;

	@Expose
	@Column(name = "PNC_Checkup")
	private Boolean PncCheckup;

	@Expose
	@Column(name = "Outcome_Nos")
	private Long OutcomeNos;

	@Expose
	@Column(name = "Child1_Name")
	private String Child1Name;

	@Expose
	@Column(name = "Child1_Gender")
	private String Child1Gender;

	@Expose
	@Column(name = "Child1_Weight")
	private Long Child1Weight;

	@Expose
	@Column(name = "Child1_BreastFeeding")
	private Boolean Child1BreastFeeding;

	@Expose
	@Column(name = "Child2_Name")
	private String Child2Name;

	@Expose
	@Column(name = "Child2_Gender")
	private String Child2Gender;

	@Expose
	@Column(name = "Child2_weight")
	private Long Child2Weight;

	@Expose
	@Column(name = "Child2_BreastFeeding")
	private Boolean Child2BreastFeeding;

	@Expose
	@Column(name = "Child3_Name")
	private String Child3Name;

	@Expose
	@Column(name = "Child3_Gender")
	private String Child3Gender;

	@Expose
	@Column(name = "Child3_Weight")
	private Long Child3Weight;

	@Expose
	@Column(name = "Child3_BreastFeeding")
	private Boolean Child3BreastFeeding;

	@Expose
	@Column(name = "Child4_Name")
	private String Child4Name;

	@Expose
	@Column(name = "Child4_Gender")
	private String Child4Gender;

	@Expose
	@Column(name = "Child4_Weight")
	private Long Child4Weight;

	@Expose
	@Column(name = "Child4_BreastFeeding")
	private Boolean Child4BreastFeeding;

	@Expose
	@Column(name = "Age")
	private Integer age;

	@Expose
	@Column(name = "Mother_Reg_Date")
	private Date MotherRegDate;

	@Expose
	@Column(name = "Last_Update_Date")
	private Date LastUpdateDate;

	@Expose
	@Column(name = "Remarks")
	private String remarks;

	@Expose
	@Column(name = "ANM_ID")
	private Integer anmID;

	@Expose
	@Column(name = "ASHA_ID")
	private Integer ashaID;

	@Expose
	@Column(name = "Call_Answered")
	private Boolean callAnswered;

	@Expose
	@Column(name = "NoCall_Reason")
	private String noCallReason;

	@Expose
	@Column(name = "NoPhone_Reason")
	private String noPhoneReason;

	@Expose
	@Column(name = "Created_By")
	private String createdBy;

	@Expose
	@Column(name = "Updated_By")
	private String updatedBy;

	@Expose
	@Column(name = "Aadhar_no")
	private String aadharNo;

	@Expose
	@Column(name = "BPL_APL")
	private String bplApl;

	@Expose
	@Column(name = "EID")
	private String eID;

	@Expose
	@Column(name = "EID_time")
	private Date EIDTime;

	@Expose
	@Column(name = "Entry_type")
	private Integer entrytype;

	@Expose
	@Column(name = "MDDS_StateID")
	private Integer MddsStateID;

	@Expose
	@Column(name = "MDDS_District_ID")
	private Integer MddsDistrictID;

	@Expose
	@Column(name = "MDDS_Taluka_ID")
	private Integer MddsTalukaID;

	@Expose
	@Column(name = "MDDS_Village_ID")
	private Integer MddsVillageID;

	@Column(name = "ProviderServiceMapID")
	private Long providerServiceMapID;

	@Transient
	private Timestamp startDate = new Timestamp(0);

	@Transient
	private Timestamp endDate = new Timestamp(Calendar.getInstance().getTimeInMillis());

	@Column(name = "CreatedDate")
	private Timestamp createdDate;

	@Transient
	private Long userID;

	@Transient
	private Boolean isMother;

	public Timestamp getStartDate() {
		return startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public Long getProviderServiceMapID() {
		return providerServiceMapID;
	}

	@Override
	public String toString() {
		return new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).serializeNulls()
				.excludeFieldsWithoutExposeAnnotation().setDateFormat("dd-MM-yyyy").create().toJson(this);

	}
}
