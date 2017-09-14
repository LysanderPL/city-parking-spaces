package pl.sunflux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sunflux.entity.Currency;

/**
 * Created by maciej on 14.09.17.
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
