package com.webApp.CompApp.models;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)// автоинкремент
    private Long id;

    @ManyToOne 
    @JoinColumn(name = "worker_id") 
    private User user;

    @ManyToOne 
    @JoinColumn(name = "workShift_id") 
    private WorkShift workShift;

    @ManyToOne 
    @JoinColumn(name = "compressor_id") 
    private Compressor compressor;


    private double workingTime; 
    private double dewPoint; 
    private double vibration;
    private double oilPressure;
    private double coolantTemp;
    private double gasPollution;
    private boolean inWork;
    private String error;

    public Report(){

    }
    
    public Report( User user, WorkShift workShift, Compressor compressor, double workingTime, double dewPoint, double vibration, double oilPressure, double coolantTemp, double gasPollution, boolean inWork, String error ){
        this.user = user;
        this.workShift = workShift;
        this.compressor = compressor;
        this.workingTime = workingTime;
        this.dewPoint= dewPoint;
        this.vibration = vibration;
        this.oilPressure = oilPressure;
        this.coolantTemp = coolantTemp;
        this.gasPollution = gasPollution;
        this.inWork = inWork;
        this.error = error;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WorkShift getWorkShift() {
        return workShift;
    }

    public void setWorkShift(WorkShift workShift) {
        this.workShift = workShift;
    }

    public Compressor getCompressor() {
        return compressor;
    }

    public void setCompressor(Compressor compressor) {
        this.compressor = compressor;
    }

    public double getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(double workingTime) {
        this.workingTime = workingTime;
    }

    public double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public double getVibration() {
        return vibration;
    }

    public void setVibration(double vibration) {
        this.vibration = vibration;
    }

    public double getOilPressure() {
        return oilPressure;
    }

    public void setOilPressure(double oilPressure) {
        this.oilPressure = oilPressure;
    }

    public double getCoolantTemp() {
        return coolantTemp;
    }

    public void setCoolantTemp(double coolantTemp) {
        this.coolantTemp = coolantTemp;
    }

    public double getGasPollution() {
        return gasPollution;
    }

    public void setGasPollution(double gasPollution) {
        this.gasPollution = gasPollution;
    }

    public boolean isInWork() {
        return inWork;
    }

    public void setInWork(boolean inWork) {
        this.inWork = inWork;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    

    
}
