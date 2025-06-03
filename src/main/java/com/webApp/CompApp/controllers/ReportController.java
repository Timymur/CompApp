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
    private final WorkShiftService workShiftService;
    private final ReportService reportService;
    
    public ReportController(UserService userService, CompressorService compressorService, WorkShiftService workShiftService, ReportService reportService) {
        this.userService = userService;
        this.compressorService = compressorService;
        this.workShiftService = workShiftService;
        this.reportService = reportService;
    }


    @GetMapping("/writeReport")
	public String writeReport(Model model) {

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
	public String postWriteReport(@RequestParam Long compressorId, @RequestParam String coolantTemp, 
		                         @RequestParam String dewPoint, @RequestParam String gasPollution,
	 							  @RequestParam String oilPressure, @RequestParam String vibration, 
                                  @RequestParam String workingTime, @RequestParam boolean inWork,
                                  @RequestParam String error,  Model model) {
    	
        User user = userService.GetCurrentUser();
        WorkShift workShift = workShiftService.findActiveByWorkerId(user.getId()).orElse(null);
        Compressor compressor = compressorService.findById(compressorId).orElse(null);
        model.addAttribute("compressor", compressor);
        
        try{
            Double workingTimeValue = Double.parseDouble(workingTime);
            Double dewPointValue = Double.parseDouble(dewPoint);
            Double vibrationValue = Double.parseDouble(vibration);
            Double oilPressureValue = Double.parseDouble(oilPressure);
            Double coolantTempValue = Double.parseDouble(coolantTemp);
            Double gasPollutionValue = Double.parseDouble(gasPollution);

            Double lastReportWorkingTime = null;
            Report lastReport = reportService.findLastReportByCompressorId(compressorId);
            if (lastReport != null) {
                 lastReportWorkingTime = lastReport.getWorkingTime();
            }
            
            if( (lastReportWorkingTime != null) &&(workingTimeValue < lastReportWorkingTime)) { // Больше предыдущего значения
                model.addAttribute("errorMessage", "Неправильное время работы компрессора");
                return "writeReport"; 
            }
            if((oilPressureValue < 0) || (vibrationValue < 0) || (gasPollutionValue < 0) || (workingTimeValue < 0)){
                model.addAttribute("errorMessage", "Вибрация, время работы компрессора,  давление или загазованность не могут быть меньше нуля");
                return "writeReport"; 
            }

            Report report = new Report(user, workShift, compressor, workingTimeValue, dewPointValue, vibrationValue, oilPressureValue, coolantTempValue, gasPollutionValue, inWork, error);
            reportService.save(report);
            return "redirect:/";
            
        }                                    
        catch(Exception e){
            model.addAttribute("errorMessage", "Пожалуйста, вводите только числа (например, 12.5 или -3.2)");
            return "writeReport"; 
        }
	}
}
