package com.trzewik.information.consumer.domain.information;

public interface InformationClient {
    Information get(String id);

    Information create(InformationService.InformationForm form);

    Information update(String id, InformationService.InformationForm form);

    Information replace(String id, InformationService.InformationForm form);

    Information delete(String id);
}
