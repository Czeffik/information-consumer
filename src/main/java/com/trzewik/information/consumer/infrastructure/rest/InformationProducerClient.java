package com.trzewik.information.consumer.infrastructure.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "InformationProducer", url = "${url.information.producer}", configuration = FeignConfig.class)
public interface InformationProducerClient {

    @GetMapping("/information/{id}")
    InformationDto getInformation(@PathVariable(value = "id") String id);
}
