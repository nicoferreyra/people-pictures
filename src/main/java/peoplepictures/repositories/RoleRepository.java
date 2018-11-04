package peoplepictures.repositories;

import org.springframework.data.repository.CrudRepository;
import peoplepictures.model.Role;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    List findAll();
    List findByName(String name);
}
