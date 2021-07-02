package com.bbva.mbnx.lib.rc03.impl;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.bbva.mbnx.lib.rc03.MBNXRC03;

public class MBNXRC03Impl extends MBNXRC03Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(MBNXRC03.class);
	private static final String SERVICE_ID="SrvModifyBlock";
	private static final String COMPLETE="COMPLETE";
	private static final String OBSERVA="CUENTA DESBLOQUEADA POR NOMINA";
	private static final String CODIGO="C01";
	
	@Override
	public boolean executeModifyBlock(String accountNumber) {
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Map<String,Object> uriVars=new HashMap<>();
		uriVars.put("account-id", accountNumber);
		uriVars.put("block-id", CODIGO);
		JSONObject json_payload=new JSONObject();
		json_payload.put("description", OBSERVA);
		json_payload.put("status", COMPLETE);
		HttpEntity<String> request= new HttpEntity<String>(json_payload.toString(),headers);
		System.out.println(request.toString());
		ResponseEntity<String> response=this.internalApiConnector.exchange(SERVICE_ID, HttpMethod.PATCH, request, String.class,uriVars);
		if(response.getStatusCode().equals(HttpStatus.NO_CONTENT)){
			return true;
		}
		return false;
	}
}
