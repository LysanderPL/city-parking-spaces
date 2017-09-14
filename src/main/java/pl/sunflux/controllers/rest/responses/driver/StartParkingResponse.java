package pl.sunflux.controllers.rest.responses.driver;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.sunflux.entity.constants.DriverTypeEnum;

/**
 * Created by maciej on 14.09.17.
 */
public class StartParkingResponse {
    @JsonProperty(value = "parking_ticket_id")
    private Long parkingTicketId;

    @JsonProperty(value = "is_parking_started")
    private Boolean isParkingStarted = false;

    @JsonProperty(value = "driver_type")
    private DriverTypeEnum driverType;

    @JsonProperty(value = "date_start")
    private String dateStart;

    public void setParkingTicketId(Long parkingTicketId) {
        this.parkingTicketId = parkingTicketId;
    }

    public void setDriverType(DriverTypeEnum driverType) {
        this.driverType = driverType;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public void setParkingStarted(Boolean parkingStarted) {
        isParkingStarted = parkingStarted;
    }
}
