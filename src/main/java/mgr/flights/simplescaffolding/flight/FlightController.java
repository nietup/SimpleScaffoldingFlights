package mgr.flights.simplescaffolding.flight;

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
@RequestMapping("/api/flight")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FlightController {
    @NonNull
    private final FlightService flightService;

    @GetMapping("/{flightNo}")
    public ResponseEntity<FlightDto> getFlightByFlightNo(@PathVariable String flightNo) {
        FlightDto flightDto = flightService.getFlightByFlightNo(flightNo).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok().body(flightDto);
    }

    @GetMapping
    public ResponseEntity<List<FlightDto>> getAllForFlight() {
        List<FlightDto> flightDtoList = flightService.getAllForFlight();
        return ResponseEntity.ok().body(flightDtoList);
    }

    @PostMapping
    public ResponseEntity<FlightDto> createFlight(@RequestBody FlightDto flightDto) {
        if (flightDto.getFlightNo() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "FlightNo must be null when creating flight");
        }

        FlightDto savedFlightDto = flightService.createFlight(flightDto);
        return ResponseEntity.ok().body(savedFlightDto);
    }

    @PutMapping
    public ResponseEntity<FlightDto> updateFlight(@RequestBody FlightDto flightDto) {
        if (flightDto.getFlightNo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "FlightNo must not be null when updating flight");
        }

        FlightDto savedFlightDto = flightService.updateFlight(flightDto)
                .orElseThrow(NotFoundException::new);

        return ResponseEntity.ok().body(savedFlightDto);
    }

    @DeleteMapping("/{flightNo}")
    public ResponseEntity<Void> deleteFlight(@PathVariable String flightNo) {
        flightService.deleteFlight(flightNo);
        return ResponseEntity.noContent().build();
    }
}
