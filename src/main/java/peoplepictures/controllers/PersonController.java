package peoplepictures.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public ResponseEntity getPeople(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.personService.read());
    }

    @GetMapping("/people/roles/{roleName}")
    @ResponseBody
    public ResponseEntity getPeopleByRole(
            @PathVariable("roleName") String roleName) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.personService.readByRole(roleName));
    }

    @GetMapping("/people/cities/{cityName}")
    @ResponseBody
    public ResponseEntity getPeopleByCity(
            @PathVariable("cityName") String cityName) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.personService.readByCity(cityName));
    }

    @GetMapping("/people/roles/{roleName}/cities/{cityName}")
    @ResponseBody
    public ResponseEntity readPeopleByRoleAndCity(
            @PathVariable("roleName") String roleName,
            @PathVariable("cityName") String cityName) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.personService.readByRoleAndCity(roleName,cityName));
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
