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
package com.iemr.mcts.services.agent;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.mcts.configure.HttpRestModal;
import com.iemr.mcts.data.mapper.ChildDataMapper;
import com.iemr.mcts.data.mapper.MotherDataMapper;
import com.iemr.mcts.data.mapper.SearchDataMapper;
import com.iemr.mcts.data.supervisor.MctsOutboundCall;
import com.iemr.mcts.model.beneficiary.BenPhoneMapModel;
import com.iemr.mcts.model.beneficiary.BeneficiaryIdentityModel;
import com.iemr.mcts.model.beneficiary.BeneficiaryModel;
import com.iemr.mcts.model.beneficiary.BeneficiarySearchModal;
import com.iemr.mcts.model.beneficiary.UpdateBeneficiaryModel;
import com.iemr.mcts.repository.agent.MCTSOutboundCallRepository;
import com.iemr.mcts.repository.supervisor.ChildValidDataRepository;
import com.iemr.mcts.repository.supervisor.FileManagerRepository;
import com.iemr.mcts.repository.supervisor.MctsDataHandlerRepository;
import com.iemr.mcts.transport.IdentityTransport;
import com.iemr.mcts.utils.config.ConfigProperties;
import com.iemr.mcts.utils.exception.IEMRException;
import com.iemr.mcts.utils.mapper.InputMapper;
import com.iemr.mcts.utils.mapper.OutputMapper;

@Service
public class MctsIdentityServiceImpl implements MctsIdentityService {

	/**
	 * Inject HttpRestModal
	 */
	@Autowired
	private HttpRestModal httpRestModal;

	/**
	 * Inject Mother Mapper
	 */
	@Autowired
	private MotherDataMapper motherDataMapper;

	/**
	 * Inject Child Mapper
	 */
	@Autowired
	private ChildDataMapper childDataMapper;

	/**
	 * Search Mapper
	 */
	@Autowired
	private SearchDataMapper searchDataMapper;

	@Autowired
	private FileManagerRepository fileManagerRepository;

	/**
	 * Common URL
	 */
	private static final String COMMON_URL = ConfigProperties.getPropertyByName("common-api");

	/**
	 * Search URL
	 */
	private static final String SEARCH_URL = ConfigProperties.getPropertyByName("common-api-search-url");

	/**
	 * Create URL
	 */
	private static final String CREATE_URL = ConfigProperties.getPropertyByName("common-api-create-url");

	/**
	 * SearchUserByID URL
	 */
	private static final String SEARCHUSERBYID_URL = ConfigProperties
			.getPropertyByName("common-api-searchuserbyid-url");

	/**
	 * SearchUserByID URL
	 */
	private static final String SEARCHUSERBYPHONENO_URL = ConfigProperties
			.getPropertyByName("common-api-searchuserbyphoneno-url");

	/**
	 * Update URL
	 */
	private static final String UPDATE_URL = ConfigProperties.getPropertyByName("common-api-update-url");

	/**
	 * OutputMapper
	 */
	private static OutputMapper outputMapper = new OutputMapper();

	/**
	 * mcts data handler repostory -- for valid data
	 */
	private MctsDataHandlerRepository mctsDataHandlerRepository;

	/**
	 * Inject mcts data handler repository -- for valid data
	 */
	@Autowired
	public void setMctsDataHandlerRepository(MctsDataHandlerRepository mctsDataHandlerRepository) {

		this.mctsDataHandlerRepository = mctsDataHandlerRepository;
	}

	/**
	 * Child valid data repository
	 */
	private ChildValidDataRepository childValidDataRepository;

	/**
	 * Inject child valid data repository
	 */
	@Autowired
	public void setChildValidDataRepository(ChildValidDataRepository childValidDataRepository) {

		this.childValidDataRepository = childValidDataRepository;
	}

	/**
	 * mcts outbound call repository
	 */
	private MCTSOutboundCallRepository mctsOutboundCallRepository;

	/**
	 * inject mcts outbound call repository
	 */
	@Autowired
	public void setMCTSOutboundCallsRepository(MCTSOutboundCallRepository mctsOutboundCallRepository) {

		this.mctsOutboundCallRepository = mctsOutboundCallRepository;
	}

	@Override
	public String searchBeneficiary(String request, HttpServletRequest servletRequest) throws IEMRException {

		BeneficiarySearchModal searchModal = InputMapper.gson().fromJson(request, BeneficiarySearchModal.class);
		BeneficiaryModel beneficiaryModel = null;
		String req = null;
		List<?> list = null;

		if (searchModal.getOutboundCallType().indexOf("PNC") != -1) {

			beneficiaryModel = searchDataMapper.ModalToBeneficiaryModel(searchModal);
			req = OutputMapper.gsonWithoutExposeRestriction().toJson(beneficiaryModel);
			req = httpRestModal.restURLConnect(req, COMMON_URL + SEARCH_URL, servletRequest.getHeader("Authorization"));
			BeneficiaryModel[] beneficiaryModels = InputMapper.gson().fromJson(req, BeneficiaryModel[].class);
			list = childDataMapper.RequestListToChildValidDataHandlerList(Arrays.asList(beneficiaryModels));
		} else {

			beneficiaryModel = searchDataMapper.ModalToBeneficiaryModel(searchModal);
			req = OutputMapper.gsonWithoutExposeRestriction().toJson(beneficiaryModel);
			req = httpRestModal.restURLConnect(req, COMMON_URL + SEARCH_URL, servletRequest.getHeader("Authorization"));
			BeneficiaryModel[] beneficiaryModels = InputMapper.gson().fromJson(req, BeneficiaryModel[].class);
			list = motherDataMapper.RequestListToMctsDataReaderDetailList(Arrays.asList(beneficiaryModels));
		}

		return list.toString();
	}

	@Override
	public String createBeneficiaries(String request, HttpServletRequest servletRequest) throws IEMRException {

		IdentityTransport identityTransport = InputMapper.gson().fromJson(request, IdentityTransport.class);
		BeneficiaryModel beneficiaryModel = null;
		MctsOutboundCall mctsOutboundCall = identityTransport.getMctsOutboundCall();
		BeneficiarySearchModal beneficiarySearchModal = identityTransport.getBeneficiarySearchModal();
		String req = null;
		BeneficiaryIdentityModel blankmodal = new BeneficiaryIdentityModel();
		List<BeneficiaryIdentityModel> blanklist = new ArrayList<BeneficiaryIdentityModel>();
		blanklist.add(blankmodal);

		if (mctsOutboundCall.getChildValidDataHandler() != null) {

			beneficiaryModel = childDataMapper.ChildValidDataHandlerToRequest(
					mctsOutboundCall.getChildValidDataHandler(), beneficiarySearchModal.getCreatedBy());
			beneficiaryModel.setFirstName(beneficiarySearchModal.getFirstName());
			beneficiaryModel.setLastName(beneficiarySearchModal.getLastName());
			beneficiaryModel.setGenderID(beneficiarySearchModal.getGenderID());
			beneficiaryModel.getI_bendemographics().setStateID(beneficiarySearchModal.getStateID());
			beneficiaryModel.getI_bendemographics().setStateName(beneficiarySearchModal.getStateName());
			beneficiaryModel.getI_bendemographics().setDistrictID(beneficiarySearchModal.getDistrictID());
			beneficiaryModel.getI_bendemographics().setDistrictName(beneficiarySearchModal.getDistrictName());
			beneficiaryModel.setCreatedBy(beneficiarySearchModal.getCreatedBy());
			beneficiaryModel.setBeneficiaryIdentities(blanklist);
			beneficiaryModel.setProviderServiceMapID(mctsOutboundCall.getProviderServiceMapID());
			beneficiaryModel.setVanID(mctsOutboundCall.getVanID());

			if (mctsOutboundCall.getChildValidDataHandler().getDOB() != null) {
				Timestamp dob = new Timestamp(mctsOutboundCall.getChildValidDataHandler().getDOB().getTime());
				beneficiaryModel.setDOB(dob);
			}

			String response = this.getByBeneficiaryPhoneNo(mctsOutboundCall.getChildValidDataHandler().getPhone_No(),
					servletRequest);
			BeneficiaryModel[] beneficiaryModelArray = InputMapper.gson().fromJson(response, BeneficiaryModel[].class);

			if (beneficiaryModelArray != null) {
				ListIterator<BeneficiaryModel> iterator = Arrays.asList(beneficiaryModelArray).listIterator();
				BeneficiaryModel beneficiary;
				while (iterator.hasNext()) {
					beneficiary = iterator.next();
					ListIterator<BenPhoneMapModel> innerIterator = beneficiary.getBenPhoneMaps().listIterator();
					BenPhoneMapModel benPhoneMapModel;
					while (innerIterator.hasNext()) {
						benPhoneMapModel = innerIterator.next();
						if (benPhoneMapModel.getBenRelationshipID() != null) {
							if (benPhoneMapModel.getBenRelationshipID() == 1) {
								beneficiaryModel.getBenPhoneMaps().get(0).setBenRelationshipID(11);
								if (beneficiary.getBeneficiaryRegID() != null) {
									beneficiaryModel.getBenPhoneMaps().get(0)
											.setParentBenRegID(beneficiary.getBeneficiaryRegID());
								}
							}
						}
					}
				}
			} else {

				beneficiaryModel.getBenPhoneMaps().get(0).setBenRelationshipID(1);
			}

			req = OutputMapper.gsonWithoutExposeRestriction().toJson(beneficiaryModel);
			req = httpRestModal.restURLConnect(req, COMMON_URL + CREATE_URL, servletRequest.getHeader("Authorization"));
			BeneficiaryModel model = InputMapper.gson().fromJson(req, BeneficiaryModel.class);

			childValidDataRepository.addBeneficiaryRegID(model.getBeneficiaryRegID(),
					mctsOutboundCall.getChildValidDataHandler().getMCTSID_no_Child_ID());
			mctsOutboundCallRepository.addBeneficiaryRegIDChild(
					mctsOutboundCall.getChildValidDataHandler().getMCTSID_no_Child_ID(), model.getBeneficiaryRegID());
			mctsOutboundCall.getChildValidDataHandler().setBeneficiaryRegID(model.getBeneficiaryRegID());
			mctsOutboundCall.getChildValidDataHandler().setBeneficiaryID(model.getBeneficiaryID());
			mctsOutboundCall.setBeneficiaryRegID(model.getBeneficiaryRegID());

		} else {

			beneficiaryModel = motherDataMapper.MctsDataReaderDetailToRequest(
					mctsOutboundCall.getMctsDataReaderDetail(), beneficiarySearchModal.getCreatedBy());
			beneficiaryModel.setFirstName(beneficiarySearchModal.getFirstName());
			beneficiaryModel.setLastName(beneficiarySearchModal.getLastName());
			beneficiaryModel.setGenderID(beneficiarySearchModal.getGenderID());
			beneficiaryModel.getI_bendemographics().setStateID(beneficiarySearchModal.getStateID());
			beneficiaryModel.getI_bendemographics().setStateName(beneficiarySearchModal.getStateName());
			beneficiaryModel.getI_bendemographics().setDistrictID(beneficiarySearchModal.getDistrictID());
			beneficiaryModel.getI_bendemographics().setDistrictName(beneficiarySearchModal.getDistrictName());
			beneficiaryModel.setCreatedBy(beneficiarySearchModal.getCreatedBy());
			beneficiaryModel.setBeneficiaryIdentities(blanklist);
			beneficiaryModel.setProviderServiceMapID(mctsOutboundCall.getProviderServiceMapID());
			beneficiaryModel.setVanID(mctsOutboundCall.getVanID());

			if (mctsOutboundCall.getMctsDataReaderDetail().getBirth_Date() != null) {
				Timestamp dob = new Timestamp(mctsOutboundCall.getMctsDataReaderDetail().getBirth_Date().getTime());
				beneficiaryModel.setDOB(dob);
			}

			String response = this.getByBeneficiaryPhoneNo(mctsOutboundCall.getMctsDataReaderDetail().getWhom_PhoneNo(),
					servletRequest);
			BeneficiaryModel[] beneficiaryModelArray = InputMapper.gson().fromJson(response, BeneficiaryModel[].class);

			if (beneficiaryModelArray != null) {
				ListIterator<BeneficiaryModel> iterator = Arrays.asList(beneficiaryModelArray).listIterator();
				BeneficiaryModel beneficiary;
				while (iterator.hasNext()) {
					beneficiary = iterator.next();
					ListIterator<BenPhoneMapModel> innerIterator = beneficiary.getBenPhoneMaps().listIterator();
					BenPhoneMapModel benPhoneMapModel;
					while (innerIterator.hasNext()) {
						benPhoneMapModel = innerIterator.next();
						if (benPhoneMapModel.getBenRelationshipID() != null) {
							if (benPhoneMapModel.getBenRelationshipID() == 1) {
								beneficiaryModel.getBenPhoneMaps().get(0).setBenRelationshipID(11);
								if (beneficiary.getBeneficiaryRegID() != null) {
									beneficiaryModel.getBenPhoneMaps().get(0)
											.setParentBenRegID(beneficiary.getBeneficiaryRegID());
								}
							}
						}
					}
				}
			} else {

				beneficiaryModel.getBenPhoneMaps().get(0).setBenRelationshipID(1);
			}

			req = OutputMapper.gsonWithoutExposeRestriction().toJson(beneficiaryModel);
			req = httpRestModal.restURLConnect(req, COMMON_URL + CREATE_URL, servletRequest.getHeader("Authorization"));
			BeneficiaryModel model = InputMapper.gson().fromJson(req, BeneficiaryModel.class);

			mctsDataHandlerRepository.addBeneficiaryRegID(model.getBeneficiaryRegID(),
					mctsOutboundCall.getMctsDataReaderDetail().getMCTSID_no());
			mctsOutboundCallRepository.addBeneficiaryRegIDMother(
					mctsOutboundCall.getMctsDataReaderDetail().getMCTSID_no(), model.getBeneficiaryRegID());
			mctsOutboundCall.getMctsDataReaderDetail().setBeneficiaryRegID(model.getBeneficiaryRegID());
			mctsOutboundCall.getMctsDataReaderDetail().setBeneficiaryID(model.getBeneficiaryID());
			mctsOutboundCall.setBeneficiaryRegID(model.getBeneficiaryRegID());
		}

		return mctsOutboundCall.toString();
	}

	@Override
	public String getBeneficiaryID(String request, HttpServletRequest servletRequest) throws IEMRException {

		MctsOutboundCall mctsOutboundCall = InputMapper.gson().fromJson(request, MctsOutboundCall.class);
		String req = null;
		BeneficiaryModel beneficiaryModel = null;
		if (mctsOutboundCall.getChildValidDataHandler() != null) {

			req = httpRestModal.restURLConnect(
					"{\"beneficiaryRegID\":" + mctsOutboundCall.getChildValidDataHandler().getBeneficiaryRegID() + "}",
					COMMON_URL + SEARCHUSERBYID_URL, servletRequest.getHeader("Authorization"));
			BeneficiaryModel[] beneficiaryModelArray = InputMapper.gson().fromJson(req, BeneficiaryModel[].class);
			mctsOutboundCall.getChildValidDataHandler().setBeneficiaryID(beneficiaryModelArray[0].getBeneficiaryID());
		} else {

			req = httpRestModal.restURLConnect(
					"{\"beneficiaryRegID\":" + mctsOutboundCall.getMctsDataReaderDetail().getBeneficiaryRegID() + "}",
					COMMON_URL + SEARCHUSERBYID_URL, servletRequest.getHeader("Authorization"));
			BeneficiaryModel[] beneficiaryModelArray = InputMapper.gson().fromJson(req, BeneficiaryModel[].class);
			mctsOutboundCall.getMctsDataReaderDetail().setBeneficiaryID(beneficiaryModelArray[0].getBeneficiaryID());
			mctsOutboundCall.getMctsDataReaderDetail().setAge(beneficiaryModelArray[0].getActualAge());
		}

		return mctsOutboundCall.toString();
	}

	@Override
	public String getByBeneficiaryPhoneNo(String request, HttpServletRequest servletRequest) throws IEMRException {

		return httpRestModal.restURLConnect("{\"phoneNo\":" + request + "}", COMMON_URL + SEARCHUSERBYPHONENO_URL,
				servletRequest.getHeader("Authorization"));
	}

	@Override
	public String updateBeneficiaries(String request, HttpServletRequest servletRequest) throws IEMRException {

		MctsOutboundCall mctsOutboundCall = InputMapper.gson().fromJson(request, MctsOutboundCall.class);

		UpdateBeneficiaryModel beneficiaryModel = null;
		String req = null;
		String response = null;
		BeneficiaryIdentityModel blankmodal = new BeneficiaryIdentityModel();
		List<BeneficiaryIdentityModel> blanklist = new ArrayList<BeneficiaryIdentityModel>();
		blanklist.add(blankmodal);

		if (mctsOutboundCall.getChildValidDataHandler() != null) {

			response = httpRestModal.restURLConnect(
					"{\"beneficiaryRegID\":" + mctsOutboundCall.getChildValidDataHandler().getBeneficiaryRegID() + "}",
					COMMON_URL + SEARCHUSERBYID_URL, servletRequest.getHeader("Authorization"));

			UpdateBeneficiaryModel[] beneficiaryModelArray = InputMapper.gson().fromJson(response,
					UpdateBeneficiaryModel[].class);
			System.out.println("error occured in IF");
			if (beneficiaryModelArray.length > 0)
				beneficiaryModel = beneficiaryModelArray[0];
			else
				throw new IEMRException("Beneficiary Not Found");

			System.out.println("error occured in IF after");

			String name = mctsOutboundCall.getChildValidDataHandler().getChild_Name();
			String[] nameArray = name.split(" ");
			String firstName = nameArray[0];
			String lastName = null;
			if (nameArray.length == 2) {
				lastName = nameArray[1];
			} else if (nameArray.length == 3) {
				lastName = nameArray[1] + " " + nameArray[2];
			}
			beneficiaryModel.setBeneficiaryRegID(mctsOutboundCall.getBeneficiaryRegID());
			beneficiaryModel.setFirstName(firstName);
			beneficiaryModel.setLastName(lastName);
			if (mctsOutboundCall.getChildValidDataHandler().getGender() != null
					&& mctsOutboundCall.getChildValidDataHandler().getGender().equalsIgnoreCase("Male")) {
				beneficiaryModel.setGenderID(1);
			} else if (mctsOutboundCall.getChildValidDataHandler().getGender() != null
					&& mctsOutboundCall.getChildValidDataHandler().getGender().equalsIgnoreCase("Female")) {
				beneficiaryModel.setGenderID(2);
			} else {
				beneficiaryModel.setGenderID(3);
			}

			beneficiaryModel.setFatherName(mctsOutboundCall.getChildValidDataHandler().getFather_Name());
			beneficiaryModel.setMotherName(mctsOutboundCall.getChildValidDataHandler().getMother_Name());
			beneficiaryModel.setDOB(mctsOutboundCall.getChildValidDataHandler()
					.setDobForIdentity(mctsOutboundCall.getChildValidDataHandler()));

			beneficiaryModel.getI_bendemographics().setCreatedDate(beneficiaryModelArray[0].getCreatedDate());
			beneficiaryModel.getI_bendemographics().setCreatedBy(beneficiaryModelArray[0].getCreatedBy());
			beneficiaryModel.setRemarks(mctsOutboundCall.getChildValidDataHandler().getRemarks());
			beneficiaryModel.setCreatedBy(beneficiaryModelArray[0].getCreatedBy());
			beneficiaryModel.setCreatedDate(beneficiaryModelArray[0].getCreatedDate());
			beneficiaryModel.setBeneficiaryIdentities(blanklist);

			List<BenPhoneMapModel> benPhoneMapList = new ArrayList<BenPhoneMapModel>();

			BenPhoneMapModel phoneMap = new BenPhoneMapModel();
			phoneMap.setPhoneNo(mctsOutboundCall.getChildValidDataHandler().getPhone_No());
			phoneMap.setPhoneTypeName(mctsOutboundCall.getChildValidDataHandler().getPhone_No_of());
			phoneMap.setParentBenRegID(beneficiaryModelArray[0].getBenPhoneMaps().get(0).getParentBenRegID());
			phoneMap.setBenRelationshipID(beneficiaryModelArray[0].getBenPhoneMaps().get(0).getBenRelationshipID());
			benPhoneMapList.add(phoneMap);
			beneficiaryModel.setBenPhoneMaps(benPhoneMapList);
			beneficiaryModel.setVanID(mctsOutboundCall.getVanID());

			beneficiaryModel.setChangeInSelfDetails(true);
			beneficiaryModel.setChangeInContacts(true);
			beneficiaryModel.setChangeInAddress(false);

			req = OutputMapper.gsonWithoutExposeRestriction().toJson(beneficiaryModel);
			req = httpRestModal.restURLConnect(req, COMMON_URL + UPDATE_URL, servletRequest.getHeader("Authorization"));

		} else {

			response = httpRestModal.restURLConnect(
					"{\"beneficiaryRegID\":" + mctsOutboundCall.getMctsDataReaderDetail().getBeneficiaryRegID() + "}",
					COMMON_URL + SEARCHUSERBYID_URL, servletRequest.getHeader("Authorization"));
			UpdateBeneficiaryModel[] beneficiaryModelArray = InputMapper.gson().fromJson(response,
					UpdateBeneficiaryModel[].class);
			System.out.println("error occured in else");
			if (beneficiaryModelArray.length > 0)
				beneficiaryModel = beneficiaryModelArray[0];
			else
				throw new IEMRException("Beneficiary Not Found");

			System.out.println("error occured in else after");

			String name = mctsOutboundCall.getMctsDataReaderDetail().getName();
			String[] nameArray = name.split(" ");
			String firstName = nameArray[0];
			String lastName = null;
			if (nameArray.length == 2) {
				lastName = nameArray[1];
			} else if (nameArray.length == 3) {
				lastName = nameArray[1] + " " + nameArray[2];
			}
			beneficiaryModel.setBeneficiaryRegID(mctsOutboundCall.getBeneficiaryRegID());
			beneficiaryModel.setFirstName(firstName);
			beneficiaryModel.setLastName(lastName);
			beneficiaryModel.setGenderID(2);
			beneficiaryModel.setGenderName("Female");
			beneficiaryModel.setSpouseName(mctsOutboundCall.getMctsDataReaderDetail().getHusband_Name());
			beneficiaryModel.setAge(mctsOutboundCall.getMctsDataReaderDetail().getAge());

			beneficiaryModel.getI_bendemographics().setCreatedDate(beneficiaryModelArray[0].getCreatedDate());
			beneficiaryModel.getI_bendemographics().setCreatedBy(beneficiaryModelArray[0].getCreatedBy());
			beneficiaryModel.setRemarks(mctsOutboundCall.getMctsDataReaderDetail().getRemarks());
			beneficiaryModel.setCreatedBy(beneficiaryModelArray[0].getCreatedBy());
			beneficiaryModel.setCreatedDate(beneficiaryModelArray[0].getCreatedDate());
			beneficiaryModel.setBeneficiaryIdentities(blanklist);

			List<BenPhoneMapModel> benPhoneMapList = new ArrayList<BenPhoneMapModel>();

			BenPhoneMapModel phoneMap = new BenPhoneMapModel();
			phoneMap.setPhoneNo(mctsOutboundCall.getMctsDataReaderDetail().getWhom_PhoneNo());
			phoneMap.setPhoneTypeName(mctsOutboundCall.getMctsDataReaderDetail().getPhoneNo_Of_Whom());
			phoneMap.setParentBenRegID(beneficiaryModelArray[0].getBenPhoneMaps().get(0).getParentBenRegID());
			phoneMap.setBenRelationshipID(beneficiaryModelArray[0].getBenPhoneMaps().get(0).getBenRelationshipID());
			phoneMap.setCreatedBy(beneficiaryModelArray[0].getCreatedBy());
			phoneMap.setCreatedDate(beneficiaryModelArray[0].getCreatedDate());
			benPhoneMapList.add(phoneMap);
			beneficiaryModel.setBenPhoneMaps(benPhoneMapList);
			beneficiaryModel.setVanID(mctsOutboundCall.getVanID());

			beneficiaryModel.setChangeInSelfDetails(true);
			beneficiaryModel.setChangeInContacts(true);
			beneficiaryModel.setChangeInAddress(false);

			req = OutputMapper.gsonWithoutExposeRestriction().toJson(beneficiaryModel);
			req = httpRestModal.restURLConnect(req, COMMON_URL + UPDATE_URL, servletRequest.getHeader("Authorization"));

		}

		return mctsOutboundCall.toString();
	}

	private Integer findBlockID(Integer districtID, String blockName) {
		Integer blockID = fileManagerRepository.getBlockID(districtID, blockName);
		if (blockID != null) {
			return blockID;
		} else {
			return 0;
		}
	}

	private Integer findVillageID(Integer blockID, String villageName) {
		Integer villageID = fileManagerRepository.getVillageID(blockID, villageName);
		return villageID;
	}

}
