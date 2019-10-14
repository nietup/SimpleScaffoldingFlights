package mgr.flights.simplescaffolding.city;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {
    City toCity(CityDto cityDto);

    CityDto toDto(City city);

    default City fromCityId(Integer cityId) {
        if (cityId == null) {
            return null;
        }
        City city = new City();
        city.setCityId(cityId);
        return city;
    }
}
