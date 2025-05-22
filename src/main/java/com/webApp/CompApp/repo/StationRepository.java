package com.webApp.CompApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.webApp.CompApp.models.Station;
import java.util.List;


public interface StationRepository extends  JpaRepository <Station, Long> {
    List<Station> findByCity(String city);
}
