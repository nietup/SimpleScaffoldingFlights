package mgr.flights.simplescaffolding.airport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mgr.flights.simplescaffolding.city.City;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airport {
    @Id
    @NotNull
    @Column(name = "iata", nullable = false, unique = true)
    private String iata;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToOne
    private City city;
}
