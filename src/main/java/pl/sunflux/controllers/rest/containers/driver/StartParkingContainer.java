package pl.sunflux.controllers.rest.containers.driver;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

public class StartParkingContainer {

    @JsonProperty(value = "driver_id_card", required = true)
    @NotBlank(message = "Driver ID card must not be blank!")
    private String driverIdCrd;

    @JsonProperty(value = "vehicle_serial_number", required = true)
    @NotBlank(message = "Vehicle serial must not be blank!")
    private String vehicleSerialNumber;

    @JsonProperty(value = "parking_meter_serial_number", required = true)
    @NotBlank(message = "Parking meter serial must not be blank!")
    private String parkingMeterSerialNumber;

//    private String currencyCode;

    public String getDriverIdCrd() {
        return driverIdCrd;
    }

    public String getVehicleSerialNumber() {
        return vehicleSerialNumber;
    }

    public String getParkingMeterSerialNumber() {
        return parkingMeterSerialNumber;
    }
}
