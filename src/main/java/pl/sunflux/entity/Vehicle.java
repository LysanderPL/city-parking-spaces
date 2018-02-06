package pl.sunflux.entity;

import javax.persistence.*;

@Table(name = "vehicle")
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "serial_number", nullable = false)
    private String serialNumber;
    @ManyToOne
    @JoinColumn(name = "driver")
    private Driver driver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
