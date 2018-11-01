package peoplepictures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class PeoplePicturesApplication {

    final static Logger logger = Logger.getLogger(PeoplePicturesApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(PeoplePicturesApplication.class, args);
    }

}
