package mgr.flights.simplescaffolding.passenger;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassengerMapper {
    Passenger toPassenger(PassengerDto passengerDto);

    PassengerDto toDto(Passenger passenger);
}
