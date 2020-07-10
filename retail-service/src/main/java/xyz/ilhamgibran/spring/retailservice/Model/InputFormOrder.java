package xyz.ilhamgibran.spring.retailservice.Model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public class InputFormOrder {
    private int origin;
    private int destination;
    private int flightClass;
    private String departureDate;
    private int adultPass;
    private int childPass;
    private List<String> passagerName;

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

    public int getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(int flightClass) {
        this.flightClass = flightClass;
    }

    public int getAdultPass() {
        return adultPass;
    }

    public void setAdultPass(int adultPass) {
        this.adultPass = adultPass;
    }

    public int getChildPass() {
        return childPass;
    }

    public void setChildPass(int childPass) {
        this.childPass = childPass;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureDateSQLFormat(){
        String month = getDepartureDate().substring(0,2);
        String day = getDepartureDate().substring(3,5);
        String year = getDepartureDate().substring(6,10);

        String departure = year + "-" + month + "-" + day;

        return departure;
    }

    public List<String> getPassagerName() {
        return passagerName;
    }

    public void setPassagerName(List<String> passagerName) {
        this.passagerName = passagerName;
    }
}
