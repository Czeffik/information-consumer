package com.trzewik.information.consumer.infrastructure;

import com.trzewik.information.consumer.infrastructure.rest.RestInfrastructureConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
    RestInfrastructureConfiguration.class
})
@Configuration
public class InfrastructureConfiguration {
}
