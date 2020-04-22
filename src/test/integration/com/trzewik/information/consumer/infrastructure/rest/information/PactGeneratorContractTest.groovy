package com.trzewik.information.consumer.infrastructure.rest.information

import com.fasterxml.jackson.databind.ObjectMapper
import com.hltech.pact.gen.PactGenerator
import spock.lang.Specification

class PactGeneratorContractTest extends Specification {
    /*
    If we have custom object mapper we should set up Spring context and use @Autowired for object mapper
     */
    ObjectMapper objectMapper = new ObjectMapper()

    PactGenerator pactGenerator = new PactGenerator()

    def "should generate pact file"() {
        expect:
            pactGenerator.writePactFiles(
                "com.trzewik.information.consumer.infrastructure.rest",
                "information-consumer",
                objectMapper,
                new File("build/pacts/"))
    }
}
