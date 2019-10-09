package mgr.flights.simplescaffolding.passenger;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PassengerMapper {

    @Mapping(source = "flight.flightNo", target = "flightNo")
    Passenger toPassenger(PassengerDto passengerDto);

    @Mapping(source = "flightNo", target = "flight")
    PassengerDto toDto(Passenger passenger);
}
