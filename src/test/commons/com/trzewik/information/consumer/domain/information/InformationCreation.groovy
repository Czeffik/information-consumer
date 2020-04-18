package com.trzewik.information.consumer.domain.information

import com.trzewik.information.consumer.domain.information.InformationService.InformationForm

trait InformationCreation implements CarCreation, PersonCreation {
    Information createInformationFrom(InformationForm form, String id = 'id for information created from form') {
        return new Information(
            id,
            form.description,
            form.message,
            createPersonFrom(form.person),
            createCarsFrom(form.cars)
        )
    }

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
        String id = 'example test id'
        String description = 'example description'
        String message = 'example message'
        PersonCreator personCreator = new PersonCreator()
        List<CarCreator> carCreators = [new CarCreator(), new CarCreator()]
    }

}
