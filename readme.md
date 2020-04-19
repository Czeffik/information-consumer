# Information-consumer app

Sample application which consumes data via `http` using [FeignClient](https://github.com/OpenFeign/feign)
from [information-producer-service](https://github.com/Czeffik/information-producer) and expose REST endpoints.
Currently, implemented as gateway - this app doesn't modify data consumed from `information-producer-service`.

## Build

LINUX:
```shell script
./gradlew clean build
```

WINDOWS:
```shell script
gradlew clean build
```

## Test

- `./gradlew clean build` automatically run `unit`/`integration`/`functional` tests
- `./gradlew clean test` for running `unit`/`integration`/`functional` tests
- `./gradlew pitest` - run mutation tests - for checking quality of tests

## Run application:
Select one of:
- run `main` method in `com.trzewik.information.consumer.App` from intellij
- run `./gradlew bootRun`
- build application with `./gradlew clean build` and execute: `java -jar build/libs/information-consumer-*.jar`

Application **as default start** on port: `8071` and **expect** that [information-producer-service](https://github.com/Czeffik/information-producer)
will be available at: `localhost:8090`.

If you want change that you can override properties:
- `server.port` - will change this application port
- `url.information.producer` - will change [information-producer-service](https://github.com/Czeffik/information-producer)
address

Application expose [SWAGGER-UI](http://localhost:8071/swagger-ui.html).
