package com.trzewik.information.consumer.infrastructure.rest.information

import com.github.tomakehurst.wiremock.WireMockServer
import com.trzewik.information.consumer.domain.information.InformationService.InformationForm

import static com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor
import static com.github.tomakehurst.wiremock.client.WireMock.patchRequestedFor
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import static com.github.tomakehurst.wiremock.client.WireMock.putRequestedFor
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo

trait InformationProducerVerifying {
    abstract WireMockServer getServer()

    void verifyPostInformationRequest(InformationForm form) {
        server.verify(1, postRequestedFor(urlEqualTo('/information')))
    }

    void verifyGetInformationRequest(String id) {
        server.verify(1, getRequestedFor(urlEqualTo("/information/${id}")))
    }

    void verifyPutInformationRequest(String id, InformationForm form) {
        server.verify(1, putRequestedFor(urlEqualTo("/information/${id}")))
    }

    void verifyPatchInformationRequest(String id, InformationForm form) {
        server.verify(1, patchRequestedFor(urlEqualTo("/information/${id}")))
    }

    void verifyDeleteInformationRequest(String id) {
        server.verify(1, deleteRequestedFor(urlEqualTo("/information/${id}")))
    }

    boolean verifyAllRequestsMatched() {
        return server.findAllUnmatchedRequests().isEmpty()
    }
}
