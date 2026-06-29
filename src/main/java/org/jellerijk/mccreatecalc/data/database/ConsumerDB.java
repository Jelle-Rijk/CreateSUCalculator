package org.jellerijk.mccreatecalc.data.database;

import org.jellerijk.mccreatecalc.data.dao.ConsumerDAO;
import org.jellerijk.mccreatecalc.entities.components.BaseConsumer;
import org.jellerijk.mccreatecalc.entities.components.Consumer;
import org.jellerijk.mccreatecalc.exceptions.DataBaseAccessException;
import org.jellerijk.mccreatecalc.util.sql.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsumerDB implements ConsumerDAO {
    private static final String TABLE = "Consumers";
    private static final String COL_IMAGE = "Image";
    private static final String COL_NAME = "Name";
    private static final String COL_IMPACT = "Impact";


    @Override
    public List<Consumer> getAll() {
        List<Consumer> consumers = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(QueryBuilder.selectAll(TABLE))) {
            ResultSet res = query.executeQuery();
            while (res.next())
                consumers.add(mapToConsumer(res));
            return consumers;
        } catch (SQLException ex) {
            throw new DataBaseAccessException("Something went wrong while getting all consumers.", ex);
        }
    }

    @Override
    public Optional<Consumer> getByName(String name) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(QueryBuilder.selectBy(TABLE, COL_NAME))) {
            query.setString(1, name);
            ResultSet res = query.executeQuery();
            return res.next() ? Optional.of(mapToConsumer(res)) : Optional.empty();
        } catch (SQLException e) {
            throw new DataBaseAccessException("Something went wrong while getting a consumer by name", e);
        }
    }

    private Consumer mapToConsumer(ResultSet res) throws SQLException {
        return BaseConsumer.Builder.aBaseConsumer()
                .withName(res.getString(COL_NAME))
                .withImg(res.getString(COL_IMAGE))
                .withStressImpact(res.getInt(COL_IMPACT))
                .build();
    }
}
