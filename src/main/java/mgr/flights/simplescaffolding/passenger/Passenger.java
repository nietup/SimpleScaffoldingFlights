package mgr.flights.simplescaffolding.passenger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mgr.flights.simplescaffolding.flight.Flight;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
    @Id
    @Column(name = "passenger_id")
    @GeneratedValue
    @NotNull
    private Integer passengerId;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "passport_no", nullable = false, unique = true)
    private String passportNo;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "phone")
    private String phone;

    @Column(name = "sub")
    private String sub;

    @ManyToOne(optional = false)
    @NotNull
    private Flight flight;
}
