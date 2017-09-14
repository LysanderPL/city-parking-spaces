package pl.sunflux.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by maciej on 13.09.17.
 */
@Configuration
@EnableJpaRepositories({
        "pl.sunflux.repository"
})
@EntityScan({
        "pl.sunflux.entity"
})
public class DbConfiguration {
}
