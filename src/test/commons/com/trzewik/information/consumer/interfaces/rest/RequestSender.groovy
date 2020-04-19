package com.trzewik.information.consumer.interfaces.rest

import io.restassured.RestAssured
import io.restassured.specification.RequestSpecification

trait RequestSender {
    int port

    RequestSpecification request(String basePath) {
        return RestAssured.given()
            .baseUri("http://localhost:${port}")
            .basePath(basePath)
            .log().all()
    }
}
