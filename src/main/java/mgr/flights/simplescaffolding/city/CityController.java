package mgr.flights.simplescaffolding.city;

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
@RequestMapping("/api/city")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CityController {
    @NonNull
    private final CityService cityService;

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getCityById(@PathVariable Integer id) {
        CityDto cityDto = cityService.getCityById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok().body(cityDto);
    }

    @GetMapping
    public ResponseEntity<List<CityDto>> getAllForCity() {
        List<CityDto> cityDtoList = cityService.getAllForCity();
        return ResponseEntity.ok().body(cityDtoList);
    }

    @PostMapping
    public ResponseEntity<CityDto> createCity(@RequestBody CityDto cityDto) {
        if (cityDto.getCityId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CityId must be null when creating city");
        }

        CityDto savedCityDto = cityService.createCity(cityDto);
        return ResponseEntity.ok().body(savedCityDto);
    }

    @PutMapping
    public ResponseEntity<CityDto> updateCity(@RequestBody CityDto cityDto) {
        if (cityDto.getCityId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CityId must not be null when updating city");
        }

        CityDto savedCityDto = cityService.updateCity(cityDto)
                .orElseThrow(NotFoundException::new);

        return ResponseEntity.ok().body(savedCityDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Integer id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}
