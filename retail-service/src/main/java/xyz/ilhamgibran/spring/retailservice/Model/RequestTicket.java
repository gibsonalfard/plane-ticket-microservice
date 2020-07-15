package xyz.ilhamgibran.spring.retailservice.Model;

import java.util.List;

public class RequestTicket {
    private int origin;
    private int destination;
    private String date;
    private int flightClass;
    private List<String> passagerName;

    public RequestTicket(){
        super();
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(int flightClass) {
        this.flightClass = flightClass;
    }

    public List<String> getPassagerName() {
        return passagerName;
    }

    public void setPassagerName(List<String> passagerName) {
        this.passagerName = passagerName;
    }
}
