package com.trzewik.information.consumer.interfaces.rest.information

import com.trzewik.information.consumer.domain.information.InformationService
import com.trzewik.information.consumer.interfaces.rest.RequestSender
import groovy.json.JsonBuilder
import io.restassured.http.ContentType
import io.restassured.response.Response

trait InformationRequestSender extends RequestSender {

    Response getInformationRequest(String id) {
        return request("/information/${id}")
            .get()
            .thenReturn()
    }

    Response postInformationRequest(InformationService.InformationForm form) {
        return request("/information")
            .contentType(ContentType.JSON)
            .body(toJson(form))
            .post()
            .thenReturn()
    }

    Response putInformationRequest(String id, InformationService.InformationForm form) {
        return request("/information/${id}")
            .contentType(ContentType.JSON)
            .body(toJson(form))
            .put()
            .thenReturn()
    }

    Response patchInformationRequest(String id, InformationService.InformationForm form) {
        return request("/information/${id}")
            .contentType(ContentType.JSON)
            .body(toJson(form))
            .patch()
            .thenReturn()
    }

    Response deleteInformationRequest(String id) {
        return request("/information/${id}")
            .delete()
            .thenReturn()
    }

    String toJson(Object object) {
        return new JsonBuilder(object).toPrettyString()
    }
}
