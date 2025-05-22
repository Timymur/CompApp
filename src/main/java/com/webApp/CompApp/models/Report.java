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


    private double working_time; 
    private double dew_point; 
    private double vibration;
    private double oil_pressure;
    private double coolant_temp;
    private double gas_pollution;
    private boolean inWork;
    private String error;

    public Report(){

    }
    
    public Report( User user, WorkShift workShift, Compressor compressor, double working_time, double dew_point, double vibration, double oil_pressure, double coolant_temp, double gas_pollution, boolean inWork, String error ){
        this.user = user;
        this.workShift = workShift;
        this.compressor = compressor;
        this.working_time = working_time;
        this.dew_point= dew_point;
        this.vibration = vibration;
        this.oil_pressure = oil_pressure;
        this.coolant_temp = coolant_temp;
        this.gas_pollution = gas_pollution;
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

    public void setSmena(WorkShift workShift) {
        this.workShift = workShift;
    }

    public Compressor getCompressor() {
        return compressor;
    }

    public void setCompressor(Compressor compressor) {
        this.compressor = compressor;
    }

    public double getWorking_time() {
        return working_time;
    }

    public void setWorking_time(double working_time) {
        this.working_time = working_time;
    }

    public double getDew_point() {
        return dew_point;
    }

    public void setDew_point(double dew_point) {
        this.dew_point = dew_point;
    }

    public double getVibration() {
        return vibration;
    }

    public void setVibration(double vibration) {
        this.vibration = vibration;
    }

    public double getOil_pressure() {
        return oil_pressure;
    }

    public void setOil_pressure(double oil_pressure) {
        this.oil_pressure = oil_pressure;
    }

    public double getCoolant_temp() {
        return coolant_temp;
    }

    public void setCoolant_temp(double coolant_temp) {
        this.coolant_temp = coolant_temp;
    }

    public double getGas_pollution() {
        return gas_pollution;
    }

    public void setGas_pollution(double gas_pollution) {
        this.gas_pollution = gas_pollution;
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
