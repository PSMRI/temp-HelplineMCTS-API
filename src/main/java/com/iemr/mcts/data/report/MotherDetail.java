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

import org.springframework.format.annotation.DateTimeFormat;

import com.google.gson.annotations.Expose;
import com.iemr.mcts.utils.mapper.OutputMapper;

import lombok.Data;

@Entity
@Table(name = "db_reporting.fact_mothervalidrecord", schema = "db_reporting")
@Data
public class MotherDetail implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Fact_MotherValidRecordID")
	private Long motherValidRecordID;

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
	private String PHCID;

	@Expose
	@Column(name = "PHC_Name")
	private String PHCName;
	
	@Expose
	@Column(name = "SUBPHC_ID")
	private String SUBPHCID;
	
	@Expose
	@Column(name = "SUBPHC_Name")
	private String SUBPHCName;
	
	@Expose
	@Column(name = "YR")
	private Character yR;
	
	@Expose
	@Column(name = "GP_Village")
	private String GPVillage;
	
	@Expose
	@Column(name = "Address")
	private String address;
	
	@Expose
	@Column(name = "MCTSID_no")
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
	@Column(name = "JSY_Beneficiary")
	private Character JSYBeneficiary;
	
	@Expose
	@Column(name = "Caste")
	private String caste;
	
	@Expose
	@Column(name = "SubCenter_Name")
	private String subCenterName;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date TT1Date;

	@Expose
	@Column(name = "TT2_Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
	@Column(name = "Abortion")
	private String abortion;
	
	@Expose
	@Column(name = "PNC_Home_Visit")
	private String PNCHomeVisit;
	
	@Expose
	@Column(name = "PNC_Complication")
	private String PNCComplication;
	
	@Expose
	@Column(name = "PPC_Method")
	private String PPCMethod;
	
	@Expose
	@Column(name = "PNC_Checkup")
	private Boolean PNCCheckup;
	
	@Expose
	@Column(name = "Outcome_Nos")
	private Long OutcomeNos;
	
	@Expose
	@Column(name = "Child1_ID")
	private Long child1ID;
	
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
	@Column(name = "Child2_ID")
	private Long child2ID;
	
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
	@Column(name = "Child3_ID")
	private Long child3ID;
	
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
	@Column(name = "Child4_ID")
	private Long child4ID;
	
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
	@Column(name = "Mother_Reg_Date")
	private Date MotherRegDate;
	
	@Expose
	@Column(name = "Last_Update_Date")
	private Date LastUpdateDate;
	
	@Expose
	@Column(name = "EID")
	private String eID;
	
	@Expose
	@Column(name = "EID_time")
	private Date EIDTime;
	
	@Expose
	@Column(name = "CPSMS_Flag")
	private Boolean CPSMSFlag;
	
	@Expose
	@Column(name = "Bank_Name")
	private String BankName;
	
	@Expose
	@Column(name = "Bank_Branch_Name")
	private String BankBranchName;
	
	@Expose
	@Column(name = "Acc_No")
	private String AccNo;
	
	@Expose
	@Column(name = "IFSC_Code")
	private String IFSCCode;
	
	@Expose
	@Column(name = "Remarks")
	private String remarks;
	
	@Column(name = "ProviderServiceMapID")
	private Long providerServiceMapID;
	
	@Transient
	private Timestamp startDate = new Timestamp(0);

	@Transient
	private Timestamp endDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
	
	@Column(name = "CreatedDate")
	private Timestamp createdDate;
	
	@Column(name = "BeneficiaryRegID")
	private Long beneficiaryRegID;
	
	@OneToOne
	@JoinColumn(name = "beneficiaryRegID", referencedColumnName = "BeneficiaryRegID", insertable = false, updatable = false)
	@Expose
	private IdentityDetail identityDetail;
	
	@Expose
	@Column(name = "High_Risk")
	private Boolean HighRisk;
	
	@Expose
	@Column(name = "High_Risk_Reason")
	private String HighRiskReason;
	
	@Column(name = "IsSelfNo")
	private Boolean isSelfNo;
	

	@Override
	public String toString()
	{
		return OutputMapper.gsonWithoutExposeRestriction().toJson(this);
	}
	
}
