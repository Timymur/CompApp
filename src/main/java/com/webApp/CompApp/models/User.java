package com.webApp.CompApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)// автоинкремент
    private Long id;

    private String role, name, surname, login, password;

    private boolean inWork;

    @ManyToOne 
    @JoinColumn(name = "station_id") 
    private Station station;

    public User(){

    }
    
    public User( String name, String surname, String login, String password, String role){
        
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role;
        this.inWork = false;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getRole(){
        return role;
    }
    public void setRole(String role){
        this.role = role;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }

    public String getLogin(){
        return login;
    }
    public void setLogin(String login){
        this.login = login;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public Station getStation (){
        return station;
    }

    public void setStation (Station station){
        this.station = station;
    }

    

    public boolean getInWork(){
        return inWork;
    }
    public void setInWork (boolean inWork){
        this.inWork = inWork;
    }
    
    
}
