package com.trzewik.information.consumer.domain.information

trait PersonFormCreation {

    InformationService.PersonForm createPersonForm(PersonFormCreator creator = new PersonFormCreator()) {
        return new InformationService.PersonForm(
            name: creator.name,
            lastName: creator.lastName
        )
    }

    static class PersonFormCreator {
        String name = 'example form name'
        String lastName = 'example form last name'
    }
}
