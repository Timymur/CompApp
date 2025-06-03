package com.webApp.CompApp.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;


@Entity
public class WorkShift {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)// автоинкремент
    private Long id;

    private String timeOfDay; 
    private LocalDate date; // 2025-05-02

    private boolean active;

    @ManyToOne 
    @JoinColumn(name = "user_id") 
    private User user;

    @ManyToOne 
    @JoinColumn(name = "station_id") 
    private Station station;

    public WorkShift(){

    }
    
    public WorkShift(String timeOfDay, LocalDate date, Station station, User user){
        this.timeOfDay = timeOfDay;
        this.date = date;
        this.station = station;
        this.user = user;
        this.active = true;
        
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public boolean getActive(){
        return active;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public String getTimeOfDay(){
        return timeOfDay;
    }
    public void setTimeOfDay(String timeOfDay){
        this.timeOfDay = timeOfDay;
    }

    public LocalDate getDate(){
        return date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public Station getStation (){
        return station;
    }

    public void setStation (Station station){
        this.station = station;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }
    
}
