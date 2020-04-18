package com.trzewik.information.consumer.domain.information

trait InformationFormCreation {
    InformationService.InformationForm createInformationForm(InformationFormCreator creator = new InformationFormCreator()) {
        return new InformationService.InformationForm(
            description: creator.description,
            message: creator.message,
            person: creator.person,
            cars: creator.cars
        )
    }

    static class InformationFormCreator implements CarFormCreation, PersonFormCreation {
        String description = 'example form description'
        String message = 'example form message'
        InformationService.PersonForm person = createPersonForm()
        List<InformationService.CarForm> cars = createCarForms()
    }
}
