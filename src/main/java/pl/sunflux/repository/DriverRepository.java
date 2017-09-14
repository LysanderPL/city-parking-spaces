package pl.sunflux.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sunflux.entity.Driver;

/**
 * Created by maciej on 14.09.17.
 */
@Repository
public interface DriverRepository extends CrudRepository<Driver, Long> {
    public Driver findByDriverUniqueName(String driverUniqueName);
}
