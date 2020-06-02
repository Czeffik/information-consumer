package com.trzewik.information.consumer.domain.information;

import lombok.Data;

import java.util.List;

public interface InformationService {
    Information get(String id);

    Information create(InformationForm form);

    Information update(String id, InformationForm form);

    Information replace(String id, InformationForm form);

    Information delete(String id);

    @Data
    class InformationForm {
        private String description;
        private String message;
        private PersonForm person;
        private List<CarForm> cars;
    }

    @Data
    class PersonForm {
        private String name;
        private String lastName;
    }

    @Data
    class CarForm {
        private String brand;
        private String model;
        private Color color;
    }
}
