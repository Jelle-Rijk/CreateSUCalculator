package org.jellerijk.mccreatecalc.data.database;

import org.jellerijk.mccreatecalc.application.dto.NetworkInfo;
import org.jellerijk.mccreatecalc.data.dao.NetworkDAO;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NetworkDBTest {
    private NetworkDAO db;
    private StressNetwork network;
    private NetworkInfo networkInfo;
    private static final String VALID_ID = "Test";
    private static final String VALID_NAME = "Test-Network";

    @BeforeEach
    void setUp() {
        network = StressNetwork.Builder.aStressNetwork()
                .withId(VALID_ID)
                .withName(VALID_NAME)
                .withGenerators(new ArrayList<>())
                .withConsumers(new ArrayList<>())
                .build();
        networkInfo = new NetworkInfo(VALID_ID, VALID_NAME);
        db = new NetworkDB();
    }


    @Test
    void insert_ValidNetwork_getByIdReturnsCorrectNetwork() {
        db.insert(network);
        assertEquals(networkInfo, db.getById(VALID_ID).orElseThrow());
        db.delete(VALID_ID);
    }

    @Test
    void delete_NetworkNotInDatabase_DoesNotThrow() {
        assertDoesNotThrow(() -> db.delete("non-existant-id"));
    }

    @Test
    void delete_NetworkInDatabase_DeletesNetwork() {
        db.insert(network);
        assertTrue(db.getById(VALID_ID).isPresent());
        db.delete(VALID_ID);
        assertTrue(db.getById(VALID_ID).isEmpty());
    }

    @Test
    void getById_NetworkNotInDatabase_ReturnsEmptyOptional() {
        assertTrue(db.getById("non-existant-id").isEmpty());
    }

    @Test
    void getById_NetworkInDatabase_ReturnsOptionalOfNetwork() {
        db.insert(network);
        assertTrue(db.getById(VALID_ID).isPresent());
        db.delete(VALID_ID);
    }
}