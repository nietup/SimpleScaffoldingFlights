package mgr.flights.simplescaffolding.aircraft;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AircraftService {

    @NonNull
    private final AircraftRepository aircraftRepository;
    @NonNull
    private final AircraftMapper aircraftMapper;

    public Optional<AircraftDto> getAircraftById(Integer id) {
        return aircraftRepository
                .findById(id)
                .map(aircraftMapper::toDto);
    }

    public List<AircraftDto> getAllForAircraft() {
        return aircraftRepository
                .findAll()
                .stream()
                .map(aircraftMapper::toDto)
                .collect(Collectors.toList());
    }

    public AircraftDto createAircraft(AircraftDto aircraftDto) {
        Aircraft aircraft = aircraftMapper.toAircraft(aircraftDto);
        Aircraft savedAircraft = aircraftRepository.save(aircraft);
        return aircraftMapper.toDto(savedAircraft);
    }

    public Optional<AircraftDto> updateAircraft(AircraftDto aircraftDto) {
        Aircraft aircraft = aircraftMapper.toAircraft(aircraftDto);

        return aircraftRepository
                .findById(aircraft.getAircraftId()).isPresent() ?
                Optional.of(aircraftRepository.save(aircraft)).map(aircraftMapper::toDto) :
                Optional.empty();
    }

    public void deleteAircraft(Integer id) {
        aircraftRepository.deleteById(id);
    }
}
