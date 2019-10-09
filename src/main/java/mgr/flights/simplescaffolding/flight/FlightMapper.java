package mgr.flights.simplescaffolding.flight;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    @Mapping(source = "aircraftId", target = "aircraft")
    @Mapping(source = "sourceIata", target = "source")
    @Mapping(source = "destinationIata", target = "destination")
    Flight toFlight(FlightDto flightDto);

    @Mapping(source = "aircraft.id", target = "aircraftId")
    @Mapping(source = "source.iata", target = "sourceIata")
    @Mapping(source = "destination.iata", target = "destinationIata")
    FlightDto toDto(Flight flight);
}
