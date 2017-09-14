package pl.sunflux.controllers.rest.responses.driver;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by maciej on 14.09.17.
 */
public class StopParkingResponse {
    @JsonProperty(value = "is_parking_stopped")
    private Boolean isParkingStopped = false;

    public void setParkingStopped(Boolean parkingStopped) {
        isParkingStopped = parkingStopped;
    }
}
