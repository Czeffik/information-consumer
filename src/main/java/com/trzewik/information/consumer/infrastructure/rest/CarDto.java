package com.trzewik.information.consumer.infrastructure.rest;

import com.trzewik.information.consumer.domain.information.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private String brand;
    private String model;
    private String color;

    public Car toCar() {
        return new Car(
            brand,
            model,
            color
        );
    }
}
