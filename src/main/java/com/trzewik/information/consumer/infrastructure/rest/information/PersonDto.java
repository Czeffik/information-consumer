package com.trzewik.information.consumer.infrastructure.rest.information;

import com.trzewik.information.consumer.domain.information.Person;
import lombok.Data;

@Data
public class PersonDto {
    private String name;
    private String lastName;

    public Person toPerson() {
        return new Person(name, lastName);
    }
}
