package com.trzewik.information.consumer.infrastructure.rest.information

import com.github.tomakehurst.wiremock.WireMockServer
import com.trzewik.information.consumer.domain.information.Information

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse
import static com.github.tomakehurst.wiremock.client.WireMock.badRequest
import static com.github.tomakehurst.wiremock.client.WireMock.delete
import static com.github.tomakehurst.wiremock.client.WireMock.get
import static com.github.tomakehurst.wiremock.client.WireMock.notFound
import static com.github.tomakehurst.wiremock.client.WireMock.patch
import static com.github.tomakehurst.wiremock.client.WireMock.post
import static com.github.tomakehurst.wiremock.client.WireMock.put
import static com.github.tomakehurst.wiremock.client.WireMock.serverError
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import static com.trzewik.information.consumer.common.JsonHelper.toJson

trait InformationProducerStubbing {
    WireMockServer server

    void stubGetInformation(Information information) {
        server.stubFor(get(urlEqualTo("/information/${information.id}"))
            .willReturn(aResponse()
                .withHeader('Content-Type', 'application/json')
                .withBody(toJson(information)))
        )
    }

    void stubPostInformation(Information information) {
        server.stubFor(post(urlEqualTo("/information"))
            .willReturn(aResponse()
                .withHeader('Content-Type', 'application/json')
                .withBody(toJson(information)))
        )
    }

    void stubPutInformation(Information information) {
        server.stubFor(put(urlEqualTo("/information/${information.id}"))
            .willReturn(aResponse()
                .withHeader('Content-Type', 'application/json')
                .withBody(toJson(information)))
        )
    }

    void stubPatchInformation(Information information) {
        server.stubFor(patch(urlEqualTo("/information/${information.id}"))
            .willReturn(aResponse()
                .withHeader('Content-Type', 'application/json')
                .withBody(toJson(information)))
        )
    }

    void stubDeleteInformation(Information information) {
        server.stubFor(delete(urlEqualTo("/information/${information.id}"))
            .willReturn(aResponse()
                .withHeader('Content-Type', 'application/json')
                .withBody(toJson(information)))
        )
    }

    void stubBadRequest(Information information) {
        server.stubFor(get(urlEqualTo("/information/${information.id}"))
            .willReturn(badRequest().withBody(errorResponseBody())))
    }

    void stubNotFound(Information information) {
        server.stubFor(get(urlEqualTo("/information/${information.id}"))
            .willReturn(notFound().withBody(errorResponseBody())))
    }

    void stubInternalServerError(Information information) {
        server.stubFor(get(urlEqualTo("/information/${information.id}"))
            .willReturn(serverError().withBody(errorResponseBody())))
    }


    void clearStubs() {
        server.resetAll()
    }

    String errorResponseBody() {
        return """{
            "message": "Can not find information with id: [1231] in repository.",
            "code": 999,
            "reason": "Test Reason"
            }"""
    }


}
