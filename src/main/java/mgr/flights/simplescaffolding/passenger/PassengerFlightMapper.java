package mgr.flights.simplescaffolding.passenger;

import mgr.flights.simplescaffolding.flight.FlightDto;
import mgr.flights.simplescaffolding.flight.FlightMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FlightMapper.class, PassengerMapper.class})
public interface PassengerFlightMapper {
    @Mapping(source = "passenger.passengerId", target = "passengerId")
    PassengerFlightDto toDto(Passenger passenger, FlightDto flight);
}
