package org.jellerijk.minecraft.persistence;

import org.jellerijk.minecraft.exceptions.DataAccessException;
import org.jellerijk.minecraft.model.component.type.ComponentType;
import org.jellerijk.minecraft.model.component.type.implementations.ComponentTypeImpl;
import org.jellerijk.minecraft.services.dataaccess.ComponentTypeDAO;
import org.jellerijk.minecraft.util.sql.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComponentTypeDB implements ComponentTypeDAO {
    private static final String TABLE = "ComponentType";
    private static final String COL_NAME = "Name";
    private static final String COL_IMG = "ImagePath";
    private static final String COL_STRESS_IMPACT = "StressImpact";
    private static final String COL_GENERATOR = "IsGenerator";
    private static final String COL_MIN_RPM = "MinimumRPM";
    private static final String COL_RPM_CONST = "RPM";

    private static final String QUERY_SELECT_ALL = QueryBuilder.selectAll(TABLE);

    @Override
    public Optional<ComponentType> load(String name) {
        return Optional.empty();
    }

    @Override
    public List<ComponentType> loadAll() {
        List<ComponentType> types = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(QUERY_SELECT_ALL)) {
            ResultSet res = query.executeQuery();
            while (res.next())
                types.add(map(res));
            return types;
        } catch (SQLException e) {
            throw new DataAccessException("Could not load all component types.", e);
        }
    }

    @Override
    public List<ComponentType> getGenerators() {
        List<ComponentType> types = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement query = conn.prepareStatement("SELECT  * FROM " + TABLE + " WHERE " + COL_GENERATOR + "=1" )) {
            ResultSet res = query.executeQuery();
            while (res.next())
                types.add(map(res));
            return types;
        } catch (SQLException e) {
            throw new DataAccessException("Could not load all component types.", e);
        }
    }

    @Override
    public List<ComponentType> getConsumers() {
        List<ComponentType> types = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement query = conn.prepareStatement("SELECT  * FROM " + TABLE + " WHERE " + COL_GENERATOR + "=0" )) {
            ResultSet res = query.executeQuery();
            while (res.next())
                types.add(map(res));
            return types;
        } catch (SQLException e) {
            throw new DataAccessException("Could not load all component types.", e);
        }
    }


    private ComponentType map(ResultSet res) throws SQLException {
        String name = res.getString(COL_NAME);
        String img = res.getString(COL_IMG);
        int stressImpact = res.getInt(COL_STRESS_IMPACT);
        boolean generator = res.getInt(COL_GENERATOR) == 1;
        int minRpm = res.getInt(COL_MIN_RPM);
        int rpm = res.getInt(COL_RPM_CONST);
        return new ComponentTypeImpl(name, img, stressImpact, generator, minRpm, rpm == 0 ? null : rpm);
    }
}
