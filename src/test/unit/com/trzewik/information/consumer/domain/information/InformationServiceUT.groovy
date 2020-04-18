package com.trzewik.information.consumer.domain.information

import spock.lang.Specification
import spock.lang.Subject

class InformationServiceUT extends Specification implements InformationCreation, InformationFormCreation {

    InformationClient client = Mock()

    @Subject
    InformationService service = InformationServiceFactory.create(client)

    def 'should get information by id using information client'() {
        given:
            def id = 'example information id'
        and:
            def information = createInformation(new InformationCreator(id: id))
        when:
            def returnedInformation = service.get(id)
        then:
            1 * client.get(id) >> information
        and:
            information == returnedInformation
    }

    def 'should create and return information using information client'() {
        given:
            def form = createInformationForm()
        and:
            def information = createInformationFrom(form)
        when:
            def returnedInformation = service.create(form)
        then:
            1 * client.create(form) >> information
        and:
            returnedInformation == information
    }

    def 'should update and return information using information client'() {
        given:
            def id = 'information id'
        and:
            def form = createInformationForm()
        and:
            def updatedInformation = createInformationFrom(form, id)
        when:
            def returnedInformation = service.update(id, form)
        then:
            1 * client.update(id, form) >> updatedInformation
        and:
            returnedInformation == updatedInformation
    }

    def 'should replace and return information using information client'() {
        given:
            def id = 'information id'
        and:
            def form = createInformationForm()
        and:
            def replacedInformation = createInformationFrom(form, id)
        when:
            def returnedInformation = service.replace(id, form)
        then:
            1 * client.replace(id, form) >> replacedInformation
        and:
            returnedInformation == replacedInformation
    }

    def 'should delete and return deleted information using information client'() {
        given: 2
            def id = 'deleted information id'
        and:
            def deletedInformation = createInformation(new InformationCreator(id: id))
        when:
            def returnedInformation = service.delete(id)
        then:
            1 * client.delete(id) >> deletedInformation
        and:
            returnedInformation == deletedInformation
    }
}
