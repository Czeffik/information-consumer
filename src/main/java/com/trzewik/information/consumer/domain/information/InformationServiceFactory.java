package com.trzewik.information.consumer.domain.information;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InformationServiceFactory {

    public static InformationService create(
        InformationReceiver informationReceiver
    ) {
        return new InformationServiceImpl(informationReceiver);
    }
}
