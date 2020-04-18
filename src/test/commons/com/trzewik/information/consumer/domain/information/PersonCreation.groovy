package com.trzewik.information.consumer.domain.information

import com.trzewik.information.consumer.domain.information.InformationService.PersonForm

trait PersonCreation {

    Person createPerson(PersonCreator creator = new PersonCreator()) {
        return new Person(
            creator.name,
            creator.lastName
        )
    }

    static class PersonCreator {
        String name = 'example name'
        String lastName = 'example description'

        PersonCreator() {}

        PersonCreator(PersonForm form) {
            this.name = form.name
            this.lastName = form.lastName
        }
    }
}
