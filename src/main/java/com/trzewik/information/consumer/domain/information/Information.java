package com.trzewik.information.consumer.domain.information;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class Information {
    private final String id;
    private final String description;
    private final String message;
    private final Person person;
    private final List<Car> cars;
}
