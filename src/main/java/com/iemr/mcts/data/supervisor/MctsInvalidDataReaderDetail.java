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
@Table(name = "t_motherinvalidrecord")
public class MctsInvalidDataReaderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Long MotherInValidRecordID;

	@Expose
	private Date Date_of_Entry;

	@Expose
	private Long Entry_type;

	@Expose
	@Column(name = "MCTSID_no", insertable = true, updatable = true)
	private Long MCTSID_no;

	@Expose
	private String Name;

	@Expose
	private String Husband_Name;

	@Expose
	private String PhoneNo_Of_Whom;

	@Expose
	private String Whom_PhoneNo;

	@Expose
	private Date Birth_Date;

	@Expose
	private Integer Age;

	@Expose
	private String Blood_Group;

	@Expose
	private String Caste;

	@Expose
	private String Aadhar_no;

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
	private Long ANM_ID;

	@Expose
	private String ANM_Name;

	@Expose
	private String ANM_Ph;

	@Expose
	private Long ASHA_ID;

	@Expose
	private String ASHA_Name;

	@Expose
	private String ASHA_Ph;

	@Expose
	private Long PHC_ID;

	@Expose
	private String PHC_Name;

	@Expose
	private Long SUBPHC_ID;

	@Expose
	private String SUBPHC_Name;

	@Expose
	private String YR;

	@Expose
	private String GP_Village;

	@Expose
	private String Address;

	@Expose
	private Date LMP_Date;

	@Expose
	private Date ANC1_Date;

	@Expose
	private Date ANC2_Date;

	@Expose
	private Date ANC3_Date;

	@Expose
	private Date ANC4_Date;

	@Expose
	private String ANC_Complication;

	@Expose
	private Date TT1_Date;

	@Expose
	private Date TT2_Date;

	@Expose
	private Date TTBooster_Date;

	@Expose
	private Date IFA100_Given_Date;

	@Expose
	private Date EDD;

	@Expose
	private String Anemia;

	@Expose
	private String RTI_STI;

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
	private String PNC_Home_Visit;

	@Expose
	private String PNC_Complication;

	@Expose
	private String PPC_Method;

	@Expose
	private String PNC_Checkup;

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
	private String EID;

	@Expose
	private String EID_time;

	@Expose
	private Boolean CPSMS_Flag;

	@Expose
	private String JSY_Beneficiary;

	@Expose
	private Date JSY_Paid_Date;

	@Expose
	private String Bank_Name;

	@Expose
	private String Bank_Branch_Name;

	@Expose
	private String Acc_No;

	@Expose
	private String IFSC_Code;

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
	private String BPL_APL;

	@Expose
	private Long MDDS_StateID;

	@Expose
	private Long MDDS_District_ID;

	@Expose
	private Long MDDS_Taluka_ID;

	@Expose
	private Long MDDS_Village_ID;

	@Expose
	private Boolean Is_Valid;

	@Expose
	private Boolean Is_Error;

	@Expose
	@Column(name = "FileID")
	private Long FileID;

	@Expose
	private String Error_Reason;

	@Expose
	private Boolean IsAllocated;

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

	@Expose
	private String InValid_Reason;

	/**
	 * @return the isAllocated
	 */
	public Boolean getIsAllocated() {
		return IsAllocated;
	}

	/**
	 * @param isAllocated the isAllocated to set
	 */
	public void setIsAllocated(Boolean isAllocated) {
		IsAllocated = isAllocated;
	}

	/**
	 * @return the is_Valid
	 */
	public Boolean getIs_Valid() {
		return Is_Valid;
	}

	/**
	 * @param is_Valid the is_Valid to set
	 */
	public void setIs_Valid(Boolean is_Valid) {
		Is_Valid = is_Valid;
	}

	/**
	 * @return the is_Error
	 */
	public Boolean getIs_Error() {
		return Is_Error;
	}

	/**
	 * @param is_Error the is_Error to set
	 */
	public void setIs_Error(Boolean is_Error) {
		Is_Error = is_Error;
	}

	/**
	 * @return the error_Reason
	 */
	public String getError_Reason() {
		return Error_Reason;
	}

	/**
	 * @param error_Reason the error_Reason to set
	 */
	public void setError_Reason(String error_Reason) {
		Error_Reason = error_Reason;
	}

	/**
	 * @return the mCTSID_no
	 */
	public Long getMCTSID_no() {
		return MCTSID_no;
	}

	/**
	 * @param mCTSID_no the mCTSID_no to set
	 */
	public void setMCTSID_no(Long mCTSID_no) {
		MCTSID_no = mCTSID_no;
	}

	/**
	 * @return the eDD
	 */
	public Date getEDD() {
		return EDD;
	}

	/**
	 * @param eDD the eDD to set
	 */
	public void setEDD(Date eDD) {

		EDD = eDD;
	}

	/**
	 * @return the lMP_Date
	 */
	public Date getLMP_Date() {
		return LMP_Date;
	}

	/**
	 * @param lMP_Date the lMP_Date to set
	 */
	public void setLMP_Date(Date lMP_Date) {
		LMP_Date = lMP_Date;
	}

	/**
	 * @return the inValid_Reason
	 */
	public String getInValid_Reason() {
		return InValid_Reason;
	}

	/**
	 * @param inValid_Reason the inValid_Reason to set
	 */
	public void setInValid_Reason(String inValid_Reason) {
		InValid_Reason = inValid_Reason;
	}

	private static OutputMapper outputMapper = new OutputMapper();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return outputMapper.gson().toJson(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((MCTSID_no == null) ? 0 : MCTSID_no.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		return true;
	}

}
