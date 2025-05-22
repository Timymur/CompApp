package com.webApp.CompApp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.webApp.CompApp.models.Report;

public interface ReportRepository extends  JpaRepository <Report, Long> {
    

    List<Report> findByWorkShift_Id(Long workShiftId);

    Optional<Report> findFirstByCompressorIdOrderByIdDesc(Long compressorId);
}

