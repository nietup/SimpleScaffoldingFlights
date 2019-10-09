package mgr.flights.simplescaffolding.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {
    private String flightNo;
    private LocalDateTime startTime;
    private LocalDateTime landingTime;
    private Integer aircraftId;
    private String sourceIata;
    private String destinationIata;
}
