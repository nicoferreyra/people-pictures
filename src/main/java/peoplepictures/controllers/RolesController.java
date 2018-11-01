package peoplepictures.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import peoplepictures.populators.PeopleDatabasePopulator;
import peoplepictures.repositories.RoleRepository;

import java.io.IOException;

@Controller
public class RolesController {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PeopleDatabasePopulator peopleDatabasePopulator;

    @GetMapping("/roles")
    @ResponseBody
    public ResponseEntity findAllRoles(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.roleRepository.findAll());
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
}
