package com.trzewik.information.consumer.infrastructure.rest;

import com.trzewik.information.consumer.domain.information.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private String name;
    private String lastName;

    public Person toPerson() {
        return new Person(name, lastName);
    }
}
