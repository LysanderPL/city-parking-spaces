package pl.sunflux.parking;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sunflux.controllers.rest.containers.driver.StopParkingContainer;
import pl.sunflux.controllers.rest.responses.driver.StopParkingResponse;
import pl.sunflux.entity.Driver;
import pl.sunflux.entity.ParkingMeter;
import pl.sunflux.entity.ParkingMeterUsage;
import pl.sunflux.entity.Vehicle;
import pl.sunflux.repository.DriverRepository;
import pl.sunflux.repository.ParkingMeterRepository;
import pl.sunflux.repository.ParkingMeterUsageRepository;
import pl.sunflux.repository.VehicleRepository;

@Service
public class ParkingStopImpl implements ParkingStopInterface {

    private final ParkingMeterUsageRepository parkingMeterUsageRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingMeterRepository parkingMeterRepository;

    @Autowired
    public ParkingStopImpl(ParkingMeterUsageRepository parkingMeterUsageRepository, DriverRepository driverRepository, VehicleRepository vehicleRepository, ParkingMeterRepository parkingMeterRepository) {
        this.parkingMeterUsageRepository = parkingMeterUsageRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingMeterRepository = parkingMeterRepository;
    }

    @Override
    public StopParkingResponse prepareResponse(StopParkingContainer stopParkingContainer) {
        Driver driver = driverRepository.findByPesel(stopParkingContainer.getPesel());
        Vehicle vehicle = vehicleRepository.findBySerialNumberAndDriver(stopParkingContainer.getVehicleSerialNumber(), driver);
        ParkingMeterUsage parkingMeterUsage = parkingMeterUsageRepository.findByIdAndVehicle(stopParkingContainer.getParkingTicketId(), vehicle);

        StopParkingResponse stopParkingResponse = new StopParkingResponse();

        if (parkingMeterUsage == null) {
            return stopParkingResponse;
        }

        DateTime dateEnd = DateTime.now();
        parkingMeterUsage.setDateEnd(dateEnd.toDate());
        parkingMeterUsageRepository.save(parkingMeterUsage);

        ParkingMeter parkingMeter = parkingMeterUsage.getParkingMeter();
        parkingMeter.setFree(true);
        parkingMeterRepository.save(parkingMeter);

        stopParkingResponse.setParkingStopped(true);
        return stopParkingResponse;
    }
}
