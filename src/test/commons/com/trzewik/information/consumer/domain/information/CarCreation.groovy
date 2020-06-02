package com.trzewik.information.consumer.domain.information

import com.trzewik.information.consumer.domain.information.InformationService.CarForm

trait CarCreation {
    List<Car> createCars(List<CarCreator> creators = [new CarCreator(), new CarCreator()]) {
        return creators.collect { createCar(it) }
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
        String color = 'GREEN'

        CarCreator() {}

        CarCreator(CarForm form) {
            this.brand = form.brand
            this.model = form.model
            this.color = form.color
        }
    }
}
