package peoplepictures.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import peoplepictures.services.RoleService;

import java.io.IOException;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    @ResponseBody
    @CrossOrigin
    public ResponseEntity getRoles(
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer start){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.roleService.read(limit, start));
    }

    @GetMapping("/roles/{roleName}")
    @ResponseBody
    public ResponseEntity getRolesByName(
            @PathVariable("roleName") String roleName,
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer start){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.roleService.read(roleName, limit, start));
    }

    @PostMapping("/populate/roles")
    @ResponseBody
    public ResponseEntity populateRole() {
        try {
            this.roleService.create();
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
