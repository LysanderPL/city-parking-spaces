package pl.sunflux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sunflux.entity.Currency;
import pl.sunflux.entity.ParkingFee;

import java.math.BigDecimal;
import java.util.Date;

@Repository
public interface ParkingFeeRepository extends JpaRepository<ParkingFee, Long> {

    @Query("SELECT SUM(pf.amount) FROM ParkingFee pf WHERE pf.currency = ?3 AND pf.dateAdd BETWEEN ?1 AND ?2")
    public BigDecimal getSumOfErningsFromToday(Date dateStart, Date dateEnd, Currency currency);
}
