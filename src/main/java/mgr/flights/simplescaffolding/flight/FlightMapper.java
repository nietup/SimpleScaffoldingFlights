package mgr.flights.simplescaffolding.flight;

import mgr.flights.simplescaffolding.aircraft.AircraftMapper;
import mgr.flights.simplescaffolding.airport.AirportMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AirportMapper.class, AircraftMapper.class})
public interface FlightMapper {
    @Mapping(source = "aircraftId", target = "aircraft")
    @Mapping(source = "sourceIata", target = "source")
    @Mapping(source = "destinationIata", target = "destination")
    Flight toFlight(FlightDto flightDto);

    @Mapping(source = "aircraft.aircraftId", target = "aircraftId")
    @Mapping(source = "source.iata", target = "sourceIata")
    @Mapping(source = "destination.iata", target = "destinationIata")
    FlightDto toDto(Flight flight);

    default Flight fromFlightNo(String flightNo) {
        if (flightNo == null) {
            return null;
        }
        Flight flight = new Flight();
        flight.setFlightNo(flightNo);
        return flight;
    }
}
