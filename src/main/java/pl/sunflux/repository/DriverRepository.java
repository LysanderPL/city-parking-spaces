package pl.sunflux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sunflux.entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    public Driver findByDriverIdCard(String driverIdCard);
}
