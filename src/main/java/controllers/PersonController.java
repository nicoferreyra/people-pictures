package controllers;

import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import repositories.PersonRepository;

@Controller
@RequestMapping(path="person")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path="/add") // Map ONLY GET Requests
    public @ResponseBody String addNewUser (
            @RequestParam String name,
            @RequestParam String role,
            @RequestParam String city) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Person n = new Person();
        n.setName(name);
        n.setRole(role);
        n.setCity(city);

        personRepository.save(n);
        return "Saved";
    }
}
