package mgr.flights.simplescaffolding.flight;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    Flight toFlight(FlightDto flightDto);

    FlightDto toDto(Flight flight);
}
