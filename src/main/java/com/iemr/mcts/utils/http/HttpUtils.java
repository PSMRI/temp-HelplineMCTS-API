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
package com.iemr.mcts.utils.http;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

@Component
public class HttpUtils {
	public static final String AUTHORIZATION = "Authorization";
	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	private RestTemplate rest;
	private HttpHeaders headers;
	private HttpStatus status;

	public HttpUtils() {
		if (rest == null) {
			rest = new RestTemplate();
			headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
		}
	}

	public String get(String uri) {
		String body;
		HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
		ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.GET, requestEntity, String.class);
		setStatus((HttpStatus) responseEntity.getStatusCode());
		body = responseEntity.getBody();
		return body;
	}

	public String get(String uri, HashMap<String, Object> header) {
		String body;
		HttpHeaders headers = new HttpHeaders();
		if (header.containsKey(headers.AUTHORIZATION)) {
			headers.add(headers.AUTHORIZATION, header.get(headers.AUTHORIZATION).toString());
		}
		if (header.containsKey(headers.CONTENT_TYPE)) {
			headers.add(headers.CONTENT_TYPE, header.get(headers.CONTENT_TYPE).toString());
		} else {
			headers.add("Content-Type", MediaType.APPLICATION_JSON);
		}
		HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
		ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.GET, requestEntity, String.class);
		setStatus((HttpStatus) responseEntity.getStatusCode());
		body = responseEntity.getBody();
		return body;
	}

	public String post(String uri, String json) {
		String body;
		HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.POST, requestEntity, String.class);
		setStatus((HttpStatus) responseEntity.getStatusCode());
		body = responseEntity.getBody();
		return body;
	}

	public String post(String uri, String data, HashMap<String, Object> header) {
		String body;
		HttpHeaders headers = new HttpHeaders();
		if (header.containsKey(headers.AUTHORIZATION)) {
			headers.add(headers.AUTHORIZATION, header.get(headers.AUTHORIZATION).toString());
		}
		headers.add("Content-Type", MediaType.APPLICATION_JSON);
		ResponseEntity<String> responseEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
		HttpEntity<String> requestEntity;
		requestEntity = new HttpEntity<String>(data, headers);
		responseEntity = rest.exchange(uri, HttpMethod.POST, requestEntity, String.class);
		setStatus((HttpStatus) responseEntity.getStatusCode());
		body = responseEntity.getBody();
		return body;
	}

	public String uploadFile(String uri, String data, HashMap<String, Object> header) throws IOException {
		String body;
		HttpHeaders headers = new HttpHeaders();
		if (header.containsKey(headers.AUTHORIZATION)) {
			headers.add(headers.AUTHORIZATION, header.get(headers.AUTHORIZATION).toString());
		}
		if (header.containsKey(headers.CONTENT_TYPE)) {
			headers.add(headers.CONTENT_TYPE, header.get(headers.CONTENT_TYPE).toString());
		} else {
			headers.add("Content-Type", MediaType.APPLICATION_JSON);
		}
		ResponseEntity<String> responseEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
		if (headers.getContentType().toString().equals(MediaType.MULTIPART_FORM_DATA_TYPE.toString())) {
			HttpEntity<FormDataMultiPart> requestEntity;
			FormDataMultiPart multiPart = null;
			try {
				multiPart = new FormDataMultiPart();
				FileInputStream is = new FileInputStream(data);
				FormDataBodyPart filePart = new FormDataBodyPart("content", is,
						MediaType.APPLICATION_OCTET_STREAM_TYPE);
				multiPart.bodyPart(filePart);
				multiPart.field("docPath", data);
				headers.add("Content-Type", MediaType.APPLICATION_JSON);
				requestEntity = new HttpEntity<FormDataMultiPart>(multiPart, headers);// new
																						// HttpEntity<String>(multiPart,
																						// headers);
				responseEntity = rest.exchange(uri, HttpMethod.POST, requestEntity, String.class);
			} catch (FileNotFoundException e) {
				logger.error(e.getMessage());
			} finally {
				if (multiPart != null)
					multiPart.close();
			}
		} else {
			HttpEntity<String> requestEntity;
			requestEntity = new HttpEntity<String>(data, headers);
			responseEntity = rest.exchange(uri, HttpMethod.POST, requestEntity, String.class);
		}
		setStatus((HttpStatus) responseEntity.getStatusCode());
		body = responseEntity.getBody();
		return body;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}