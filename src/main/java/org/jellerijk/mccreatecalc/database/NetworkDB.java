package org.jellerijk.mccreatecalc.database;

import org.jellerijk.mccreatecalc.application.usecases.network.NetworkInfo;
import org.jellerijk.mccreatecalc.data.NetworkDAO;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.jellerijk.mccreatecalc.exceptions.DataBaseAccessException;
import org.jellerijk.mccreatecalc.util.sql.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            query.executeQuery();
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
}
