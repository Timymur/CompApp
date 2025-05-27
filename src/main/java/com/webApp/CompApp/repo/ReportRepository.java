package com.webApp.CompApp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.webApp.CompApp.models.Report;

public interface ReportRepository extends  JpaRepository <Report, Long> {
    

    List<Report> findByWorkShift_Id(Long workShiftId);

    Optional<Report> findFirstByCompressorIdOrderByIdDesc(Long compressorId);

    @Query("SELECT r FROM Report r WHERE r.compressor.id = :compressorId ORDER BY r.id")
    List<Report> findTopByCompressorIdOrderByIdDesc(@Param("compressorId") Long compressorId, PageRequest pageable);
}

