package pl.sunflux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sunflux.entity.Driver;

/**
 * Created by maciej on 14.09.17.
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    public Driver findByPesel(String pesel);
}
