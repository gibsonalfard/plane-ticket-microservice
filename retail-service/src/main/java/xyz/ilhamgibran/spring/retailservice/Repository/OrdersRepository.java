package xyz.ilhamgibran.spring.retailservice.Repository;

import org.springframework.data.repository.CrudRepository;
import xyz.ilhamgibran.spring.retailservice.Model.Orders;

public interface OrdersRepository extends CrudRepository<Orders, Integer> {

}
