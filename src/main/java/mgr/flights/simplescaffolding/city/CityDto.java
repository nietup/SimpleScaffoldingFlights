package mgr.flights.simplescaffolding.city;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {
    private Integer cityId;
    private String name;
    private Integer timeZone;
}
