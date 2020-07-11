package xyz.ilhamgibran.spring.retailservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.ilhamgibran.spring.retailservice.Model.Ticket;
import xyz.ilhamgibran.spring.retailservice.Model.TicketSeat;
import xyz.ilhamgibran.spring.retailservice.Repository.TicketRepository;
import xyz.ilhamgibran.spring.retailservice.Repository.TicketSeatRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class APIController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketSeatRepository ticketSeatRepository;

    @RequestMapping(value = "/ticket/get", method = RequestMethod.GET)
    public Ticket giveTicket(@RequestParam int origin
            , @RequestParam int des
            , @RequestParam(name="class") int flightClass
            , @RequestParam String date
            , @RequestParam int passager){
        Ticket ticket = ticketRepository.searchStockAvailability(date, origin, des, flightClass, passager);

        if (ticket != null){
            List<TicketSeat> seat = ticketSeatRepository.getSeatByStatus(ticket.getTicketId(), 1);
            for(int i=0; i < passager; i++){
                // System booking
                seat.get(i).setAvailability(2);
                ticketSeatRepository.save(seat.get(i));
            }
        }

        return ticket;
    }

    @RequestMapping(value = "/ticket/order", method = RequestMethod.POST)
    public int orderTicket(@RequestBody int origin
            , @RequestBody int destination
            , @RequestBody int flight_class
            , @RequestBody String date
            , @RequestBody String[] passanger_name){
        System.out.println("Passager Name Number 1 : " + passanger_name[0]);
        return 0;
    }
}
