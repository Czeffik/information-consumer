package com.trzewik.information.consumer.interfaces.rest;

import com.trzewik.information.consumer.domain.information.Person;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonDto {
    private final String name;
    private final String lastName;

    public static PersonDto from(Person person) {
        return new PersonDto(
            person.getName(),
            person.getLastName()
        );
    }
}
