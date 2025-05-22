package com.webApp.CompApp.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.webApp.CompApp.models.Compressor;
import com.webApp.CompApp.models.WorkShift;
import com.webApp.CompApp.models.User;
import com.webApp.CompApp.repo.CompressorRepository;
import com.webApp.CompApp.repo.WorkShiftRepository;
import com.webApp.CompApp.repo.UserRepository;

@Service
public class WorkShiftService {
    
    private final WorkShiftRepository workShiftRepository;


    public WorkShiftService(WorkShiftRepository workShiftRepository){
        this.workShiftRepository = workShiftRepository;
    }

    public List<WorkShift> findAll(){
        return workShiftRepository.findAll();
    }
    public List<WorkShift> findByStationId(Long stationId){
        return workShiftRepository.findByStation_Id(stationId);
    }
    public List<WorkShift> findByWorkerId(Long workerId){
        return workShiftRepository.findByUser_Id(workerId);
    }

    public Optional<WorkShift> findActiveByWorkerId(Long workerId) {
        return workShiftRepository.findActiveWorkShiftByWorker(workerId);
    }

    public void save(WorkShift workShift) {
        workShiftRepository.save(workShift);
    }
}
