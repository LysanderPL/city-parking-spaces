package pl.sunflux.controllers.rest.responses.owner;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CheckTodayEarningsResponse {
    @JsonProperty(value = "earnings")
    private BigDecimal earnings;

    public void setEarnings(BigDecimal earnings) {
        this.earnings = earnings;
    }
}
