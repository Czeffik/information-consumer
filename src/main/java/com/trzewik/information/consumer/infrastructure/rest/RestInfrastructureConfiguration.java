package com.trzewik.information.consumer.infrastructure.rest;

import com.trzewik.information.consumer.infrastructure.rest.information.InformationProducerClient;
import feign.codec.ErrorDecoder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.cloud.commons.httpclient.ApacheHttpClientConnectionManagerFactory;
import org.springframework.cloud.commons.httpclient.ApacheHttpClientFactory;
import org.springframework.cloud.commons.httpclient.DefaultApacheHttpClientConnectionManagerFactory;
import org.springframework.cloud.commons.httpclient.DefaultApacheHttpClientFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
    FeignAutoConfiguration.class,
    HttpMessageConvertersAutoConfiguration.class
})
@EnableFeignClients(clients = {InformationProducerClient.class})
@Configuration
public class RestInfrastructureConfiguration {

    @Bean
    ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }

    /*
    Below beans adding possibility to use PATCH method in FeignClients.
     */
    @Bean
    ApacheHttpClientFactory apacheHttpClientFactory(HttpClientBuilder httpClientBuilder) {
        return new DefaultApacheHttpClientFactory(httpClientBuilder);
    }

    @Bean
    HttpClientBuilder httpClientBuilder() {
        return HttpClientBuilder.create();
    }

    @Bean
    ApacheHttpClientConnectionManagerFactory apacheHttpClientConnectionManagerFactory() {
        return new DefaultApacheHttpClientConnectionManagerFactory();
    }
}
