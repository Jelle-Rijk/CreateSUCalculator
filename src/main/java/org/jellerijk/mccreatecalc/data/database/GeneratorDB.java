package org.jellerijk.mccreatecalc.data.database;

import org.jellerijk.mccreatecalc.data.dao.GeneratorDAO;
import org.jellerijk.mccreatecalc.entities.Generator;
import org.jellerijk.mccreatecalc.exceptions.DataBaseAccessException;
import org.jellerijk.mccreatecalc.util.sql.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GeneratorDB implements GeneratorDAO {
    private static final String TABLE = "Generators";
    private static final String COL_IMAGE = "Image";
    private static final String COL_SU = "StressUnits";
    private static final String COL_NAME = "Name";

    @Override
    public Optional<Generator> getByName(String name) {
        Generator generator = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(QueryBuilder.selectBy(TABLE, COL_NAME))) {
            query.setString(1, name);
            ResultSet res = query.executeQuery();
            if (res.next())
                generator = mapToGenerator(res);
            return Optional.ofNullable(generator);
        } catch (SQLException e) {
            throw new DataBaseAccessException(String.format("Something went wrong while retrieving generator with name: %s", name), e);
        }
    }

    @Override
    public List<Generator> getAll() {
        List<Generator> generators = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(QueryBuilder.selectAll(TABLE))) {
            ResultSet res = query.executeQuery();
            while (res.next())
                generators.add(mapToGenerator(res));
            return generators;
        } catch (SQLException e) {
            throw new DataBaseAccessException("Something went wrong while getting all generators in the database", e);
        }
    }

    private Generator mapToGenerator(ResultSet res) throws SQLException {
        return new Generator(res.getString(COL_NAME), res.getString(COL_IMAGE), res.getInt(COL_SU));
    }
}
