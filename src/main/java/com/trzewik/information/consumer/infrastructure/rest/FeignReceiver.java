package com.trzewik.information.consumer.infrastructure.rest;

import com.trzewik.information.consumer.domain.information.Information;
import com.trzewik.information.consumer.domain.information.InformationReceiver;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class FeignReceiver implements InformationReceiver {
    private final InformationProducerClient client;

    @Override
    public Information getInformation(String id) {
        return client.getInformation(id).toInformation();
    }
}
