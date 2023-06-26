package com.iemr.mcts.controller.secondoryreport;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iemr.mcts.services.secondaryreport.SecondaryDataReportService;
import com.iemr.mcts.utils.response.OutputResponse;

import io.swagger.annotations.ApiParam;

@CrossOrigin
@RequestMapping({ "/mctsReportController" })
@RestController
public class MctsSecondaryReportController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	@Autowired
	private SecondaryDataReportService secondaryReportService;

	@CrossOrigin()
	@RequestMapping(value = "/getDataReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getDataReport(
			@ApiParam("{\"startDate\":\"Timestamp\", \"endDate\":\"Timestamp\", \"providerServiceMapID\":\"Integer \", \"isMother\":\"Boolean\"}") @RequestBody String jsonRequest) {
		String filename=null;
		InputStreamResource file = null;
		try {
			filename= getFileName(jsonRequest, "MCTSDataReport");
			file = new InputStreamResource(secondaryReportService.getDataReport(jsonRequest, filename));
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			if (e.getMessage().equalsIgnoreCase("No data found"))
				return ResponseEntity.status(500).body(e.getMessage());
			else
				return ResponseEntity.status(5000).body(e.getMessage());
		}
	}

	@CrossOrigin
	@RequestMapping(value = "/getCallDetailReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getCallDetailReport(
			@ApiParam("{\"startDate\":\"Timestamp\", \"endDate\":\"Timestamp\", \"providerServiceMapID\":\"Integer \", \"userID\":\"Integer\"}") @RequestBody String jsonRequest) {
		String filename = null;
		InputStreamResource file = null;
		try {
			filename = getFileName(jsonRequest, "MCTSCallDetailReport");
			file = new InputStreamResource(secondaryReportService.getCallDetailReport(jsonRequest, filename));
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			if (e.getMessage().equalsIgnoreCase("No data found"))
				return ResponseEntity.status(500).body(e.getMessage());
			else
				return ResponseEntity.status(5000).body(e.getMessage());
		}
	}

	@CrossOrigin
	@RequestMapping(value = "/getCallDetailReportUnique", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getCallDetailReportUnique(
			@ApiParam("{\"startDate\":\"Timestamp\", \"endDate\":\"Timestamp\", \"providerServiceMapID\":\"Integer \", \"userID\":\"Integer\"}") @RequestBody String jsonRequest) {
		String filename = null;
		InputStreamResource file = null;
		try {
			filename = getFileName(jsonRequest, "MCTSCallDetailReportUnique");
			file = new InputStreamResource(secondaryReportService.getCallDetailReportUnique(jsonRequest, filename));
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			if (e.getMessage().equalsIgnoreCase("No data found"))
				return ResponseEntity.status(500).body(e.getMessage());
			else
				return ResponseEntity.status(5000).body(e.getMessage());
		}
	}
   
	@CrossOrigin
	@RequestMapping(value = "/getCallAnsweredReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getCallAnsweredReport(
			@ApiParam("{\"startDate\":\"Timestamp\", \"endDate\":\"Timestamp\", \"providerServiceMapID\":\"Integer \", "
					+ "\"userID\":\"Integer\", \"callTypeName\":\"Answered or Not Answered\"}") @RequestBody String jsonRequest) {
		
		String filename = null;
		InputStreamResource file = null;
		try {
			filename = getFileName(jsonRequest, "Calls_Answered_Report");
			file = new InputStreamResource(secondaryReportService.getCallAnsweredReport(jsonRequest, filename));
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			if (e.getMessage().equalsIgnoreCase("No data found"))
				return ResponseEntity.status(500).body(e.getMessage());
			else
				return ResponseEntity.status(5000).body(e.getMessage());
		}
	}
    
    @CrossOrigin
	@RequestMapping(value = "/getComplaintReport", method = RequestMethod.POST, headers = "Authorization")
	public  ResponseEntity<Object> getComplaintReport(@ApiParam("{\"startDate\":\"Timestamp\", \"endDate\":\"Timestamp\", \"providerServiceMapID\":\"Integer \", \"userID\":\"Integer\"}")
			@RequestBody String jsonRequest)
	{
    	String filename = null;
		InputStreamResource file = null;
		try {
			filename = getFileName(jsonRequest, "Complaint_Report");
			file = new InputStreamResource(secondaryReportService.getComplaintReport(jsonRequest, filename));
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			if (e.getMessage().equalsIgnoreCase("No data found"))
				return ResponseEntity.status(500).body(e.getMessage());
			else
				return ResponseEntity.status(5000).body(e.getMessage());
		}
	}
    
    @CrossOrigin
	@RequestMapping(value = "/getInvalidRecordReport", method = RequestMethod.POST,headers = "Authorization")
	public ResponseEntity<Object> getInvalidRecordReport(@ApiParam("{\"startDate\":\"Timestamp\", \"endDate\":\"Timestamp\", \"providerServiceMapID\":\"Integer \", \"isMother\":\"Boolean\"}")
			@RequestBody String jsonRequest){
	 String filename=null;
	 InputStreamResource file = null;
	{
		
		try {
			filename= getFileName(jsonRequest, "Invalid_Records_Report");
			file = new InputStreamResource(secondaryReportService.getInvalidRecordReport(jsonRequest, filename));
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			if (e.getMessage().equalsIgnoreCase("No data found"))
				return ResponseEntity.status(500).body(e.getMessage());
			else
				return ResponseEntity.status(5000).body(e.getMessage());
		}
	}
	}

	@CrossOrigin
	@RequestMapping(value = "/getCallNotAnsweredReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getCallNotAnsweredReport(
			@ApiParam("{\"startDate\":\"Timestamp\", \"endDate\":\"Timestamp\", \"providerServiceMapID\":\"Integer \", "
					+ "\"userID\":\"Integer\", \"callTypeName\":\"Answered or Not Answered\"}") @RequestBody String jsonRequest) {
		String filename = null;
		InputStreamResource file = null;
		try {
			filename = getFileName(jsonRequest, "MCTSCallNotAnsweredReport");
			file = new InputStreamResource(secondaryReportService.getCallNotAnsweredReport(jsonRequest, filename));
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			if (e.getMessage().equalsIgnoreCase("No data found"))
				return ResponseEntity.status(500).body(e.getMessage());
			else
				return ResponseEntity.status(5000).body(e.getMessage());
		}
	}

	@CrossOrigin
	@RequestMapping(value = "/getHighRiskReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getHighRiskReport(
			@ApiParam("{\"startDate\":\"Timestamp\", \"endDate\":\"Timestamp\", \"providerServiceMapID\":\"Integer \", \"userID\":\"Integer\"}") @RequestBody String jsonRequest) {
		String filename = null;
		InputStreamResource file = null;
		try {
			filename = getFileName(jsonRequest, "MCTSHighRiskReport");
			file = new InputStreamResource(secondaryReportService.getHighRiskReport(jsonRequest, filename));
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			if (e.getMessage().equalsIgnoreCase("No data found"))
				return ResponseEntity.status(500).body(e.getMessage());
			else
				return ResponseEntity.status(5000).body(e.getMessage());
		}
	}

	@CrossOrigin
	@RequestMapping(value = "/getDailyReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getDailyReport(
			@ApiParam("{\"startDate\":\"Timestamp\", \"endDate\":\"Timestamp\", \"providerServiceMapID\":\"Integer \", "
					+ "\"userID\":\"Integer\", \"isMother\":\"Boolean\"}") @RequestBody String jsonRequest) {
		String filename = null;
		InputStreamResource file = null;
		try {
			filename = getFileName(jsonRequest, "MCTSDailyReport");
			file = new InputStreamResource(secondaryReportService.getDailyReport(jsonRequest, filename));
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			if (e.getMessage().equalsIgnoreCase("No data found"))
				return ResponseEntity.status(500).body(e.getMessage());
			else
				return ResponseEntity.status(5000).body(e.getMessage());
		}
	}

	@CrossOrigin
	@RequestMapping(value = "/getNHMReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getNHMReport(
			@ApiParam("{\"startDate\":\"Timestamp\", \"endDate\":\"Timestamp\", \"providerServiceMapID\":\"Integer \", \"userID\":\"Integer\"}") @RequestBody String jsonRequest) {
		String filename =null;
		InputStreamResource file = null;
		try {
			filename = getFileName(jsonRequest, "MCTSNHMReport");
			file = new InputStreamResource(secondaryReportService.getNHMReport(jsonRequest, filename));
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			if (e.getMessage().equalsIgnoreCase("No data found"))
				return ResponseEntity.status(500).body(e.getMessage());
			else
				return ResponseEntity.status(5000).body(e.getMessage());
		}
	}
	
	@CrossOrigin
	@RequestMapping(value = "/getCongenitalAnomaliesReport", method = RequestMethod.POST, headers = "Authorization")
	public ResponseEntity<Object> getCongenitalAnomaliesReport(@ApiParam("{\"startDate\":\"Timestamp\", \"endDate\":\"Timestamp\", \"providerServiceMapID\":\"Integer \", \"userID\":\"Integer\"}")
			@RequestBody String jsonRequest, HttpServletRequest httpRequest)
	{
		String filename =null;
		InputStreamResource file = null;
		try {
			filename = getFileName(jsonRequest, "Congenital_Anomalies_Report");
			file = new InputStreamResource(secondaryReportService.getCongenitalAnomaliesReport(jsonRequest, filename));
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (filename + ".xlsx"))
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
		} catch (Exception e) {
			logger.error("Report Name:" + filename + " Timestamp:" + System.currentTimeMillis() + " Error: "
					+ e.getMessage());
			if (e.getMessage().equalsIgnoreCase("No data found"))
				return ResponseEntity.status(500).body(e.getMessage());
			else
				return ResponseEntity.status(5000).body(e.getMessage());
		}
	}

	public String getFileName(String jsonRequest, String name) {
		String fileName = null;
		JsonObject jsnOBJ = new JsonObject();
		JsonParser jsnParser = new JsonParser();
		JsonElement jsnElmnt = jsnParser.parse(jsonRequest);
		jsnOBJ = jsnElmnt.getAsJsonObject();
		if (jsnOBJ != null && jsnOBJ.has("fileName"))// add null check for fileName
		{
			fileName = jsnOBJ.get("fileName").getAsString();
		}
		if (fileName == null)
			fileName = name;
		
		return fileName;
	}
}
