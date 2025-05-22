package com.webApp.CompApp.controllers;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webApp.CompApp.models.Compressor;
import com.webApp.CompApp.models.Report;
import com.webApp.CompApp.models.WorkShift;
import com.webApp.CompApp.models.Station;
import com.webApp.CompApp.models.User;
import com.webApp.CompApp.services.CompressorService;
import com.webApp.CompApp.services.ReportService;
import com.webApp.CompApp.services.WorkShiftService;
import com.webApp.CompApp.services.UserService;


@Controller
public class ReportController {

    private final UserService userService;
    private final CompressorService compressorService;
    private final WorkShiftService smenaService;
    private final ReportService reportService;
    
    public ReportController(UserService userService, CompressorService compressorService, WorkShiftService smenaService, ReportService reportService) {
        this.userService = userService;
        this.compressorService = compressorService;
        this.smenaService = smenaService;
        this.reportService = reportService;
    }


    @GetMapping("/writeReport")
	public String WriteReport(Model model) {

        User user = userService.GetCurrentUser();
        if (user == null) return "redirect:/";      

        Station station = user.getStation();
        if (station == null) {
            return "redirect:/";
        }

        List<Compressor> compressors = compressorService.findByStationId(station.getId());
        model.addAttribute("compressors", compressors);
		
    	
		return "writeReport";
        
	}

    @PostMapping("/writeReport")
	public String PostWriteReport(@RequestParam Long compressor_id, @RequestParam String coolant_temp, 
		                         @RequestParam String dew_point, @RequestParam String gas_pollution,
	 							  @RequestParam String oil_pressure, @RequestParam String vibration, 
                                  @RequestParam String working_time, @RequestParam boolean in_work,
                                  @RequestParam String error,  Model model) {
    	
        User user = userService.GetCurrentUser();
        WorkShift smena = smenaService.findActiveByWorkerId(user.getId()).orElse(null);
        Compressor compressor = compressorService.findById(compressor_id).orElse(null);
        

        
        try{
            double workingTime = Double.parseDouble(working_time);
            double dewPoint = Double.parseDouble(dew_point);
            double vibrationValue = Double.parseDouble(vibration);
            double oilPressure = Double.parseDouble(oil_pressure);
            double coolantTemp = Double.parseDouble(coolant_temp);
            double gasPollution = Double.parseDouble(gas_pollution);

            if(workingTime < 0) { // Больше предыдущего значения
                model.addAttribute("errorMessage", "Неправильное время работы компрессора");
                return "writeReport"; 
            }

            Report report = new Report(user, smena, compressor, workingTime, dewPoint, vibrationValue, oilPressure, coolantTemp, gasPollution, in_work, error);
            reportService.save(report);
            return "redirect:/";
            
        }                                    
        catch(Exception e){
            model.addAttribute("errorMessage", "Пожалуйста, вводите только числа (например, 12.5 или -3.2)");
            return "writeReport"; 
        }
	}
}
