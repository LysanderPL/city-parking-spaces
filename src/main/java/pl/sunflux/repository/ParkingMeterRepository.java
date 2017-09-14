package pl.sunflux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sunflux.entity.ParkingMeter;

/**
 * Created by maciej on 14.09.17.
 */
@Repository
public interface ParkingMeterRepository extends JpaRepository<ParkingMeter, Long> {
    public ParkingMeter findBySerialNumberAndIsFreeTrue(String serialNumber);
}
