package peoplepictures.repositories;

import org.springframework.data.repository.CrudRepository;
import peoplepictures.model.City;
import peoplepictures.model.Role;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Integer> {
    List<City> findAll();
    List<City> findByName(String name);
}
