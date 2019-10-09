package mgr.flights.simplescaffolding.airport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirportDto {
    private String iata;
    private String name;
    private Integer cityId;
}
