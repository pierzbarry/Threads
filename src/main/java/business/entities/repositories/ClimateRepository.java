package business.entities.repositories;

import business.entities.Climate;
import org.springframework.data.repository.CrudRepository;

public interface ClimateRepository extends CrudRepository<Climate, Long> {
    Climate findByName(String name);
}