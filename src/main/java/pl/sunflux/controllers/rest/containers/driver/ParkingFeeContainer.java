package pl.sunflux.controllers.rest.containers.driver;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

public class ParkingFeeContainer {
    @JsonProperty(value = "parking_ticket_id", required = true)
    @NotBlank(message = "Parking ticket must not be blank!")
    private Long parkingTicketId;

    public Long getParkingTicketId() {
        return parkingTicketId;
    }
}
