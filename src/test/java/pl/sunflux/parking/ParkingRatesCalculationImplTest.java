package pl.sunflux.parking;

import org.junit.Test;
import pl.sunflux.entity.*;
import pl.sunflux.entity.constants.DriverTypeEnum;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by maciej on 14.09.17.
 */
public class ParkingRatesCalculationImplTest {

    @Test
    public void testCalculationForVip() {

//        assertEquals();
    }

    @Test
    public void testCalculationForRegular() throws ParseException {
        Currency currency = new Currency();
        currency.setCurrencyCourseToPLN(new BigDecimal(1));

        Driver driver = new Driver();
        driver.setDriverTypeEnum(DriverTypeEnum.REGULAR);

        Vehicle vehicle = new Vehicle();
        vehicle.setDriver(driver);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ParkingMeterUsage parkingMeterUsage = new ParkingMeterUsage();
        parkingMeterUsage.setCurrency(currency);
        parkingMeterUsage.setDateStart(df.parse("2017-09-14 13:00:00"));
        parkingMeterUsage.setDateEnd(df.parse("2017-09-14 14:00:00"));
        parkingMeterUsage.setParkingMeter(new ParkingMeter());
        parkingMeterUsage.setVehicle(vehicle);

        ParkingRatesCalculationInterface parkingRatesCalculation = new ParkingRatesCalculationImpl();
        BigDecimal result = parkingRatesCalculation.calculateParkingFee(parkingMeterUsage);

        assertEquals(new BigDecimal(1), result);
    }

    @Test(expected = NoCalculationException.class)
    public void testNoCalculationException() {

    }


}
