package repositories;

import model.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by nferreyra on 30/10/18.
 */
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
