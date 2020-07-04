package xyz.ilhamgibran.spring.retailservice.Repository;

import org.springframework.data.repository.CrudRepository;
import xyz.ilhamgibran.spring.retailservice.Model.Provider;

public interface ProviderRepository extends CrudRepository<Provider, Integer> {
}
