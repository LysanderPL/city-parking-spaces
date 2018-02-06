package pl.sunflux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sunflux.entity.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    public Currency findByCurrencyCode(String currencyCode);
}
