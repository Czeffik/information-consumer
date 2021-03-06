package com.trzewik.information.consumer.interfaces.rest.information;

import com.trzewik.information.consumer.domain.information.Car;
import com.trzewik.information.consumer.domain.information.Color;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CarDto implements Serializable {
    private final String brand;
    private final String model;
    private final Color color;

    public static CarDto from(Car car) {
        return new CarDto(
            car.getBrand(),
            car.getModel(),
            car.getColor()
        );
    }
}
