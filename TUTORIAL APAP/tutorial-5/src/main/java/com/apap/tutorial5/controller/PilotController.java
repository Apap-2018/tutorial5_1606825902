package com.apap.tutorial5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.FlightService;
import com.apap.tutorial5.service.PilotService;

/*
 * PilotController
 */
@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@Autowired
	private FlightService flightService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value="/pilot/add", method=RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value="/pilot/add", method=RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	@RequestMapping(value = "/pilot/view", method=RequestMethod.GET)
	private String viewPilot(@RequestParam(value="licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		List<FlightModel> flights = flightService.getFlightDetailByPilot(pilot.getLicenseNumber());
		model.addAttribute("flights", flights);
		model.addAttribute("pilot", pilot);
		return "view-pilot";
	}
	
	@RequestMapping(value="/pilot/delete/{licenseNumber}", method=RequestMethod.GET)
	public String deletePilot(@PathVariable(value="licenseNumber") String licenseNumber, Model model) {
		pilotService.deletePilot(licenseNumber);
		return "deletePage";
	}
	
	@RequestMapping(value="/pilot/update/{licenseNumber}", method=RequestMethod.GET)
	public String updatePilot(@PathVariable(value="licenseNumber") String licenseNumber, Model model) {
		model.addAttribute("newpilot", new PilotModel());
		PilotModel Pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("pilot", Pilot);
		return "updatePilot";
	}
	
	@RequestMapping(value="/pilot/update", method=RequestMethod.POST)
	public String updatePilotSubmit(@ModelAttribute PilotModel newpilot) {
		String licenseNumber = newpilot.getLicenseNumber();
		pilotService.pilotUpdate(licenseNumber, newpilot);
		return "update";
	}
}
