package xyz.ilhamgibran.spring.retailservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.ilhamgibran.spring.retailservice.Model.*;
import xyz.ilhamgibran.spring.retailservice.Repository.OrderTicketRepository;
import xyz.ilhamgibran.spring.retailservice.Repository.OrdersRepository;
import xyz.ilhamgibran.spring.retailservice.Repository.TicketRepository;
import xyz.ilhamgibran.spring.retailservice.Repository.TicketSeatRepository;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/api")
public class APIController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketSeatRepository ticketSeatRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderTicketRepository orderTicketRepository;

    @Autowired
    private AsyncController asyncController;

    private CompletableFuture thread;

    @RequestMapping(value = "/ticket/get", method = RequestMethod.GET)
    public Ticket giveTicket(@RequestParam int origin
            , @RequestParam int des
            , @RequestParam(name="class") int flightClass
            , @RequestParam String date
            , @RequestParam int passager){
        Ticket ticket = ticketRepository.searchStockAvailability(date, origin, des, flightClass, passager);
        List<Integer> seatList = new ArrayList<>();

        if (ticket != null){
            List<TicketSeat> seat = ticketSeatRepository.getSeatByStatus(ticket.getTicketId(), 1);
            for(int i=0; i < passager; i++){
                // System booking
                seat.get(i).setAvailability(2);
                seatList.add(seat.get(i).getSeatId());
                ticketSeatRepository.save(seat.get(i));
            }
        }

        try {
            thread = asyncController.wait10sec(seatList);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ticket;
    }

    @RequestMapping(value = "/ticket/order", method = RequestMethod.POST)
    public int orderTicket(@RequestBody RequestTicket input){
        if(input != null){
            System.out.println("Tanggal : " +  input.getDate());
            System.out.println("Passager Name Number 1 : " + input.getPassagerName().get(0));

            Ticket ticket = ticketRepository.searchAvailability(input.getDate()
                    ,input.getOrigin(),input.getDestination(), input.getFlightClass());
            double amount = input.getPassagerName().size()*ticket.getPrice();

            // Save Order
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            java.sql.Date now = new Date(Calendar.getInstance().getTime().getTime());
            Orders myOrder = new Orders(now, amount);
            ordersRepository.save(myOrder);

            List<TicketSeat> seat = ticketSeatRepository.getSeatByStatus(ticket.getTicketId(), 2);
            int idx = 0;
            for(String name : input.getPassagerName()){
                orderTicketRepository.save(new OrderTicket(myOrder, seat.get(idx), name));
                seat.get(idx).setAvailability(0);
                ticketSeatRepository.save(seat.get(idx));
                idx += 1;
            }

            ticket.setStock(ticket.getStock()-input.getPassagerName().size());
            ticketRepository.save(ticket);
        }else{
            return 25;
        }
        return 0;
    }
}
