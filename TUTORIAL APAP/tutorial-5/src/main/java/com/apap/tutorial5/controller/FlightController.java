package com.apap.tutorial5.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.FlightService;
import com.apap.tutorial5.service.PilotService;

/*
 * FlightController
 */
@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value="/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value="licenseNumber") String licenseNumber, Model model) {
PilotModel pilot = new PilotModel();
		
		List<FlightModel> listFlight = new ArrayList<FlightModel>();
		FlightModel flight = new FlightModel();
		listFlight.add(flight);
		pilot.setPilotFlight(listFlight);
		
		model.addAttribute("pilot", pilot);
		model.addAttribute("licenseNumber", licenseNumber);
		return "addFlight";
		
	}
	@RequestMapping(value="/flight/add/{licenseNumber}", method = RequestMethod.POST, params= {"submit"})
	private String addFlightSubmit(@PathVariable(value = "licenseNumber") String licenseNumber, @ModelAttribute PilotModel pilot_new) {
		PilotModel pilot_insert = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		for (int i = 0; i < pilot_new.getPilotFlight().size(); i++) {
			FlightModel flight = pilot_new.getPilotFlight().get(i);
			flight.setPilot(pilot_insert);
			flightService.addFlight(flight);
		}
		return "add";
	}
	
	@RequestMapping(value = "/flight/delete", method = RequestMethod.POST)
	private String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
		for(FlightModel flight : pilot.getPilotFlight()) {
			flightService.deleteById(flight.getId());
		}
		return "deletePage";
	}
	@RequestMapping(value="/flight/update/{id}", method=RequestMethod.GET)
	public String updateFlight(@PathVariable(value="id") long id, Model model) {
		model.addAttribute("updateFl", new FlightModel());
		FlightModel Flight = flightService.getFlightDetailById(id);
		model.addAttribute("flight", Flight);
		return "updateFlight";
	}
	
	@RequestMapping(value="/flight/update", method=RequestMethod.POST)
	public String updateFlightSubmit(@ModelAttribute FlightModel updateFl) {
		long id = updateFl.getId();
		flightService.updateFlight(id, updateFl);
		return "update";
	}
	
	@RequestMapping(value="/flight/view", method=RequestMethod.GET)
	public String viewFlight(Model model) {
		List<FlightModel> flights = flightService.getFlight();
		model.addAttribute("flights", flights);
		return "view-Flight";
	}
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.POST, params= {"addRow"})
	private String addRow(@PathVariable(value="licenseNumber") String licenseNumber, @ModelAttribute PilotModel pilot, Model model) {
		pilot.getPilotFlight().add(new FlightModel());
		model.addAttribute("licenseNumber", licenseNumber);
		model.addAttribute("pilot", pilot);
		return "addFlight";
}
}

