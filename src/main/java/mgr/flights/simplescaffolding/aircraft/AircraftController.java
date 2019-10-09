package mgr.flights.simplescaffolding.aircraft;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import mgr.flights.simplescaffolding.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/api/aircraft")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AircraftController {
    @NonNull
    private final AircraftService aircraftService;

    @GetMapping("/{id}")
    public ResponseEntity<AircraftDto> getAircraftById(@PathVariable Integer id) {
        AircraftDto aircraftDto = aircraftService.getAircraftById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok().body(aircraftDto);
    }

    @GetMapping
    public ResponseEntity<List<AircraftDto>> getAllForAircraft() {
        List<AircraftDto> aircraftDtoList = aircraftService.getAllForAircraft();
        return ResponseEntity.ok().body(aircraftDtoList);
    }

    @PostMapping
    public ResponseEntity<AircraftDto> createAircraft(@RequestBody AircraftDto aircraftDto) {
        if (aircraftDto.getAircraftId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AircraftId must be null when creating aircraft");
        }

        AircraftDto savedAircraftDto = aircraftService.createAircraft(aircraftDto);
        return ResponseEntity.ok().body(savedAircraftDto);
    }

    @PutMapping
    public ResponseEntity<AircraftDto> updateAircraft(@RequestBody AircraftDto aircraftDto) {
        if (aircraftDto.getAircraftId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AircraftId must not be null when updating aircraft");
        }

        AircraftDto savedAircraftDto = aircraftService.updateAircraft(aircraftDto)
                .orElseThrow(NotFoundException::new);

        return ResponseEntity.ok().body(savedAircraftDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAircraft(@PathVariable Integer id) {
        aircraftService.deleteAircraft(id);
        return ResponseEntity.noContent().build();
    }
}
