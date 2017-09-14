package pl.sunflux.controllers.rest;

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
import pl.sunflux.entity.Driver;
import pl.sunflux.entity.ParkingMeter;
import pl.sunflux.entity.ParkingMeterUsage;
import pl.sunflux.entity.Vehicle;
import pl.sunflux.entity.constants.DriverTypeEnum;
import pl.sunflux.repository.DriverRepository;
import pl.sunflux.repository.ParkingMeterRepository;
import pl.sunflux.repository.ParkingMeterUsageRepository;
import pl.sunflux.repository.VehicleRepository;

import java.util.Date;

/**
 * Created by maciej on 14.09.17.
 */
@RequestMapping(value = "driver")
@RestController
public class DriverController {

    @Autowired
    private ParkingMeterRepository parkingMeterRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParkingMeterUsageRepository parkingMeterUsageRepository;

    @RequestMapping(value = "start-parking", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<ParkingMeterUsage> startParking(@RequestBody StartParkingContainer startParkingContainer) {
        ParkingMeter parkingMeter = parkingMeterRepository.findBySerialNumberAndIsFreeTrue(startParkingContainer.getParkingMeterSerialNumber());

        if (parkingMeter == null) {
            return new ResponseEntity<>(new ParkingMeterUsage(), HttpStatus.NOT_ACCEPTABLE);
        }

        parkingMeter.setFree(false);
        parkingMeterRepository.save(parkingMeter);

        Driver driver = driverRepository.findByDriverUniqueName(startParkingContainer.getDriverUniqueName());
        if (driver == null) {
            driver = new Driver();
            driver.setDriverUniqueName(startParkingContainer.getDriverUniqueName());
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

        ParkingMeterUsage parkingMeterUsage = new ParkingMeterUsage();
        parkingMeterUsage.setDateStart(new Date(System.currentTimeMillis()));
        parkingMeterUsage.setVehicle(vehicle);
        parkingMeterUsage.setParkingMeter(parkingMeter);

        parkingMeterUsageRepository.save(parkingMeterUsage);

        return new ResponseEntity<>(parkingMeterUsage, HttpStatus.OK);
    }

    @RequestMapping(value = "stop-parking", produces = "application/json")
    public void stopParking(@RequestBody StopParkingContainer stopParkingContainer) {

    }

    @RequestMapping(value = "parking-fee", produces = "application/json")
    public void parkingFee(@RequestBody ParkingFeeContainer parkingFeeContainer) {

    }
}
