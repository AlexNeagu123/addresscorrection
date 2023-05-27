package com.pa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NameToNodeMapServiceTest {
    @Autowired
    private NameToNodeMapService nameToNodeService;

    @Test
    void givenWashington_whenPrintBranchMethodCalled_thenItPrintsData() {
        nameToNodeService.printBranches("Washington");
    }

    @Test
    void givenIasi_whenPrintBranchMethodCalled_thenItPrintsData() {
        nameToNodeService.printBranches("Iasi");
    }
}
