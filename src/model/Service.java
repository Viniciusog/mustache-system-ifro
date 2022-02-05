package model;

import java.time.Duration;


public class Service {
    private int idService;
    private String name;
    private String description;
    private int durationService;
    private float price;
    
    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationService() {
        return durationService;
    }

    public void setDurationService(int durationService) {
        this.durationService = durationService;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return getName(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
