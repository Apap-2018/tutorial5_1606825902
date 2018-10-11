package com.apap.tutorial5.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.repository.PilotDB;

/*
 * PilotServiceImpl
 */
@Service
@Transactional
public class PilotServiceImpl implements PilotService {
	@Autowired
	private PilotDB pilotDB;

	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		// TODO Auto-generated method stub
		return pilotDB.findByLicenseNumber(licenseNumber);
	}

	@Override
	public void addPilot(PilotModel pilot) {
		// TODO Auto-generated method stub
		pilotDB.save(pilot);
		
	}

	@Override
	public void deletePilot(String licenseNumber) {
		// TODO Auto-generated method stub
		PilotModel target = pilotDB.findByLicenseNumber(licenseNumber);
		pilotDB.delete(target);
	}

	@Override
	public PilotModel getPilotByLicenseNumber(String licenseNumber) {
		// TODO Auto-generated method stub
		return pilotDB.findByLicenseNumber(licenseNumber);
	}

	@Override
	public void pilotUpdate(String licenseNumber, PilotModel newpilot) {
		// TODO Auto-generated method stub
		PilotModel updatePilot = getPilotByLicenseNumber(licenseNumber);
		updatePilot.setName(newpilot.getName());
		updatePilot.setFlyHour(newpilot.getFlyHour());
		pilotDB.save(updatePilot);
		
	}
}
