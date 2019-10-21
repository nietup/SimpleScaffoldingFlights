package mgr.flights.simplescaffolding.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
class SearchRequest {
    @NonNull
    private String sourceCity;
    @NonNull
    private String destinationCity;
    @NonNull
    private LocalDateTime startTime;
    private Integer timeRange = 10;
}

