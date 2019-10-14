package mgr.flights.simplescaffolding.airport;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, String> {

    Optional<Airport> findByIata(String iata);

    void deleteByIata(String iata);
}
