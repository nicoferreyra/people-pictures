package peoplepictures.repositories;

import org.springframework.data.repository.CrudRepository;
import peoplepictures.model.Role;

import java.util.List;

/**
 * Created by nferreyra on 31/10/18.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
    List<Role> findByName(String name);
}