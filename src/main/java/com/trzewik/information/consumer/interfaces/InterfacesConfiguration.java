package com.trzewik.information.consumer.interfaces;

import com.trzewik.information.consumer.interfaces.rest.RestInterfacesConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
    RestInterfacesConfiguration.class
})
@Configuration
public class InterfacesConfiguration {
}
