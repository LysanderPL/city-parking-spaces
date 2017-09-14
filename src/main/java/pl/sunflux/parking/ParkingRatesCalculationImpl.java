package pl.sunflux.parking;

import org.springframework.stereotype.Service;
import pl.sunflux.entity.ParkingMeterUsage;

import java.math.BigDecimal;

/**
 * Created by maciej on 14.09.17.
 */
@Service
public class ParkingRatesCalculationImpl implements ParkingRatesCalculationInterface {
    @Override
    public BigDecimal calculateParkingFee(ParkingMeterUsage parkingMeterUsage) throws NoCalculationException {


        return null;
    }
}
