package peoplepictures.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import peoplepictures.services.PersonService;

import java.io.IOException;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/people")
    @ResponseBody
    public ResponseEntity getPeople(
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer start){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.personService.read(limit, start));
    }

    @GetMapping("/people/{personName}")
    @ResponseBody
    public ResponseEntity getPeopleByName(
            @PathVariable("personName") String personName,
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer start){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.personService.read(personName, limit, start));
    }

    @GetMapping("/people/roles/{roleName}")
    @ResponseBody
    public ResponseEntity getPeopleByRole(
            @PathVariable("roleName") String roleName,
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer start){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.personService.readByRole(limit, start, roleName));
    }

    @GetMapping("/people/cities/{cityName}")
    @ResponseBody
    public ResponseEntity getPeopleByCity(
            @PathVariable("cityName") String cityName,
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer start){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.personService.readByCity(limit, start, cityName));
    }

    @GetMapping("/people/roles/{roleName}/cities/{cityName}")
    @ResponseBody
    public ResponseEntity readPeopleByRoleAndCity(
            @PathVariable("roleName") String roleName,
            @PathVariable("cityName") String cityName,
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer start){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.personService.readByRoleAndCity(limit, start, roleName,cityName));
    }

    @GetMapping("/people/cities/{cityName}/roles/{roleName}")
    @ResponseBody
    public ResponseEntity readPeopleByCityAndRole(
            @PathVariable("cityName") String roleName,
            @PathVariable("roleName") String cityName,
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer start){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.personService.readByRoleAndCity(limit, start, roleName,cityName));
    }

    @PostMapping("/populate/people")
    @ResponseBody
    public ResponseEntity populatePerson() {
        try {
            this.personService.create();
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("[Populate people] People data populated properly.");
        } catch(IOException ioe){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("[Populate people] The operation did not finish properly.");
        }
    }
}
