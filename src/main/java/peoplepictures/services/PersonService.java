package peoplepictures.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peoplepictures.model.Person;
import peoplepictures.populators.PeopleDatabasePopulator;
import peoplepictures.repositories.PersonRepository;
import peoplepictures.utils.ResultPaginator;

import java.io.IOException;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PeopleDatabasePopulator peopleDatabasePopulator;
    @Autowired
    private ResultPaginator resultPaginator;

    public List<Person> read(Integer limit, Integer start){
        List<Person> list = this.personRepository.findAll();
        return resultPaginator.paginateResults(list, limit, start);
    }

    public List<Person> read(String personName, Integer limit, Integer start){
        List<Person> list = this.personRepository.findByName(personName.replace("-"," "));
        return resultPaginator.paginateResults(list, limit, start);
    }

    public List<Person> readByRole(Integer limit, Integer start, String roleName){
        List<Person> list = this.personRepository.findByRole(roleName);
        return resultPaginator.paginateResults(list, limit, start);
    }

    public List<Person> readByCity(Integer limit, Integer start, String cityName){
        List<Person> list =  this.personRepository.findByCity(cityName);
        return resultPaginator.paginateResults(list, limit, start);
    }

    public List<Person> readByRoleAndCity(Integer limit, Integer start, String roleName, String cityName){
        List<Person> list =  this.personRepository.findByRoleAndCity(roleName, cityName);
        return resultPaginator.paginateResults(list, limit, start);
    }

    public void create() throws IOException {
        this.peopleDatabasePopulator.populatePerson(this.personRepository);
    }

}
