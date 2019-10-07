package mgr.flights.simplescaffolding.city;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {
    City toCity(CityDto cityDto);

    CityDto toDto(City city);
}
