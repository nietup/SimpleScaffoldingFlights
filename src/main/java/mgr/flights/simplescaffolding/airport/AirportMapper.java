package mgr.flights.simplescaffolding.airport;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    @Mapping(source = "cityId", target = "city")
    Airport toAirport(AirportDto airportDto);

    @Mapping(source = "city.id", target = "cityId")
    @Mapping(source = "city.name", target = "cityName")
    AirportDto toDto(Airport airport);
}
