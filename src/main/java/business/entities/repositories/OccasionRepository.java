package business.entities.repositories;

import business.entities.Occasion;
import org.springframework.data.repository.CrudRepository;

public interface OccasionRepository extends CrudRepository<Occasion, Long> {
    Occasion findByName(String name);
}