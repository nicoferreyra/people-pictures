package peoplepictures.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peoplepictures.model.Person;
import peoplepictures.populators.PeopleDatabasePopulator;
import peoplepictures.repositories.PersonRepository;

import java.io.IOException;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PeopleDatabasePopulator peopleDatabasePopulator;

    public Iterable<Person> read(){
        return this.personRepository.findAll();
    }

    public List<Person> readByRole(String roleName){
        return this.personRepository.findByRole(roleName);
    }

    public List<Person> readByCity(String cityName){
        return this.personRepository.findByCity(cityName);
    }

    public List<Person> readByRoleAndCity(String roleName, String cityName){
        return this.personRepository.findByRoleAndCity(roleName, cityName);
    }

    public void create() throws IOException {
        this.peopleDatabasePopulator.populatePerson(this.personRepository);
    }

}
