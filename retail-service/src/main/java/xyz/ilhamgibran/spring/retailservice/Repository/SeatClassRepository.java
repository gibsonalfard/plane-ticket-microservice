package xyz.ilhamgibran.spring.retailservice.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import xyz.ilhamgibran.spring.retailservice.Model.City;
import xyz.ilhamgibran.spring.retailservice.Model.SeatClass;

public interface SeatClassRepository extends CrudRepository<SeatClass, Integer> {
    @Query(value = "SELECT * FROM seat_class WHERE class_id = ?1", nativeQuery = true)
    public SeatClass getSeatClassById(int id);
}
