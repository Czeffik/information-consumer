package com.trzewik.information.consumer.interfaces.rest.information

import com.trzewik.information.consumer.domain.information.Car
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
import spock.lang.Specification

@SpringBootTest(
    classes = [RestInterfacesConfiguration, RestInterfacesTestConfig],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class InformationControllerIT extends Specification implements InformationCreation, InformationRequestSender, InformationFormCreation {
    @Autowired
    InformationService informationServiceMock

    @LocalServerPort
    int localServerPort

    JsonSlurper slurper = new JsonSlurper()

    def setup() {
        port = localServerPort
    }

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
            with(parseResponse(response)) {
                id == information.id
                description == information.description
                message == information.message
                with(person) {
                    name == information.person.name
                    lastName == information.person.lastName
                }
                cars.size() == information.cars.size()
                validateCars(cars, information.cars)
            }
    }

    boolean validateCars(parsedCars, List<Car> cars) {
        parsedCars.each { pc ->
            assert cars.any { it == createCar(new CarCreator(brand: pc.brand, model: pc.model, color: pc.color)) }
        }
        return true
    }

    def parseResponse(Response response) {
        return slurper.parseText(response.body().asString())
    }

}
