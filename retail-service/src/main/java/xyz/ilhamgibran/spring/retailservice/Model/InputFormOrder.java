package xyz.ilhamgibran.spring.retailservice.Model;

public class InputFormOrder {
    private int origin;
    private int destination;
    private int flightClass;
    private int adultPass;
    private int childPass;

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
}
