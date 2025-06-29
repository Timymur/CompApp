package com.webApp.CompApp.services;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    public List<Report> findTop15ReportsByCompressorId(Long compressorId){
        
        PageRequest pageable = PageRequest.of(0, 15);
        List<Report> reports = reportRepository.findTopByCompressorIdOrderByIdDesc(compressorId, pageable);
        return reports;
    }

    public List<Report> findTop15ReportsByCompressorIdWithShift(Long compressorId){
        List<Report> reports = reportRepository.findTop15ReportsByCompressorIdWithShift(compressorId);
        return reports;
    }


}
