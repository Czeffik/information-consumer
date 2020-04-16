package com.trzewik.information.consumer.infrastructure.rest;

import com.trzewik.information.consumer.domain.information.Information;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformationDto {
    private String id;
    private String description;
    private String message;
    private PersonDto person;
    private List<CarDto> cars;


    public Information toInformation() {
        return new Information(
            id,
            description,
            message,
            person.toPerson(),
            cars.stream().map(CarDto::toCar).collect(Collectors.toList())
        );
    }

}
