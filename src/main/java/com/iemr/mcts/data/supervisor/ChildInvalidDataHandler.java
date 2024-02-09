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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.google.gson.annotations.Expose;
import com.iemr.mcts.utils.mapper.OutputMapper;

@Entity
@Table(name = "t_childinvaliddata")
public class ChildInvalidDataHandler {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Long RowID;

	@Expose
	private Date Date_of_Entry;

	@Expose
	private Long Entry_type;

	@Expose
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
	private Date DOB;

	@Expose
	private String Place_of_Birth;

	@Expose
	private String Gender;

	@Expose
	private String Caste;

	@Expose
	private String BloodGroup;

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
	private String State_Name;

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
	private Long PHC_ID;

	@Expose
	private String PHC_Name;

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
	private Long ANM_ID;

	@Expose
	private String ANM_Name;

	@Expose
	private String ANM_Phone_No;

	@Expose
	private Long ASHA_ID;

	@Expose
	private String ASHA_Name;

	@Expose
	private String ASHA_Phone_No;

	@Expose
	private Date BCG_Date;

	@Expose
	private Date OPV0_Date;

	@Expose
	private Date Hepatitis_B1_Date;

	@Expose
	private Date DPT1_Date;

	@Expose
	private Date OPV1_Date;

	@Expose
	private Date Hepatitis_B2_Date;

	@Expose
	private Date DPT2_Date;

	@Expose
	private Date OPV2_Date;

	@Expose
	private Date Hepatitis_B3_Date;

	@Expose
	private Date DPT3_Date;

	@Expose
	private Date OPV3_Date;

	@Expose
	private Date Hepatitis_B4_Date;

	@Expose
	private Date Measles_Date;

	@Expose
	private Date VitA_Dose1_Date;

	@Expose
	private Date MR_Date;

	@Expose
	private Date DPTBooster_Date;

	@Expose
	private Date OPVBooster_Date;

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
	private Date JE_Date;

	@Expose
	private Date DT5_Date;

	@Expose
	private Date TT10_Date;

	@Expose
	private Date TT16_Date;

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
	private Long SMS_Status;

	@Expose
	private String Created_By;

	@Expose
	private String Updated_By;

	@Expose
	private Long MDDS_StateID;

	@Expose
	private Long MDDS_District_ID;

	@Expose
	private Long MDDS_Taluka_ID;

	@Expose
	private Long MDDS_Village_ID;

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
	private Long CID_NO;

	@Expose
	private Long MID_NO;

	@Expose
	// private Boolean Duplicate_Boolean;
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
	private String CreatedBy;

	@Expose
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Date CreatedDate;

	@Expose
	private String ModifiedBy;

	@Expose
	private Date LastModDate;

	/**
	 * @return the mCTSID_no_Child_ID
	 */
	public Long getMCTSID_no_Child_ID() {
		return MCTSID_no_Child_ID;
	}

	/**
	 * @param mCTSID_no_Child_ID the mCTSID_no_Child_ID to set
	 */
	public void setMCTSID_no_Child_ID(Long mCTSID_no_Child_ID) {
		MCTSID_no_Child_ID = mCTSID_no_Child_ID;
	}

	/**
	 * @return the dOB
	 */
	public Date getDOB() {
		return DOB;
	}

	/**
	 * @return the mother_ID
	 */
	public Long getMother_ID() {
		return Mother_ID;
	}

	/**
	 * @param mother_ID the mother_ID to set
	 */
	public void setMother_ID(Long mother_ID) {
		Mother_ID = mother_ID;
	}

	/**
	 * @param dOB the dOB to set
	 */
	public void setDOB(Date dOB) {
		DOB = dOB;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return OutputMapper.gson().toJson(this);
	}
}
