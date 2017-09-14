package pl.sunflux.parking;

import org.junit.Before;
import org.junit.Test;
import pl.sunflux.entity.*;
import pl.sunflux.entity.constants.DriverTypeEnum;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by maciej on 14.09.17.
 */
public class ParkingRatesCalculationImplTest {

    private ParkingMeterUsage parkingMeterUsage = new ParkingMeterUsage();
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Before
    public void prepareData() {
        Currency currency = new Currency();
        currency.setCurrencyCourseToPLN(new BigDecimal(1));

        Driver driver = new Driver();

        Vehicle vehicle = new Vehicle();
        vehicle.setDriver(driver);

        parkingMeterUsage.setCurrency(currency);
        parkingMeterUsage.setParkingMeter(new ParkingMeter());
        parkingMeterUsage.setVehicle(vehicle);
    }

    @Test
    public void testCalculationForVip() throws ParseException, NoCalculationException {
        ParkingRatesCalculationInterface parkingRatesCalculation = new ParkingRatesCalculationImpl();
        parkingMeterUsage.getVehicle().getDriver().setDriverTypeEnum(DriverTypeEnum.VIP);

        String[] dateStart = new String[]{"2017-09-14 13:00:00", "2017-09-14 13:30:00", "2017-09-14 13:00:00", "2017-09-14 13:00:00", "2017-09-14 13:00:00", "2017-09-14 13:00:00", "2017-09-14 13:00:00"};
        String[] dateEnd = new String[]{"2017-09-14 14:00:00", "2017-09-14 14:00:00", "2017-09-14 15:00:00", "2017-09-14 16:00:00", "2017-09-14 17:00:00", "2017-09-14 18:00:00", "2017-09-14 19:00:00"};
        Double[] expectedResult = new Double[]{0.0, 0.0, 2.0, 5.0, 9.5, 16.25, 26.375};

        makeAssertions(parkingRatesCalculation, dateStart, dateEnd, expectedResult);
    }

    @Test
    public void testCalculationForRegular() throws ParseException, NoCalculationException {
        ParkingRatesCalculationInterface parkingRatesCalculation = new ParkingRatesCalculationImpl();
        parkingMeterUsage.getVehicle().getDriver().setDriverTypeEnum(DriverTypeEnum.REGULAR);

        String[] dateStart = new String[]{"2017-09-14 13:00:00", "2017-09-14 13:30:00", "2017-09-14 13:00:00", "2017-09-14 13:00:00", "2017-09-14 13:00:00", "2017-09-14 13:00:00", "2017-09-14 13:00:00"};
        String[] dateEnd = new String[]{"2017-09-14 14:00:00", "2017-09-14 14:00:00", "2017-09-14 15:00:00", "2017-09-14 16:00:00", "2017-09-14 17:00:00", "2017-09-14 18:00:00", "2017-09-14 19:00:00"};
        Double[] expectedResult = new Double[]{1.0, 1.0, 3.0, 7.0, 15.0, 31.0, 63.0};

        makeAssertions(parkingRatesCalculation, dateStart, dateEnd, expectedResult);
    }

    private void makeAssertions(ParkingRatesCalculationInterface parkingRatesCalculation, String[] dateStart, String[] dateEnd, Double[] expectedResult) throws ParseException, NoCalculationException {
        for (int i = 0; i < dateEnd.length; i++) {
            parkingMeterUsage.setDateStart(df.parse(dateStart[i]));
            parkingMeterUsage.setDateEnd(df.parse(dateEnd[i]));
            BigDecimal result = parkingRatesCalculation.calculateParkingFee(parkingMeterUsage);
            assertEquals(new BigDecimal(expectedResult[i]), result);
        }
    }

    @Test(expected = NoCalculationException.class)
    public void testNoCalculationException() throws NoCalculationException {
        ParkingRatesCalculationInterface parkingRatesCalculation = new ParkingRatesCalculationImpl();
        parkingRatesCalculation.calculateParkingFee(null);
    }


}
