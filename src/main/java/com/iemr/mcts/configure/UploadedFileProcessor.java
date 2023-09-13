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
package com.iemr.mcts.configure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.iemr.mcts.customexception.DateFormatNotMatchException;
import com.iemr.mcts.customexception.ExcelColumnDataException;
import com.iemr.mcts.data.supervisor.ChildValidDataHandler;
import com.iemr.mcts.data.supervisor.MctsDataReaderDetail;
import com.iemr.mcts.utils.exception.IEMRException;
import com.iemr.mcts.utils.mapper.InputMapper;

public class UploadedFileProcessor {

	TikaConfig config;
	Detector detector;
	TikaInputStream stream;
	Metadata metadata;

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	private static InputMapper inputMapper = new InputMapper();

	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * method to initialize TikaConfig and Detector
	 */
	private void init() {

		config = TikaConfig.getDefaultConfig();
		detector = config.getDetector();
	}

	/**
	 * this is to determine the content type of the file based on the file headers
	 * 
	 * @param inputStream
	 * @param fileName
	 * @return content type of the file
	 * @throws TikaException
	 * @throws IOException
	 */
	public String detectFileType(byte[] byteArray) throws TikaException, IOException {

		Tika tika = new Tika();
		String detectedType = tika.detect(byteArray);
		return detectedType;
	}

	/**
	 * function to create file check sum
	 * 
	 * @param inputStream
	 * @return file checksum
	 * @throws Exception
	 */
	public String getMD5Checksum(byte[] byteArray) throws IOException {

		byte[] digest = null;

		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			digest = md5.digest(byteArray);
		} catch (NoSuchAlgorithmException e) {

			logger.error(e.getMessage());
		}

		return new BigInteger(1, digest).toString(16);
	}

	/**
	 * method to read mcts data from the excel file
	 * 
	 * @param inputStream
	 * @param fieldsMap
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws EncryptedDocumentException
	 * @throws IEMRException
	 */
	public List<MctsDataReaderDetail> getMotherDataAsList(MultipartFile file, Map<String, String> fieldsMap)
			throws IOException, EncryptedDocumentException, InvalidFormatException, IEMRException {

		List<MctsDataReaderDetail> dataReaderDetails = new ArrayList<MctsDataReaderDetail>();

		FileInputStream fileInputStream = null;

		try {
			String fileName = file.getName();
			String sanitizedFile = (URI.create(fileName).normalize()).toString();
			File convFile = new File(sanitizedFile);
			FileUtils.writeByteArrayToFile(convFile, file.getBytes());
			fileInputStream = new FileInputStream(convFile);
			XSSFWorkbook workbook = this.getXSSFWorkbook(fileInputStream);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			Iterator<Row> rowIterator = datatypeSheet.iterator();

			mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
			mapper.setSerializationInclusion(Include.NON_NULL);

			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				if (row.getRowNum() != 0) {
					JSONObject objectMap = this.getDataObjectMap(row, fieldsMap, evaluator);
					MctsDataReaderDetail dataReaderDetail = inputMapper.gson().fromJson(objectMap.toString(),
							MctsDataReaderDetail.class);
					dataReaderDetail.setIsAllocated(false);
					dataReaderDetails.add(dataReaderDetail);
				}

			}

		} finally {
			if (fileInputStream != null)
				fileInputStream.close();
		}

		return dataReaderDetails;
	}

	/**
	 * method to read mcts data from the excel file
	 * 
	 * @param inputStream
	 * @param fieldsMap
	 * @return
	 * @throws IOException
	 * @throws EncryptedDocumentException
	 * @throws OpenXML4JException
	 * @throws SAXException
	 * @throws IEMRException
	 */
	public List<ChildValidDataHandler> getChildDataAsList(MultipartFile file, Map<String, String> fieldsMap)
			throws IOException, EncryptedDocumentException, OpenXML4JException, SAXException, IEMRException {

		List<ChildValidDataHandler> childDataHandlers = new ArrayList<ChildValidDataHandler>();

		FileInputStream fileInputStream = null;

		try {
			String fileName = file.getName();
			String sanitizedFile = (URI.create(fileName).normalize()).toString();
			File convFile = new File(sanitizedFile);
			FileUtils.writeByteArrayToFile(convFile, file.getBytes());
			fileInputStream = new FileInputStream(convFile);

			XSSFWorkbook workbook = this.getXSSFWorkbook(fileInputStream);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			Iterator<Row> rowIterator = datatypeSheet.iterator();

			mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
			mapper.setSerializationInclusion(Include.NON_NULL);

			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				if (row.getRowNum() != 0) {
					JSONObject objectMap = this.getDataObjectMap(row, fieldsMap, evaluator);
					ChildValidDataHandler childDataHandler = inputMapper.gson().fromJson(objectMap.toString(),
							ChildValidDataHandler.class);
					childDataHandlers.add(childDataHandler);
				}

			}

		} finally {
			if (fileInputStream != null)
				fileInputStream.close();
		}

		return childDataHandlers;
	}

	/**
	 * to read mcts data object from each row
	 * 
	 * @param row
	 * @param Xls sheet headers
	 * @return
	 */
	public List<String> getHeadersAsList(Row row) {

		List<String> headers = new ArrayList<String>();
		if (row != null) {

			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if (cell != null) {

					headers.add(cell.getStringCellValue().trim());
				}
			}
		}

		return headers;
	}

	@SuppressWarnings("deprecation")
	public JSONObject getDataObjectMap(Row row, Map<String, String> fieldsMap, FormulaEvaluator evaluator)
			throws DateFormatNotMatchException {

		JSONObject object = new JSONObject();

		String dateFormats[] = { "dd-MM-yyyy", "dd/MM/yyyy", "dd-MMM-yy" };
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSS");
		DataFormatter dataFormatter = new DataFormatter();
		Date date = null;

		if (row.getRowNum() != 0) {

			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if (cell != null) {

					String key = cell.getSheet().getRow(0).getCell(cell.getColumnIndex()).getStringCellValue().trim();
					try {
						switch (cell.getCellTypeEnum()) {

						case STRING:
							if (cell.getStringCellValue().length() > 0 && !cell.getStringCellValue().equals(" ")
									&& fieldsMap.get(key) != null) {

								if (!cell.getStringCellValue().trim().equals("NA")) {
									if (key.toLowerCase().indexOf("date") != -1
											|| key.toLowerCase().indexOf("_dt") != -1) {
										try {

											for (String formstStr : dateFormats) {

												try {
													SimpleDateFormat formatDate = new SimpleDateFormat(formstStr);
													date = formatDate.parse(dataFormatter.formatCellValue(cell));
													break;
												} catch (Exception e) {

												}

											}

										} catch (Exception e) {

											logger.error(e.getMessage());
											throw new DateFormatNotMatchException(
													"Date format does not match with(dd-mm-yyyy)" + " for column " + key
															+ " in the line #" + (row.getRowNum() + 1));
										}
										if (date != null) {
											object.put(fieldsMap.get(key), dateFormat1.format(date));
										}
									} else
										object.put(fieldsMap.get(key), cell.getStringCellValue());
								}
							}
							break;

						case BLANK:
							break;

						case ERROR:
							break;

						case BOOLEAN:

							object.put(fieldsMap.get(key), String.valueOf(cell.getBooleanCellValue()));
							break;

						default:
							if (DateUtil.isCellDateFormatted(cell)) {

								try {
									date = cell.getDateCellValue();
									object.put(fieldsMap.get(key), dateFormat1.format(date));
								} catch (Exception e) {
								}

							}

							else {
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().trim().equals("NA")) {
									if (cell.getStringCellValue().length() > 0 && !cell.getStringCellValue().equals(" ")
											&& fieldsMap.get(key) != null) {
										if (key.toLowerCase().indexOf("_dt") != -1
												|| key.toLowerCase().indexOf("date") != -1
														&& !key.toLowerCase().equals("updated_by")) {

											try {
												for (String formstStr : dateFormats) {

													try {
														SimpleDateFormat formatDate = new SimpleDateFormat(formstStr);
														date = formatDate.parse(dataFormatter.formatCellValue(cell));
														break;
													} catch (Exception e) {

													}

												}

											} catch (Exception e) {

												throw new DateFormatNotMatchException(
														"Date format does not match with(dd-mm-yyyy)" + " for column "
																+ key + " in the line #" + (row.getRowNum() + 1));
											}

											if (date != null) {
												object.put(fieldsMap.get(key), dateFormat1.format(date));
											}

										}

										else
											object.put(fieldsMap.get(key), cell.getStringCellValue());
									}

								}
							}
						}
					} catch (DateFormatNotMatchException e) {

						throw e;
					} catch (Exception e) {

						throw new ExcelColumnDataException(
								"Data error at column " + key + " in the line #" + (row.getRowNum() + 1));
					}
				}

			}
		}

		return object;
	}

	private synchronized XSSFWorkbook getXSSFWorkbook(FileInputStream fileInputStream)
			throws InvalidFormatException, IOException {

		OPCPackage pkg = OPCPackage.open(fileInputStream);
		XSSFWorkbook workbook = new XSSFWorkbook(pkg);
		return workbook;
	}
}
