package com.pa.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NameToNodeMapServiceTest {
    @Autowired
    private NameToNodeMapService nameToNodeService;

    @Test
    void givenNewYork_whenPrintBranchMethodCalled_thenItPrintsData() {
        nameToNodeService.printBranch("New York");
    }

    @Test
    void givenIasi_whenPrintBranchMethodCalled_thenItPrintsData() {
        nameToNodeService.printBranch("Iasi");
    }
}
