package pl.sunflux.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by maciej on 14.09.17.
 */
@Table(name = "currency")
@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "currency_code", nullable = false, unique = true)
    private String currencyCode;
    @Column(name = "currency_course_to_PLN", nullable = false)
    private BigDecimal currencyCourseToPLN;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getCurrencyCourseToPLN() {
        return currencyCourseToPLN;
    }

    public void setCurrencyCourseToPLN(BigDecimal currencyCourseToPLN) {
        this.currencyCourseToPLN = currencyCourseToPLN;
    }
}
