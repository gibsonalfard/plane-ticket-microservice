package xyz.ilhamgibran.spring.retailservice.Repository;

import org.springframework.data.repository.CrudRepository;
import xyz.ilhamgibran.spring.retailservice.Model.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
}
