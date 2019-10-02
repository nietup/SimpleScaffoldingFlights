package mgr.flights.simplescaffolding.city;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "City")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    @Column(name = "city_id")
    private Integer cityId;
    @Column(name = "name")
    private String name;
    @Column(name = "time_zone")
    private Integer timeZone;
}
