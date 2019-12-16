package business.entities.repositories;

import business.entities.Wind;
import org.springframework.data.repository.CrudRepository;

public interface WindRepository extends CrudRepository<Wind, Long> {
    Wind findByName(String name);
}