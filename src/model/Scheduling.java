
package model;

public class Scheduling {
    
    private int id;
    private Service service;
    private String date, startTime, endTime;
    private Employee functionary;
    private Client client;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Employee getFunctionary() {
        return functionary;
    }

    public void setFunctionary(Employee functionary) {
        this.functionary = functionary;
    }   

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return (startTime + " - " + endTime); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
