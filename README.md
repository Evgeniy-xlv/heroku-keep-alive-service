# heroku-keep-alive-service

[![](https://jitpack.io/v/Evgeniy-xlv/heroku-keep-alive-service.svg)](https://jitpack.io/#Evgeniy-xlv/heroku-keep-alive-service)

A service keeps your heroku app full time alive and available.

## How to use:

* open the main class of your spring application and annotate it by `@EnableScheduling`. 
  Also, you must modify `@SpringBootApplication` to `@SpringBootApplication(scanBasePackages = "ru.xlv.hka")`