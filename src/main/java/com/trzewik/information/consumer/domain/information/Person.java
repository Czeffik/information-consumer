package com.trzewik.information.consumer.domain.information;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class Person {
    private final String name;
    private final String lastName;
}
