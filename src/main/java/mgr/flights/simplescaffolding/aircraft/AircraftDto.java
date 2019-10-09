package mgr.flights.simplescaffolding.aircraft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AircraftDto {
    private Integer aircraftId;
    private String model;
    private String company;
    private Integer seats;
}
