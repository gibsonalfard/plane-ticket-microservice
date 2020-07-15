package xyz.ilhamgibran.spring.retailservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import xyz.ilhamgibran.spring.retailservice.Model.Ticket;
import xyz.ilhamgibran.spring.retailservice.Model.TicketSeat;
import xyz.ilhamgibran.spring.retailservice.Repository.TicketRepository;
import xyz.ilhamgibran.spring.retailservice.Repository.TicketSeatRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class AsyncController {

    @Autowired
    private TicketSeatRepository ticketSeatRepository;

    @Async("processExecutor")
    public CompletableFuture wait10sec(List<Integer> seat) throws InterruptedException {
        // Artificial delay of 1s for demonstration purposes
//        for(int x=0;x<10000000;x++){
//            x+=1;
//        }
        TimeUnit.SECONDS.sleep(60);
        boolean unchange = false;
        for(int i=0;i<seat.size();i++){
            Optional<TicketSeat> ticket = ticketSeatRepository.findById(seat.get(i));
            if(ticket.get().getAvailability() == 2){
                unchange = true;
                break;
            }
        }

        if(unchange){
            System.out.println("Unchanged");
            for(int i=0;i<seat.size();i++){
                Optional<TicketSeat> ticket = ticketSeatRepository.findById(seat.get(i));
                ticket.get().setAvailability(1);
                ticketSeatRepository.save(ticket.get());
            }
        }

        return CompletableFuture.completedFuture(seat);
    }
}
