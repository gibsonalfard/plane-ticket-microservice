package xyz.ilhamgibran.spring.retailservice.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import xyz.ilhamgibran.spring.retailservice.Model.City;

public interface CityRepository extends CrudRepository<City, Integer> {
    @Query(value = "SELECT * FROM city WHERE city_id = ?1", nativeQuery = true)
    public City getCityById(int id);
}
