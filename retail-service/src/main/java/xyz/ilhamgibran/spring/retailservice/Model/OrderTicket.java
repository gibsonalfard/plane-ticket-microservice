package xyz.ilhamgibran.spring.retailservice.Model;

import javax.persistence.*;

@Entity
public class OrderTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int orderTickerId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "seatId")
    private TicketSeat ticket;
    private String passagerName;

    public OrderTicket(Orders order, TicketSeat ts, String passagerName){
        this.order = order;
        this.ticket = ts;
        this.passagerName = passagerName;
    }

    public int getOrderTickerId() {
        return orderTickerId;
    }

    public void setOrderTickerId(int orderTickerId) {
        this.orderTickerId = orderTickerId;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }
}
