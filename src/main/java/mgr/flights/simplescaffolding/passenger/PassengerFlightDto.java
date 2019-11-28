package mgr.flights.simplescaffolding.passenger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerFlightDto {
    private Integer passengerId;
    private String firstName;
    private String lastName;
    private String flightNo;
    private LocalDateTime startTime;
    private LocalDateTime landingTime;
    private Integer aircraftId;
    private String sourceIata;
    private String destinationIata;
}
