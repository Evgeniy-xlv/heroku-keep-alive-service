package ru.xlv.hka.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.xlv.hka.configuration.HkaProperties;

@Service
public class HkaService {

    private final HkaProperties properties;
    private final WebClient webClient;

    public HkaService(HkaProperties properties, @Qualifier("hkaWebClient") WebClient webClient) {
        this.properties = properties;
        this.webClient = webClient;
    }

    @Scheduled(cron = HkaProperties.SCHEDULER_PERIOD_CRON)
    public void ping() throws Exception {
        final ResponseEntity<Void> responseEntity = webClient.get()
                .uri(properties.getEndpointPath())
                .retrieve()
                .toBodilessEntity()
                .block();
        if (responseEntity == null || !responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new Exception("An error has occurred during GET request to " + HkaProperties.ENDPOINT_PATH +
                    (responseEntity != null ? ". Status code: " + responseEntity.getStatusCode() : ""));
        }
    }
}
