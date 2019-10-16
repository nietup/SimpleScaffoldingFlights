package mgr.flights.simplescaffolding.passenger;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PassengerService {

    @NonNull
    private final PassengerRepository passengerRepository;
    @NonNull
    private final PassengerMapper passengerMapper;

    public Optional<PassengerDto> getPassengerById(Integer id) {
        return passengerRepository
                .findById(id)
                .map(passengerMapper::toDto);
    }

    public List<PassengerDto> getAllForPassenger() {
        return passengerRepository
                .findAll()
                .stream()
                .map(passengerMapper::toDto)
                .collect(Collectors.toList());
    }

    public PassengerDto createPassenger(PassengerDto passengerDto) {
        Passenger passenger = passengerMapper.toPassenger(passengerDto);
        Passenger savedPassenger = passengerRepository.save(passenger);
        return passengerMapper.toDto(savedPassenger);
    }

    public Optional<PassengerDto> updatePassenger(PassengerDto passengerDto) {
        Passenger passenger = passengerMapper.toPassenger(passengerDto);

        return passengerRepository
                .findById(passenger.getPassengerId()).isPresent() ?
                Optional.of(passengerRepository.save(passenger)).map(passengerMapper::toDto) :
                Optional.empty();
    }

    public void deletePassenger(Integer id) {
        passengerRepository.deleteById(id);
    }

    public List<PassengerDto> getPassengersByFlightNo(String flightNo) {
        return passengerRepository
                .findAllByFlightFlightNo(flightNo)
                .stream()
                .map(passengerMapper::toDto)
                .collect(Collectors.toList());
    }
}
