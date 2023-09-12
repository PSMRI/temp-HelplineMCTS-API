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
package com.iemr.mcts.services.supervisor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tika.exception.TikaException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.mcts.configure.HttpRestModal;
import com.iemr.mcts.configure.UploadedFileProcessor;
import com.iemr.mcts.constants.MctsConstants;
import com.iemr.mcts.customexception.DateFormatNotMatchException;
import com.iemr.mcts.customexception.ExcelColumnDataException;
import com.iemr.mcts.data.mapper.MctsDataMapper;
import com.iemr.mcts.data.mapper.UploadData;
import com.iemr.mcts.data.supervisor.ChildInvalidDataHandler;
import com.iemr.mcts.data.supervisor.ChildValidDataHandler;
import com.iemr.mcts.data.supervisor.DBExcelFieldName;
import com.iemr.mcts.data.supervisor.FileManager;
import com.iemr.mcts.data.supervisor.MctsDataReaderDetail;
import com.iemr.mcts.data.supervisor.MctsInvalidDataReaderDetail;
import com.iemr.mcts.data.supervisor.MctsOutboundCall;
import com.iemr.mcts.data.supervisor.MctsStatewiseFieldsDetail;
import com.iemr.mcts.data.utils.MctsBenificairyUpdateUtils;
import com.iemr.mcts.repository.agent.MCTSOutboundCallRepository;
import com.iemr.mcts.repository.agent.ProviderServiceMapRepository;
import com.iemr.mcts.repository.supervisor.BeneficiaryRepository;
import com.iemr.mcts.repository.supervisor.CallConfigurationRepository;
import com.iemr.mcts.repository.supervisor.ChildDataReaderRepository;
import com.iemr.mcts.repository.supervisor.ChildInvalidDataRepository;
import com.iemr.mcts.repository.supervisor.ChildValidDataRepository;
import com.iemr.mcts.repository.supervisor.FileManagerRepository;
import com.iemr.mcts.repository.supervisor.MctsDataHandlerRepository;
import com.iemr.mcts.repository.supervisor.MctsInvalidDataRepository;
import com.iemr.mcts.repository.supervisor.MctsStatewiseFieldsRepository;
import com.iemr.mcts.utils.MCTSDateUtils;
import com.iemr.mcts.utils.exception.IEMRException;
import com.iemr.mcts.utils.mapper.InputMapper;
import com.iemr.mcts.utils.mapper.OutputMapper;

@Service
public class MctsDataHandlerServiceImpl implements MctsDataHandlerService {

	private static UploadedFileProcessor fileProcessor = new UploadedFileProcessor();

	private static InputMapper inputMapper = new InputMapper();

	private static OutputMapper outputMapper = new OutputMapper();

	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	/**
	 * JSONObject
	 */
	org.json.simple.JSONObject json = null;

	/**
	 * method for calculate age
	 */
	private Integer getAge(Date dob) {

		LocalDate ldob = dob.toLocalDate();
		Period period = Period.between(ldob, LocalDate.now());
		return period.getYears();
	}

	/**
	 * mcts utils
	 */
	private MCTSDateUtils mctsDateUtils;

	/**
	 * Inject mcts date utils
	 */
	@Autowired
	public void setMCTSDateUtils(MCTSDateUtils mctsDateUtils) {

		this.mctsDateUtils = mctsDateUtils;
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

	/**
	 * file repository
	 */
	private FileManagerRepository fileManagerRepository;

	/**
	 * Inject file repository
	 */
	@Autowired
	public void setFileManagerRepository(FileManagerRepository fileManagerRepository) {

		this.fileManagerRepository = fileManagerRepository;
	}

	/**
	 * mcts statewise fields repository
	 */
	private MctsStatewiseFieldsRepository mctsStatewiseFieldsRepository;

	/**
	 * inject mcts statewise fields repository
	 */
	@Autowired
	public void setMctsStatewiseFieldsRepository(MctsStatewiseFieldsRepository mctsStatewiseFieldsRepository) {

		this.mctsStatewiseFieldsRepository = mctsStatewiseFieldsRepository;
	}

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
	 * mother data repository -- for invalid data
	 */
	private MctsInvalidDataRepository mctsInvalidDataRepository;

	/**
	 * 
	 * @param mctsInvalidDataRepository
	 */
	@Autowired
	public void setMctsInvalidDataRepository(MctsInvalidDataRepository mctsInvalidDataRepository) {

		this.mctsInvalidDataRepository = mctsInvalidDataRepository;
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
	 * Child invalid data repository
	 */
	private ChildInvalidDataRepository childInvalidDataRepository;

	/**
	 * Inject invalid data repository
	 */
	@Autowired
	public void setChildInvalidDataRepository(ChildInvalidDataRepository childInvalidDataRepository) {

		this.childInvalidDataRepository = childInvalidDataRepository;
	}

	/**
	 * Beneficiary Repository
	 */
	private BeneficiaryRepository beneficiaryRepository;

	/**
	 * Inject Beneficiary Repository
	 */
	@Autowired
	public void setBeneficiaryRepository(BeneficiaryRepository beneficiaryRepository) {

		this.beneficiaryRepository = beneficiaryRepository;
	}

	/**
	 * Child data reader repository
	 */
	private ChildDataReaderRepository childDataReaderRepository;

	/**
	 * Inject Child data reader repository
	 * 
	 * @param childDataReaderRepository
	 */
	@Autowired
	public void setChildDataReaderRepository(ChildDataReaderRepository childDataReaderRepository) {

		this.childDataReaderRepository = childDataReaderRepository;
	}

	/**
	 * call configuration repository
	 */
	private CallConfigurationRepository callConfigurationRepository;

	/**
	 * Inject call configuration repository
	 * 
	 * @param callConfigurationRepository
	 */
	@Autowired
	public void setCallConfigurationRepository(CallConfigurationRepository callConfigurationRepository) {

		this.callConfigurationRepository = callConfigurationRepository;
	}

	/**
	 * Http Rest Modal
	 */
	private HttpRestModal httpRestModal;

	/**
	 * Inject Http Rest Modal
	 */
	@Autowired
	public void setHttpRestModal(HttpRestModal httpRestModal) {

		this.httpRestModal = httpRestModal;
	}

	/**
	 * ProviderServiceMapRepository
	 */
	private ProviderServiceMapRepository providerServiceMapRepository;

	/**
	 * Inject ProviderServiceMapRepository
	 */
	@Autowired
	public void setProviderServiceMapRepository(ProviderServiceMapRepository providerServiceMapRepository) {

		this.providerServiceMapRepository = providerServiceMapRepository;
	}

	@Autowired
	private MctsDataMapper mctsDataMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iemr.mcts.services.supervisor.MctsDataHadlerService#mctsDataUpload(
	 * org.springframework.web.multipart.MultipartFile)
	 */

	@Override
	public UploadData validateData(UploadData uploaddata) {
		String message = "";
		Iterator<Row> rowIterator = uploaddata.getRowIterator();
		Iterable<Row> iterable = () -> rowIterator;
		FormulaEvaluator evaluator = uploaddata.getEvaluator();
		Map<String, String> fieldsMap = uploaddata.getFieldsMap();

		FileManager fileManager = uploaddata.getFileManager();
		if (uploaddata.getIsmotherdata() != null && uploaddata.getIsmotherdata()) {
			uploaddata.setIsmotherdata(true);
			List<MctsDataReaderDetail> mothervalid = new ArrayList<>();

			List<MctsInvalidDataReaderDetail> motherinvalid = new ArrayList<>();
			final Long fileid = fileManager.getFileID();
			final String createdby = fileManager.getCreatedBy();
			ConcurrentHashMap<Long, String> map = new ConcurrentHashMap();
			StreamSupport.stream(iterable.spliterator(), false).forEach(action -> {
				Row row = action;
				if (row.getRowNum() != 0 && row.getCell(0) != null) {

					JSONObject objectMap = fileProcessor.getDataObjectMap(row, fieldsMap, evaluator);
					try {
						MctsDataReaderDetail dataReaderDetail = inputMapper.gson().fromJson(objectMap.toString(),
								MctsDataReaderDetail.class);

						dataReaderDetail.setIsAllocated(false);
						dataReaderDetail.setFileID(fileid);
						dataReaderDetail.setCreatedBy(createdby);
						if (this.savaMotherMctsData(dataReaderDetail, map)) {
							mothervalid.add(dataReaderDetail);
							map.put(dataReaderDetail.getMCTSID_no(), "1");
						} else {
							MctsInvalidDataReaderDetail invalidDataReaderDetail = inputMapper.gson().fromJson(
									outputMapper.gson().toJson(dataReaderDetail), MctsInvalidDataReaderDetail.class);
							motherinvalid.add(invalidDataReaderDetail);

						}
					} catch (Exception e) {

						throw new ExcelColumnDataException("Data error in the line " + (row.getRowNum() + 1));
					}
				}
			});
			uploaddata.setMotherinvalid(motherinvalid);
			uploaddata.setMothervalid(mothervalid);
			fileManager.setValidRecordCount(Long.valueOf(mothervalid.size()));
			fileManager.setErroredRecordCount(Long.valueOf(motherinvalid.size()));
			message = mothervalid.size() + " valid and " + motherinvalid.size() + " invalid records.";
		}

		if (uploaddata.getIschilddata() != null && uploaddata.getIschilddata()) {
			uploaddata.setIschilddata(true);
			List<ChildValidDataHandler> childvalid = new ArrayList<>();

			List<ChildInvalidDataHandler> childinvalid = new ArrayList<>();

			ChildValidDataHandler childValidDataHandler = null;
			ConcurrentHashMap<Long, String> mapc = new ConcurrentHashMap();
			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				if (row.getRowNum() != 0) {
					JSONObject objectMap = fileProcessor.getDataObjectMap(row, fieldsMap, evaluator);

					try {

						childValidDataHandler = inputMapper.gson().fromJson(objectMap.toString(),
								ChildValidDataHandler.class);

						childValidDataHandler.setIsAllocated(false);
						childValidDataHandler.setCreatedBy(fileManager.getCreatedBy());
						childValidDataHandler.setFileID(fileManager.getFileID());
						if (this.savaChildMctsData(childValidDataHandler, mapc)) {
							childvalid.add(childValidDataHandler);
							mapc.put(childValidDataHandler.getMCTSID_no_Child_ID(), "1");
						} else {
							ChildInvalidDataHandler invalidChildDetail = inputMapper.gson().fromJson(
									outputMapper.gson().toJson(childValidDataHandler), ChildInvalidDataHandler.class);
							childinvalid.add(invalidChildDetail);
						}

					} catch (Exception e) {

						if (e.getMessage().substring(e.getMessage().lastIndexOf("Exception:") + 10) != null) {
							throw new ExcelColumnDataException("Data error in the line " + (row.getRowNum() + 1)
									+ e.getMessage().substring(e.getMessage().lastIndexOf("Exception:") + 10));
						} else {
							throw new ExcelColumnDataException("Data error in the line " + (row.getRowNum() + 1));
						}
					}
				}

			}

			uploaddata.setChildinvalid(childinvalid);
			uploaddata.setChildvalid(childvalid);
			fileManager.setValidRecordCount(Long.valueOf(childvalid.size()));
			fileManager.setErroredRecordCount(Long.valueOf(childinvalid.size()));
			message = childvalid.size() + " valid and " + childinvalid.size() + " invalid records. ";
		}

		fileManager.setFileStatusID(2L);
		fileManager.setStatusReason(message);
		fileManagerRepository.save(fileManager);
		return uploaddata;
	}

	@Override
	public String savemother(UploadData uploadData) {
		try {
			FileManager entities = uploadData.getFileManager();
			entities.setModifiedBy(entities.getCreatedBy());
			logger.info("ismother " + uploadData.getIsmotherdata() + " ischild" + uploadData.getIschilddata() + " ");

			if (uploadData.getIschilddata() != null && uploadData.getIschilddata()) {
				uploadData.getChildvalid().parallelStream().forEach(action -> {
					logger.info("saving");
					action = childValidDataRepository.save(action);
					this.setChildOutboundcalls(action, uploadData.getFileManager());
					logger.info("saved" + action.getRowID());

				});
				uploadData.getChildinvalid().parallelStream().forEach(action -> {
					childInvalidDataRepository.save(action);
				});
				entities.setValidRecordCount((long) uploadData.getChildvalid().size());
				entities.setErroredRecordCount((long) uploadData.getChildinvalid().size());
			}

			if (uploadData.getIsmotherdata() != null && uploadData.getIsmotherdata()) {
				uploadData.getMothervalid().parallelStream().forEach(action -> {
					logger.info("saving");
					action = mctsDataHandlerRepository.save(action);
					this.setMotherOutboundcalls(action, uploadData.getFileManager());
					logger.info("saved" + action.getMotherValidRecordID());

				});
				uploadData.getMotherinvalid().parallelStream().forEach(action -> {
					mctsInvalidDataRepository.save(action);
				});
				entities.setValidRecordCount((long) uploadData.getMothervalid().size());
				entities.setErroredRecordCount((long) uploadData.getMotherinvalid().size());
			}
			if (uploadData.getFileManager() != null) {
				entities.setFileStatusID(3L);
				fileManagerRepository.save(entities);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "Done";
	}

	@Override
	public UploadData mctsDataUpload(String request, HttpServletRequest servletRequest)
			throws IOException, TikaException, IEMRException {

		UploadData uploaddata = new UploadData();
		FileManager fileManager = inputMapper.gson().fromJson(request, FileManager.class);
		String message = "";
		byte[] byteArray = Base64.getDecoder().decode(fileManager.getFileContent());

		String fileCheckSum = fileProcessor.getMD5Checksum(byteArray);
		String fileType = fileProcessor.detectFileType(byteArray);

		if (fileType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			MctsStatewiseFieldsDetail statewiseFieldsDetail = inputMapper.gson().fromJson(request,
					MctsStatewiseFieldsDetail.class);

			boolean isUploaded = fileManagerRepository.isUploaded(fileCheckSum);
			fileManager.setmD5CheckSum(fileCheckSum);

			if (!isUploaded) {

				int count = callConfigurationRepository.getConfigCount(fileManager.getProviderServiceMapID());
				if (count == 0) {

					message = "Please complete call configuration and upload again";
					uploaddata.setMessage(message);
					return uploaddata;
				}

				statewiseFieldsDetail = mctsStatewiseFieldsRepository.getAllFields(
						statewiseFieldsDetail.getProviderServiceMapID(), statewiseFieldsDetail.getFieldFor());

				if (!(statewiseFieldsDetail != null) || !(statewiseFieldsDetail.getDataFields() != null)) {

					message = "XLS db configuration not available";
					uploaddata.setMessage(message);
					return uploaddata;
				}

				DBExcelFieldName[] excelFieldNames = inputMapper.gson().fromJson(statewiseFieldsDetail.getDataFields(),
						DBExcelFieldName[].class);

				fileManager.setProviderServiceMapID(statewiseFieldsDetail.getProviderServiceMapID());
				Map<String, String> fieldsMap = new HashMap<String, String>();

				for (DBExcelFieldName fieldName : excelFieldNames) {

					fieldsMap.put(fieldName.getExcelColumnName().trim(), fieldName.getDbColumnName().trim());
				}

				XSSFWorkbook workbook = null;
				try {

					workbook = this.getXSSFWorkbook(byteArray);
					int numberofSheets = workbook.getNumberOfSheets();
					if (numberofSheets > 1) {

						message = "Please upload file with single sheet";
						uploaddata.setMessage(message);
						return uploaddata;
					} else {

						Sheet datatypeSheet = workbook.getSheetAt(0);

						List<String> headers = fileProcessor.getHeadersAsList(datatypeSheet.getRow(0));
						Set<String> keySet = new HashSet<String>(fieldsMap.keySet());
						// New logic implemented for accepting more columns
						if (keySet.size() <= 0 && headers.size() <= 0) {
							message = "Data File Is Empty Please Fill The Mandatory Fields Or User Is not Mapped With State";
							uploaddata.setMessage(message);
							return uploaddata;
						}
						FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
						Iterator<Row> rowIterator = datatypeSheet.iterator();

						uploaddata.setRowIterator(rowIterator);
						uploaddata.setFieldsMap(fieldsMap);
						uploaddata.setEvaluator(evaluator);

						fileManager.setFileStatusID(1L);

						if (statewiseFieldsDetail.getFieldFor().equals(MctsConstants.MOTHER_RECORDS)) {
							uploaddata.setIsmotherdata(true);
							fileManager.setIsMother(true);

						}

						if (statewiseFieldsDetail.getFieldFor().equals(MctsConstants.CHILD_RECORDS)) {
							uploaddata.setIschilddata(true);
							fileManager.setIsMother(false);

						}
						fileManager = fileManagerRepository.save(fileManager);
						uploaddata.setFileManager(fileManager);
						message = " FileID " + fileManager.getFileID();
					}

				} catch (ExcelColumnDataException e) {

					message = e.getMessage();

				} catch (DateFormatNotMatchException e) {

					message = e.getMessage();
				} catch (EncryptedDocumentException e) {

					message = e.toString();
				} catch (InvalidFormatException e) {

					message = e.toString();
				} finally {

					if (workbook != null) {
						workbook.close();
					}
				}

			} else {

				message = "This file has been already uploaded";
			}

		} else {

			message = "Invalid file type " + fileType;
		}
		uploaddata.setMessage(message);
		return uploaddata;
	}

	private Boolean savaMotherMctsData(MctsDataReaderDetail dataReaderDetail, ConcurrentHashMap<Long, String> map) {

		try {

			dataReaderDetail.setHigh_Risk(false);
			dataReaderDetail.setIs_Valid(true);
			dataReaderDetail.setDeleted(false);
			String rel = dataReaderDetail.getPhoneNo_Of_Whom();
			dataReaderDetail.setPhoneNo_Of_Whom(
					rel.substring(0, 1).toUpperCase() + rel.substring(1, rel.length()).toLowerCase());

			if (dataReaderDetail.getLMP_Date() == null) {

				dataReaderDetail.setIs_Valid(false);
				dataReaderDetail.setInValid_Reason("LMP date is blank");
			}

			if (dataReaderDetail.getLMP_Date() != null) {
				dataReaderDetail.setEDD(this.getEDD(dataReaderDetail.getLMP_Date()));
			}

			if (!(dataReaderDetail.getMCTSID_no() != null)) {

				dataReaderDetail.setIs_Valid(false);
				dataReaderDetail.setInValid_Reason("Mother ID not Present");
			}

			if (dataReaderDetail.getEDD() != null
					&& dataReaderDetail.getEDD().toLocalDate().isBefore(LocalDate.now())) {

				dataReaderDetail.setIs_Valid(false);
				dataReaderDetail.setInValid_Reason("EDD Passed Current date - " + dataReaderDetail.getEDD());
			}

			if (dataReaderDetail.getMCTSID_no() != null
					&& mctsDataHandlerRepository.ifExists(dataReaderDetail.getMCTSID_no())) {

				dataReaderDetail.setIs_Valid(false);
				dataReaderDetail.setInValid_Reason("Duplicate Entry");
			}
			if (dataReaderDetail.getMCTSID_no() != null && map.containsKey(dataReaderDetail.getMCTSID_no())) {

				dataReaderDetail.setIs_Valid(false);
				dataReaderDetail.setInValid_Reason("Duplicate Entry");
			}

			if (dataReaderDetail.getWhom_PhoneNo() == null) {
				dataReaderDetail.setIs_Valid(false);
				dataReaderDetail.setInValid_Reason("Phone no not available");
			}

			if (dataReaderDetail.getLMP_Date() != null
					&& dataReaderDetail.getLMP_Date().toLocalDate().isAfter(LocalDate.now())) {
				dataReaderDetail.setIs_Valid(false);
				dataReaderDetail.setInValid_Reason("LMP date is future date");
			}

			if (dataReaderDetail.getIs_Valid() == true) {

				// mark high risk as false
				dataReaderDetail.setHigh_Risk(false);

				// age check on high risk
				if (dataReaderDetail.getBirth_Date() != null) {
					Integer age = this.getAge(dataReaderDetail.getBirth_Date());
					if (age < 18 || age > 35) {

						dataReaderDetail.setHigh_Risk(true);
						dataReaderDetail.setHigh_Risk_Reason("Mother age is below 18 or above 35");
					}
					dataReaderDetail.setAge(age);
				} else if (dataReaderDetail.getBirth_Date() == null && dataReaderDetail.getAge() != null) {
					Date birthDate = this.calculateBirthDate(dataReaderDetail.getAge());
					dataReaderDetail.setBirth_Date(birthDate);
					if (dataReaderDetail.getAge() < 18 || dataReaderDetail.getAge() > 35) {

						dataReaderDetail.setHigh_Risk(true);
						dataReaderDetail.setHigh_Risk_Reason("Mother age is below 18 or above 35");
					}
				}

				/// child 1 weight
				if (dataReaderDetail.getChild1_Weight() != null) {

					if (dataReaderDetail.getChild1_Weight() < 2.5 || dataReaderDetail.getChild1_Weight() > 4.5) {
						dataReaderDetail.setHigh_Risk(true);
						dataReaderDetail
								.setHigh_Risk_Reason("First child weight is below 2.5 pound or above 4.5 pound");
					}
				}

				// child 2 weight check on high risk
				if (dataReaderDetail.getChild2_weight() != null) {

					if (dataReaderDetail.getChild2_weight() < 2.5 || dataReaderDetail.getChild2_weight() > 4.5) {
						dataReaderDetail.setHigh_Risk(true);
						dataReaderDetail
								.setHigh_Risk_Reason("Second child weight is below 2.5 pound or above 4.5 pound");
					}
				}

				// child 3 weight on high risk
				if (dataReaderDetail.getChild3_Weight() != null) {

					if (dataReaderDetail.getChild3_Weight() < 2.5 || dataReaderDetail.getChild3_Weight() > 4.5) {
						dataReaderDetail.setHigh_Risk(true);
						dataReaderDetail
								.setHigh_Risk_Reason("Third child weight is below 2.5 pound or above 4.5 pound");
					}
				}

				// child 3 weight on high risk
				if (dataReaderDetail.getChild4_Weight() != null) {

					if (dataReaderDetail.getChild4_Weight() < 2.5 || dataReaderDetail.getChild4_Weight() > 4.5) {
						dataReaderDetail.setHigh_Risk(true);
						dataReaderDetail
								.setHigh_Risk_Reason("Fourth child weight is below 2.5 pound or above 4.5 pound");
					}
				}

				// high risk check on anemia value
				if (dataReaderDetail.getAnemia() != null && dataReaderDetail.getAnemia().equalsIgnoreCase("severe<7")) {
					dataReaderDetail.setHigh_Risk(true);
					dataReaderDetail.setHigh_Risk_Reason("Severe Anemia <7");
				}

				// for NA in phone no of whome check
				if (!(dataReaderDetail.getPhoneNo_Of_Whom() != null)) {

					dataReaderDetail.setPhoneNo_Of_Whom("NA");
				}
				logger.info("savaMotherMctsData " + outputMapper.gson().toJson(dataReaderDetail) + " to valid record.");
				return true;
			}

			else {
				logger.info(
						"savaMotherMctsData " + outputMapper.gson().toJson(dataReaderDetail) + " to invalid record.");
				return false;
			}

		} catch (Exception e) {

			logger.error(e.getMessage());
		}
		return false;
	}

	/**
	 * 
	 * @param mapc
	 * @param
	 * @param manager
	 * @return
	 */
	private Boolean savaChildMctsData(ChildValidDataHandler childDataHandler, ConcurrentHashMap<Long, String> mapc) {

		try {

			childDataHandler.setDeleted(false);
			childDataHandler.setIs_Valid(true);
			String rel = childDataHandler.getPhone_No_of();
			childDataHandler
					.setPhone_No_of(rel.substring(0, 1).toUpperCase() + rel.substring(1, rel.length()).toLowerCase());
			if (!(childDataHandler.getMCTSID_no_Child_ID() != null)) {

				childDataHandler.setIs_Valid(false);
				childDataHandler.setError_Reason("Child ID is empty");
			}

			if (childDataHandler.getDOB() == null) {

				childDataHandler.setIs_Valid(false);
				childDataHandler.setError_Reason("Date of birth is passed or its empty");
			}

			if (this.getAge(childDataHandler.getDOB()) >= 1) {

				childDataHandler.setIs_Valid(false);
				childDataHandler.setError_Reason("Date of birth has passed");
			}

			if (childValidDataRepository.ifExists(childDataHandler.getMCTSID_no_Child_ID()) == true) {

				childDataHandler.setIs_Valid(false);
				childDataHandler.setError_Reason("Data already present");
			}
			if (childDataHandler.getMCTSID_no_Child_ID() != null
					&& mapc.containsKey(childDataHandler.getMCTSID_no_Child_ID())) {

				childDataHandler.setIs_Valid(false);
				childDataHandler.setError_Reason("Data already present");
			}

			if (childDataHandler.getIs_Valid() == true) {

				logger.info("savaChildMctsData " + outputMapper.gson().toJson(childDataHandler) + " to valid record.");
				return true;
			}

			else {

				logger.info(
						"savaChildMctsData " + outputMapper.gson().toJson(childDataHandler) + " to invalid record.");
				return false;
			}

		} catch (Exception e) {

		}
		return false;
	}

	/**
	 * method for edd calc
	 */
	private Date getEDD(Date lmpDate) {

		LocalDate ld = lmpDate.toLocalDate();
		ld = ld.plusMonths(9);
		ld = ld.plusDays(7);
		return Date.valueOf(ld);
	}

	/**
	 * to get XSSFWorkbook from file input stream
	 * 
	 * @param fileInputStream
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	private XSSFWorkbook getXSSFWorkbook(byte[] byteArray) throws InvalidFormatException, IOException {

		XSSFWorkbook workbook = new XSSFWorkbook(new ByteArrayInputStream(byteArray));
		return workbook;
	}

	/**
	 * method to create mother outbound call
	 */
	private void setMotherOutboundcalls(MctsDataReaderDetail motherValidRecordDetail, FileManager manager) {
		List<MctsOutboundCall> mctsOutboundCalllist = new ArrayList<>();
		MctsOutboundCall mctsOutboundCall;
		logger.info("setMotherOutboundcalls Start " + outputMapper.gson().toJson(motherValidRecordDetail)
				+ " to valid record.");
		Map<String, MctsOutboundCall> callsDateMap = mctsDateUtils.getCallTypesDates(
				motherValidRecordDetail.getLMP_Date(), motherValidRecordDetail.getEDD(),
				manager.getProviderServiceMapID());
		Iterator<Map.Entry<String, MctsOutboundCall>> itrator = callsDateMap.entrySet().iterator();
		while (itrator.hasNext()) {

			Map.Entry<String, MctsOutboundCall> entry = itrator.next();

			mctsOutboundCall = new MctsOutboundCall();

			mctsOutboundCall.setProviderServiceMapID(manager.getProviderServiceMapID());
			mctsOutboundCall.setOutboundCallType(entry.getKey());
			mctsOutboundCall.setCallDateFrom(entry.getValue().getCallDateFrom());
			mctsOutboundCall.setCallDateTo(entry.getValue().getCallDateTo());
			mctsOutboundCall.setAllocationStatus("unallocated");
			mctsOutboundCall.setCallStatus("Open");
			mctsOutboundCall.setNoOfTrials(0);
			mctsOutboundCall.setMotherID(motherValidRecordDetail.getMCTSID_no());
			mctsOutboundCall.setBeneficiaryRegID(motherValidRecordDetail.getBeneficiaryRegID());
			mctsOutboundCall.setCreatedBy(manager.getCreatedBy());
			mctsOutboundCall.setDisplayOBCallType(entry.getValue().getDisplayOBCallType());

			logger.info("setMotherOutboundcalls End " + outputMapper.gson().toJson(mctsOutboundCall)
					+ " to outbound record.");

			mctsOutboundCalllist.add(mctsOutboundCall);
		}
		mctsOutboundCallRepository.save(mctsOutboundCalllist);
		mctsDataHandlerRepository.markIsAllocate(motherValidRecordDetail.getMCTSID_no());
	}

	/**
	 * method to create mother outbound call
	 */
	private void setChildOutboundcalls(ChildValidDataHandler childValidDataHandler, FileManager manager) {
		List<MctsOutboundCall> mctsOutboundCalllist = new ArrayList<>();
		MctsOutboundCall mctsOutboundCall;
		logger.info("setChildOutboundcalls Start to outbound record.");
		Map<String, MctsOutboundCall> childCallsDateMap = mctsDateUtils
				.getChildCallTypesDates(childValidDataHandler.getDOB(), manager.getProviderServiceMapID());
		Iterator<Map.Entry<String, MctsOutboundCall>> itrator = childCallsDateMap.entrySet().iterator();
		while (itrator.hasNext()) {

			Map.Entry<String, MctsOutboundCall> entry = itrator.next();
			mctsOutboundCall = new MctsOutboundCall();

			mctsOutboundCall.setProviderServiceMapID(manager.getProviderServiceMapID());
			mctsOutboundCall.setOutboundCallType(entry.getKey());
			mctsOutboundCall.setCallDateFrom(entry.getValue().getCallDateFrom());
			mctsOutboundCall.setCallDateTo(entry.getValue().getCallDateTo());
			mctsOutboundCall.setAllocationStatus("unallocated");
			mctsOutboundCall.setCallStatus("Open");
			mctsOutboundCall.setNoOfTrials(0);
			if (childValidDataHandler.getMother_ID() != null) {
				mctsOutboundCall.setMotherID(childValidDataHandler.getMother_ID());
			}
			mctsOutboundCall.setChildID(childValidDataHandler.getMCTSID_no_Child_ID());
			mctsOutboundCall.setBeneficiaryRegID(childValidDataHandler.getBeneficiaryRegID());
			mctsOutboundCall.setCreatedBy(manager.getCreatedBy());
			mctsOutboundCall.setDisplayOBCallType(entry.getValue().getDisplayOBCallType());
			logger.info("setChildOutboundcalls End to outbound record.");

			mctsOutboundCalllist.add(mctsOutboundCall);
		}
		mctsOutboundCallRepository.save(mctsOutboundCalllist);
		childDataReaderRepository.markIsAllocate(childValidDataHandler.getMCTSID_no_Child_ID());
	}

	@Override
	public String updateBeneficiary(String request) throws IEMRException {

		MctsBenificairyUpdateUtils benificairyUpdateUtils = inputMapper.gson().fromJson(request,
				MctsBenificairyUpdateUtils.class);

		if (benificairyUpdateUtils.getFieldFor().equals(MctsConstants.MOTHER_RECORDS)) {

			mctsDataHandlerRepository.save(benificairyUpdateUtils.getMctsDataReaderDetail());
		} else {

			childValidDataRepository.save(benificairyUpdateUtils.getChildValidDataHandler());
		}

		return "Deatils are updated sucessfully";
	}

	@Override
	public FileManager savefilemanger(FileManager fileManager) {
		return fileManagerRepository.save(fileManager);
	}

	@Override
	public FileManager uploadstatus(String request) throws IEMRException {

		FileManager fileManager = InputMapper.gson().fromJson(request, FileManager.class);
		List<FileManager> file = fileManagerRepository
				.findTop1ByProviderServiceMapIDOrderByFileIDDesc(fileManager.getProviderServiceMapID());
		FileManager filem = new FileManager();
		if (file != null && file.size() > 0) {
			filem = file.get(0);
			if (filem != null && filem.getIsMother()) {

				filem.setValidRecordUpload(mctsDataHandlerRepository.validCount(filem.getFileID()));
				filem.setErroredRecordUpload(mctsInvalidDataRepository.invalidCount(filem.getFileID()));
			} else {
				if (filem != null) {
					filem.setValidRecordUpload(childValidDataRepository.validCount(filem.getFileID()));
					filem.setErroredRecordUpload(childInvalidDataRepository.invalidCount(filem.getFileID()));
				}
			}
		}
		return filem;
	}

	Date calculateBirthDate(Integer age) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1 * age);
		Timestamp dob = new Timestamp(cal.getTimeInMillis());
		Date birthdate = new Date(dob.getTime());
		return birthdate;
	}
}
