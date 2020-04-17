package com.trzewik.information.consumer.infrastructure;

import com.trzewik.information.consumer.domain.information.InformationClient;
import com.trzewik.information.consumer.domain.information.InformationService;
import com.trzewik.information.consumer.domain.information.InformationServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {
    @Bean
    InformationService informationService(
        InformationClient informationReceiver
    ) {
        return InformationServiceFactory.create(informationReceiver);
    }
}
