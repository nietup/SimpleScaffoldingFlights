package mgr.flights.simplescaffolding.passenger;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

    List<Passenger> findAllByFlightFlightNo(String flightNo);

    List<Passenger> findBySub(String sub);
}
