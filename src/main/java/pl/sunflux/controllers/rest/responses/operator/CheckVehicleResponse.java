package pl.sunflux.controllers.rest.responses.operator;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckVehicleResponse {
    @JsonProperty(value = "vehicle_started_parking_meter")
    private Boolean isVehicleHasStartedParkingMeter = false;

    public void setVehicleHasStartedParkingMeter(Boolean vehicleHasStartedParkingMeter) {
        isVehicleHasStartedParkingMeter = vehicleHasStartedParkingMeter;
    }
}
