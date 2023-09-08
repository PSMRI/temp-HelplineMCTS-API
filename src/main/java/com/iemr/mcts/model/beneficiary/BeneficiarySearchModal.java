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

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BeneficiarySearchModal {

	private String outboundCallType;
	private String beneficiaryID;
	private Short titleId;
	private String titleName;
	private String firstName;
	private String middleName;
	private String lastName;
	private Short genderID;
	private Timestamp dOB;
	private String govtIdentityNo;
	private Short govtIdentityTypeID;
	private Integer cityID;
	private String cityName;
	private Integer stateID;
	private String stateName;
	private Integer districtID;
	private String districtName;
	private Integer blockID;
	private String blockName;
	private Integer districtBranchID;
	private String districtBranchName;
	private Integer countryID;
	private String countryName;
	private String pinCode;
	private String contactNumber;
	private String createdBy;
}
