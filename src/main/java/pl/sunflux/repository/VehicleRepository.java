package pl.sunflux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sunflux.entity.Driver;
import pl.sunflux.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    public Vehicle findBySerialNumberAndDriver(String serialNumber, Driver driver);
    public Vehicle findBySerialNumber(String serialNumber);
}
