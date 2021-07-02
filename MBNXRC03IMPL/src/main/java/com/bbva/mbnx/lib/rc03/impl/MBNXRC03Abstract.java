package com.bbva.mbnx.lib.rc03.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.library.AbstractLibrary;
import com.bbva.elara.utility.api.connector.APIConnector;
import com.bbva.elara.utility.api.connector.APIConnectorBuilder;

import com.bbva.mbnx.lib.rc03.MBNXRC03;

public abstract class MBNXRC03Abstract extends AbstractLibrary implements MBNXRC03 {

	private static final Logger LOGGER = LoggerFactory.getLogger(MBNXRC03.class);
	
	protected ApplicationConfigurationService applicationConfigurationService;
	
	protected APIConnector internalApiConnector;

		
	protected APIConnectorBuilder apiConnectorBuilder;	
	
	/**
	 * @param applicationConfigurationService the applicationConfigurationService to set
	 */
	public void setApplicationConfigurationService(
			ApplicationConfigurationService applicationConfigurationService) {
		this.applicationConfigurationService = applicationConfigurationService;
	}
	
	
	/**
	 * @param internalApiConnector the internalApiConnector to set
	 */
	public void setInternalApiConnector(APIConnector internalApiConnector) {
		this.internalApiConnector = internalApiConnector;
	}
	
			
	/**
	 * @param apiConnectorBuilder the apiConnectorBuilder to set
	 */
	public void setApiConnectorBuilder(APIConnectorBuilder apiConnectorBuilder) {
		this.apiConnectorBuilder = apiConnectorBuilder;
	}
			
}
