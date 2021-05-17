package ru.xlv.hka.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Configuration
@EnableConfigurationProperties(HkaProperties.class)
public class HkaConfiguration {

    @Bean
    public WebClient hkaWebClient(HkaProperties hkaProperties) {
        return WebClient.builder()
                .baseUrl(Objects.requireNonNull(hkaProperties.getDomain(),
                        "Domain is null! Define heroku-keep-alive.domain=<your_domain> to your app properties file."))
                .build();
    }
}
