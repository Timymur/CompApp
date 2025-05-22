package com.webApp.CompApp.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.webApp.CompApp.models.Compressor;
import com.webApp.CompApp.models.Station;
import com.webApp.CompApp.models.User;
import com.webApp.CompApp.repo.CompressorRepository;
import com.webApp.CompApp.repo.StationRepository;
import com.webApp.CompApp.repo.UserRepository;


@Service
public class StationService {

    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository){
        this.stationRepository = stationRepository;
    }

    public List<Station> findAll(){
        return stationRepository.findAll();
    }
    public List<Station> findByCity(String city){
        return stationRepository.findByCity(city);
    }

    public void save(Station station) {
        stationRepository.save(station);
    }

    public Station getReferenceById(long stationId){
        return stationRepository.getReferenceById(stationId);
    }
    

}





