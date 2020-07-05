package xyz.ilhamgibran.spring.retailservice.Repository;

import org.springframework.data.repository.CrudRepository;
import xyz.ilhamgibran.spring.retailservice.Model.City;

public interface CityRepository extends CrudRepository<City, Integer> {
}
