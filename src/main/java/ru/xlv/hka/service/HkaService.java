package ru.xlv.hka.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.xlv.hka.configuration.HkaProperties;

import java.util.Map;

@Service
public class HkaService {

    private final HkaProperties properties;
    private final WebClient webClient;

    public HkaService(HkaProperties properties, @Qualifier("hkaWebClient") WebClient webClient) {
        this.properties = properties;
        this.webClient = webClient;
    }

    @Scheduled(cron = HkaProperties.SCHEDULER_PERIOD_CRON)
    public void ping() {
        doRequest(properties.getHeaders());
    }

    private void doRequest(Map<String, String[]> headers) {
        final WebClient.RequestHeadersSpec<?> requestHeadersSpec = webClient.get().uri(properties.getEndpointPath());
        headers.forEach(requestHeadersSpec::header);
        final ResponseEntity<?> responseEntity = requestHeadersSpec
                .retrieve()
                .toBodilessEntity()
                .block();
        if (responseEntity == null || !responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("An error has occurred during GET request to " + HkaProperties.ENDPOINT_PATH +
                    (responseEntity != null ? ". Status code: " + responseEntity.getStatusCode() : ""));
        }
    }
}
