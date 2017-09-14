package pl.sunflux.parking;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sunflux.controllers.rest.containers.driver.StartParkingContainer;
import pl.sunflux.controllers.rest.responses.driver.StartParkingResponse;
import pl.sunflux.entity.Driver;
import pl.sunflux.entity.ParkingMeter;
import pl.sunflux.entity.ParkingMeterUsage;
import pl.sunflux.entity.Vehicle;
import pl.sunflux.entity.constants.DriverTypeEnum;
import pl.sunflux.repository.*;

@Service
public class ParkingStartImpl implements ParkingStartInterface {

    private final ParkingMeterRepository parkingMeterRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final CurrencyRepository currencyRepository;
    private final ParkingMeterUsageRepository parkingMeterUsageRepository;

    @Autowired
    public ParkingStartImpl(ParkingMeterRepository parkingMeterRepository, DriverRepository driverRepository, VehicleRepository vehicleRepository, CurrencyRepository currencyRepository, ParkingMeterUsageRepository parkingMeterUsageRepository) {
        this.parkingMeterRepository = parkingMeterRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
        this.currencyRepository = currencyRepository;
        this.parkingMeterUsageRepository = parkingMeterUsageRepository;
    }

    @Override
    public StartParkingResponse prepareResponse(StartParkingContainer startParkingContainer) {
        ParkingMeter parkingMeter = parkingMeterRepository.findBySerialNumberAndIsFreeTrue(startParkingContainer.getParkingMeterSerialNumber());
        StartParkingResponse startParkingResponse = new StartParkingResponse();

        if (parkingMeter == null) {
            return startParkingResponse;
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

        return startParkingResponse;
    }
}
