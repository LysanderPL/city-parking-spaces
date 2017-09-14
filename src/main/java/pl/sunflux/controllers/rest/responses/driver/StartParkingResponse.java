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

    public Long getParkingTicketId() {
        return parkingTicketId;
    }

    public void setParkingTicketId(Long parkingTicketId) {
        this.parkingTicketId = parkingTicketId;
    }

    public DriverTypeEnum getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverTypeEnum driverType) {
        this.driverType = driverType;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public Boolean getParkingStarted() {
        return isParkingStarted;
    }

    public void setParkingStarted(Boolean parkingStarted) {
        isParkingStarted = parkingStarted;
    }
}
