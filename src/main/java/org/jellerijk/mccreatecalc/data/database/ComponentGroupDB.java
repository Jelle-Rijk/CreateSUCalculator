package org.jellerijk.mccreatecalc.data.database;

import org.jellerijk.mccreatecalc.data.dao.ComponentGroupDAO;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;
import org.jellerijk.mccreatecalc.entities.components.*;
import org.jellerijk.mccreatecalc.exceptions.DataBaseAccessException;
import org.jellerijk.mccreatecalc.util.sql.SQLBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComponentGroupDB implements ComponentGroupDAO {
    private static final String TABLE_COMPONENT_GROUP = "ComponentGroups";
    private static final String TABLE_WATERWHEELS = "WaterWheelComponentGroups";
    private static final String TABLE_CONSUMERS = "ConsumerComponentGroups";

    public void addGroupToNetwork(ComponentGroup group, String networkId) {
        try (Connection conn = DBConnection.getConnection(false)) {
            try {
                addComponentGroupToNetwork(conn, group, networkId);
                switch (group.getComponentType()) {
                    case WATER_WHEEL -> addWaterWheelGroup(conn, group);
                    case WINDMILL -> addWindmillGroup(conn, group);
                    case CONSUMER -> addConsumerGroup(conn, group);
                }
                conn.commit();
            } catch (SQLException | DataBaseAccessException ex) {
                conn.rollback();
                throw ex;
            }
        } catch (SQLException ex) {
            throw new DataBaseAccessException("Something went wrong while adding a group to a network.", ex);
        }
    }

    private void addConsumerGroup(Connection conn, ComponentGroup group) {
        try (PreparedStatement query = conn.prepareStatement(Queries.ADD_CONSUMER_GROUP)) {
            query.setString(1, group.getId());
            query.setString(2, group.getComponent().getName());
            query.setInt(3, group.getComponent().getRpm());
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new DataBaseAccessException("Something went wrong while adding the consumer group's info.", ex);
        }
    }

    private void addWindmillGroup(Connection conn, ComponentGroup group) {
        try (PreparedStatement query = conn.prepareStatement(Queries.ADD_WINDMILL_GROUP)) {
            query.setString(1, group.getId());
            query.setInt(2, ((Windmill) group.getComponent()).getSails());
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new DataBaseAccessException("Something went wrong while adding the windmill group's info.", ex);
        }
    }

    private void addWaterWheelGroup(Connection conn, ComponentGroup group) {
        try (PreparedStatement query = conn.prepareStatement(Queries.ADD_WATERWHEEL_GROUP)) {
            query.setString(1, group.getId());
            query.setInt(2, ((WaterWheel) group.getComponent()).getSize().ordinal());
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new DataBaseAccessException("Something went wrong while adding the water wheel group's info.", ex);
        }
    }

    private void addComponentGroupToNetwork(Connection conn, ComponentGroup group, String networkId) {
        try (PreparedStatement query = conn.prepareStatement(Queries.ADD_COMPONENT_GROUP_TO_NETWORK)) {
            query.setString(1, group.getId());
            query.setString(2, networkId);
            query.setInt(3, group.getAmount());
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new DataBaseAccessException("Something went wrong while adding the component group's base information to the network.", ex);
        }
    }


    @Override
    public List<ComponentGroup> getGroupsForNetwork(String networkId) {
        try (Connection conn = DBConnection.getConnection()) {
            List<ComponentGroup> groups = new ArrayList<>();
            groups.addAll(selectComponentsInNetwork(conn, networkId, Queries.SELECT_WATERWHEELS_BY_NETWORK, ComponentType.WATER_WHEEL));
            groups.addAll(selectComponentsInNetwork(conn, networkId, Queries.SELECT_WINDMILLS_BY_NETWORK, ComponentType.WINDMILL));
            groups.addAll(selectComponentsInNetwork(conn, networkId, Queries.SELECT_CONSUMERS_BY_NETWORK, ComponentType.CONSUMER));
            return groups;
        } catch (SQLException ex) {
            throw new DataBaseAccessException("Something went wrong while getting all component groups for a network.", ex);
        }
    }

    @Override
    public Optional<ComponentGroup> get(String groupId) {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement query = conn.prepareStatement(Queries.GET_BY_ID)) {
            query.setString(1, groupId);
            ResultSet res = query.executeQuery();

            if (!res.next()) return Optional.empty();

            res.getInt(WaterWheels.COL_SIZE);
            if (!res.wasNull()) {
                return Optional.of(mapResToComponentGroup(res, ComponentType.WATER_WHEEL));
            }
            res.getInt(Windmills.COL_SAILS);
            if (!res.wasNull()) {
                return Optional.of(mapResToComponentGroup(res, ComponentType.WINDMILL));
            }
            res.getString(Consumers.COL_CONSUMER);
            if (!res.wasNull()) {
                return Optional.of(mapResToComponentGroup(res, ComponentType.CONSUMER));
            }
            throw new DataBaseAccessException("The requested entry exists but does not have size, sails or name column.", null);

        } catch (SQLException ex) {
            throw new DataBaseAccessException("Something went wrong while getting a group from a network.", ex);
        }
    }

    public void deleteGroup(String groupId) {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement query = conn.prepareStatement(Queries.DELETE_BY_ID)) {
            query.setString(1, groupId);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseAccessException("Something went wrong while deleting component group by id", e);
        }
    }

    private List<ComponentGroup> selectComponentsInNetwork(Connection conn, String networkId, String sql, ComponentType type) {
        List<ComponentGroup> componentGroups = new ArrayList<>();
        try (PreparedStatement query = conn.prepareStatement(sql)) {
            query.setString(1, networkId);
            ResultSet res = query.executeQuery();
            while (res.next()) componentGroups.add(mapResToComponentGroup(res, type));
            return componentGroups;
        } catch (SQLException ex) {
            throw new DataBaseAccessException("Something went wrong while retrieving component groups for network", ex);
        }
    }

    private ComponentGroup mapResToComponentGroup(ResultSet res, ComponentType type) throws SQLException {
        String id = res.getString(Columns.ID);
        int amount = res.getInt("Amount");
        Component component = switch (type) {
            case CONSUMER -> mapConsumer(res);
            case WATER_WHEEL -> mapWaterWheel(res);
            case WINDMILL -> mapWindmill(res);
            default -> throw new UnsupportedOperationException();
        };
        return new ComponentGroup(id, component, amount);
    }

    private Component mapWindmill(ResultSet res) throws SQLException {
        return new WindmillImpl(res.getInt("Sails"));
    }

    private Component mapWaterWheel(ResultSet res) throws SQLException {
        int size = res.getInt("Size");
        WaterWheelType type = WaterWheelType.values()[size];
        return new WaterWheel(type);
    }

    private Component mapConsumer(ResultSet res) throws SQLException {
        String name = res.getString("Name");
        String img = res.getString("Image");
        int rpm = res.getInt("RPM");
        int stressImpact = res.getInt("Impact");
        return new BaseConsumer(name, img, rpm, stressImpact);
    }

    private static class Columns {
        private static final String ID = "GroupId";
        private static final String NETWORK = "Network";
        private static final String AMOUNT = "Amount";
    }

    private static class Windmills {
        private static final String TABLE = "WindmillComponentGroups";
        private static final String COL_SAILS = "Sails";
        private static final String COL_ID = "GroupId";
    }

    private static class WaterWheels {
        private static final String TABLE = "WaterWheelComponentGroups";
        private static final String COL_SIZE = "Size";
        private static final String COL_ID = "GroupId";
    }

    private static class Consumers {
        private static final String TABLE = "ConsumerComponentGroups";
        private static final String COL_CONSUMER = "Consumer";
        private static final String COL_RPM = "RPM";
        private static final String COL_ID = "GroupId";
    }

    private static class Queries {
        private static final String SELECT_WATERWHEELS_BY_NETWORK = new SQLBuilder().select()
                .from(TABLE_COMPONENT_GROUP)
                .rightJoin(TABLE_COMPONENT_GROUP, Columns.ID, WaterWheels.TABLE, Columns.ID)
                .where(Columns.NETWORK)
                .build();
        private static final String SELECT_WINDMILLS_BY_NETWORK = new SQLBuilder().select()
                .from(TABLE_COMPONENT_GROUP)
                .rightJoin(TABLE_COMPONENT_GROUP, Columns.ID, Windmills.TABLE, Columns.ID)
                .where(Columns.NETWORK)
                .build();
        private static final String SELECT_CONSUMERS_BY_NETWORK = new SQLBuilder().select()
                .from(TABLE_COMPONENT_GROUP)
                .rightJoin(TABLE_COMPONENT_GROUP, Columns.ID, Consumers.TABLE, Columns.ID)
                .leftJoin(Consumers.TABLE, Consumers.COL_CONSUMER, "Consumers", "Name")
                .where(Columns.NETWORK)
                .build();

        private static final String GET_BY_ID = new SQLBuilder().select()
                .from(TABLE_COMPONENT_GROUP)
                .leftJoin(TABLE_COMPONENT_GROUP, Columns.ID, TABLE_WATERWHEELS, Columns.ID)
                .leftJoin(TABLE_COMPONENT_GROUP, Columns.ID, Windmills.TABLE, Columns.ID)
                .leftJoin(TABLE_COMPONENT_GROUP, Columns.ID, TABLE_CONSUMERS, Columns.ID)
                .leftJoin(TABLE_CONSUMERS, Consumers.COL_CONSUMER, "Consumers", "Name")
                .where(TABLE_COMPONENT_GROUP + "." + Columns.ID)
                .build();

        private static final String ADD_COMPONENT_GROUP_TO_NETWORK = new SQLBuilder().insertInto(TABLE_COMPONENT_GROUP)
                .values(Columns.ID, Columns.NETWORK, Columns.AMOUNT)
                .build();
        private static final String ADD_WATERWHEEL_GROUP = new SQLBuilder().insertInto(WaterWheels.TABLE)
                .values(WaterWheels.COL_ID, WaterWheels.COL_SIZE)
                .build();
        private static final String ADD_WINDMILL_GROUP = new SQLBuilder().insertInto(Windmills.TABLE)
                .values(Windmills.COL_ID, Windmills.COL_SAILS)
                .build();
        private static final String ADD_CONSUMER_GROUP = new SQLBuilder().insertInto(Consumers.TABLE)
                .values(Consumers.COL_ID, Consumers.COL_CONSUMER, Consumers.COL_RPM)
                .build();

        private static final String DELETE_BY_ID = new SQLBuilder().deleteFrom(TABLE_COMPONENT_GROUP)
                .where(Columns.ID)
                .build();
    }
}
