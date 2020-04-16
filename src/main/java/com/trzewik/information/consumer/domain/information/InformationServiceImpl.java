package com.trzewik.information.consumer.domain.information;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class InformationServiceImpl implements InformationService {
    private final InformationReceiver receiver;

    @Override
    public Information get(String id) {
        return receiver.getInformation(id);
    }

    @Override
    public Information create(InformationForm form) {
        return null;
    }

    @Override
    public Information update(String id, InformationForm form) {
        return null;
    }

    @Override
    public Information replace(String id, InformationForm form) {
        return null;
    }

    @Override
    public Information delete(String id) {
        return null;
    }
}
