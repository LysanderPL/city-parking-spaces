package pl.sunflux.parking;

import org.joda.time.DateTime;
import org.joda.time.Period;
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
        if (parkingMeterUsage == null || parkingMeterUsage.getDateStart() == null || parkingMeterUsage.getDateEnd() == null) {
            throw new NoCalculationException();
        }

        BigDecimal sum = new BigDecimal(0);
        Period period = new Period(new DateTime(parkingMeterUsage.getDateStart()), new DateTime(parkingMeterUsage.getDateEnd()));
        Integer hours = period.getHours();
        if (period.getMinutes() > 0) {
            hours++;
        }

        Integer counter = 0;
        BigDecimal last = new BigDecimal(1);

        switch (parkingMeterUsage.getVehicle().getDriver().getDriverTypeEnum()) {
            case VIP:
                while (counter < hours) {
                    if (counter < 1) {
                    } else if (counter < 2) {
                        sum = sum.add(last = new BigDecimal(2));
                    } else if (counter.compareTo(3) <= 1) {
                        sum = sum.add(last = new BigDecimal(last.doubleValue() * 1.5));
                    }
                    counter++;
                }
            case REGULAR:
                while (counter < hours) {
                    if (counter < 1) {
                        sum = sum.add(new BigDecimal(1));
                    } else if (counter.compareTo(3) <= 1) {
                        sum = sum.add(last = new BigDecimal(last.doubleValue() * 2));
                    }
                    counter++;
                }
        }

        return new BigDecimal(sum.doubleValue() * parkingMeterUsage.getCurrency().getCurrencyCourseToPLN().doubleValue());
    }
}
