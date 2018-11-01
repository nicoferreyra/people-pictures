package peoplepictures.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import peoplepictures.constants.ApplicationConstants;
import peoplepictures.model.City;
import peoplepictures.model.Person;
import peoplepictures.model.Role;
import peoplepictures.repositories.CityRepository;
import peoplepictures.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import peoplepictures.repositories.RoleRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Controller
public class PersonController {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/roles")
    @ResponseBody
    public Iterable<Role> findAllRoles(){
        return roleRepository.findAll();
    }

    @GetMapping("/create-database")
    @ResponseBody
    public String createDatabase() {
        try {
            String peopleJson = new Scanner(new File(ApplicationConstants.PEOPLE_JSON_PATH)).useDelimiter("\\Z").next();
            List<Map<String, String>> jsonList = new ObjectMapper().readValue(peopleJson, ArrayList.class);

            jsonList.stream().forEach(personMap -> {
                String name = personMap.get("name");
                String roleName = personMap.get("role");
                String cityName = personMap.get("city");
                if (personRepository.findByNameAndRoleAndCity(name, roleName, cityName).isEmpty()) {
                    Person person = new Person();
                    person.setName(name);
                    person.setRole(roleName);
                    person.setCity(cityName);
                    personRepository.save(person);
                    if (roleRepository.findByName(roleName).isEmpty()) {
                        Role role = new Role();
                        role.setName(roleName);
                        roleRepository.save(role);
                    }
                    if (cityRepository.findByName(cityName).isEmpty()) {
                        City city = new City();
                        city.setName(cityName);
                        cityRepository.save(city);
                    }
                }
            });
            return "{status: 201, message: database were created properly}";
        } catch(IOException ioe){
            return "{status: 500, message: the operation did not complete properly}";
        }
    }
}
