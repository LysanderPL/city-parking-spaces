package pl.sunflux.controllers.rest.containers.driver;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

public class StopParkingContainer {
    @JsonProperty(value = "parking_ticket_id", required = true)
    @NotBlank(message = "Parking ticket must not be blank!")
    private Long parkingTicketId;

    @JsonProperty(value = "vehicle_serial_number", required = true)
    @NotBlank(message = "Vehicle serial must not be blank!")
    private String vehicleSerialNumber;

    @JsonProperty(value = "driver_id_card", required = true)
    @NotBlank(message = "Driver ID card must not be blank!")
    private String driverIdCard;

    public Long getParkingTicketId() {
        return parkingTicketId;
    }

    public String getVehicleSerialNumber() {
        return vehicleSerialNumber;
    }

    public String getDriverIdCard() {
        return driverIdCard;
    }
}
