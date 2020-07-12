package com.trzewik.information.consumer.infrastructure.rest;

import feign.Response;
import feign.codec.ErrorDecoder;

class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
                throw new RestClientException.BadRequest();
            case 404:
                throw new RestClientException.NotFound();
            default:
                throw new RestClientException.Error();
        }
    }
}
