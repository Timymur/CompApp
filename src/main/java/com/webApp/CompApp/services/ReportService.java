package com.webApp.CompApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webApp.CompApp.models.Compressor;
import com.webApp.CompApp.models.Report;

import com.webApp.CompApp.repo.ReportRepository;

@Service
public class ReportService {
    

    private final ReportRepository reportRepository;


    public ReportService(ReportRepository reportRepository){
        this.reportRepository = reportRepository;
    }

    public List<Report> findAll(){
        return reportRepository.findAll();
    }
   
    public List<Report> findByWorkShiftId(Long workShiftId){
        return reportRepository.findByWorkShift_Id(workShiftId);
    }

    public Report findLastReportByCompressorId(Long compressorId) {
        return reportRepository.findFirstByCompressorIdOrderByIdDesc(compressorId).orElse(null);
    }

    public void save(Report report) {
        reportRepository.save(report);
    }

}
