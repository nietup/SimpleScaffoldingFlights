package mgr.flights.simplescaffolding.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Optional<City> getCityById(Integer id) {
        return cityRepository.findById(id);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public Optional<City> updateCity(City city) {
        return cityRepository.findById(city.getCityId()).isPresent() ?
                Optional.of(cityRepository.save(city)) :
                Optional.empty();
    }

    public void deleteCity(Integer id) {
        cityRepository.deleteById(id);
    }
}
