package peoplepictures.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import peoplepictures.populators.PeopleDatabasePopulator;
import peoplepictures.repositories.CityRepository;
import peoplepictures.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import peoplepictures.repositories.RoleRepository;

import java.io.IOException;

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
    public ResponseEntity findAllRoles(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.roleRepository.findAll());
    }

    @GetMapping("/cities")
    @ResponseBody
    public ResponseEntity findAllCities(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.cityRepository.findAll());
    }

    @GetMapping("/people")
    @ResponseBody
    public ResponseEntity findAllPeople(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.personRepository.findAll());
    }

    @GetMapping("/people/roles/{roleName}")
    @ResponseBody
    public ResponseEntity findPeopleByRole(
            @PathVariable("roleName") String roleName) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.personRepository.findByRole(roleName));
    }

    @GetMapping("/people/cities/{cityName}")
    @ResponseBody
    public ResponseEntity findPeopleByCity(
            @PathVariable("cityName") String cityName) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.personRepository.findByCity(cityName));
    }

    @GetMapping("/people/roles/{roleName}/cities/{cityName}")
    @ResponseBody
    public ResponseEntity findPeopleByRoleAndCity(
            @PathVariable("roleName") String roleName,
            @PathVariable("cityName") String cityName) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.personRepository.findByRoleAndCity(roleName,cityName));
    }

    @PostMapping("/populate/people")
    @ResponseBody
    public ResponseEntity populatePerson() {
        try {
            this.peopleDatabasePopulator.populatePerson(this.personRepository);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("[Populate people] People data populated properly.");
        } catch(IOException ioe){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("[Populate people] The operation did not finish properly.");
        }
    }

    @PostMapping("/populate/roles")
    @ResponseBody
    public ResponseEntity populateRole() {
        try {
            this.peopleDatabasePopulator.populateRole(this.roleRepository);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("[Populate roles] Roles data populated properly.");
        } catch(IOException ioe){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("[Populate roles] The operation did not finish properly.");
        }
    }

    @PostMapping("/populate/cities")
    @ResponseBody
    public ResponseEntity populateCity() {
        try {
            this.peopleDatabasePopulator.populateCity(this.cityRepository);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("[Populate cities] Cities data populated properly.");
        } catch(IOException ioe){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("[Populate cities] The operation did not finish properly.");
        }
    }
}
