package com.bbva.mbnx.lib.rc03;

import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.mock;

import javax.annotation.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbva.elara.utility.api.connector.APIConnector;
import com.bbva.elara.utility.api.connector.APIConnectorBuilder;
import com.bbva.mbnx.lib.rc03.impl.MBNXRC03Impl;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/MBNXRC03-app.xml",
		"classpath:/META-INF/spring/MBNXRC03-app-test.xml",
		"classpath:/META-INF/spring/MBNXRC03-arc.xml",
		"classpath:/META-INF/spring/MBNXRC03-arc-test.xml" })
public class MBNXRC03Test {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MBNXRC03.class);
	
	private MBNXRC03Impl mbnxRC03;
	
	@Mock
	private APIConnector internalApiConnector;
	/*
	@Resource(name = "mbnxRC03")
	private MBNXRC03 mbnxRC03;
	*/
	@Before
	public void setUp() throws Exception {
		mbnxRC03=new MBNXRC03Impl();
		getObjectIntrospection();
	}
	
	private Object getObjectIntrospection() throws Exception{
		Object result = this.mbnxRC03;
		if(this.mbnxRC03 instanceof Advised){
			Advised advised = (Advised) this.mbnxRC03;
			result = advised.getTargetSource().getTarget();
		}
		return result;
	}
	
	@Test
	public void executeUnlockSuccessTest(){
		LOGGER.info("Executing the success unlock test...");
		internalApiConnector=mock(APIConnector.class);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>("JSON RESPONSE",HttpStatus.NO_CONTENT);
		Mockito.when(internalApiConnector.exchange(Matchers.anyString(), Matchers.any(HttpMethod.class), Matchers.any(), Matchers.<Class<String>>any(), anyMap())).thenReturn(responseEntity);
		mbnxRC03.setInternalApiConnector(internalApiConnector);
		Assert.assertTrue(mbnxRC03.executeModifyBlock("1234"));
	}
	
	@Test
	public void executeUnlockFailureTest(){
		LOGGER.info("Executing the failure unlock test...");
		internalApiConnector=mock(APIConnector.class);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>("JSON RESPONSE",HttpStatus.NOT_FOUND);
		Mockito.when(internalApiConnector.exchange(Matchers.anyString(), Matchers.any(HttpMethod.class), Matchers.any(), Matchers.<Class<String>>any(), anyMap())).thenReturn(responseEntity);
		mbnxRC03.setInternalApiConnector(internalApiConnector);
		Assert.assertFalse(mbnxRC03.executeModifyBlock("BBVA"));
		
	}
	
}
