package peoplepictures.repositories;

import peoplepictures.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findAll();

    List<Person> findByName(String name);

    List<Person> findByNameAndRoleAndCity(String name, String role, String city);

    List<Person> findByRoleAndCity(String role, String city);

    List<Person> findByRole(String role);

    List<Person> findByCity(String city);
}
