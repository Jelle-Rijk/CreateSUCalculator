package org.jellerijk.minecraft.persistence;

import org.jellerijk.minecraft.exceptions.DataAccessException;
import org.jellerijk.minecraft.model.component.NetworkComponent;
import org.jellerijk.minecraft.model.network.Network;
import org.jellerijk.minecraft.model.network.implementations.NetworkImpl;
import org.jellerijk.minecraft.services.repositories.NetworkDAO;
import org.jellerijk.minecraft.util.sql.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StressNetworkDB implements NetworkDAO {
    @Override
    public void add(Network network) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(Queries.QUERY_INSERT_NETWORK)) {
            query.setString(1, network.id());
            query.setString(2, network.name());
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessException("Something went wrong while saving the network.", ex);
        }
    }

    @Override
    public void update(Network network) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(Queries.QUERY_UPDATE)) {
            query.setString(1, network.name());
            query.setString(Queries.DATA_COLS.length + 1, network.id());
        } catch (SQLException ex) {
            throw new DataAccessException("Something went wrong while updating network.", ex);
        }
    }

    @Override
    public Optional<Network> load(String id) {
        Network n = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(QueryBuilder.selectBy(Queries.NETWORK_TABLE, Queries.NETWORK_COL_ID))) {
            query.setString(1, id);
            ResultSet res = query.executeQuery();
            if (res.next()) {
                // TODO implement retrieving components
                n = mapNetworkEntity(res, getComponentsInNetwork(id));
            }
            return Optional.ofNullable(n);
        } catch (SQLException ex) {
            throw new DataAccessException("Something went wrong while loading the network.", ex);
        }
    }

    private Map<NetworkComponent, Integer> getComponentsInNetwork(String networkId) {
        Map<NetworkComponent, Integer> components = new HashMap<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement query = conn.prepareStatement()) {
            query.setString(1, networkId);
            ResultSet res = query.executeQuery();
            while (res.next()) {
                NetworkComponent key = mapNetworkComponent(res);
                int value = res.getInt(Queries.NETCOMPONENT_COL_AMOUNT);
                components.put(key, value);
            }
            return components;
        } catch (SQLException ex) {
            throw new DataAccessException(String.format("Something went wrong while loading components in network %s", networkId), ex);
        }
    }

    @Override
    public Map<String, String> loadAllNamesAndIDs() {
        Map<String, String> networks = new HashMap<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(Queries.QUERY_LOAD_ALL_NAMES_AND_IDS)) {
            ResultSet res = query.executeQuery();
            while (res.next()) {
                networks.put(res.getString(Queries.NETWORK_COL_ID), res.getString(Queries.NETWORK_COL_NAME));
            }
            return networks;
        } catch (SQLException ex) {
            throw new DataAccessException("Could not load the names and IDs of all networks.", ex);
        }
    }

    @Override
    public void delete(String id) {

    }

    private NetworkComponent mapNetworkComponent(ResultSet res) {

    }

    private Network mapNetworkEntity(ResultSet res, Map<NetworkComponent, Integer> components) throws SQLException {
        String id = res.getString(Queries.NETWORK_COL_ID);
        String name = res.getString(Queries.NETWORK_COL_NAME);
        Map<NetworkComponent, Integer> generators = components.entrySet()
                .stream()
                .filter(e -> e.getKey().isGenerator())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        Map<NetworkComponent, Integer> consumers = components.entrySet()
                .stream()
                .filter(e -> !e.getKey().isGenerator())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return new NetworkImpl(id, name, generators, consumers);
    }

    private static class Queries {

        private static final String NETWORK_COL_ID = "Id";
        private static final String NETWORK_COL_NAME = "Name";
        private static final String NETWORK_TABLE = "";

        private static final String NETCOMPONENT_COL_NETWORK = "NetworkId";
        private static final String NETCOMPONENT_COL_COMPONENT = "ComponentName";
        private static final String NETCOMPONENT_COL_AMOUNT = "Amount";
        private static final String NETCOMPONENT_TABLE = "";

        private static final String COMPONENT_TABLE = "Component";
        private static final String COMPONENT_COL_NAME = "Name";
        private static final String[] DATA_COLS = new String[]{NETWORK_COL_NAME};
        private static final String[] COLS = Stream.concat(Stream.of(NETWORK_COL_ID), Arrays.stream(DATA_COLS))
                .toArray(String[]::new);

        private static final String QUERY_UPDATE = QueryBuilder.updateCols(NETWORK_TABLE, NETWORK_COL_ID, Arrays.asList(DATA_COLS));
        private static final String QUERY_LOAD_ALL_NAMES_AND_IDS = "SELECT " + String.join(",", NETWORK_COL_ID, NETWORK_COL_NAME) + " FROM " + NETWORK_TABLE;
        private static final String QUERY_INSERT_NETWORK = QueryBuilder.insert(NETWORK_TABLE, COLS);
        private static final String QUERY_SELECT_COMPONENTS_IN_NETWORK =
                "SELECT * FROM " + NETCOMPONENT_TABLE + " nc JOIN " + COMPONENT_TABLE + " c ON c." + COMPONENT_COL_NAME + "=nc." + NETCOMPONENT_COL_COMPONENT + " WHERE nc." + NETCOMPONENT_COL_NETWORK + "=?";
    }
