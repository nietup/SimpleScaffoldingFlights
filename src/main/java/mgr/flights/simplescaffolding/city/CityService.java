package mgr.flights.simplescaffolding.city;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import mgr.flights.simplescaffolding.airport.AirportDto;
import mgr.flights.simplescaffolding.airport.AirportMapper;
import mgr.flights.simplescaffolding.airport.AirportService;
import mgr.flights.simplescaffolding.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CityService {

    @NonNull
    private final CityRepository cityRepository;
    @NonNull
    private final CityMapper cityMapper;
    @NonNull
    private final AirportService airportService;
    @NonNull
    private final AirportMapper airportMapper;

    public List<CityDto> getAllForCity() {
        return cityRepository
                .findAll()
                .stream()
                .map(cityMapper::toDto)
                .collect(Collectors.toList());
    }

    public CityDto createCity(CityDto cityDto) {
        City city = cityMapper.toCity(cityDto);
        City savedCity = cityRepository.save(city);
        return cityMapper.toDto(savedCity);
    }

    public Optional<CityDto> updateCity(CityDto cityDto) {
        City city = cityMapper.toCity(cityDto);

        return cityRepository
                .findById(city.getCityId()).isPresent() ?
                Optional.of(cityRepository.save(city)).map(cityMapper::toDto) :
                Optional.empty();
    }

    public void deleteCity(Integer id) {
        cityRepository.deleteById(id);
    }

    public Optional<CityDto> getCityByName(String name) {
        return cityRepository
                .findByName(name)
                .map(cityMapper::toDto);
    }

    public List<AirportDto> getAirportsByCityName(String name) {
        Integer cityId = getCityByName(name).orElseThrow(NotFoundException::new).getCityId();
        return airportService
                .getAirportByCityId(cityId)
                .stream()
                .map(airportMapper::toDto)
                .collect(Collectors.toList());
    }
}
