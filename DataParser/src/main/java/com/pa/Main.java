package com.pa;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        DataParser dataParser = new DataParser();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(new File("WebServer/src/main/resources/out/geoNames.json"), dataParser.getAllGeoNames());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}