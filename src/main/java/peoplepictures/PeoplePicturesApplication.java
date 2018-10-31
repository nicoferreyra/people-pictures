package peoplepictures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PeoplePicturesApplication {

    //final static Logger logger = Logger.getLogger(PeoplePicturesApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(PeoplePicturesApplication.class, args);

        /*
        try{
            PeopleProcessor.populateDataBase();
        }catch(FileNotFoundException fnfe){
            logger.info("[error-reading-datasource] The people database could not be populated");
        }
        */
    }

}
