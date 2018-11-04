package peoplepictures.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peoplepictures.model.City;
import peoplepictures.populators.PeopleDatabasePopulator;
import peoplepictures.repositories.CityRepository;
import peoplepictures.utils.ResultPaginator;

import java.io.IOException;
import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private PeopleDatabasePopulator peopleDatabasePopulator;
    @Autowired
    private ResultPaginator resultPaginator;

    public Iterable<City> read(Integer limit, Integer start){
        List<City> list =  this.cityRepository.findAll();
        return resultPaginator.paginateResults(list, limit, start);
    }

    public void create() throws IOException {
        this.peopleDatabasePopulator.populateCity(this.cityRepository);
    }
}
