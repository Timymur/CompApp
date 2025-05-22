package com.webApp.CompApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Compressor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String model;
    private int number;


    @ManyToOne 
    @JoinColumn(name = "station_id") 
    private Station station;

    public Compressor(){

    }
    
    public Compressor(String model, int number, Station station){
        this.model = model;
        this.number = number;
        this.station = station;
        
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getModel(){
        return model;
    }
    public void setModel(String model){
        this.model = model;
    }

    public int getNumber(){
        return number;
    }
    public void setNumber(int number){
        this.number = number;
    }

 
    public Station getStation (){
        return station;
    }

    public void setStation (Station station){
        this.station = station;
    }
    
}
