package pl.sunflux.controllers.rest.containers.driver;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by maciej on 14.09.17.
 */
public class ParkingFeeContainer {
    @JsonProperty(value = "parking_ticket_id", required = true)
    @NotNull
    private Long parkingTicketId;

    public Long getParkingTicketId() {
        return parkingTicketId;
    }
}
