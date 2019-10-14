package mgr.flights.simplescaffolding.airport;

import mgr.flights.simplescaffolding.city.CityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CityMapper.class})
public interface AirportMapper {

    @Mapping(source = "cityId", target = "city")
    Airport toAirport(AirportDto airportDto);

    @Mapping(source = "city.cityId", target = "cityId")
    AirportDto toDto(Airport airport);

    default Airport fromIata(String iata) {
        if (iata == null) {
            return null;
        }
        Airport airport = new Airport();
        airport.setIata(iata);
        return airport;
    }
}
