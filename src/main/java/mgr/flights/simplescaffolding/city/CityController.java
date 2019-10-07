package mgr.flights.simplescaffolding.city;

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
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Integer id) {
        City city = cityService.getCityById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok().body(city);
    }

    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = cityService.getAllCities();
        return ResponseEntity.ok().body(cities);
    }

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City city) {
        if (city.getCityId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CityId must be null when creating city");
        }

        City savedCity = cityService.createCity(city);
        return ResponseEntity.ok().body(savedCity);
    }

    @PutMapping
    public ResponseEntity<City> updateCity(@RequestBody City city) {
        if (city.getCityId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CityId must not be null when updating city");
        }

        City savedCity = cityService.updateCity(city)
                .orElseThrow(NotFoundException::new);

        return ResponseEntity.ok().body(savedCity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Integer id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}
