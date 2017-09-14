package pl.sunflux.parking;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.sunflux.controllers.rest.containers.driver.StopParkingContainer;
import pl.sunflux.controllers.rest.responses.driver.StopParkingResponse;
import pl.sunflux.entity.*;
import pl.sunflux.repository.*;

@Service
public class ParkingStopImpl implements ParkingStopInterface {

    private final ParkingMeterUsageRepository parkingMeterUsageRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingMeterRepository parkingMeterRepository;
    private final ParkingFeeRepository parkingFeeRepository;
    private final ParkingRatesCalculationImpl parkingRatesCalculation;

    @Autowired
    public ParkingStopImpl(ParkingMeterUsageRepository parkingMeterUsageRepository, DriverRepository driverRepository, VehicleRepository vehicleRepository, ParkingMeterRepository parkingMeterRepository, ParkingFeeRepository parkingFeeRepository, ParkingRatesCalculationImpl parkingRatesCalculation) {
        this.parkingMeterUsageRepository = parkingMeterUsageRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingMeterRepository = parkingMeterRepository;
        this.parkingFeeRepository = parkingFeeRepository;
        this.parkingRatesCalculation = parkingRatesCalculation;
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

        saveParkingFee(parkingMeterUsage);

        ParkingMeter parkingMeter = parkingMeterUsage.getParkingMeter();
        parkingMeter.setFree(true);
        parkingMeterRepository.save(parkingMeter);


        stopParkingResponse.setParkingStopped(true);
        return stopParkingResponse;
    }

    @Async
    void saveParkingFee(ParkingMeterUsage parkingMeterUsage) {
        try {
            ParkingFee parkingFee = new ParkingFee();
            parkingFee.setAmount(parkingRatesCalculation.calculateParkingFee(parkingMeterUsage));
            parkingFee.setCurrency(parkingMeterUsage.getCurrency());
            parkingFeeRepository.save(parkingFee);
        } catch (NoCalculationException e) {
            e.printStackTrace();
        }
    }
}
