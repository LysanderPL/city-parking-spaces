package pl.sunflux.controllers.rest.containers.driver;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by maciej on 14.09.17.
 */
public class StartParkingContainer {

    @JsonProperty(value = "pesel")
    @NotNull
    private String pesel;

    @JsonProperty(value = "vehicle_serial_number")
    @NotNull
    private String vehicleSerialNumber;

    @JsonProperty(value = "parking_meter_serial_number")
    @NotNull
    private String parkingMeterSerialNumber;

    public String getPesel() {
        return pesel;
    }

    public String getVehicleSerialNumber() {
        return vehicleSerialNumber;
    }

    public String getParkingMeterSerialNumber() {
        return parkingMeterSerialNumber;
    }
}
