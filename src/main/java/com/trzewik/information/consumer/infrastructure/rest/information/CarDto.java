package com.trzewik.information.consumer.infrastructure.rest.information;

import com.trzewik.information.consumer.domain.information.Car;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
