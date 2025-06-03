package com.webApp.CompApp.controllers;
import com.webApp.CompApp.repo.StationRepository;
import com.webApp.CompApp.repo.UserRepository;
import com.webApp.CompApp.services.CompressorService;
import com.webApp.CompApp.services.ReportService;
import com.webApp.CompApp.services.StationService;
import com.webApp.CompApp.services.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webApp.CompApp.models.Compressor;
import com.webApp.CompApp.models.Report;
import com.webApp.CompApp.models.Station;
import com.webApp.CompApp.models.User;



@Controller
public class StationController {

	private final UserService userService;
    private final StationService stationService;

    public StationController(UserService userService, StationService stationService) {
        this.userService = userService;
        this.stationService = stationService;
    }

	@GetMapping("/addStation")
	public String addStation(Model model) {
		model.addAttribute("title", "Создание станции");
		return "addStation";
	}

	@PostMapping("/addStation")
	public String addStationPost(@RequestParam String city, @RequestParam int number, Model model) {

		List<Station> stations = stationService.findByCity(city);

		for(Station station : stations){
			if(station.getNumber() == number){
				model.addAttribute("errorMessage", "Такая станция уже существует");
				return "addStation"; // возвращаемся на страницу  с ошибкой
			}
		}
		Station station = new Station(city, number);
		stationService.save(station);
		return "redirect:/";
	}

	@GetMapping("/joinToStation")
	public String joinToStation(Model model) {

		List<Station> stations = stationService.findAll(); 
    	model.addAttribute("stations", stations);
		model.addAttribute("title", "Присоединиться к станции");
    	
		return "joinToStation";
	}

	@PostMapping("/joinToStation")
	public String handleJoinToStation(@RequestParam("stationId") Long stationId) {
		
		Station station = stationService.getReferenceById(stationId);
		
    	User user = userService.GetCurrentUser();
        if (user == null) return "redirect:/"; 

		if(user.getStation() == null){
			user.setStation(station);
			userService.save(user);

		}
    	return "redirect:/";
	}

	@GetMapping("/outStation")
	public String outStation(Model model) {

		model.addAttribute("title", "Покинуть станцию");
		return "outStation";
	}

	@PostMapping("/outStation")
	public String postOutStation() {

    	User user = userService.GetCurrentUser();
        if (user == null) return "redirect:/"; 

		if(user.getStation() != null){
			user.setStation(null);
			userService.save(user);

		}
    	return "redirect:/";
	}

}

