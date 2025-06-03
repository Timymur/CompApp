package com.webApp.CompApp.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import com.webApp.CompApp.models.Report;
import com.webApp.CompApp.models.Compressor;
import com.webApp.CompApp.models.WorkShift;
import com.webApp.CompApp.models.Station;
import com.webApp.CompApp.models.Task;
import com.webApp.CompApp.models.User;
import com.webApp.CompApp.repo.CompressorRepository;
import com.webApp.CompApp.repo.WorkShiftRepository;
import com.webApp.CompApp.repo.StationRepository;
import com.webApp.CompApp.repo.TaskRepository;
import com.webApp.CompApp.repo.UserRepository;
import com.webApp.CompApp.services.CompressorService;
import com.webApp.CompApp.services.ReportService;
import com.webApp.CompApp.services.StationService;
import com.webApp.CompApp.services.TaskService;
import com.webApp.CompApp.services.UserService;

@Controller
public class MainController {

	private final UserService userService;;
	private final CompressorService compressorService;
	private final ReportService reportService;
	private final TaskService taskService;

	public MainController(UserService userService, CompressorService compressorService, ReportService reportService, TaskService taskService) {
		this.userService = userService;
		this.compressorService = compressorService;
		this.reportService = reportService;
		this.taskService = taskService;
    }

	@GetMapping("/")
	public String Home(Model model) {

		User user = userService.GetCurrentUser();
        if (user == null) return "authorization"; 

		model.addAttribute("user", user);

        Station station = user.getStation();
		if (station == null) {
			model.addAttribute("nullStation", "Вы не относитесь ни к одной станции");
			return "home"; 
		}
        
		model.addAttribute("station", station);
		
		List<User> workers = userService.findByStationId(station.getId());

        User boss = null;
        List<User> workersWithoutBoss = new ArrayList<>();
		
        for (User worker : workers) {
            if ("Начальник".equals(worker.getRole())) {
                boss = worker;
				model.addAttribute("boss", boss);
            } else {
                workersWithoutBoss.add(worker);
            }
			if(worker.getInWork()) {
				model.addAttribute("workerOnWorkShift", worker);
			}
        }
		
		List<Compressor> compressors = compressorService.findByStationId(station.getId());

		Map<Long, Report> lastReportsMap = new HashMap<>();
		
		if(compressors == null) model.addAttribute("nullCompressors", "На этой станции нет компрессоров");
		else {
			for (Compressor compressor : compressors) {
    			Report lastReport = reportService.findLastReportByCompressorId(compressor.getId());
    			lastReportsMap.put(compressor.getId(), lastReport);
			}
		
			model.addAttribute("lastReportsMap", lastReportsMap);
			model.addAttribute("compressors", compressors);
		}
        
		List<Task> tasks = taskService.findByStationIdAndStatusFalse(station.getId());

		model.addAttribute("tasks", tasks);
        model.addAttribute("workers", workersWithoutBoss);
        model.addAttribute("station", station);

		model.addAttribute("title", "Главная страница");
		return "home";
	}

	@GetMapping("/logout")
	public String Logout(Model model) {
		model.addAttribute("title", "Выход");
		return "logout";
	}

}