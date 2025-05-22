package com.webApp.CompApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)// автоинкремент
    private Long id;

    @ManyToOne 
    @JoinColumn(name = "worker_id") 
    private User worker;

    @ManyToOne 
    @JoinColumn(name = "boss_id") 
    private User boss;

    private String theme, text;

    private boolean status;

    public Task (){

    }
    public Task(User worker, User boss, String theme, String text) {
        this.worker = worker;
        this.boss = boss;
        this.theme = theme;
        this.text = text;
        this.status = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getWorker() {
        return worker;
    }

    public void setWorker(User worker) {
        this.worker = worker;
    }

    public User getBoss() {
        return boss;
    }

    public void setBoss(User boss) {
        this.boss = boss;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getStatus(){
        return status;
    }
    
    public void setStatus(boolean status){
        this.status = status;
    }


}
