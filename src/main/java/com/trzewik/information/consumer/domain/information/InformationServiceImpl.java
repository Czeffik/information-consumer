package com.trzewik.information.consumer.domain.information;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class InformationServiceImpl implements InformationService {
    private final InformationClient client;

    @Override
    public Information get(String id) {
        return client.get(id);
    }

    @Override
    public Information create(InformationForm form) {
        return client.create(form);
    }

    @Override
    public Information update(String id, InformationForm form) {
        return client.update(id, form);
    }

    @Override
    public Information replace(String id, InformationForm form) {
        return client.replace(id, form);
    }

    @Override
    public Information delete(String id) {
        return client.delete(id);
    }
}
