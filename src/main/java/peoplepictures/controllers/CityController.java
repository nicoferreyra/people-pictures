package peoplepictures.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import peoplepictures.services.CityService;

import java.io.IOException;

@Controller
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/cities")
    @ResponseBody
    public ResponseEntity getCities(
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer start){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.cityService.read(limit, start));
    }

    @GetMapping("/cities/{cityName}")
    @ResponseBody
    public ResponseEntity getRoles(
            @PathVariable("cityName") String cityName,
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer start){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.cityService.read(cityName, limit, start));
    }

    @PostMapping("/populate/cities")
    @ResponseBody
    public ResponseEntity populateCity() {
        try {
            this.cityService.create();
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
