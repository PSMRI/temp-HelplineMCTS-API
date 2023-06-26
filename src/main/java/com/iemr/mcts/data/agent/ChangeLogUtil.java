package com.iemr.mcts.data.agent;

import java.sql.Timestamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.iemr.mcts.utils.mapper.OutputMapper;

public class ChangeLogUtil {

	@SerializedName("changeLog")
	@Expose
	private String changeLog;
	
	@SerializedName("modifiedBy")
	@Expose
	private String modifiedBy;
	
	@SerializedName("modifiedDate")
	@Expose
	private Timestamp modifiedDate;
	
	private static OutputMapper outputMapper = new OutputMapper();
	
	/**
	 * @return the changeLog
	 */
	public String getChangeLog() {
		return changeLog;
	}
	
	/**
	 * @param changeLog the changeLog to set
	 */
	public void setChangeLog(String changeLog) {
		this.changeLog = changeLog;
	}
	
	/**
	 * @return the mofidifedBy
	 */
	public String getMofidifedBy() {
		return modifiedBy;
	}
	
	/**
	 * @param mofidifedBy the mofidifedBy to set
	 */
	public void setMofidifedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	/**
	 * @return the modifiedDate
	 */
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	
	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((changeLog == null) ? 0 : changeLog.hashCode());
		result = prime * result + ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result + ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChangeLogUtil other = (ChangeLogUtil) obj;
		if (changeLog == null) {
			if (other.changeLog != null)
				return false;
		} else if (!changeLog.equals(other.changeLog))
			return false;
		if (modifiedBy == null) {
			if (other.modifiedBy != null)
				return false;
		} else if (!modifiedBy.equals(other.modifiedBy))
			return false;
		if (modifiedDate == null) {
			if (other.modifiedDate != null)
				return false;
		} else if (!modifiedDate.equals(other.modifiedDate))
			return false;
		return true;
	}

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
