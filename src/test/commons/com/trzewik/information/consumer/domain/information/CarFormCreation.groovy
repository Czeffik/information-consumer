package com.trzewik.information.consumer.domain.information

trait CarFormCreation {
    List<InformationService.CarForm> createCarForms(List<CarFormCreator> creators = [new CarFormCreator(), new CarFormCreator()]) {
        return creators.collect { createCarForm(it) }
    }

    InformationService.CarForm createCarForm(CarFormCreator creator = new CarFormCreator()) {
        return new InformationService.CarForm(
            brand: creator.brand,
            model: creator.model,
            color: creator.color
        )
    }

    static class CarFormCreator {
        String brand = 'example form brand'
        String model = 'example form model'
        String color = 'GREEN'
    }
}
