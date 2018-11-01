package peoplepictures.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import peoplepictures.populators.PeopleDatabasePopulator;
import peoplepictures.repositories.CityRepository;

import java.io.IOException;

@Controller
public class CitiesController {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private PeopleDatabasePopulator peopleDatabasePopulator;

    @GetMapping("/cities")
    @ResponseBody
    public ResponseEntity findAllCities(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.cityRepository.findAll());
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
