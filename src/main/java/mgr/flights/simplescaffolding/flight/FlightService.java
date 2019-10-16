package mgr.flights.simplescaffolding.flight;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import mgr.flights.simplescaffolding.aircraft.AircraftService;
import mgr.flights.simplescaffolding.exception.NotFoundException;
import mgr.flights.simplescaffolding.passenger.PassengerDto;
import mgr.flights.simplescaffolding.passenger.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<PassengerDto> getPassengersByFlightNo(String flightNo) {
        return passengerService.getPassengersByFlightNo(flightNo);
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
}
