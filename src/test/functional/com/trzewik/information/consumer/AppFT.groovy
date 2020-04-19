package com.trzewik.information.consumer

import com.github.tomakehurst.wiremock.WireMockServer
import com.trzewik.information.consumer.domain.information.Information
import com.trzewik.information.consumer.domain.information.InformationCreation
import com.trzewik.information.consumer.domain.information.InformationFormCreation
import com.trzewik.information.consumer.domain.information.InformationService
import com.trzewik.information.consumer.infrastructure.rest.information.InformationProducerStubbing
import com.trzewik.information.consumer.infrastructure.rest.information.InformationProducerVerifying
import com.trzewik.information.consumer.interfaces.rest.information.InformationRequestSender
import com.trzewik.information.consumer.interfaces.rest.information.InformationResponseValidator
import groovy.json.JsonSlurper
import io.restassured.response.Response
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import spock.lang.Shared
import spock.lang.Specification

@ActiveProfiles(['test'])
@SpringBootTest(
    classes = [App],
    properties = [
        'url.information.producer=localhost:9080'
    ],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@DirtiesContext
/**
 * TODO:
 * This class should use @AutoConfigureWireMock annotation
 * but currently it is not possible because of issue:
 * https://github.com/spring-cloud/spring-cloud-contract/issues/665
 */
class AppFT extends Specification implements InformationCreation, InformationRequestSender, InformationProducerVerifying,
    InformationFormCreation, InformationProducerStubbing, InformationResponseValidator {

    @LocalServerPort
    int localServerPort

    @Shared
    WireMockServer wireMockServer

    def setupSpec() {
        wireMockServer = new WireMockServer(9080)
        wireMockServer.start()
    }

    def setup() {
        setServer(wireMockServer)
        port = localServerPort
        slurper = new JsonSlurper()
    }

    def cleanup() {
        clearStubs()
    }

    def cleanupSpec() {
        wireMockServer.stop()
    }

    def 'should create information'() {
        given:
            InformationService.InformationForm form = createInformationForm()
        and:
            Information information = createInformation(new InformationCreator('id-of-info', form))
        and:
            stubPostInformation(information)
        when:
            Response response = postInformationRequest(form)
        then:
            verifyPostInformationRequest(form)
        and:
            verifyAllRequestsMatched()
        and:
            response.statusCode() == 201
        and:
            validateResponse(response, information)
    }

    def 'should get information'() {
        given:
            Information information = createInformation()
        and:
            stubGetInformation(information)
        when:
            Response response = getInformationRequest(information.id)
        then:
            verifyGetInformationRequest(information.id)
        and:
            verifyAllRequestsMatched()
        and:
            response.statusCode() == 200
        and:
            validateResponse(response, information)
    }

    def 'should delete information'() {
        given:
            Information information = createInformation()
        and:
            stubDeleteInformation(information)
        when:
            Response response = deleteInformationRequest(information.id)
        then:
            verifyDeleteInformationRequest(information.id)
        and:
            verifyAllRequestsMatched()
        and:
            response.statusCode() == 200
        and:
            validateResponse(response, information)
    }

    def 'should update information'() {
        given:
            InformationService.InformationForm form = createInformationForm()
        and:
            Information information = createInformation(new InformationCreator('id-of-info', form))
        and:
            stubPatchInformation(information)
        when:
            Response response = patchInformationRequest(information.id, form)
        then:
            verifyPatchInformationRequest(information.id, form)
        and:
            verifyAllRequestsMatched()
        and:
            response.statusCode() == 200
        and:
            validateResponse(response, information)
    }

    def 'should replace information'() {
        given:
            InformationService.InformationForm form = createInformationForm()
        and:
            Information information = createInformation(new InformationCreator('id-of-info', form))
        and:
            stubPutInformation(information)
        when:
            Response response = putInformationRequest(information.id, form)
        then:
            verifyPutInformationRequest(information.id, form)
        and:
            verifyAllRequestsMatched()
        and:
            response.statusCode() == 200
        and:
            validateResponse(response, information)
    }


    @Override
    void setServer(WireMockServer server) {
        InformationProducerStubbing.super.setServer(server)
        InformationProducerVerifying.super.setServer(server)
    }

    @Override
    WireMockServer getServer() {
        return wireMockServer
    }
}
