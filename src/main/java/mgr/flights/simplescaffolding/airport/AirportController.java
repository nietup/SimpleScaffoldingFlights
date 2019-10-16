package mgr.flights.simplescaffolding.airport;

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
@RequestMapping("/api/airport")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AirportController {
    @NonNull
    private final AirportService airportService;

    @GetMapping("/{iata}")
    public ResponseEntity<AirportDto> getAirportByIata(@PathVariable String iata) {
        AirportDto airportDto = airportService.getAirportByIata(iata).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok().body(airportDto);
    }

    @GetMapping
    public ResponseEntity<List<AirportDto>> getAllForAirport() {
        List<AirportDto> airportDtoList = airportService.getAllForAirport();
        return ResponseEntity.ok().body(airportDtoList);
    }

    @PostMapping
    public ResponseEntity<AirportDto> createAirport(@RequestBody AirportDto airportDto) {
        if (airportDto.getIata() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AirportIata must not be null when creating airport");
        }

        AirportDto savedAirportDto = airportService.createAirport(airportDto);
        return ResponseEntity.ok().body(savedAirportDto);
    }

    @PutMapping
    public ResponseEntity<AirportDto> updateAirport(@RequestBody AirportDto airportDto) {
        if (airportDto.getIata() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AirportIata must not be null when updating airport");
        }

        AirportDto savedAirportDto = airportService.updateAirport(airportDto)
                .orElseThrow(NotFoundException::new);

        return ResponseEntity.ok().body(savedAirportDto);
    }

    @DeleteMapping("/{iata}")
    public ResponseEntity<Void> deleteAirport(@PathVariable String iata) {
        airportService.deleteAirport(iata);
        return ResponseEntity.noContent().build();
    }
}
