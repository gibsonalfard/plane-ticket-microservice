package xyz.ilhamgibran.spring.retailservice.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import xyz.ilhamgibran.spring.retailservice.Model.TicketSeat;

import java.util.List;

public interface TicketSeatRepository extends CrudRepository<TicketSeat, Integer> {
    @Query(value = "SELECT * FROM ticket_seat WHERE ticket_id = ?1 AND availability = ?2", nativeQuery = true)
    public List<TicketSeat> getSeatByStatus(int ticketId, int status);
}
