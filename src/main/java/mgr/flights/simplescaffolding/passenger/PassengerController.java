package mgr.flights.simplescaffolding.passenger;

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
@RequestMapping("/api/passenger")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PassengerController {
    @NonNull
    private final PassengerService passengerService;

    @GetMapping("/{id}")
    public ResponseEntity<PassengerDto> getPassengerById(@PathVariable Integer id) {
        PassengerDto passengerDto = passengerService.getPassengerById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok().body(passengerDto);
    }

    @GetMapping
    public ResponseEntity<List<PassengerDto>> getAllForPassenger() {
        List<PassengerDto> passengerDtoList = passengerService.getAllForPassenger();
        return ResponseEntity.ok().body(passengerDtoList);
    }

    @PostMapping
    public ResponseEntity<PassengerDto> createPassenger(@RequestBody PassengerDto passengerDto) {
        if (passengerDto.getPassengerId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "PassengerId must be null when creating passenger");
        }

        PassengerDto savedPassengerDto = passengerService.createPassenger(passengerDto);
        return ResponseEntity.ok().body(savedPassengerDto);
    }

    @PutMapping
    public ResponseEntity<PassengerDto> updatePassenger(@RequestBody PassengerDto passengerDto) {
        if (passengerDto.getPassengerId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "PassengerId must not be null when updating passenger");
        }

        PassengerDto savedPassengerDto = passengerService.updatePassenger(passengerDto)
                .orElseThrow(NotFoundException::new);

        return ResponseEntity.ok().body(savedPassengerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Integer id) {
        passengerService.deletePassenger(id);
        return ResponseEntity.noContent().build();
    }
}
