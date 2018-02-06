package pl.sunflux.entity;

import pl.sunflux.entity.constants.DriverTypeEnum;

import javax.persistence.*;

@Table(name = "driver")
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "driver_id_card", unique = true, nullable = false)
    private String driverIdCard;
    @Column(name = "driver_type", nullable = false)
    private DriverTypeEnum driverTypeEnum = DriverTypeEnum.REGULAR;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDriverIdCard() {
        return driverIdCard;
    }

    public void setDriverIdCard(String driverIdCard) {
        this.driverIdCard = driverIdCard;
    }

    public DriverTypeEnum getDriverTypeEnum() {
        return driverTypeEnum;
    }

    public void setDriverTypeEnum(DriverTypeEnum driverTypeEnum) {
        this.driverTypeEnum = driverTypeEnum;
    }
}
