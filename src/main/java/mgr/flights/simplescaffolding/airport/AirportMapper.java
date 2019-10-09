package mgr.flights.simplescaffolding.airport;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AirportMapper {
    Airport toAirport(AirportDto airportDto);

    AirportDto toDto(Airport airport);
}
