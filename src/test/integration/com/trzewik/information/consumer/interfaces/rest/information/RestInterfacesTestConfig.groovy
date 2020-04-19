package com.trzewik.information.consumer.interfaces.rest.information

import com.trzewik.information.consumer.domain.information.InformationService
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import spock.mock.DetachedMockFactory

@TestConfiguration
class RestInterfacesTestConfig {
    DetachedMockFactory factory = new DetachedMockFactory()

    @Bean
    InformationService informationServiceMock() {
        return factory.Mock(InformationService)
    }
}
