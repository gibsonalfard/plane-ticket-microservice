package xyz.ilhamgibran.spring.retailservice.Repository;

import org.springframework.data.repository.CrudRepository;
import xyz.ilhamgibran.spring.retailservice.Model.OrderTicket;

public interface OrderTicketRepository extends CrudRepository<OrderTicket, Integer> {
}
