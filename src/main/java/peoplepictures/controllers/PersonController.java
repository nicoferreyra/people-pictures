package peoplepictures.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import peoplepictures.populators.PeopleDatabasePopulator;
import peoplepictures.repositories.CityRepository;
import peoplepictures.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import peoplepictures.repositories.RoleRepository;
import peoplepictures.rest.Response;

import java.io.IOException;
import java.util.Map;

@Controller
public class PersonController {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private PeopleDatabasePopulator peopleDatabasePopulator;

    @GetMapping("/roles")
    @ResponseBody
    public Map<String, Object>  findAllRoles(){
        return Response
                .builder()
                .httpStatus(HttpStatus.CREATED.value())
                .message("database created properly")
                .data(this.roleRepository.findAll())
                .build()
                .asMap();
    }

    @GetMapping("/cities")
    @ResponseBody
    public Map<String, Object>  findAllCities(){
        return Response
                .builder()
                .httpStatus(HttpStatus.CREATED.value())
                .message("database created properly")
                .data(this.cityRepository.findAll())
                .build()
                .asMap();
    }

    @GetMapping("/populate_person")
    @ResponseBody
    public Map<String, Object> populatePerson() {
        try {
            this.peopleDatabasePopulator.populatePerson(this.personRepository);
            return Response
                    .builder()
                    .httpStatus(HttpStatus.CREATED.value())
                    .message("Person table populated properly")
                    .build()
                    .asMap();
        } catch(IOException ioe){
            return Response
                    .builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("The operation did not finish properly")
                    .build()
                    .asMap();
        }
    }

    @GetMapping("/populate_role")
    @ResponseBody
    public Map<String, Object> populateRole() {
        try {
            this.peopleDatabasePopulator.populateRole(this.roleRepository);
            return Response
                    .builder()
                    .httpStatus(HttpStatus.CREATED.value())
                    .message("Role table populated properly")
                    .build()
                    .asMap();
        } catch(IOException ioe){
            return Response
                    .builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("The operation did not finish properly")
                    .build()
                    .asMap();
        }
    }

    @GetMapping("/populate_city")
    @ResponseBody
    public Map<String, Object> populateCity() {
        try {
            this.peopleDatabasePopulator.populateCity(this.cityRepository);
            return Response
                    .builder()
                    .httpStatus(HttpStatus.CREATED.value())
                    .message("City table populated properly")
                    .build()
                    .asMap();
        } catch(IOException ioe){
            return Response
                    .builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("The operation did not finish properly")
                    .build()
                    .asMap();
        }
    }
}
