package mgr.flights.simplescaffolding.city;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @Id
    @Column(name = "city_id")
    @GeneratedValue
    @NotNull
    private Integer cityId;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "time_zone")
    private Integer timeZone;
}
