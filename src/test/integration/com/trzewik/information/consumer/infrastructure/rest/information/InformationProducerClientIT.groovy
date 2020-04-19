package com.trzewik.information.consumer.infrastructure.rest.information

import com.github.tomakehurst.wiremock.WireMockServer
import com.trzewik.information.consumer.domain.information.InformationCreation
import com.trzewik.information.consumer.domain.information.InformationFormCreation
import com.trzewik.information.consumer.infrastructure.rest.RestInfrastructureConfiguration
import feign.FeignException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

@ContextConfiguration(
    classes = [RestInfrastructureConfiguration]
)
@TestPropertySource(
    properties = [
        'url.information.producer=localhost:${wiremock.server.port}'
    ]
)
/**
 * This class should be replaced with @AutoConfigureWireMock(port = 0) - start on random port, when this issue:
 * https://github.com/spring-cloud/spring-cloud-contract/issues/1303
 * will be fixed in `spring-cloud-starter-contract-stub-runner`
 */
@AutoConfigureWireMock(port = 9090)
class InformationProducerClientIT extends Specification implements InformationProducerStubbing, InformationCreation, InformationFormCreation {
    @Autowired
    InformationProducerClient client

    @Autowired
    WireMockServer wireMockServer

    def setup() {
        server = wireMockServer
    }

    def cleanup() {
        clearStubs()
    }

    def 'should send get request and receive information'() {
        given:
            def information = createInformation()
        and:
            stubGetInformation(information)
        when:
            def received = client.get(information.id)
        then:
            received == information
    }

    def 'should send post request and receive created information'() {
        given:
            def id = 'information-post-id'
        and:
            def form = createInformationForm()
        and:
            def information = createInformation(new InformationCreator(id, form))
        and:
            stubPostInformation(information)
        when:
            def received = client.create(form)
        then:
            received == information
    }

    def 'should send patch request and receive updated information'() {
        given:
            def id = 'information-patch-id'
        and:
            def form = createInformationForm()
        and:
            def information = createInformation(new InformationCreator(id, form))
        and:
            stubPatchInformation(information)
        when:
            def received = client.update(id, form)
        then:
            received == information
    }

    def 'should send put request and receive replaced information'() {
        given:
            def id = 'information-put-id'
        and:
            def form = createInformationForm()
        and:
            def information = createInformation(new InformationCreator(id, form))
        and:
            stubPutInformation(information)
        when:
            def received = client.replace(id, form)
        then:
            received == information
    }

    def 'should send delete request and receive deleted information'() {
        given:
            def information = createInformation()
        and:
            stubDeleteInformation(information)
        when:
            def received = client.delete(information.id)
        then:
            received == information
    }

    def 'should throw exception when returned bad request'(){
        given:
            def information = createInformation()
        and:
            stubBadRequest(information)
        when:
            client.get(information.id)
        then:
            FeignException ex = thrown()
        and:
            with(ex.message){
                contains(errorResponseBody())
                contains('[400 Bad Request] during [GET] to')
                contains(information.id)
            }
    }

    def 'should throw exception when returned not found'(){
        given:
            def information = createInformation()
        and:
            stubNotFound(information)
        when:
            client.get(information.id)
        then:
            FeignException ex = thrown()
        and:
            with(ex.message){
                contains(errorResponseBody())
                contains('[404 Not Found] during [GET] to')
                contains(information.id)
            }
    }

    def 'should throw exception when returned internal server error'(){
        given:
            def information = createInformation()
        and:
            stubInternalServerError(information)
        when:
            client.get(information.id)
        then:
            FeignException ex = thrown()
        and:
            with(ex.message){
                contains(errorResponseBody())
                contains('[500 Server Error] during [GET] to')
                contains(information.id)
            }
    }

}
