package com.trzewik.information.consumer.domain.information;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Car {
    private final String brand;
    private final String model;
    private final String color;
}
