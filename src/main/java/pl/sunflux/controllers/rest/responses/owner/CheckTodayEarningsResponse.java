package pl.sunflux.controllers.rest.responses.owner;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public class CheckTodayEarningsResponse {
    @JsonProperty(value = "earnings")
    private BigDecimal earnings;

    public void setEarnings(BigDecimal earnings) {
        this.earnings = earnings;
    }
}
