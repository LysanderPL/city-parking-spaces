package pl.sunflux.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sunflux.controllers.rest.responses.operator.CheckVehicleResponse;
import pl.sunflux.entity.ParkingMeterUsage;
import pl.sunflux.entity.Vehicle;
import pl.sunflux.repository.ParkingMeterUsageRepository;
import pl.sunflux.repository.VehicleRepository;

/**
 * Created by maciej on 14.09.17.
 */
@RequestMapping(value = "parking-operator")
@RestController
public class ParkingOperatorController {

    private final VehicleRepository vehicleRepository;
    private final ParkingMeterUsageRepository parkingMeterUsageRepository;

    @Autowired
    public ParkingOperatorController(VehicleRepository vehicleRepository, ParkingMeterUsageRepository parkingMeterUsageRepository) {
        this.vehicleRepository = vehicleRepository;
        this.parkingMeterUsageRepository = parkingMeterUsageRepository;
    }

    @RequestMapping(value = "check-vehicle/{serial-number}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<CheckVehicleResponse> checkVehicle(@PathVariable(value = "serial-number") String serialNumber) {
        Vehicle vehicle = vehicleRepository.findBySerialNumber(serialNumber);
        ParkingMeterUsage parkingMeterUsage = parkingMeterUsageRepository.findFirstByVehicleOrderByIdDesc(vehicle);

        CheckVehicleResponse checkVehicleResponse = new CheckVehicleResponse();

        if (parkingMeterUsage == null || parkingMeterUsage.getDateEnd() != null) {
            return new ResponseEntity<>(checkVehicleResponse, HttpStatus.OK);
        }

        checkVehicleResponse.setVehicleHasStartedParkingMeter(true);
        return new ResponseEntity<>(checkVehicleResponse, HttpStatus.OK);
    }

}
