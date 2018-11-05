package peoplepictures.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peoplepictures.model.Role;
import peoplepictures.populators.PeopleDatabasePopulator;
import peoplepictures.repositories.RoleRepository;

import java.io.IOException;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PeopleDatabasePopulator peopleDatabasePopulator;

    public Iterable<Role> read(){
        return this.roleRepository.findAll();
    }

    public void create() throws IOException {
        this.peopleDatabasePopulator.populateRole(this.roleRepository);
    }
}
