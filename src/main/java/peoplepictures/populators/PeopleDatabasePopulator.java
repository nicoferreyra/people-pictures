package peoplepictures.populators;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import peoplepictures.model.City;
import peoplepictures.model.Person;
import peoplepictures.model.Role;
import peoplepictures.repositories.CityRepository;
import peoplepictures.repositories.PersonRepository;
import peoplepictures.repositories.RoleRepository;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Scope("singleton")
public class PeopleDatabasePopulator {
    private static final String PEOPLE_JSON_URI = "https://gist.githubusercontent.com/ricardoul/c25bd1ade4d0977ab9bf660fae8dcc81/raw/feaa8243af1b324a995e9a0b3c047b2a79d6cd83/tarmac-team.json";
    private List<Map<String, String>> peopleJsonList = new ArrayList<>();
    private String imageUri = "http://tarmac.io/assets/members/{name_to_replace}.png";

    public void populatePerson(PersonRepository personRepository) throws IOException{
        getPeopleJsonList().stream().forEach(personMap -> {
            String name = personMap.get("name");
            String roleName = personMap.get("role");
            String cityName = personMap.get("city");
            if (personRepository.findByNameAndRoleAndCity(name, roleName, cityName).isEmpty()) {
                Person person = new Person();
                person.setName(name);
                person.setRole(roleName);
                person.setCity(cityName);
                this.setPersonImage(person);
                personRepository.save(person);
            }
        });
    }

    private void setPersonImage(Person person){
        person.setImageUri(imageUri.replace("{name_to_replace}",
          person.getName().toLowerCase().replace(" ", "-") ));
    }

    public void populateRole(RoleRepository roleRepository) throws IOException{
        getPeopleJsonList().stream().map(person -> person.get("role")).distinct().forEach(roleName -> {
            if (roleRepository.findByName(roleName).isEmpty()) {
                Role role = new Role();
                role.setName(roleName);
                roleRepository.save(role);
            }
        });
    }

    public void populateCity(CityRepository cityRepository) throws IOException{
        getPeopleJsonList().stream().map(person -> person.get("city")).distinct().forEach(cityName -> {
            if (cityRepository.findByName(cityName).isEmpty()) {
                City city = new City();
                city.setName(cityName);
                cityRepository.save(city);
            }
        });
    }

    private List<Map<String, String>> getPeopleJsonList() throws IOException{
        if(this.peopleJsonList.isEmpty()){
            String peopleJson = getJsonFromServer();
            this.peopleJsonList = new ObjectMapper().readValue(peopleJson, ArrayList.class);
        }
        return this.peopleJsonList;
    }

    private String getJsonFromServer(){
        return ClientBuilder.newClient()
                .target(PEOPLE_JSON_URI)
                .request().accept(MediaType.APPLICATION_JSON)
                .get(String.class);
    }
}
