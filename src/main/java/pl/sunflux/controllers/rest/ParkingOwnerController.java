package pl.sunflux.controllers.rest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.sunflux.controllers.rest.responses.owner.CheckTodayEarningsResponse;
import pl.sunflux.repository.CurrencyRepository;
import pl.sunflux.repository.ParkingFeeRepository;

import java.math.BigDecimal;
import java.util.Date;

@RequestMapping(value = "parking-owner")
@RestController
public class ParkingOwnerController {

    private final ParkingFeeRepository parkingFeeRepository;
    private final CurrencyRepository currencyRepository;

    @Autowired
    public ParkingOwnerController(ParkingFeeRepository parkingFeeRepository, CurrencyRepository currencyRepository) {
        this.parkingFeeRepository = parkingFeeRepository;
        this.currencyRepository = currencyRepository;
    }

    @RequestMapping(value = "check-today-earnings", method = RequestMethod.GET)
    public ResponseEntity<CheckTodayEarningsResponse> checkTodayEarnings() {
        CheckTodayEarningsResponse checkTodayEarningsResponse = new CheckTodayEarningsResponse();

        Date dateStart = DateTime.now().withTimeAtStartOfDay().toDate();
        Date dateEnd = DateTime.now().withTime(23, 59, 59, 999).toDate();

        BigDecimal earnings = parkingFeeRepository.getSumOfErningsFromToday(dateStart, dateEnd, currencyRepository.findByCurrencyCode("PLN"));
        checkTodayEarningsResponse.setEarnings(earnings);

        return new ResponseEntity<>(checkTodayEarningsResponse, HttpStatus.OK);
    }

}
