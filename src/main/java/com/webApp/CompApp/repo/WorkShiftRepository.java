package com.webApp.CompApp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.webApp.CompApp.models.Compressor;
import com.webApp.CompApp.models.WorkShift;

public interface WorkShiftRepository extends  JpaRepository <WorkShift, Long> {
    List<WorkShift> findByStation_Id(Long stationId);
    List<WorkShift> findByUser_Id(Long workerId);

    @Query("SELECT s FROM WorkShift s WHERE s.user.id = :userId AND s.active = true")
    Optional<WorkShift> findActiveWorkShiftByWorker(@Param("userId") Long userId);
}
