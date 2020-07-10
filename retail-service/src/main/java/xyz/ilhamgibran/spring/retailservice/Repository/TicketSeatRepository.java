package xyz.ilhamgibran.spring.retailservice.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import xyz.ilhamgibran.spring.retailservice.Model.TicketSeat;

import java.util.List;

public interface TicketSeatRepository extends CrudRepository<TicketSeat, Integer> {
    @Query(value = "SELECT * FROM ticket_seat WHERE availability = 1 and ticket_id = ?1", nativeQuery = true)
    public List<TicketSeat> getAvailableSeat(int ticketId);
}
