package xyz.ilhamgibran.spring.retailservice.Model;

import javax.persistence.*;

@Entity
public class TicketSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatId;

    @ManyToOne
    @JoinColumn(name = "ticketId")
    private Ticket ticket;
    private char seatRow;
    private int seatCols;
    private int availability;

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public char getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(char seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatCols() {
        return seatCols;
    }

    public void setSeatCols(int seatCols) {
        this.seatCols = seatCols;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
}
