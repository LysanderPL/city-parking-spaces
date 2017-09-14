package pl.sunflux.parking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sunflux.controllers.rest.containers.driver.ParkingFeeContainer;
import pl.sunflux.controllers.rest.responses.driver.ParkingFeeResponse;
import pl.sunflux.entity.ParkingMeterUsage;
import pl.sunflux.repository.ParkingMeterUsageRepository;

import java.math.BigDecimal;

@Service
public class ParkingFeeImpl implements ParkingFeeInterface {

    private final ParkingMeterUsageRepository parkingMeterUsageRepository;
    private final ParkingRatesCalculationInterface parkingRatesCalculationInterface;

    @Autowired
    public ParkingFeeImpl(ParkingMeterUsageRepository parkingMeterUsageRepository, ParkingRatesCalculationInterface parkingRatesCalculationInterface) {
        this.parkingMeterUsageRepository = parkingMeterUsageRepository;
        this.parkingRatesCalculationInterface = parkingRatesCalculationInterface;
    }

    @Override
    public ParkingFeeResponse prepareResponse(ParkingFeeContainer parkingFeeContainer) {
        ParkingMeterUsage parkingMeterUsage = parkingMeterUsageRepository.findById(parkingFeeContainer.getParkingTicketId()).get();
        ParkingFeeResponse parkingFeeResponse = new ParkingFeeResponse();

        try {
            BigDecimal parkingFee = parkingRatesCalculationInterface.calculateParkingFee(parkingMeterUsage);
            parkingFeeResponse.setParkingFee(parkingFee);
            parkingFeeResponse.setCurrency(parkingMeterUsage.getCurrency());
        } catch (NoCalculationException e) {
            parkingFeeResponse.setParkingFee(new BigDecimal(0));
        }
        return parkingFeeResponse;
    }
}
