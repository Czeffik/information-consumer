package com.trzewik.information.consumer;

import com.trzewik.information.consumer.infrastructure.DomainConfiguration;
import com.trzewik.information.consumer.infrastructure.InfrastructureConfiguration;
import com.trzewik.information.consumer.interfaces.InterfacesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import(
    {
        DomainConfiguration.class,
        InfrastructureConfiguration.class,
        InterfacesConfiguration.class,

        ConfigurationPropertiesAutoConfiguration.class,
        PropertyPlaceholderAutoConfiguration.class
    }
)
@SpringBootConfiguration
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
