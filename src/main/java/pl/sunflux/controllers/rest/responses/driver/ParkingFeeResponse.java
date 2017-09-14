package pl.sunflux.controllers.rest.responses.driver;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.sunflux.entity.Currency;

import java.math.BigDecimal;

/**
 * Created by maciej on 14.09.17.
 */
public class ParkingFeeResponse {
    @JsonProperty(value = "parking_fee")
    private BigDecimal parkingFee;
    @JsonProperty(value = "currency")
    private Currency currency;

    public void setParkingFee(BigDecimal parkingFee) {
        this.parkingFee = parkingFee;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
