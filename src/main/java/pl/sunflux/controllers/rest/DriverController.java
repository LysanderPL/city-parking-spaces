package pl.sunflux.controllers.rest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.sunflux.controllers.rest.containers.driver.ParkingFeeContainer;
import pl.sunflux.controllers.rest.containers.driver.StartParkingContainer;
import pl.sunflux.controllers.rest.containers.driver.StopParkingContainer;
import pl.sunflux.controllers.rest.responses.driver.ParkingFeeResponse;
import pl.sunflux.controllers.rest.responses.driver.StartParkingResponse;
import pl.sunflux.controllers.rest.responses.driver.StopParkingResponse;
import pl.sunflux.entity.Driver;
import pl.sunflux.entity.ParkingMeter;
import pl.sunflux.entity.ParkingMeterUsage;
import pl.sunflux.entity.Vehicle;
import pl.sunflux.entity.constants.DriverTypeEnum;
import pl.sunflux.parking.NoCalculationException;
import pl.sunflux.parking.ParkingRatesCalculationInterface;
import pl.sunflux.repository.*;

import java.math.BigDecimal;

/**
 * Created by maciej on 14.09.17.
 */
@RequestMapping(value = "driver")
@RestController
public class DriverController {

    private final ParkingMeterRepository parkingMeterRepository;

    private final DriverRepository driverRepository;

    private final VehicleRepository vehicleRepository;

    private final ParkingMeterUsageRepository parkingMeterUsageRepository;

    private final CurrencyRepository currencyRepository;

    private final ParkingRatesCalculationInterface parkingRatesCalculationInterface;

    @Autowired
    public DriverController(ParkingMeterRepository parkingMeterRepository, DriverRepository driverRepository, VehicleRepository vehicleRepository, ParkingMeterUsageRepository parkingMeterUsageRepository, CurrencyRepository currencyRepository, ParkingRatesCalculationInterface parkingRatesCalculationInterface) {
        this.parkingMeterRepository = parkingMeterRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingMeterUsageRepository = parkingMeterUsageRepository;
        this.currencyRepository = currencyRepository;
        this.parkingRatesCalculationInterface = parkingRatesCalculationInterface;
    }

    @RequestMapping(value = "start-parking", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<StartParkingResponse> startParking(@RequestBody StartParkingContainer startParkingContainer) {
        ParkingMeter parkingMeter = parkingMeterRepository.findBySerialNumberAndIsFreeTrue(startParkingContainer.getParkingMeterSerialNumber());
        StartParkingResponse startParkingResponse = new StartParkingResponse();

        if (parkingMeter == null) {
            return new ResponseEntity<>(startParkingResponse, HttpStatus.NOT_ACCEPTABLE);
        }

        parkingMeter.setFree(false);
        parkingMeterRepository.save(parkingMeter);

        Driver driver = driverRepository.findByPesel(startParkingContainer.getPesel());
        if (driver == null) {
            driver = new Driver();
            driver.setPesel(startParkingContainer.getPesel());
            driver.setDriverTypeEnum(DriverTypeEnum.REGULAR);
            driverRepository.save(driver);
        }

        Vehicle vehicle = vehicleRepository.findBySerialNumberAndDriver(startParkingContainer.getVehicleSerialNumber(), driver);
        if (vehicle == null) {
            vehicle = new Vehicle();
            vehicle.setSerialNumber(startParkingContainer.getVehicleSerialNumber());
            vehicle.setDriver(driver);
            vehicleRepository.save(vehicle);
        }

        DateTime dateStart = DateTime.now();

        ParkingMeterUsage parkingMeterUsage = new ParkingMeterUsage();
        parkingMeterUsage.setDateStart(dateStart.toDate());
        parkingMeterUsage.setVehicle(vehicle);
        parkingMeterUsage.setParkingMeter(parkingMeter);
        parkingMeterUsage.setCurrency(currencyRepository.findByCurrencyCode("PLN"));

        parkingMeterUsageRepository.save(parkingMeterUsage);

        startParkingResponse.setDateStart(dateStart.toString());
        startParkingResponse.setDriverType(driver.getDriverTypeEnum());
        startParkingResponse.setParkingTicketId(parkingMeterUsage.getId());
        startParkingResponse.setParkingStarted(true);

        return new ResponseEntity<>(startParkingResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "stop-parking", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<StopParkingResponse> stopParking(@RequestBody StopParkingContainer stopParkingContainer) {
        Driver driver = driverRepository.findByPesel(stopParkingContainer.getPesel());
        Vehicle vehicle = vehicleRepository.findBySerialNumberAndDriver(stopParkingContainer.getVehicleSerialNumber(), driver);
        ParkingMeterUsage parkingMeterUsage = parkingMeterUsageRepository.findByIdAndVehicle(stopParkingContainer.getParkingTicketId(), vehicle);

        StopParkingResponse stopParkingResponse = new StopParkingResponse();

        if (parkingMeterUsage == null) {
            return new ResponseEntity<>(stopParkingResponse, HttpStatus.BAD_REQUEST);
        }

        DateTime dateEnd = DateTime.now();
        parkingMeterUsage.setDateEnd(dateEnd.toDate());
        parkingMeterUsageRepository.save(parkingMeterUsage);

        stopParkingResponse.setParkingStopped(true);
        return new ResponseEntity<>(stopParkingResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "parking-fee", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<ParkingFeeResponse> parkingFee(@RequestBody ParkingFeeContainer parkingFeeContainer) {
        ParkingMeterUsage parkingMeterUsage = parkingMeterUsageRepository.findById(parkingFeeContainer.getParkingTicketId()).get();
        ParkingFeeResponse parkingFeeResponse = new ParkingFeeResponse();

        try {
            BigDecimal parkingFee = parkingRatesCalculationInterface.calculateParkingFee(parkingMeterUsage);
            parkingFeeResponse.setParkingFee(parkingFee);
            parkingFeeResponse.setCurrency(parkingMeterUsage.getCurrency());
            return new ResponseEntity<>(parkingFeeResponse, HttpStatus.OK);
        } catch (NoCalculationException e) {
            parkingFeeResponse.setParkingFee(new BigDecimal(0));
            return new ResponseEntity<>(parkingFeeResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
