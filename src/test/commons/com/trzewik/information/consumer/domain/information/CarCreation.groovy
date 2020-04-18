package com.trzewik.information.consumer.domain.information

import com.trzewik.information.consumer.domain.information.InformationService.CarForm

trait CarCreation {
    List<Car> createCars(List<CarCreator> creators = [new CarCreator(), new CarCreator()]) {
        return creators.collect { createCar(it) }
    }

    List<Car> createCarsFrom(List<CarForm> forms) {
        return forms.collect { createCarFrom(it) }
    }

    Car createCarFrom(CarForm form) {
        return new Car(
            form.brand,
            form.model,
            form.color
        )
    }

    Car createCar(CarCreator creator = new CarCreator()) {
        return new Car(
            creator.brand,
            creator.model,
            creator.color
        )
    }

    static class CarCreator {
        String brand = 'example brand'
        String model = 'example model'
        String color = 'example color'
    }
}
