package org.jellerijk.mccreatecalc.data.database;

import org.jellerijk.mccreatecalc.entities.ComponentGroup;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.jellerijk.mccreatecalc.entities.components.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ComponentGroupDBTest {
    private static final String CONSUMER_ID = "Test-Consumer";
    private static final String LARGE_WATERWHEEL_ID = "Test-LargeWaterWheel";
    private static final String TEST_NETWORK = "Test-Network";
    private static final String WATERWHEEL_ID = "Test-WaterWheel";
    private static final String WINDMILL_ID = "Test-Windmill";
    ComponentGroupDB db;

    @BeforeAll
    static void beforeAll() {
        NetworkDB networkDB = new NetworkDB();
        networkDB.insert(new StressNetwork(TEST_NETWORK, "Test-Network", new ArrayList<>(), new ArrayList<>()));
    }

    @AfterAll
    static void afterAll() {
        NetworkDB networkDB = new NetworkDB();
        networkDB.delete(TEST_NETWORK);
    }

    private void addConsumer() {
        Consumer c = mock();
        when(c.getName()).thenReturn("Clockwork Bearing");
        when(c.getRpm()).thenReturn(64);
        ComponentGroup cg = new ComponentGroup(CONSUMER_ID, c, 25);
        db.addGroupToNetwork(cg, TEST_NETWORK);
    }

    private void deleteConsumer() {
        db.deleteGroup(CONSUMER_ID);
    }

    private void deleteLargeWaterWheel() {
        db.deleteGroup("Test-LargeWaterWheel");
    }

    @Test
    void addGroupToNetwork_AddConsumer() {
        addConsumer();
        assertTrue(db.get(CONSUMER_ID).isPresent());
        deleteConsumer();
    }

    @Test
    void addGroupToNetwork_AddLargeWaterWheel() {
        addLargeWaterWheel();
        assertTrue(db.get(LARGE_WATERWHEEL_ID).isPresent());
        deleteLargeWaterWheel();
    }

    @Test
    void addGroupToNetwork_AddSmallWaterWheel() {
        addSmallWaterWheel();
        assertTrue(db.get(WATERWHEEL_ID).isPresent());
        deleteSmallWaterWheel();
    }

    @Test
    void addGroupToNetwork_AddWindmill() {
        addWindmill();
        assertTrue(db.get(WINDMILL_ID).isPresent());
        deleteWindmill();
    }

    void addLargeWaterWheel() {
        WaterWheel w = new WaterWheel(WaterWheelType.LARGE);
        ComponentGroup cg = new ComponentGroup(LARGE_WATERWHEEL_ID, w, 16);
        db.addGroupToNetwork(cg, TEST_NETWORK);
    }

    void addSmallWaterWheel() {
        WaterWheel w = new WaterWheel(WaterWheelType.SMALL);
        ComponentGroup cg = new ComponentGroup(WATERWHEEL_ID, w, 16);
        db.addGroupToNetwork(cg, TEST_NETWORK);
    }

    void addWindmill() {
        Windmill w = new WindmillImpl(32);
        ComponentGroup cg = new ComponentGroup(WINDMILL_ID, w, 5);
        db.addGroupToNetwork(cg, TEST_NETWORK);
    }

    @Test
    void deleteGroup_ConsumerIdInDatabase_deletes() {
        addConsumer();
        db.deleteGroup(CONSUMER_ID);
        assertTrue(db.get(CONSUMER_ID).isEmpty());
    }

    @Test
    void deleteGroup_WaterWheelIdInDatabase_deletes() {
        addSmallWaterWheel();
        db.deleteGroup(WATERWHEEL_ID);
        assertTrue(db.get(WATERWHEEL_ID).isEmpty());
    }

    @Test
    void deleteGroup_WindmillIdInDatabase_deletes() {
        addWindmill();
        db.deleteGroup(WINDMILL_ID);
        assertTrue(db.get(WINDMILL_ID).isEmpty());
    }

    void deleteSmallWaterWheel() {
        db.deleteGroup(WATERWHEEL_ID);
    }

    void deleteWindmill() {
        db.deleteGroup(WINDMILL_ID);
    }

    @Test
    void getAllGroupsForNetwork() {
        addConsumer();
        addLargeWaterWheel();
        addSmallWaterWheel();
        addWindmill();
        List<ComponentGroup> groups = db.getGroupsForNetwork(TEST_NETWORK);
        assertEquals(4, groups.size());
        deleteWindmill();
        deleteConsumer();
        deleteSmallWaterWheel();
        deleteLargeWaterWheel();
    }

    @Test
    void getNetworkId_GroupExists_ReturnsCorrectNetworkId() {
        addWindmill();
        assertEquals(TEST_NETWORK, db.getNetworkId(WINDMILL_ID).orElseThrow());
        deleteWindmill();
    }

    @Test
    void getNetworkId_GroupDoesNotExist_ReturnsEmptyOptional() {
        assertTrue(db.getNetworkId("non-existant-id").isEmpty());
    }



    @BeforeEach
    void setUp() {
        db = new ComponentGroupDB();
    }

}