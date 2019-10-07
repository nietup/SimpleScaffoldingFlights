package mgr.flights.simplescaffolding.city;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CityService {

    @NonNull
    private final CityRepository cityRepository;
    @NonNull
    private final CityMapper cityMapper;

    public Optional<City> getCityById(Integer id) {
        return cityRepository.findById(id);
    }

    public List<City> getAllForCity() {
        return cityRepository.findAll();
    }

    public City createCity(City city) {
        CityDto ddd = cityMapper.toDto(city);
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
