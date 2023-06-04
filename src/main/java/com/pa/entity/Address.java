package com.pa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String country;
    private String state;
    private String city;

    @Override
    public String toString() {
        return country + ", " + state + ", " + city;
    }
}
