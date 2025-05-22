package com.webApp.CompApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Station {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)// автоинкремент
    private Long id;

    private String city;
    private int number;

    public Station(){

    }
    
    public Station(String city, int number){
        this.city = city;
        this.number = number;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city = city;
    }

    public int getNumber(){
        return number;
    }
    public void setNumber(int number){
        this.number = number;
    }
    
}
