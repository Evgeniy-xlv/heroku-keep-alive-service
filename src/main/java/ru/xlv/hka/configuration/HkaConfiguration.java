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
        final WebClient.Builder builder = WebClient.builder()
                .baseUrl(String.format("https://%s.herokuapp.com/", Objects.requireNonNull(hkaProperties.getDomain(),
                        "Domain is null! Define heroku-keep-alive.domain=<your_domain> in your app properties file.")));
        hkaProperties.getHeaders().forEach(builder::defaultHeader);
        if (hkaProperties.getAuthorization().getUsername() != null && hkaProperties.getAuthorization().getPassword() != null) {
            builder.defaultHeaders(header -> header.setBasicAuth(
                    hkaProperties.getAuthorization().getUsername(), hkaProperties.getAuthorization().getPassword()));
        }
        return builder.build();
    }
}
