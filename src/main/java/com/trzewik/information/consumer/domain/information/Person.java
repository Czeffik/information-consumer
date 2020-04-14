package com.trzewik.information.consumer.domain.information;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Person {
    private final String name;
    private final String lastName;
}
