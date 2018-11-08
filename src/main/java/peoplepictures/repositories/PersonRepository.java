package peoplepictures.repositories;

import peoplepictures.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findAll();

    List findByName(String name);

    List findByNameAndRoleAndCity(String name, String role, String city);

    List findByRoleAndCity(String role, String city);

    List findByRole(String role);

    List findByCity(String city);
}
