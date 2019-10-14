package mgr.flights.simplescaffolding.aircraft;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AircraftMapper {
    Aircraft toAircraft(AircraftDto aircraftDto);

    AircraftDto toDto(Aircraft aircraft);

    default Aircraft fromAircraftId(Integer aircraftId) {
        if (aircraftId == null) {
            return null;
        }
        Aircraft aircraft = new Aircraft();
        aircraft.setAircraftId(aircraftId);
        return aircraft;
    }
}
