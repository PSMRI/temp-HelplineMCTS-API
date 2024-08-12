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
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.mcts.constants.MctsConstants;
import com.iemr.mcts.data.agent.MctsOutboundCallDetail;
import com.iemr.mcts.data.supervisor.AgentCallAllocationDetail;
import com.iemr.mcts.data.supervisor.AllocatedCalls;
import com.iemr.mcts.data.supervisor.ChildValidDataHandler;
import com.iemr.mcts.data.supervisor.MctsDataReaderDetail;
import com.iemr.mcts.data.supervisor.MctsOutboundCall;
import com.iemr.mcts.data.supervisor.OutboundWorklist;
import com.iemr.mcts.data.utils.ReallocationDataUtil;
import com.iemr.mcts.repository.agent.HighRiskReasonRepository;
import com.iemr.mcts.repository.agent.MCTSOutboundCallRepository;
import com.iemr.mcts.utils.exception.IEMRException;
import com.iemr.mcts.utils.mapper.InputMapper;

@Service
public class MctsOutbondCallServiceImpl implements MctsOutbondCallService {

	InputMapper inputMapper = new InputMapper();

	/**
	 * Inject HttpRestModal
	 */
	@Autowired
	private MctsIdentityServiceImpl mctsIdentityServiceImpl;

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iemr.mcts.services.agent.OutbondCallService#getOutbondCalls()
	 */

	public static OutboundWorklist MapMotherfromObj(Object objects[], Long benRegID,
			MctsDataReaderDetail readerDetail) {
		return new OutboundWorklist(((Number) objects[0]).longValue(), benRegID, (Integer) objects[2],
				((Number) objects[3]).longValue(), (String) objects[4], (Timestamp) objects[5], (Integer) objects[6],
				(String) objects[7], ((Number) objects[9]).longValue(), readerDetail);
	}

	public static OutboundWorklist MapChildfromObj(Object objects[], Long benRegID, Long motherID,
			ChildValidDataHandler childValidDataHandler) {
		return new OutboundWorklist(benRegID, (String) objects[1], (Timestamp) objects[3], (Integer) objects[4],
				(String) objects[2], ((Number) objects[11]).longValue(), motherID, ((Number) objects[5]).longValue(),
				childValidDataHandler);
	}

	@Override
	public String getOutbondCalls(String request) throws IEMRException {
		MctsOutboundCall mctsOutboundCall = inputMapper.gson().fromJson(request, MctsOutboundCall.class);

		List<Objects[]> motherRecords = mctsOutboundCallRepository
				.getAgentAllocatedMotherList(mctsOutboundCall.getAllocatedUserID());

		List<OutboundWorklist> agentMotherList = new ArrayList<OutboundWorklist>();

		MctsDataReaderDetail readerDetail = null;

		for (Object[] objects : motherRecords) {
			if (objects != null && objects.length > 0) {
				readerDetail = new MctsDataReaderDetail();
				readerDetail.setName((String) objects[8]);
				readerDetail.setMCTSID_no(((Number) objects[9]).longValue());
				readerDetail.setPhoneNo_Of_Whom((String) objects[10]);
				readerDetail.setWhom_PhoneNo((String) objects[11]);
				readerDetail.setHigh_Risk((Boolean) objects[12]);

				Long benRegID = null;

				if (objects[1] != null) {
					benRegID = ((Number) objects[1]).longValue();
				}
				readerDetail.setBeneficiaryRegID(benRegID);

				agentMotherList.add(MapMotherfromObj(objects, benRegID, readerDetail));
			}
		}
		List<Long> regIDList = new ArrayList<Long>();
		List<Long> obIDList = new ArrayList<Long>();

		for (OutboundWorklist mother : agentMotherList) {
			Long benRegID = mother.getBeneficiaryRegID();

			if (benRegID != null) {
				regIDList.add(benRegID);
			} else {
				obIDList.add(mother.getObCallID());
			}
		}
		Map<Long, MctsOutboundCallDetail> RegIDMap = new HashMap<Long, MctsOutboundCallDetail>();
		Map<Long, MctsOutboundCallDetail> OBIDMap = new HashMap<Long, MctsOutboundCallDetail>();

		if (regIDList.size() > 0) {
			ArrayList<MctsOutboundCallDetail> dataByRegIDList = mctsOutboundCallRepository
					.getLastCallDetailByRegID(regIDList);

			for (MctsOutboundCallDetail details : dataByRegIDList) {
				/* adding only recent call record */
				if (RegIDMap.get(details.getBeneficiaryRegID()) == null) {
					RegIDMap.put(details.getBeneficiaryRegID(), details);
				}
			}
		}
		if (obIDList.size() > 0) {
			ArrayList<MctsOutboundCallDetail> dataByOBIDList = mctsOutboundCallRepository
					.getLastCallDetailByOBCallID(obIDList);

			for (MctsOutboundCallDetail details : dataByOBIDList) {
				/* adding only recent call record */
				if (OBIDMap.get(details.getObCallID()) == null) {
					OBIDMap.put(details.getObCallID(), details);
				}
			}
		}
		agentMotherList.parallelStream().forEach(mother -> {
			Long benRegID = mother.getBeneficiaryRegID();
			Long obID = mother.getObCallID();

			if (benRegID != null) {
				MctsOutboundCallDetail callHistory = RegIDMap.get(benRegID);
				if (callHistory != null) {
					mother.setLastCallRemark(callHistory.getRemark());
					mother.setLastCallTime(callHistory.getCreatedDate());
				}
			} else {
				MctsOutboundCallDetail callHistory = OBIDMap.get(obID);
				if (callHistory != null) {
					mother.setLastCallRemark(callHistory.getRemark());
					mother.setLastCallTime(callHistory.getCreatedDate());
				}
			}
		});
		List<Objects[]> childRecords = mctsOutboundCallRepository
				.getAgentAllocatedChildList(mctsOutboundCall.getAllocatedUserID());

		List<OutboundWorklist> agentChildList = new ArrayList<OutboundWorklist>();
		ChildValidDataHandler childDetail = null;

		for (Object[] objects : childRecords) {
			if (objects != null && objects.length > 0) {
				childDetail = new ChildValidDataHandler();
				childDetail.setChild_Name((String) objects[6]);
				childDetail.setMCTSID_no_Child_ID(((Number) objects[5]).longValue());
				childDetail.setMother_Name((String) objects[8]);
				Long motherID = null;
				if (objects[7] != null) {
					motherID = ((Number) objects[7]).longValue();
					childDetail.setMother_ID(motherID);
				}
				childDetail.setPhone_No((String) objects[10]);
				childDetail.setPhone_No_of((String) objects[9]);

				Long benRegID = null;

				if (objects[0] != null) {
					benRegID = ((Number) objects[0]).longValue();
				}
				childDetail.setBeneficiaryRegID(benRegID);

				agentChildList.add(MapChildfromObj(objects, benRegID, motherID, childDetail));
			}
		}

		List<Long> childRegIDList = new ArrayList<Long>();
		List<Long> childOBIDList = new ArrayList<Long>();

		for (OutboundWorklist child : agentChildList) {
			Long benRegID = child.getBeneficiaryRegID();

			if (benRegID != null) {
				childRegIDList.add(benRegID);
			} else {
				childOBIDList.add(child.getObCallID());
			}
		}
		Map<Long, MctsOutboundCallDetail> childRegIDMap = new HashMap<Long, MctsOutboundCallDetail>();
		Map<Long, MctsOutboundCallDetail> childOBIDMap = new HashMap<Long, MctsOutboundCallDetail>();

		if (childRegIDList.size() > 0) {
			ArrayList<MctsOutboundCallDetail> childDataByRegIDList = mctsOutboundCallRepository
					.getLastCallDetailByRegID(childRegIDList);

			for (MctsOutboundCallDetail details : childDataByRegIDList) {
				/* adding only recent call record */
				if (childRegIDMap.get(details.getBeneficiaryRegID()) == null) {
					childRegIDMap.put(details.getBeneficiaryRegID(), details);
				}
			}
		}
		if (childOBIDList.size() > 0) {
			ArrayList<MctsOutboundCallDetail> childDataByOBIDList = mctsOutboundCallRepository
					.getLastCallDetailByOBCallID(childOBIDList);

			for (MctsOutboundCallDetail details : childDataByOBIDList) {
				/* adding only recent call record */
				if (childOBIDMap.get(details.getObCallID()) == null) {
					childOBIDMap.put(details.getObCallID(), details);
				}
			}
		}

		agentChildList.parallelStream().forEach(child -> {
			Long benRegID = child.getBeneficiaryRegID();
			Long obID = child.getObCallID();

			if (benRegID != null) {
				MctsOutboundCallDetail callHistory = childRegIDMap.get(benRegID);
				if (callHistory != null) {
					child.setLastCallRemark(callHistory.getRemark());
					child.setLastCallTime(callHistory.getCreatedDate());
				}
			} else {
				MctsOutboundCallDetail callHistory = childOBIDMap.get(obID);
				if (callHistory != null) {
					child.setLastCallRemark(callHistory.getRemark());
					child.setLastCallTime(callHistory.getCreatedDate());
				}
			}
		});
		List<OutboundWorklist> mctsOutboundCallList = new ArrayList<OutboundWorklist>();
		mctsOutboundCallList.addAll(agentMotherList);
		mctsOutboundCallList.addAll(agentChildList);
		Collections.sort(mctsOutboundCallList, OutboundWorklist.getSortCompoByCallFromDate());
		return mctsOutboundCallList.toString();
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

	private HighRiskReasonRepository highRiskReasonRepository;

	/**
	 * @param highRiskReasonRepository the highRiskReasonRepository to set
	 */
	@Autowired
	public void setHighRiskReasonRepository(HighRiskReasonRepository highRiskReasonRepository) {
		this.highRiskReasonRepository = highRiskReasonRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iemr.mcts.services.supervisor.AgentCallAllocationServices#
	 * allocateCalls(com.iemr.mcts.data.supervisor.AgentCallAllocationDetails)
	 * method to move calls from data from valid data table to outbound call table
	 */
	@Override
	public String allocateCalls(String request) throws IEMRException {

		AgentCallAllocationDetail agentCallAllocationDetail = inputMapper.gson().fromJson(request,
				AgentCallAllocationDetail.class);

		MctsOutboundCall[] mctsOutboundCalls = agentCallAllocationDetail.getMctsOutboundCalls();
		MctsOutboundCall mctsOutboundCall;
		Integer count = 0;
		Integer userIndex = -1;
		List<Integer> users = agentCallAllocationDetail.getUserID();
		Integer totalCalls = users.size() * agentCallAllocationDetail.getAllocateNo();

		List<List<Long>> userCallList = new ArrayList<>();
		for (int i = 0; i < users.size(); i++) {
			userCallList.add(new ArrayList<>());
		}
		for (int eachCall = 0; eachCall < totalCalls; eachCall++) {

			if (eachCall < mctsOutboundCalls.length) {
				userIndex++;
				if (userIndex == users.size()) {

					userIndex = 0;
				}

				int y = users.get(userIndex);
				mctsOutboundCall = mctsOutboundCalls[eachCall];
				List<Long> buff = userCallList.get(userIndex);

				buff.add(mctsOutboundCall.getObCallID());
				count++;
			}
		}
		for (int i = 0; i < users.size(); i++) {
			mctsOutboundCallRepository.allocateCallList(userCallList.get(i), users.get(i));
		}

		return count.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iemr.mcts.services.supervisor.AgentCallAllocationServices#
	 * getUnallocatedCalls() this method is to get all mother list details which are
	 * not allocated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getUnallocatedCalls(String request) throws IEMRException {

		List<OutboundWorklist> unAllocatedCallList = new ArrayList<OutboundWorklist>();
		MctsOutboundCall mctsOutboundCall = inputMapper.gson().fromJson(request, MctsOutboundCall.class);

		List<Objects[]> motherCalls = null;
		List<Objects[]> childCalls = null;
		if (mctsOutboundCall.getCallDateFrom() != null) {
			motherCalls = mctsOutboundCallRepository.getMotherUnAllocatedCalls(mctsOutboundCall.getCallDateFrom(),
					mctsOutboundCall.getCallDateTo(), mctsOutboundCall.getProviderServiceMapID());
			childCalls = mctsOutboundCallRepository.getChildUnAllocatedCalls(mctsOutboundCall.getCallDateFrom(),
					mctsOutboundCall.getCallDateTo(), mctsOutboundCall.getProviderServiceMapID());
		} else {
			motherCalls = mctsOutboundCallRepository
					.getMotherUnallocatedCalls(mctsOutboundCall.getProviderServiceMapID());
			childCalls = mctsOutboundCallRepository
					.getChildUnallocatedCalls(mctsOutboundCall.getProviderServiceMapID());
		}

		MctsDataReaderDetail readerDetail = null;
		for (Object[] objects : motherCalls) {
			if (objects != null && objects.length > 0) {
				readerDetail = new MctsDataReaderDetail();
				readerDetail.setPhoneNo_Of_Whom((String) objects[5]);
				readerDetail.setHigh_Risk((Boolean) objects[6]);

				unAllocatedCallList.add(new OutboundWorklist(((Number) objects[0]).longValue(), (String) objects[1],
						(String) objects[2], (Timestamp) objects[3], (String) objects[4], readerDetail));
			}
		}
		ChildValidDataHandler childValidDataHandler = null;
		for (Object[] objects : childCalls) {
			if (objects != null && objects.length > 0) {
				childValidDataHandler = new ChildValidDataHandler();
				childValidDataHandler.setPhone_No_of((String) objects[5]);

				unAllocatedCallList.add(new OutboundWorklist(((Number) objects[0]).longValue(), (String) objects[1],
						(String) objects[2], (Timestamp) objects[3], (String) objects[4], childValidDataHandler));
			}
		}
		Collections.sort(unAllocatedCallList, OutboundWorklist.getSortCompoByCallFromDate());
		return unAllocatedCallList.toString();
	}

	/*
	 * screen to get users lis (non-Javadoc)
	 * 
	 * @see com.iemr.mcts.services.agent.MctsOutbondCallService#getReallocateCalls(
	 * java.lang.String)
	 */
	@Override
	public String getReallocateCalls(String request) throws IEMRException {

		ReallocationDataUtil reallocationDataUtil = inputMapper.gson().fromJson(request, ReallocationDataUtil.class);
		MctsOutboundCall mctsOutboundCall = reallocationDataUtil.getMctsOutboundCall();
		Map<Integer, List<AllocatedCalls>> userMap = new HashMap<Integer, List<AllocatedCalls>>();
		for (Integer userID : reallocationDataUtil.getUserIDs()) {

			if (reallocationDataUtil.getRecordType().equals(MctsConstants.MOTHER)) {

				if (mctsOutboundCall.getCallDateFrom() != null) {
					List<AllocatedCalls> totalCalls = mctsOutboundCallRepository.getUsersMotherAllocatedCalls(userID,
							mctsOutboundCall.getCallDateFrom(), mctsOutboundCall.getCallDateTo());
					Collections.sort(totalCalls, AllocatedCalls.getSortCompoByCallFromDate());
					userMap.put(userID, totalCalls);
				} else {

					List<AllocatedCalls> totalCalls = mctsOutboundCallRepository.getUsersMotherAllocatedCalls(userID);
					Collections.sort(totalCalls, AllocatedCalls.getSortCompoByCallFromDate());
					userMap.put(userID, totalCalls);
				}
			} else {
				if (mctsOutboundCall.getCallDateFrom() != null) {
					List<AllocatedCalls> totalCalls = mctsOutboundCallRepository.getUsersChildAllocatedCalls(userID,
							mctsOutboundCall.getCallDateFrom(), mctsOutboundCall.getCallDateTo());
					Collections.sort(totalCalls, AllocatedCalls.getSortCompoByCallFromDate());
					userMap.put(userID, totalCalls);
				} else {

					List<AllocatedCalls> totalCalls = mctsOutboundCallRepository.getUsersChildAllocatedCalls(userID);
					Collections.sort(totalCalls, AllocatedCalls.getSortCompoByCallFromDate());
					userMap.put(userID, totalCalls);
				}
			}
		}
		return userMap.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iemr.mcts.services.agent.MctsOutbondCallService#moveCallsToBucket(
	 * java.lang.String)
	 */
	@Override
	public String moveCallsToBucket(String request) throws IEMRException {

		MctsOutboundCall[] mctsOutboundCalls = InputMapper.gson().fromJson(request, MctsOutboundCall[].class);
		List<MctsOutboundCall> list = Arrays.asList(mctsOutboundCalls);

		List<Long> listobCallIDs = new ArrayList<>();

		list.forEach(action -> {
			listobCallIDs.add(action.getObCallID());
		});

		mctsOutboundCallRepository.moveToBucket(listobCallIDs);

		return "Data moved to bucket successfully";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iemr.mcts.services.agent.MctsOutbondCallService#getNextANC_PNC(java.lang.
	 * String)
	 */
	@Override
	public String getNextANC_PNC(String request) throws IEMRException {

		MctsOutboundCall call = InputMapper.gson().fromJson(request, MctsOutboundCall.class);
		String outboundCallType = call.getOutboundCallType();
		outboundCallType = outboundCallType.substring(0, outboundCallType.length() - 1) + (Integer
				.parseInt(outboundCallType.substring(outboundCallType.length() - 1, outboundCallType.length())) + 1);
		if (call.getChildID() != null) {

			call = mctsOutboundCallRepository.findByChildIDAndOutboundCallType(call.getChildID(), outboundCallType);
		} else {

			call = mctsOutboundCallRepository.findByMotherIDAndOutboundCallType(call.getMotherID(), outboundCallType);
		}

		if (call != null)
			return call.getCallDateFrom().toString();
		else
			return "";
	}

	@Override
	public String getUpdatedObject(String request, HttpServletRequest servletRequest) throws IEMRException {

		MctsOutboundCall call = InputMapper.gson().fromJson(request, MctsOutboundCall.class);

		MctsOutboundCall benCall;

		if (call.getChildID() != null) {

			call = mctsOutboundCallRepository.getUpdatedChildRecord(call.getObCallID());
		} else {

			call = mctsOutboundCallRepository.getUpdatedMotherRecord(call.getObCallID());
		}

		if (call.getChildValidDataHandler() != null) {

			if (call.getChildValidDataHandler().getBeneficiaryRegID() != null) {

				String req = mctsIdentityServiceImpl.getBeneficiaryID(request, servletRequest);
				benCall = InputMapper.gson().fromJson(req, MctsOutboundCall.class);
				call.getChildValidDataHandler().setBeneficiaryID(benCall.getChildValidDataHandler().getBeneficiaryID());
			}

		} else {

			if (call.getMctsDataReaderDetail().getBeneficiaryRegID() != null) {

				String req = mctsIdentityServiceImpl.getBeneficiaryID(request, servletRequest);
				benCall = InputMapper.gson().fromJson(req, MctsOutboundCall.class);
				call.getMctsDataReaderDetail().setBeneficiaryID(benCall.getMctsDataReaderDetail().getBeneficiaryID());
				call.getMctsDataReaderDetail().setAge(benCall.getMctsDataReaderDetail().getAge());
			}
		}

		return call.toString();
	}

	private List<MctsOutboundCall> getAgentCalls(Integer allocatedUserID, Boolean fetchChildRecords) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MctsOutboundCall> criteriaQuery = criteriaBuilder.createQuery(MctsOutboundCall.class);
		Root<MctsOutboundCall> root = criteriaQuery.from(MctsOutboundCall.class);
		Timestamp currentTime = new Timestamp(Calendar.getInstance().getTime().getTime());
		List<Predicate> predicates = new ArrayList<Predicate>();
		Subquery<MctsOutboundCall> validOutboundCall = criteriaQuery.subquery(MctsOutboundCall.class);
		Root<MctsOutboundCall> validOutboundCallRoot = validOutboundCall.from(MctsOutboundCall.class);
		List<Predicate> validCallPredicates = new ArrayList<Predicate>();
		validCallPredicates.add(criteriaBuilder.equal(validOutboundCallRoot.get("allocatedUserID"), allocatedUserID));
		validCallPredicates.add(criteriaBuilder.notEqual(validOutboundCallRoot.get("callStatus"), "Completed"));
		validCallPredicates.add(criteriaBuilder.notEqual(validOutboundCallRoot.get("callStatus"), "NA"));
		validCallPredicates
				.add(criteriaBuilder.or(criteriaBuilder.isNull(validOutboundCallRoot.get("prefferedCallDate")),
						criteriaBuilder.lessThan(validOutboundCallRoot.get("prefferedCallDate"), currentTime)));
		validOutboundCall.select(validOutboundCallRoot).where(validCallPredicates.toArray(new Predicate[] {}));

		predicates.add(criteriaBuilder.equal(root.get("obCallID"), criteriaBuilder.any(validOutboundCall)));
		criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}));
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("outboundCallType")));
		if (fetchChildRecords) {
			Join<MctsOutboundCall, ChildValidDataHandler> beneficiaryRecords = root.join("childValidDataHandler",
					JoinType.INNER);
			criteriaQuery.groupBy(root.get("childID"), root.get("outboundCallType"));
		} else {
			Join<MctsOutboundCall, MctsDataReaderDetail> beneficiaryRecords = root.join("mctsDataReaderDetail",
					JoinType.INNER);
			criteriaQuery.groupBy(root.get("motherID"), root.get("outboundCallType"));
		}
		TypedQuery<MctsOutboundCall> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iemr.mcts.services.agent.MctsOutbondCallService#getHighRiskReason(
	 * java.lang.String)
	 */
	@Override
	public String getHighRiskReason() throws IEMRException {
		List list = new ArrayList();
		list = highRiskReasonRepository.getHighRiskReason();
		return list.toString();
	}

	@Override
	public String getMotherWorklist(String request) throws IEMRException {
		MctsOutboundCall mctsOutboundCall = inputMapper.gson().fromJson(request, MctsOutboundCall.class);

		List<Objects[]> motherRecords = mctsOutboundCallRepository
				.getAgentAllocatedMotherList(mctsOutboundCall.getAllocatedUserID());

		List<OutboundWorklist> agentMotherList = new ArrayList<OutboundWorklist>();

		MctsDataReaderDetail readerDetail = null;

		for (Object[] objects : motherRecords) {
			if (objects != null && objects.length > 0) {
				readerDetail = new MctsDataReaderDetail();
				readerDetail.setName((String) objects[8]);
				readerDetail.setMCTSID_no(((Number) objects[9]).longValue());
				readerDetail.setPhoneNo_Of_Whom((String) objects[10]);
				readerDetail.setWhom_PhoneNo((String) objects[11]);
				readerDetail.setHigh_Risk((Boolean) objects[12]);

				Long benRegID = null;

				if (objects[1] != null) {
					benRegID = ((Number) objects[1]).longValue();
				}
				readerDetail.setBeneficiaryRegID(benRegID);

				agentMotherList.add(MapMotherfromObj(objects, benRegID, readerDetail));
			}
		}
		List<Long> regIDList = new ArrayList<Long>();
		List<Long> obIDList = new ArrayList<Long>();

		for (OutboundWorklist mother : agentMotherList) {
			Long benRegID = mother.getBeneficiaryRegID();

			if (benRegID != null) {
				regIDList.add(benRegID);
			} else {
				obIDList.add(mother.getObCallID());
			}
		}
		Map<Long, MctsOutboundCallDetail> RegIDMap = new HashMap<Long, MctsOutboundCallDetail>();
		Map<Long, MctsOutboundCallDetail> OBIDMap = new HashMap<Long, MctsOutboundCallDetail>();

		if (regIDList.size() > 0) {
			ArrayList<MctsOutboundCallDetail> dataByRegIDList = mctsOutboundCallRepository
					.getLastCallDetailByRegID(regIDList);

			for (MctsOutboundCallDetail details : dataByRegIDList) {
				/* adding only recent call record */
				if (RegIDMap.get(details.getBeneficiaryRegID()) == null) {
					RegIDMap.put(details.getBeneficiaryRegID(), details);
				}
			}
		}
		if (obIDList.size() > 0) {
			ArrayList<MctsOutboundCallDetail> dataByOBIDList = mctsOutboundCallRepository
					.getLastCallDetailByOBCallID(obIDList);

			for (MctsOutboundCallDetail details : dataByOBIDList) {
				/* adding only recent call record */
				if (OBIDMap.get(details.getObCallID()) == null) {
					OBIDMap.put(details.getObCallID(), details);
				}
			}
		}
		agentMotherList.parallelStream().forEach(mother -> {
			Long benRegID = mother.getBeneficiaryRegID();
			Long obID = mother.getObCallID();

			if (benRegID != null) {
				MctsOutboundCallDetail callHistory = RegIDMap.get(benRegID);
				if (callHistory != null) {
					mother.setLastCallRemark(callHistory.getRemark());
					mother.setLastCallTime(callHistory.getCreatedDate());
				}
			} else {
				MctsOutboundCallDetail callHistory = OBIDMap.get(obID);
				if (callHistory != null) {
					mother.setLastCallRemark(callHistory.getRemark());
					mother.setLastCallTime(callHistory.getCreatedDate());
				}
			}
		});

		List<OutboundWorklist> mctsOutboundCallList = new ArrayList<OutboundWorklist>();
		Collections.sort(agentMotherList, OutboundWorklist.getSortCompoByCallFromDate());
		return agentMotherList.toString();
	}

	@Override
	public String getChildWorklist(String request) throws IEMRException {
		MctsOutboundCall mctsOutboundCall = inputMapper.gson().fromJson(request, MctsOutboundCall.class);

		List<Objects[]> motherRecords = mctsOutboundCallRepository
				.getAgentAllocatedMotherChildList(mctsOutboundCall.getAllocatedUserID());

		List<OutboundWorklist> agentMotherList = new ArrayList<OutboundWorklist>();

		MctsDataReaderDetail readerDetail = null;

		for (Object[] objects : motherRecords) {
			if (objects != null && objects.length > 0) {
				readerDetail = new MctsDataReaderDetail();
				readerDetail.setName((String) objects[8]);
				readerDetail.setMCTSID_no(((Number) objects[9]).longValue());
				readerDetail.setPhoneNo_Of_Whom((String) objects[10]);
				readerDetail.setWhom_PhoneNo((String) objects[11]);
				readerDetail.setHigh_Risk((Boolean) objects[12]);

				Long benRegID = null;

				if (objects[1] != null) {
					benRegID = ((Number) objects[1]).longValue();
				}
				readerDetail.setBeneficiaryRegID(benRegID);

				agentMotherList.add(MapMotherfromObj(objects, benRegID, readerDetail));
			}
		}
		List<Long> regIDList = new ArrayList<Long>();
		List<Long> obIDList = new ArrayList<Long>();

		for (OutboundWorklist mother : agentMotherList) {
			Long benRegID = mother.getBeneficiaryRegID();

			if (benRegID != null) {
				regIDList.add(benRegID);
			} else {
				obIDList.add(mother.getObCallID());
			}
		}
		Map<Long, MctsOutboundCallDetail> RegIDMap = new HashMap<Long, MctsOutboundCallDetail>();
		Map<Long, MctsOutboundCallDetail> OBIDMap = new HashMap<Long, MctsOutboundCallDetail>();

		if (regIDList.size() > 0) {
			ArrayList<MctsOutboundCallDetail> dataByRegIDList = mctsOutboundCallRepository
					.getLastCallDetailByRegID(regIDList);

			for (MctsOutboundCallDetail details : dataByRegIDList) {
				/* adding only recent call record */
				if (RegIDMap.get(details.getBeneficiaryRegID()) == null) {
					RegIDMap.put(details.getBeneficiaryRegID(), details);
				}
			}
		}
		if (obIDList.size() > 0) {
			ArrayList<MctsOutboundCallDetail> dataByOBIDList = mctsOutboundCallRepository
					.getLastCallDetailByOBCallID(obIDList);

			for (MctsOutboundCallDetail details : dataByOBIDList) {
				/* adding only recent call record */
				if (OBIDMap.get(details.getObCallID()) == null) {
					OBIDMap.put(details.getObCallID(), details);
				}
			}
		}
		agentMotherList.parallelStream().forEach(mother -> {
			Long benRegID = mother.getBeneficiaryRegID();
			Long obID = mother.getObCallID();

			if (benRegID != null) {
				MctsOutboundCallDetail callHistory = RegIDMap.get(benRegID);
				if (callHistory != null) {
					mother.setLastCallRemark(callHistory.getRemark());
					mother.setLastCallTime(callHistory.getCreatedDate());
				}
			} else {
				MctsOutboundCallDetail callHistory = OBIDMap.get(obID);
				if (callHistory != null) {
					mother.setLastCallRemark(callHistory.getRemark());
					mother.setLastCallTime(callHistory.getCreatedDate());
				}
			}
		});
		List<Objects[]> childRecords = mctsOutboundCallRepository
				.getAgentAllocatedChildList(mctsOutboundCall.getAllocatedUserID());

		List<OutboundWorklist> agentChildList = new ArrayList<OutboundWorklist>();
		ChildValidDataHandler childDetail = null;

		for (Object[] objects : childRecords) {
			if (objects != null && objects.length > 0) {
				childDetail = new ChildValidDataHandler();
				childDetail.setChild_Name((String) objects[6]);
				childDetail.setMCTSID_no_Child_ID(((Number) objects[5]).longValue());
				childDetail.setMother_Name((String) objects[8]);
				Long motherID = null;
				if (objects[7] != null) {
					motherID = ((Number) objects[7]).longValue();
					childDetail.setMother_ID(motherID);
				}
				childDetail.setPhone_No((String) objects[10]);
				childDetail.setPhone_No_of((String) objects[9]);

				Long benRegID = null;

				if (objects[0] != null) {
					benRegID = ((Number) objects[0]).longValue();
				}
				childDetail.setBeneficiaryRegID(benRegID);

				agentChildList.add(MapChildfromObj(objects, benRegID, motherID, childDetail));
			}
		}

		List<Long> childRegIDList = new ArrayList<Long>();
		List<Long> childOBIDList = new ArrayList<Long>();

		for (OutboundWorklist child : agentChildList) {
			Long benRegID = child.getBeneficiaryRegID();

			if (benRegID != null) {
				childRegIDList.add(benRegID);
			} else {
				childOBIDList.add(child.getObCallID());
			}
		}
		Map<Long, MctsOutboundCallDetail> childRegIDMap = new HashMap<Long, MctsOutboundCallDetail>();
		Map<Long, MctsOutboundCallDetail> childOBIDMap = new HashMap<Long, MctsOutboundCallDetail>();

		if (childRegIDList.size() > 0) {
			ArrayList<MctsOutboundCallDetail> childDataByRegIDList = mctsOutboundCallRepository
					.getLastCallDetailByRegID(childRegIDList);

			for (MctsOutboundCallDetail details : childDataByRegIDList) {
				/* adding only recent call record */
				if (childRegIDMap.get(details.getBeneficiaryRegID()) == null) {
					childRegIDMap.put(details.getBeneficiaryRegID(), details);
				}
			}
		}
		if (childOBIDList.size() > 0) {
			ArrayList<MctsOutboundCallDetail> childDataByOBIDList = mctsOutboundCallRepository
					.getLastCallDetailByOBCallID(childOBIDList);

			for (MctsOutboundCallDetail details : childDataByOBIDList) {
				/* adding only recent call record */
				if (childOBIDMap.get(details.getObCallID()) == null) {
					childOBIDMap.put(details.getObCallID(), details);
				}
			}
		}

		agentChildList.parallelStream().forEach(child -> {
			Long benRegID = child.getBeneficiaryRegID();
			Long obID = child.getObCallID();

			if (benRegID != null) {
				MctsOutboundCallDetail callHistory = childRegIDMap.get(benRegID);
				if (callHistory != null) {
					child.setLastCallRemark(callHistory.getRemark());
					child.setLastCallTime(callHistory.getCreatedDate());
				}
			} else {
				MctsOutboundCallDetail callHistory = childOBIDMap.get(obID);
				if (callHistory != null) {
					child.setLastCallRemark(callHistory.getRemark());
					child.setLastCallTime(callHistory.getCreatedDate());
				}
			}
		});
		List<OutboundWorklist> mctsOutboundCallList = new ArrayList<OutboundWorklist>();
		mctsOutboundCallList.addAll(agentMotherList);
		mctsOutboundCallList.addAll(agentChildList);
		Collections.sort(mctsOutboundCallList, OutboundWorklist.getSortCompoByCallFromDate());
		return mctsOutboundCallList.toString();
	}

}
