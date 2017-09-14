package pl.sunflux.controllers.rest.containers.driver;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by maciej on 14.09.17.
 */
public class StopParkingContainer {
    @JsonProperty(value = "driver_unique_name")
    @NotNull
    private String driverUniqueName;

    @JsonProperty(value = "vehicle_serial_number")
    @NotNull
    private String vehicleSerialNumber;

    public String getDriverUniqueName() {
        return driverUniqueName;
    }

    public String getVehicleSerialNumber() {
        return vehicleSerialNumber;
    }
}
