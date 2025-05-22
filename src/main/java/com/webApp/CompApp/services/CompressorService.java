package com.webApp.CompApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webApp.CompApp.models.Compressor;
import com.webApp.CompApp.models.User;
import com.webApp.CompApp.repo.CompressorRepository;
import com.webApp.CompApp.repo.UserRepository;

@Service
public class CompressorService {

    private final CompressorRepository compressorRepository;


    public CompressorService(CompressorRepository compressorRepository){
        this.compressorRepository = compressorRepository;
    }

    public Optional<Compressor> findById(Long compressorId){
        return compressorRepository.findById(compressorId);
    }
    public List<Compressor> findAll(){
        return compressorRepository.findAll();
    }
    public List<Compressor> findByStationId(Long stationId){
        return compressorRepository.findByStation_Id(stationId);
    }

    public void save(Compressor compressor) {
        compressorRepository.save(compressor);
    }

}



