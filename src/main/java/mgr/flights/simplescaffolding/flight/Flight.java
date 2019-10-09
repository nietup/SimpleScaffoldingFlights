package mgr.flights.simplescaffolding.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mgr.flights.simplescaffolding.aircraft.Aircraft;
import mgr.flights.simplescaffolding.airport.Airport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @NotNull
    @Column(name = "flight_no", nullable = false, unique = true)
    private String flightNo;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @NotNull
    @Column(name = "landing_time", nullable = false)
    private LocalDateTime landingTime;

    @ManyToOne(optional = false)
    @NotNull
    private Aircraft aircraft;

    @ManyToOne(optional = false)
    @NotNull
    private Airport source;

    @ManyToOne(optional = false)
    @NotNull
    private Airport destination;
}
