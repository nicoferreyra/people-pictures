package peoplepictures.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peoplepictures.model.Person;
import peoplepictures.model.Role;
import peoplepictures.populators.PeopleDatabasePopulator;
import peoplepictures.repositories.RoleRepository;
import peoplepictures.utils.ResultPaginator;

import java.io.IOException;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PeopleDatabasePopulator peopleDatabasePopulator;
    @Autowired
    private ResultPaginator resultPaginator;

    public List read(Integer limit, Integer start){
        List list =  this.roleRepository.findAll();
        return resultPaginator.paginateResults(list, limit, start);
    }

    public List read(String roleName, Integer limit, Integer start){
        List list = this.roleRepository.findByName(roleName.replace("-"," "));
        return resultPaginator.paginateResults(list, limit, start);
    }

    public void create() throws IOException {
        this.peopleDatabasePopulator.populateRole(this.roleRepository);
    }
}
