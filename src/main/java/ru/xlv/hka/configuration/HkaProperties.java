package ru.xlv.hka.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties("heroku-keep-alive")
public class HkaProperties {

    public static final String ENDPOINT_PATH = "${heroku-keep-alive.endpoint-path:/hka/ping}";
    public static final String SCHEDULER_PERIOD_CRON = "${heroku-keep-alive.ping-period-cron:0 */2 * * * ?}";

    private String endpointPath = "/hka/ping";

    private String domain;
    private Map<String, String[]> headers = new HashMap<>();

    private Credentials authorization;

    @Data
    public static class Credentials {
        private String username;
        private String password;
    }
}
