package rocks.gebsattel.hochzeit.config

import org.jasypt.util.password.StrongPasswordEncryptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories("rocks.gebsattel.hochzeit.repositories")
class CommonBeanConfig {

    @Bean
    StrongPasswordEncryptor strongEncryptor(){
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor()
    }
}
