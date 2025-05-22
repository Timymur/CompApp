package com.webApp.CompApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.webApp.CompApp.models.Compressor;
import com.webApp.CompApp.models.User;

public interface CompressorRepository extends  JpaRepository <Compressor, Long>{
    List<Compressor> findByStation_Id(Long stationId);

    
}
