package com.trzewik.information.consumer.common

import groovy.json.JsonBuilder

class JsonHelper {
    static String toJson(Object object) {
        return new JsonBuilder(object).toPrettyString()
    }
}
