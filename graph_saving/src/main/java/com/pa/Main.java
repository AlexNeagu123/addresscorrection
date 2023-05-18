package com.pa;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<GeoName> parsedData = objectMapper.readValue(new File("src/main/resources/static/geoNames.json"), new TypeReference<List<GeoName>>() {});
        } catch (IOException databindException) {
            System.out.println("Couldn't deserialize the JSON file. " + databindException.getMessage());
        }
    }
}