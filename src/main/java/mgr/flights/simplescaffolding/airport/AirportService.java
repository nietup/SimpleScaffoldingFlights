package mgr.flights.simplescaffolding.airport;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AirportService {

    @NonNull
    private final AirportRepository airportRepository;
    @NonNull
    private final AirportMapper airportMapper;

    public Optional<AirportDto> getAirportByIata(String iata) {
        return airportRepository
                .findByIata(iata)
                .map(airportMapper::toDto);
    }

    public List<AirportDto> getAllForAirport() {
        return airportRepository
                .findAll()
                .stream()
                .map(airportMapper::toDto)
                .collect(Collectors.toList());
    }

    public AirportDto createAirport(AirportDto airportDto) {
        Airport airport = airportMapper.toAirport(airportDto);
        Airport savedAirport = airportRepository.save(airport);
        return airportMapper.toDto(savedAirport);
    }

    public Optional<AirportDto> updateAirport(AirportDto airportDto) {
        Airport airport = airportMapper.toAirport(airportDto);

        return airportRepository
                .findByIata(airport.getIata()).isPresent() ?
                Optional.of(airportRepository.save(airport)).map(airportMapper::toDto) :
                Optional.empty();
    }

    public void deleteAirport(String iata) {
        airportRepository.deleteByIata(iata);
    }

    public List<Airport> getAirportByCityId(Integer cityId) {
        return airportRepository.findByCityCityId(cityId);
    }
}
