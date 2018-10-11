package com.apap.tutorial5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.repository.FlightDB;

/*
 * FlightServiceImpl
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightDB flightDb;

	@Override
	public void addFlight(FlightModel flight) {
		// TODO Auto-generated method stub
		flightDb.save(flight);
	}

	@Override
	public FlightModel getFlightDetailById(long id) {
		// TODO Auto-generated method stub
		return flightDb.getOne(id);
	}

	@Override
	public List<FlightModel> getFlightDetailByPilot(String licenseNumber) {
		// TODO Auto-generated method stub
		return flightDb.findByPilotLicenseNumber(licenseNumber);
	}
	@Override
	public void deleteById(long id) {
		flightDb.deleteById(id);
	}

	@Override
	public void updateFlight(long id, FlightModel updateFl) {
		// TODO Auto-generated method stub
		FlightModel oldFlight = flightDb.getOne(id);
		oldFlight.setTime(updateFl.getTime());
		oldFlight.setDestination(updateFl.getDestination());
		oldFlight.setFlightNumber(updateFl.getFlightNumber());
		oldFlight.setOrigin(updateFl.getOrigin());
		flightDb.save(oldFlight);
	}

	@Override
	public List<FlightModel> getFlight() {
		// TODO Auto-generated method stub
		List<FlightModel> result = flightDb.findAll();
		return result;
	}
	
}
