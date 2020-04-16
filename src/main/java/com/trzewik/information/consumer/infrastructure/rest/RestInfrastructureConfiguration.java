package com.trzewik.information.consumer.infrastructure.rest;

import com.trzewik.information.consumer.domain.information.InformationReceiver;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
    FeignAutoConfiguration.class,
    FeignConfig.class,
    HttpMessageConvertersAutoConfiguration.class
})
@EnableFeignClients(basePackages = "com.trzewik.information.consumer.infrastructure.rest")
@Configuration
public class RestInfrastructureConfiguration {

    @Bean
    InformationReceiver informationReceiver(InformationProducerClient informationProducerClient) {
        return new FeignReceiver(informationProducerClient);
    }
}
