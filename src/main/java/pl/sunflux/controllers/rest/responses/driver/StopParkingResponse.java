package pl.sunflux.controllers.rest.responses.driver;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StopParkingResponse {
    @JsonProperty(value = "is_parking_stopped")
    private Boolean isParkingStopped = false;

    public void setParkingStopped(Boolean parkingStopped) {
        isParkingStopped = parkingStopped;
    }
}
