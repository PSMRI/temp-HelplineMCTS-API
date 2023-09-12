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
package com.iemr.mcts.model.beneficiary;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

import com.iemr.mcts.model.user.Title;
import com.iemr.mcts.model.userbeneficiary.GenderModel;
import com.iemr.mcts.model.userbeneficiary.GovtIdentityType;
import com.iemr.mcts.model.userbeneficiary.MaritalStatusModel;
import com.iemr.mcts.model.userbeneficiary.SexualOrientationModel;
import com.iemr.mcts.model.userbeneficiary.Status;

import lombok.Data;

@Data
public class BeneficiaryModel {

	private Long beneficiaryRegID;
	private BeneficiaryDemographicsModel i_bendemographics;
	private List<BenPhoneMapModel> benPhoneMaps;
	private List<BenPhoneMapModel> parentBenPhoneMaps;
	private String beneficiaryID;
	private Short titleId;
	private String titleName;
	private Title m_title;
	private String firstName;
	private String middleName;
	private String lastName;
	private Short statusID;
	private String statusName;
	private Status status;
	private Short genderID;
	private String genderName;
	private GenderModel m_gender;
	private Short maritalStatusID;
	private String maritalStatusName;
	private MaritalStatusModel maritalStatusModel;
	private Short sexualOrientationId;
	private SexualOrientationModel sexualOrientationModel;
	private Timestamp dOB;
	private Integer age;
	private String fatherName;
	private String motherName;// newly added
	private String spouseName;
	private SimpleDateFormat formatYear/* = new SimpleDateFormat("yyyy") */;
	private String govtIdentityNo;
	private Short govtIdentityTypeID;
	private GovtIdentityType govtIdentityType;
	private Short registeredServiceID;
	private String sourceOfInformation;
	private Boolean deleted;
	private String createdBy;
	private Timestamp createdDate;
	private String modifiedBy;
	private Boolean isHIVPos;
	private String placeOfWork;
	private String remarks;
	private Long providerServiceMapID;
	private List<BeneficiaryIdentityModel> beneficiaryIdentities;

	// For Data Sync
	private Integer vanID;

	private Integer actualAge;

	public BeneficiaryModel() {

	}

	public static Date getJavaSqlData(Timestamp timestamp) {
		if (timestamp != null) {
			LocalDateTime ld = timestamp.toLocalDateTime();
			return Date.valueOf(ld.toLocalDate());
		}

		else {

			Date d = null;

			return d;
		}
	}
}
