package com.trzewik.information.consumer.domain.information

trait InformationFormCreation implements CarFormCreation, PersonFormCreation {
    InformationService.InformationForm createInformationForm(InformationFormCreator creator = new InformationFormCreator()) {
        return new InformationService.InformationForm(
            description: creator.description,
            message: creator.message,
            person: createPersonForm(creator.person),
            cars: createCarForms(creator.cars)
        )
    }

    static class InformationFormCreator {
        String description = 'example form description'
        String message = 'example form message'
        PersonFormCreator person = new PersonFormCreator()
        List<CarFormCreator> cars = [new CarFormCreator(), new CarFormCreator()]
    }
}
