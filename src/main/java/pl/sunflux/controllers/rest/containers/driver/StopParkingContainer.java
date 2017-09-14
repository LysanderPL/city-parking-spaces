package pl.sunflux.controllers.rest.containers.driver;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by maciej on 14.09.17.
 */
public class StopParkingContainer {
    @JsonProperty(value = "parking_ticket_id")
    @NotNull
    private Long parkingTicketId;

    @JsonProperty(value = "vehicle_serial_number")
    @NotNull
    private String vehicleSerialNumber;

    @JsonProperty(value = "pesel")
    @NotNull
    private String pesel;

    public Long getParkingTicketId() {
        return parkingTicketId;
    }

    public String getVehicleSerialNumber() {
        return vehicleSerialNumber;
    }

    public String getPesel() {
        return pesel;
    }
}
