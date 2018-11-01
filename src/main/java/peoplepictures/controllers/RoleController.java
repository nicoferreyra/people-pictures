package peoplepictures.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import peoplepictures.services.RolesService;

import java.io.IOException;

@Controller
public class RoleController {

    @Autowired
    private RolesService rolesService;

    @GetMapping("/roles")
    @ResponseBody
    public ResponseEntity getRoles(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.rolesService.read());
    }

    @PostMapping("/populate/roles")
    @ResponseBody
    public ResponseEntity populateRole() {
        try {
            this.rolesService.create();
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
