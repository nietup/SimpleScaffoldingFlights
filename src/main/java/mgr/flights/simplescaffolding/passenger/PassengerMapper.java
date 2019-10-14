package mgr.flights.simplescaffolding.passenger;

import mgr.flights.simplescaffolding.flight.FlightMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FlightMapper.class})
public interface PassengerMapper {

    @Mapping(source = "flightNo", target = "flight")
    Passenger toPassenger(PassengerDto passengerDto);

    @Mapping(source = "flight.flightNo", target = "flightNo")
    PassengerDto toDto(Passenger passenger);

    default Passenger fromPassengerId(Integer passengerId) {
        if (passengerId == null) {
            return null;
        }
        Passenger passenger = new Passenger();
        passenger.setPassengerId(passengerId);
        return passenger;
    }
}
