package ru.xlv.hka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HerokuKeepAliveServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HerokuKeepAliveServiceApplication.class, args);
    }

}
