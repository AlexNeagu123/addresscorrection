package com.pa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GeoGraphServiceTest {
    @Autowired
    private GeoGraphService geoGraphService;

    @Test
    void givenItaly_whenPrintCountryTreeMethodCalled_thenItPrintsData() {
        geoGraphService.printCountryTree("Italian Republic");
    }

    @Test
    void givenRomania_whenPrintCountryTreeMethodCalled_thenItPrintsData() {
        geoGraphService.printCountryTree("Romania");
    }

    @Test
    void givenUSA_whenPrintCountryTreeMethodCalled_thenItPrintsData() {
        geoGraphService.printCountryTree("United States");
    }
}