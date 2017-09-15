package pl.sunflux.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories({
        "pl.sunflux.repository"
})
@EntityScan({
        "pl.sunflux.entity"
})
public class DbConfiguration {
}
