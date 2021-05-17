package ru.xlv.hka.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.xlv.hka.configuration.HkaProperties;

@RestController
public class HkaController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(HkaProperties.ENDPOINT_PATH)
    public void ping() {}
}
