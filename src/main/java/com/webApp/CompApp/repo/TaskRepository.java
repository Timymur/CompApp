package com.webApp.CompApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.webApp.CompApp.models.Task;

public interface TaskRepository extends  JpaRepository <Task, Long> {
    
}
