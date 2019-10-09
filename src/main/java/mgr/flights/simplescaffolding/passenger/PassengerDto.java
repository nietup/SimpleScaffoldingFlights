package mgr.flights.simplescaffolding.passenger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {
    private Integer passengerId;
    private String firstName;
    private String lastName;
    private String passportNo;
    private String street;
    private String city;
    private String countryCode;
    private String phone;
    private String flightNo;
}
