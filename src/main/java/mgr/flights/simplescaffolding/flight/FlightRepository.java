package mgr.flights.simplescaffolding.flight;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, String> {

    Optional<Flight> findByFlightNo(String flightNo);

    void deleteByFlightNo(String flightNo);
}
