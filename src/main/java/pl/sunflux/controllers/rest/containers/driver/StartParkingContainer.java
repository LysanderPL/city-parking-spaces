package pl.sunflux.controllers.rest.containers.driver;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by maciej on 14.09.17.
 */
public class StartParkingContainer {

    @JsonProperty(value = "driver_unique_name")
    @NotNull
    private String driverUniqueName;

    @JsonProperty(value = "vehicle_serial_number")
    @NotNull
    private String vehicleSerialNumber;

    @JsonProperty(value = "parking_meter_serial_number")
    @NotNull
    private String parkingMeterSerialNumber;

    public String getDriverUniqueName() {
        return driverUniqueName;
    }

    public String getVehicleSerialNumber() {
        return vehicleSerialNumber;
    }

    public String getParkingMeterSerialNumber() {
        return parkingMeterSerialNumber;
    }
}
