package mgr.flights.simplescaffolding.passenger;

import mgr.flights.simplescaffolding.flight.FlightDto;
import mgr.flights.simplescaffolding.flight.FlightMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FlightMapper.class, PassengerMapper.class})
public interface PassengerFlightMapper {
    PassengerFlightDto toDto(Passenger passenger, FlightDto flight);
}
