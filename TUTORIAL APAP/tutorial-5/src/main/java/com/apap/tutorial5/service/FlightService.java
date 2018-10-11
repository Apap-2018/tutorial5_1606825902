package com.apap.tutorial5.service;

import java.util.List;

import com.apap.tutorial5.model.FlightModel;

/*
 * FlightService
 */
public interface FlightService {
	void addFlight(FlightModel flight);
	FlightModel getFlightDetailById(long id);
	List<FlightModel> getFlightDetailByPilot(String licenseNumber);
	void deleteById(long id);
	void updateFlight(long id, FlightModel updateFl);
	List<FlightModel> getFlight();
}
