package com.webApp.CompApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webApp.CompApp.models.Compressor;
import com.webApp.CompApp.models.Task;

import com.webApp.CompApp.repo.TaskRepository;


@Service
public class TaskService {

     private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Optional<Task> findById(Long taskId){
        return taskRepository.findById(taskId);
    }

    public List<Task> findByStationId(Long stationId){
        return taskRepository.findByStation_Id(stationId);
    }

    public List<Task> findByStationIdAndStatusTrue(Long stationId){
        return taskRepository.findByStation_IdAndStatusTrue(stationId);
    }
    public List<Task> findByStationIdAndStatusFalse(Long stationId){
        return taskRepository.findByStation_IdAndStatusFalse(stationId);
    }

    public void save(Task task) {
        taskRepository.save(task);
    }


}
