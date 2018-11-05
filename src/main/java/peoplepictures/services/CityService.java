package peoplepictures.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peoplepictures.model.City;
import peoplepictures.populators.PeopleDatabasePopulator;
import peoplepictures.repositories.CityRepository;

import java.io.IOException;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private PeopleDatabasePopulator peopleDatabasePopulator;

    public Iterable<City> read(){
        return this.cityRepository.findAll();
    }

    public void create() throws IOException {
        this.peopleDatabasePopulator.populateCity(this.cityRepository);
    }
}
