package com.trzewik.information.consumer.domain.information;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class Car {
    private final String brand;
    private final String model;
    private final Color color;
}
