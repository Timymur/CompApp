package com.webApp.CompApp.controllers;

import java.util.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;


import com.webApp.CompApp.models.Compressor;
import com.webApp.CompApp.models.WorkShift;
import com.webApp.CompApp.models.Station;
import com.webApp.CompApp.models.Task;
import com.webApp.CompApp.models.User;
import com.webApp.CompApp.models.Report;
import com.webApp.CompApp.repo.CompressorRepository;
import com.webApp.CompApp.repo.WorkShiftRepository;
import com.webApp.CompApp.repo.StationRepository;
import com.webApp.CompApp.repo.TaskRepository;
import com.webApp.CompApp.repo.UserRepository;
import com.webApp.CompApp.services.CompressorService;
import com.webApp.CompApp.services.ReportService;
import com.webApp.CompApp.services.StationService;
import com.webApp.CompApp.services.UserService;
import com.webApp.CompApp.services.WorkShiftService;

@Controller
public class WorkShiftController {
    
    private final UserService userService;
    // private final StationService stationService;
	private final CompressorService compressorService;
    private final WorkShiftService workShiftService;
    private final ReportService reportService;

	public WorkShiftController(UserService userService, StationService stationService, CompressorService compressorService, WorkShiftService workShiftService, ReportService reportService) {
    this.userService = userService;
    // this.stationService = stationService;
	this.compressorService = compressorService;
    this.workShiftService = workShiftService;
    this.reportService = reportService;
    }


    @GetMapping("/openWorkShift")
	public String openWorkShift(Model model) {

        User user = userService.GetCurrentUser();
        if ((user == null) || (user.getInWork())) return "redirect:/"; 

		model.addAttribute("title", "Открытие смены");
		return "openWorkShift";
	}

    @PostMapping("/openWorkShift")
	public String postOpenWorkShift(@RequestParam LocalDate date, @RequestParam String timeOfDay, Model model) {

        User user = userService.GetCurrentUser();
        if (user == null) return "redirect:/"; 
        
        Station station = user.getStation();
        if (station == null) return "redirect:/"; 
            
        List<User> users = userService.findByStationId(station.getId());

        for(User worker : users){
            if(worker.getInWork()) {
                model.addAttribute("errorMessage", "Дождитесь окончания смены");
			    return "openWorkShift"; 
            }
        }

        List<WorkShift> workShifts = workShiftService.findByStationId(station.getId());
        for( WorkShift workShift : workShifts){
            if((workShift.getDate().equals(date) ) && (workShift.getTimeOfDay().equals(timeOfDay) )){
                 model.addAttribute("errorMessage", "Такая смена уже закрыта");
			    return "openWorkShift"; 
            }
        }

        user.setInWork(true); // Машинист на смене.
        userService.save(user);

        WorkShift newWorkShift = new WorkShift(timeOfDay, date, station, user); //Создали смену
        workShiftService.save(newWorkShift);

        return "redirect:/";

	}

    @GetMapping("/closeWorkShift")
	public String closeWorkShift(Model model) {

        User user = userService.GetCurrentUser();
        
        if ((user == null) || (!user.getInWork())) {
            return "redirect:/"; 
        }
		model.addAttribute("title", "Закрытие смены");
		return "closeWorkShift";
	}

    @PostMapping("/closeWorkShift")
	public String postCloseWorkShift(Model model) {

        User user = userService.GetCurrentUser();
        if (user == null) return "redirect:/"; 
        
        Station station = user.getStation();
        if (station == null) return "redirect:/"; 

        List<Compressor> compressors = compressorService.findByStationId(station.getId());
        WorkShift workShift = workShiftService.findActiveByWorkerId(user.getId()).orElse(null);
        List<Report> reports = reportService.findByWorkShiftId(workShift.getId());

        if(compressors.size() > reports.size()){
            model.addAttribute("errorMessage", "Перед закрытием смены внести показания по всем компрессорам");
		    return "closeWorkShift";
        }
        workShift.setActive(false);
        workShiftService.save(workShift);

        user.setInWork(false);
        userService.save(user);

        return "redirect:/";
	}

}
