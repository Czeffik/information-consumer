package com.trzewik.information.consumer.interfaces.rest.information

import com.trzewik.information.consumer.domain.information.InformationService
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import spock.mock.DetachedMockFactory

@Profile(RestInterfacesTestConfig.PROFILE)
@TestConfiguration
class RestInterfacesTestConfig {
    final static String PROFILE = 'rest-interfaces-test-config'
    DetachedMockFactory factory = new DetachedMockFactory()

    @Bean
    InformationService informationServiceMock() {
        return factory.Mock(InformationService)
    }
}
