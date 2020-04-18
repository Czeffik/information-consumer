package com.trzewik.information.consumer.domain.information

import com.trzewik.information.consumer.domain.information.InformationService.InformationForm

trait InformationCreation implements CarCreation, PersonCreation {

    Information createInformation(InformationCreator creator = new InformationCreator()) {
        return new Information(
            creator.id,
            creator.description,
            creator.message,
            createPerson(creator.personCreator),
            createCars(creator.carCreators)
        )
    }


    static class InformationCreator {
        String id = 'example-test-id'
        String description = 'example description'
        String message = 'example message'
        PersonCreator personCreator = new PersonCreator()
        List<CarCreator> carCreators = [new CarCreator(), new CarCreator()]

        InformationCreator() {}

        InformationCreator(String id, InformationForm form) {
            this.id = id
            this.description = form.description
            this.message = form.message
            this.personCreator = new PersonCreator(form.person)
            this.carCreators = form.cars.collect { new CarCreator(it) }
        }
    }

}
