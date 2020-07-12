package com.trzewik.information.consumer.infrastructure.rest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestClientException {
    public static class BadRequest extends RuntimeException{}
    public static class NotFound extends RuntimeException {}
    public static class Error extends RuntimeException {}
}
