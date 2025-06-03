package com.webApp.CompApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webApp.CompApp.models.Task;

public interface TaskRepository extends  JpaRepository <Task, Long> {
    
    List<Task> findByStation_Id(Long stationId);

    List<Task> findByStation_IdAndStatusFalse(Long stationId);

    List<Task> findByStation_IdAndStatusTrue(Long stationId);
}
