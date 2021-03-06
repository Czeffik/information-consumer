package com.trzewik.information.consumer.interfaces.rest.information

import com.trzewik.information.consumer.domain.information.Car
import com.trzewik.information.consumer.domain.information.CarCreation
import com.trzewik.information.consumer.domain.information.Information
import groovy.json.JsonSlurper
import io.restassured.response.Response

trait InformationResponseValidator {
    abstract JsonSlurper getSlurper()

    boolean validateErrorResponse(Response response, String exceptionMessage) {
        def parsedResponse = parseResponse(response)
        assert parsedResponse.message == exceptionMessage
        assert parsedResponse.code == 500
        assert parsedResponse.reason == 'Internal Server Error'
        return true
    }

    boolean validateResponse(Response response, Information information) {
        def parsedResponse = parseResponse(response)
        assert parsedResponse.id == information.id
        assert parsedResponse.description == information.description
        assert parsedResponse.message == information.message
        assert parsedResponse.person.name == information.person.name
        assert parsedResponse.person.lastName == information.person.lastName
        assert parsedResponse.cars.size() == information.cars.size()
        validateCars(parsedResponse.cars, information.cars)
        return true
    }

    def parseResponse(Response response) {
        return slurper.parseText(response.body().asString())
    }

    void validateCars(parsedCars, List<Car> cars) {
        assert parsedCars.collect { createCar(new CarCreation.CarCreator(brand: it.brand, model: it.model, color: it.color)) } == cars
    }
}
