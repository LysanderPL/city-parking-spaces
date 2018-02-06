package pl.sunflux.entity;

import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "parking_fee")
@Entity
public class ParkingFee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Digits(integer = 19, fraction = 16)
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "date_add", nullable = false)
    private Date dateAdd = DateTime.now().toDate();
    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
