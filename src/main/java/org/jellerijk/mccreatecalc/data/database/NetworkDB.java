package org.jellerijk.mccreatecalc.data.database;

import org.jellerijk.mccreatecalc.application.dto.NetworkInfo;
import org.jellerijk.mccreatecalc.data.dao.NetworkDAO;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.jellerijk.mccreatecalc.exceptions.DataBaseAccessException;
import org.jellerijk.mccreatecalc.util.sql.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NetworkDB implements NetworkDAO {
    private static final String COL_ID = "Id";
    private static final String COL_NAME = "Name";
    private static final String TABLE = "Network";

    @Override
    public void insert(StressNetwork network) {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement query = conn.prepareStatement(
                QueryBuilder.insert(TABLE, List.of(COL_ID, COL_NAME)))) {
            query.setString(1, network.id());
            query.setString(2, network.name());
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new DataBaseAccessException("Something went wrong while inserting a network.", ex);
        }
    }

    @Override
    public List<NetworkInfo> loadInfoAndIdsForAllNetworks() {
        List<NetworkInfo> networks = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection(); PreparedStatement query = conn.prepareStatement(
                QueryBuilder.selectAll(TABLE, COL_ID, COL_NAME))) {
            ResultSet res = query.executeQuery();
            while (res.next()) networks.add(new NetworkInfo(res.getString(COL_ID), res.getString(COL_NAME)));
            return networks;
        } catch (SQLException ex) {
            throw new DataBaseAccessException("Something went wrong while loading info for all networks.", ex);
        }
    }

    @Override
    public Optional<StressNetwork> getById(String id) {
        StressNetwork network = null;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement query = conn.prepareStatement(QueryBuilder.selectBy(TABLE, COL_ID))) {
            query.setString(1, id);
            ResultSet res = query.executeQuery();
            if (res.next())
                network = mapToStressNetwork(res);
            return Optional.ofNullable(network);
        } catch (SQLException e) {
            throw new DataBaseAccessException("Something went wrong while getting a network by its id.", e);
        }
    }

    @Override
    public void update(StressNetwork network) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(QueryBuilder.updateCols(TABLE, COL_ID, List.of(COL_NAME)))) {
            query.setString(1, network.name());
            query.setString(2, network.id());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseAccessException("Something went wrong while updating a stress network.", e);
        }
    }

    private StressNetwork mapToStressNetwork(ResultSet res) throws SQLException {
        String id = res.getString(COL_ID);
        String name = res.getString(COL_NAME);
        return StressNetwork.Builder.aStressNetwork()
                .withId(id)
                .withName(name)
                .withGenerators(new ArrayList<>())
                .build(); // TODO fill components argument with actual components
    }
}
