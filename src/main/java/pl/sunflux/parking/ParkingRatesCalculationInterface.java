package pl.sunflux.parking;

import pl.sunflux.entity.ParkingMeterUsage;

import java.math.BigDecimal;

/**
 * Created by maciej on 14.09.17.
 */
public interface ParkingRatesCalculationInterface {
    public BigDecimal calculateParkingFee(ParkingMeterUsage parkingMeterUsage) throws NoCalculationException;
}
