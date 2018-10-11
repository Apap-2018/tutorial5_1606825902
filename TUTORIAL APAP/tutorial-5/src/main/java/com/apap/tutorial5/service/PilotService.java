package com.apap.tutorial5.service;

import com.apap.tutorial5.model.PilotModel;

/*
 * Pilot Service 
 */
public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	
	void addPilot(PilotModel pilot);
	void deletePilot(String licenseNumber);
	PilotModel getPilotByLicenseNumber(String licenseNumber);
	void pilotUpdate(String licenseNumber, PilotModel newpilot);
}
