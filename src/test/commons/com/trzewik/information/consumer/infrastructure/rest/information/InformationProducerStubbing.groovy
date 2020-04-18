package com.trzewik.information.consumer.infrastructure.rest.information

import com.github.tomakehurst.wiremock.WireMockServer
import com.trzewik.information.consumer.domain.information.Information
import groovy.json.JsonBuilder

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse
import static com.github.tomakehurst.wiremock.client.WireMock.delete
import static com.github.tomakehurst.wiremock.client.WireMock.get
import static com.github.tomakehurst.wiremock.client.WireMock.patch
import static com.github.tomakehurst.wiremock.client.WireMock.post
import static com.github.tomakehurst.wiremock.client.WireMock.put
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo

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

    void clearStubs() {
        server.resetAll()
    }

    String toJson(Object object) {
        return new JsonBuilder(object).toPrettyString()
    }
}
