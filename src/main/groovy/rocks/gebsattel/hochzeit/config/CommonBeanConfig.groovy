package rocks.gebsattel.hochzeit.config

import org.jasypt.util.password.StrongPasswordEncryptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommonBeanConfig {

    @Bean
    StrongPasswordEncryptor strongEncryptor(){
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor()
    }
}
