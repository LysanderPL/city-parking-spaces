package pl.sunflux.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.sunflux.controllers.rest.containers.driver.ParkingFeeContainer;
import pl.sunflux.controllers.rest.containers.driver.StartParkingContainer;
import pl.sunflux.controllers.rest.containers.driver.StopParkingContainer;
import pl.sunflux.controllers.rest.responses.ValidationError;
import pl.sunflux.controllers.rest.responses.ValidationErrorBuilder;
import pl.sunflux.controllers.rest.responses.driver.ParkingFeeResponse;
import pl.sunflux.controllers.rest.responses.driver.StartParkingResponse;
import pl.sunflux.controllers.rest.responses.driver.StopParkingResponse;
import pl.sunflux.parking.ParkingFeeInterface;
import pl.sunflux.parking.ParkingStartInterface;
import pl.sunflux.parking.ParkingStopInterface;

import javax.validation.Valid;

@RequestMapping(value = "driver")
@RestController
public class DriverController {

    private final ParkingFeeInterface parkingFeeInterface;
    private final ParkingStopInterface parkingStopInterface;
    private final ParkingStartInterface parkingStartInterface;

    @Autowired
    public DriverController(ParkingFeeInterface parkingFeeInterface, ParkingStopInterface parkingStopInterface, ParkingStartInterface parkingStartInterface) {
        this.parkingFeeInterface = parkingFeeInterface;
        this.parkingStopInterface = parkingStopInterface;
        this.parkingStartInterface = parkingStartInterface;
    }

    @RequestMapping(value = "start-parking", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<StartParkingResponse> startParking(@Valid @RequestBody StartParkingContainer startParkingContainer) {
        return new ResponseEntity<>(parkingStartInterface.prepareResponse(startParkingContainer), HttpStatus.OK);
    }

    @RequestMapping(value = "stop-parking", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<StopParkingResponse> stopParking(@Valid @RequestBody StopParkingContainer stopParkingContainer) {
        return new ResponseEntity<>(parkingStopInterface.prepareResponse(stopParkingContainer), HttpStatus.OK);
    }

    @RequestMapping(value = "parking-fee", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<ParkingFeeResponse> parkingFee(@Valid @RequestBody ParkingFeeContainer parkingFeeContainer) {
        return new ResponseEntity<>(parkingFeeInterface.prepareResponse(parkingFeeContainer), HttpStatus.OK);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(MethodArgumentNotValidException exception) {
        return createValidationError(exception);
    }

    private ValidationError createValidationError(MethodArgumentNotValidException e) {
        return ValidationErrorBuilder.fromBindingErrors(e.getBindingResult());
    }
}
