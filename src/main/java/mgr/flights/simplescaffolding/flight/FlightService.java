package mgr.flights.simplescaffolding.flight;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import mgr.flights.simplescaffolding.aircraft.AircraftService;
import mgr.flights.simplescaffolding.airport.AirportDto;
import mgr.flights.simplescaffolding.city.CityService;
import mgr.flights.simplescaffolding.exception.NotFoundException;
import mgr.flights.simplescaffolding.passenger.PassengerFlightDto;
import mgr.flights.simplescaffolding.passenger.PassengerFlightMapper;
import mgr.flights.simplescaffolding.passenger.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FlightService {

    @NonNull
    private final FlightRepository flightRepository;
    @NonNull
    private final FlightMapper flightMapper;
    @NonNull
    private final PassengerService passengerService;
    @NonNull
    private final AircraftService aircraftService;
    @NonNull
    private final CityService cityService;
    @NonNull
    private final PassengerFlightMapper passengerFlightMapper;

    public Optional<FlightDto> getFlightByFlightNo(String flightNo) {
        return flightRepository
                .findByFlightNo(flightNo)
                .map(flightMapper::toDto);
    }

    public List<FlightDto> getAllForFlight() {
        return flightRepository
                .findAll()
                .stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }

    public FlightDto createFlight(FlightDto flightDto) {
        Flight flight = flightMapper.toFlight(flightDto);
        Flight savedFlight = flightRepository.save(flight);
        return flightMapper.toDto(savedFlight);
    }

    public Optional<FlightDto> updateFlight(FlightDto flightDto) {
        Flight flight = flightMapper.toFlight(flightDto);

        return flightRepository
                .findByFlightNo(flight.getFlightNo()).isPresent() ?
                Optional.of(flightRepository.save(flight)).map(flightMapper::toDto) :
                Optional.empty();
    }

    public void deleteFlight(String flightNo) {
        flightRepository.deleteByFlightNo(flightNo);
    }

    public boolean hasFreeSeats(String flightNo) {
        Flight flight = flightRepository
                .findByFlightNo(flightNo)
                .orElseThrow(NotFoundException::new);

        Integer allSeats = aircraftService
                .getAircraftById(flight.getAircraft().getAircraftId())
                .orElseThrow(NotFoundException::new)
                .getSeats();

        Integer takenSeats = passengerService
                .getPassengersByFlightNo(flightNo)
                .size();

        return allSeats > takenSeats;
    }

    public List<FlightDto> searchFlight(SearchRequest searchRequest) {
        LocalDateTime minTime = searchRequest.getStartTime().minusHours(searchRequest.getTimeRange() / 2);
        LocalDateTime maxTime = searchRequest.getStartTime().plusHours(searchRequest.getTimeRange() / 2);

        List<String> startingIatas = cityService
                .getAirportsByCityName(searchRequest.getSourceCity())
                .stream()
                .map(AirportDto::getIata)
                .collect(Collectors.toList());

        List<String> landingIatas = cityService
                .getAirportsByCityName(searchRequest.getDestinationCity())
                .stream()
                .map(AirportDto::getIata)
                .collect(Collectors.toList());
        ;

        List<FlightDto> resultFlights = startingIatas
                .stream()
                .map(flightRepository::findBySourceIata)
                .flatMap(Collection::stream)
                .filter(flight -> landingIatas.contains(flight.getDestination().getIata()))
                .filter(flight -> flight.getStartTime().isAfter(minTime))
                .filter(flight -> flight.getStartTime().isBefore(maxTime))
                .map(flightMapper::toDto)
                .collect(Collectors.toList());

        return resultFlights;
    }

    public List<PassengerFlightDto> getFlightsByPassengerSub(String sub) {
        return passengerService
                .getPassengersBySub(sub)
                .stream()
                .map(passenger -> {
                    FlightDto flight = getFlightByFlightNo(passenger.getFlight().getFlightNo())
                            .orElseThrow(NotFoundException::new);
                    return passengerFlightMapper.toDto(passenger, flight);
                })
                .collect(Collectors.toList());
    }
}
