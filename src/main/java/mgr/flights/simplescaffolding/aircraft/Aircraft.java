package mgr.flights.simplescaffolding.aircraft;

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
public class Aircraft {
    @Id
    @Column(name = "aircraft_id")
    @GeneratedValue
    @NotNull
    private Integer aircraftId;
}
