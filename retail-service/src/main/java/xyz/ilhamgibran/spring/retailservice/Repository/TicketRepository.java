package xyz.ilhamgibran.spring.retailservice.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import xyz.ilhamgibran.spring.retailservice.Model.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    @Query(value = "SELECT * FROM ticket WHERE flight_date = ?1 " +
            "AND origin = ?2 AND destination = ?3 AND seat_class = ?4", nativeQuery = true)
    public Ticket searchAvailability(String departure, int ori, int des, int seat);

    @Query(value = "SELECT * FROM ticket WHERE flight_date = ?1 "
            + "AND origin = ?2 AND destination = ?3 AND seat_class = ?4 AND stock >= ?5", nativeQuery = true)
    public Ticket searchStockAvailability(String departure, int ori, int des, int seat, int demand);
}
