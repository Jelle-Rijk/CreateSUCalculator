package org.jellerijk.mccreatecalc.data.local;

import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SelectedNetworkTest {
    private SelectedNetwork sn;

    @BeforeEach
    void setUp() {
        sn = new SelectedNetwork();
    }

    @Test
    void write_ValidWrite_SavesValue() {
        StressNetwork network = mock();
        sn.write(network);
        assertEquals(network, sn.read().orElseThrow());
    }

    @Test
    void write_NullWrite_DoesNotThrow() {
        assertDoesNotThrow(() -> sn.write(null));
    }

    @Test
    void read_AfterNullWrite_ReturnsEmptyOptional() {
        sn.write(null);
        assertTrue(() -> sn.read().isEmpty());
    }

    @Test
    void getId_SelectedSet_ReturnsOptionalWithId() {
        StressNetwork network = mock();
        when(network.id()).thenReturn("test-id");
        sn.write(network);
        assertEquals("test-id", sn.getId().orElseThrow());
    }

    @Test
    void getId_SelectedIsNull_ReturnsEmptyOptional() {
        sn.write(mock());
        sn.write(null);
        assertTrue(sn.getId().isEmpty());
    }
}