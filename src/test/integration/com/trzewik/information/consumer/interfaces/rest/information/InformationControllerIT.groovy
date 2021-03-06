package com.trzewik.information.consumer.interfaces.rest.information

import com.trzewik.information.consumer.domain.information.Information
import com.trzewik.information.consumer.domain.information.InformationCreation
import com.trzewik.information.consumer.domain.information.InformationFormCreation
import com.trzewik.information.consumer.domain.information.InformationService
import com.trzewik.information.consumer.interfaces.rest.RestInterfacesConfiguration
import groovy.json.JsonSlurper
import io.restassured.response.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import spock.lang.Shared
import spock.lang.Specification

@ActiveProfiles([RestInterfacesTestConfig.PROFILE, 'test'])
@SpringBootTest(
    classes = [RestInterfacesConfiguration, RestInterfacesTestConfig],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class InformationControllerIT extends Specification implements InformationCreation, InformationRequestSender, InformationFormCreation, InformationResponseValidator {
    @Shared
    JsonSlurper slurper = new JsonSlurper()

    @Autowired
    InformationService informationServiceMock

    @LocalServerPort
    int port

    def 'should get information by id'() {
        given:
            Information information = createInformation()
        when:
            Response response = getInformationRequest(information.id)
        then:
            1 * informationServiceMock.get(information.id) >> information
        and:
            response.statusCode() == 200
        and:
            validateResponse(response, information)
    }

    def 'should create information'() {
        given:
            InformationService.InformationForm form = createInformationForm()
        and:
            Information information = createInformation(new InformationCreator('information-id', form))
        when:
            Response response = postInformationRequest(form)
        then:
            1 * informationServiceMock.create(form) >> information
        and:
            response.statusCode() == 201
        and:
            validateResponse(response, information)
    }

    def 'should replace information'() {
        given:
            InformationService.InformationForm form = createInformationForm()
        and:
            String id = 'information-id'
        and:
            Information information = createInformation(new InformationCreator(id, form))
        when:
            Response response = putInformationRequest(id, form)
        then:
            1 * informationServiceMock.replace(id, form) >> information
        and:
            response.statusCode() == 200
        and:
            validateResponse(response, information)
    }

    def 'should update information'() {
        given:
            InformationService.InformationForm form = createInformationForm()
        and:
            String id = 'information-id'
        and:
            Information information = createInformation(new InformationCreator(id, form))
        when:
            Response response = patchInformationRequest(id, form)
        then:
            1 * informationServiceMock.update(id, form) >> information
        and:
            response.statusCode() == 200
        and:
            validateResponse(response, information)
    }

    def 'should delete information'() {
        given:
            Information information = createInformation()
        when:
            Response response = deleteInformationRequest(information.id)
        then:
            1 * informationServiceMock.delete(information.id) >> information
        and:
            response.statusCode() == 200
        and:
            validateResponse(response, information)
    }

    def 'should handle exception and wrap it in error dto'() {
        given:
            String id = 'example-id'
        and:
            String exceptionMessage = 'Example exception msg'
        when:
            Response response = deleteInformationRequest(id)
        then:
            1 * informationServiceMock.delete(id) >> { throw new RuntimeException(exceptionMessage) }
        and:
            response.statusCode() == 500
        and:
            validateErrorResponse(response, exceptionMessage)
    }
}
