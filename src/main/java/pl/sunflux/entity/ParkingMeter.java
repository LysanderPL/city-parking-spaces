package pl.sunflux.entity;

import javax.persistence.*;

/**
 * Created by maciej on 14.09.17.
 */
@Table(name = "parking_meter")
@Entity
public class ParkingMeter {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "serial_number", nullable = false, unique = true)
    private String serialNumber;
    @Column(name = "is_free", nullable = false)
    private Boolean isFree = true;

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

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }
}
