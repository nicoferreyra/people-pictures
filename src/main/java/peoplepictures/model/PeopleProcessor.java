package peoplepictures.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import peoplepictures.repositories.PersonRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class PeopleProcessor {

    @Autowired
    private static PersonRepository personRepository;

    private static final String peopleJsonPath = "/src/main/datasource/people.json";

    public static void populateDataBase() throws FileNotFoundException{
        String myJson = new Scanner(new File(peopleJsonPath)).useDelimiter("\\Z").next();

        Map map = new GsonJsonParser().parseMap(myJson);
    }

}
