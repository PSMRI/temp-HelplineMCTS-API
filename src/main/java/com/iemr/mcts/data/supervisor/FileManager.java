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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.mcts.utils.mapper.OutputMapper;

import lombok.Data;

@Data
@Entity
@Table(name = "t_filemanager")
public class FileManager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name = "FileID")
	private Long fileID;

	@Expose
	@Column(name = "FileTypeID")
	private Long fileTypeID;

	@Expose
	@Column(name = "FileName")
	private String fileName;

	@Expose
	@Column(name = "FilePath")
	private String filePath;

	@Expose
	@Column(name = "FileStatusID")
	private Long fileStatusID;

	@Expose
	@Column(name = "UserID")
	private Long userID;

	@Expose
	@Column(name = "TotalRecordCount")
	private Long totalRecordCount;

	@Expose
	@Column(name = "ValidRecordCount")
	private Long validRecordCount;

	@Expose
	@Column(name = "ErroredRecordCount")
	private Long erroredRecordCount;

	@Expose
	@Transient
	private Long validRecordUpload;

	@Expose
	@Transient
	private Long erroredRecordUpload;

	@Expose
	@Column(name = "FileLoadDate")
	private Date fileLoadDate;

	@Expose
	@Column(name = "IsMother")
	private Boolean isMother;

	@Expose
	@Column(name = "ProviderServiceMapID")
	private Long providerServiceMapID;

	@Expose
	@Column(name = "MD5CheckSum")
	private String md5CheckSum;

	@Expose
	@Column(name = "StatusReason")
	private String statusReason;

	@Expose
	@Column(name = "Deleted")
	private boolean deleted;

	@Expose
	@Column(name = "CreatedBy", insertable = true, updatable = false)
	private String createdBy;

	@Expose
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Date createdDate;

	@Expose
	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Expose
	@Column(name = "LastModDate")
	private Date lastModDate;

	@Transient
	private String fileContent;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FileStatusID", insertable = false, updatable = false)
	@Expose
	private FileStatus fileStatus;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fileManager")
	private List<ChildDataReader> childDataReaders;

	/**
	 * @return the providerServiceMapID
	 */
	public Long getProviderServiceMapID() {
		return providerServiceMapID;
	}

	/**
	 * @param providerServiceMapID the providerServiceMapID to set
	 */
	public void setProviderServiceMapID(Long providerServiceMapID) {
		this.providerServiceMapID = providerServiceMapID;
	}

	/**
	 * @return the md5CheckSum
	 */
	public String getMd5CheckSum() {
		return md5CheckSum;
	}

	/**
	 * @param md5CheckSum the md5CheckSum to set
	 */
	public void setMd5CheckSum(String md5CheckSum) {
		this.md5CheckSum = md5CheckSum;
	}

	/**
	 * @return the outputMapper
	 */
	public static OutputMapper getOutputMapper() {
		return outputMapper;
	}

	/**
	 * @param outputMapper the outputMapper to set
	 */
	public static void setOutputMapper(OutputMapper outputMapper) {
		FileManager.outputMapper = outputMapper;
	}

	/**
	 * Default Constructor
	 */
	public FileManager() {

	}

	/**
	 * @return the fileID
	 */
	public Long getFileID() {
		return fileID;
	}

	/**
	 * @param fileID the fileID to set
	 */
	public void setFileID(Long fileID) {
		this.fileID = fileID;
	}

	/**
	 * @return the fileTypeID
	 */
	public Long getFileTypeID() {
		return fileTypeID;
	}

	/**
	 * @param fileTypeID the fileTypeID to set
	 */
	public void setFileTypeID(Long fileTypeID) {
		this.fileTypeID = fileTypeID;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the fileStatusID
	 */
	public Long getFileStatusID() {
		return fileStatusID;
	}

	/**
	 * @param fileStatusID the fileStatusID to set
	 */
	public void setFileStatusID(Long fileStatusID) {
		this.fileStatusID = fileStatusID;
	}

	/**
	 * @return the userID
	 */
	public Long getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(Long userID) {
		this.userID = userID;
	}

	/**
	 * @return the totalRecordCount
	 */
	public Long getTotalRecordCount() {
		return totalRecordCount;
	}

	/**
	 * @param totalRecordCount the totalRecordCount to set
	 */
	public void setTotalRecordCount(Long totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	/**
	 * @return the validRecordCount
	 */
	public Long getValidRecordCount() {
		return validRecordCount;
	}

	/**
	 * @param validRecordCount the validRecordCount to set
	 */
	public void setValidRecordCount(Long validRecordCount) {
		this.validRecordCount = validRecordCount;
	}

	/**
	 * @return the erroredRecordCount
	 */
	public Long getErroredRecordCount() {
		return erroredRecordCount;
	}

	/**
	 * @param erroredRecordCount the erroredRecordCount to set
	 */
	public void setErroredRecordCount(Long erroredRecordCount) {
		this.erroredRecordCount = erroredRecordCount;
	}

	/**
	 * @return the fileLoadDate
	 */
	public Date getFileLoadDate() {
		return fileLoadDate;
	}

	/**
	 * @param fileLoadDate the fileLoadDate to set
	 */
	public void setFileLoadDate(Date fileLoadDate) {
		this.fileLoadDate = fileLoadDate;
	}

	/**
	 * @return the mD5CheckSum
	 */
	public String getmD5CheckSum() {
		return md5CheckSum;
	}

	/**
	 * @param mD5CheckSum the mD5CheckSum to set
	 */
	public void setmD5CheckSum(String mD5CheckSum) {
		this.md5CheckSum = mD5CheckSum;
	}

	/**
	 * @return the deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the lastModDate
	 */
	public Date getLastModDate() {
		return lastModDate;
	}

	/**
	 * @param lastModDate the lastModDate to set
	 */
	public void setLastModDate(Date lastModDate) {
		this.lastModDate = lastModDate;
	}

	/**
	 * @return the fileContent
	 */
	public String getFileContent() {
		return fileContent;
	}

	/**
	 * @param fileContent the fileContent to set
	 */
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	/**
	 * Output mapper
	 */
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

}
