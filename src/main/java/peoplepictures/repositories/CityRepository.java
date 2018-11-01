package peoplepictures.repositories;

import org.springframework.data.repository.CrudRepository;
import peoplepictures.model.City;

import java.util.List;

/**
 * Created by nferreyra on 31/10/18.
 */
public interface CityRepository extends CrudRepository<City, Integer> {
    List<City> findByName(String name);
}
