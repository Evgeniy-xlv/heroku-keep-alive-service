package ru.xlv.hka.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("heroku-keep-alive")
public class HkaProperties {

    public static final String ENDPOINT_PATH = "${heroku-keep-alive.endpoint-path:/hka/ping}";
    public static final String SCHEDULER_PERIOD_CRON = "${heroku-keep-alive.ping-period-cron:0 */2 * * * ?}";

    private String domain;
    private String endpointPath;
}
