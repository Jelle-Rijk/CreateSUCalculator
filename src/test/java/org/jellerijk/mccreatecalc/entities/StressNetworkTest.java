package org.jellerijk.mccreatecalc.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StressNetworkTest {
    private static final String VALID_NAME = "Test-Network";
    private static final String VALID_ID = "Test123";
    private static int expectedSUProduced;
    private static int expectedSUConsumed;
    private static final List<GeneratorEntry> VALID_GENERATORS = new ArrayList<>();
    private StressNetworkBuilder defaultStressNetwork;


    @BeforeAll
    static void init() {
        Generator gen1 = GeneratorTestBuilder.defaultGenerator().withSuGeneration(1000).build();
        GeneratorEntry entry1 = GeneratorEntryTestBuilder.defaultGeneratorEntry()
                .withGenerator(gen1)
                .withAmount(5)
                .build();
        Generator gen2 = GeneratorTestBuilder.defaultGenerator().withSuGeneration(35).build();
        GeneratorEntry entry2 = GeneratorEntryTestBuilder.defaultGeneratorEntry()
                .withGenerator(gen2)
                .withAmount(20)
                .build();
        VALID_GENERATORS.add(entry1);
        VALID_GENERATORS.add(entry2);

        expectedSUProduced = entry1.calculateSUProduced() + entry2.calculateSUProduced();
        expectedSUConsumed = 0;
    }

    @BeforeEach
    void setUp() {
        defaultStressNetwork = StressNetworkBuilder.aStressNetwork()
                .withId(VALID_ID)
                .withName(VALID_NAME)
                .withGenerators(VALID_GENERATORS);
    }

    @Test
    void calculateSUConsumed() {
    }

    //    === calculateSUProduced() ===
    @Test
    void calculateSUProduced_returnsCorrectValue() {
        assertEquals(expectedSUProduced, defaultStressNetwork.build().calculateSUProduced());
    }

    @Test
    void calculateSUBalance() {
        assertEquals(expectedSUProduced - expectedSUConsumed, defaultStressNetwork.build().calculateSUBalance());
    }

    @Test
    void id() {
    }

    @Test
    void name() {
    }

    @Test
    void generators() {
    }
}