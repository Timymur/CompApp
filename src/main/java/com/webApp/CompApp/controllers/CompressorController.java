package com.webApp.CompApp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webApp.CompApp.models.Compressor;
import com.webApp.CompApp.models.Report;
import com.webApp.CompApp.models.Station;
import com.webApp.CompApp.models.User;
import com.webApp.CompApp.models.WorkShift;
import com.webApp.CompApp.repo.CompressorRepository;
import com.webApp.CompApp.repo.StationRepository;
import com.webApp.CompApp.repo.UserRepository;
import com.webApp.CompApp.services.UserService;
import com.webApp.CompApp.services.CompressorService;
import com.webApp.CompApp.services.ReportService;
import com.webApp.CompApp.services.WorkShiftService;


@Controller
public class CompressorController {


    private final UserService userService;
    private final CompressorService compressorService;
    private final ReportService reportService;
    private final WorkShiftService workShiftService;

    
    public CompressorController(UserService userService, CompressorService compressorService, ReportService reportService, WorkShiftService workShiftService) {
        this.userService = userService;
        this.compressorService = compressorService;
        this.reportService = reportService;
        this.workShiftService = workShiftService;
    }

    

    @GetMapping("/compressor")
	public String Compressor(Model model) {
        Iterable<Compressor> compressors = compressorService.findAll();
		model.addAttribute("compressors", compressors);
		return "compressor";
	}

    @GetMapping("/addCompressor")
	public String AddCompressor(Model model) {
        
		model.addAttribute("title", "Добавить компрессор");
		return "addCompressor";
	}

    @PostMapping("/addCompressor")
	public String AddCompressorPost(@RequestParam String modelCompr, @RequestParam int number, Model model) {

        User user = userService.GetCurrentUser();
        if (user == null) return "redirect:/"; 

        Station station = user.getStation();
        if (station == null)   return "redirect:/";
        

        List<Compressor> compressors = compressorService.findByStationId(station.getId());

        for (Compressor compressor : compressors){
            if(compressor.getNumber() == number){
                model.addAttribute("errorMessage", "Компрессор с таким номером уже существует");
				return "addCompressor"; // возвращаемся на страницу  с ошибкой
            }
        }

        Compressor compressor = new Compressor(modelCompr, number, station);
        compressorService.save(compressor);

		return "redirect:/";
		
	}

    @GetMapping("/choiceCompressor")
	public String choiceCompressor(Model model) {

        User user = userService.GetCurrentUser();
        if (user == null) return "redirect:/"; 

        if(!user.getInWork())return "redirect:/"; 

        Station station = user.getStation();
        if (station == null)   return "redirect:/";
        

        List<Compressor> compressors = compressorService.findByStationId(station.getId());
        model.addAttribute("compressors", compressors);
		
    	
		return "choiceCompressor";
        
	}

    @PostMapping("/choiceCompressor")
	public String handlChoiceCompressor(@RequestParam("compressorId") Long compressorId, Model model) {
		
    	User user = userService.GetCurrentUser();
        if (user == null) return "redirect:/"; 

		WorkShift workShift = workShiftService.findActiveByWorkerId(user.getId()).orElse(null);
        
        List<Report> reports = reportService.findByWorkShiftId(workShift.getId());

        boolean alreadyExists = reports.stream()
            .anyMatch(report -> report.getCompressor().getId().equals(compressorId));

        if (alreadyExists) {
            Station station = user.getStation();
            if (station == null)   return "redirect:/";
        
            List<Compressor> compressors = compressorService.findByStationId(station.getId());
            model.addAttribute("compressors", compressors);
		
            model.addAttribute("errorMessage", "Показания на этот компрессор уже составлены");
            return "choiceCompressor"; 
        }

        Compressor compressor = compressorService.findById(compressorId).orElse(null);
        model.addAttribute("compressor", compressor);
        return "writeReport";
	}

    @GetMapping("/compressor/{id}")
    public String compressorReport(@PathVariable(value="id") Long compressorId, Model model) {
        
        Compressor compressor = compressorService.findById(compressorId).orElse(null);

        List <Report> reports = reportService.findTop15ReportsByCompressorId(compressorId);

        model.addAttribute("reports", reports);
        model.addAttribute("compressor", compressor);

        return "compressor";
    }

}
