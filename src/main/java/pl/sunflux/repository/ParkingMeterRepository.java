package pl.sunflux.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sunflux.entity.ParkingMeter;

/**
 * Created by maciej on 14.09.17.
 */
@Repository
public interface ParkingMeterRepository extends CrudRepository<ParkingMeter, Long> {
    public ParkingMeter getFirstByFree(Boolean free);
}
