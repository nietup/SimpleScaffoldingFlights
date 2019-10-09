package mgr.flights.simplescaffolding.aircraft;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AircraftMapper {
    Aircraft toAircraft(AircraftDto aircraftDto);

    AircraftDto toDto(Aircraft aircraft);
}
