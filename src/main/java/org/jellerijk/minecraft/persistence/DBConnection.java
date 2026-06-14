package org.jellerijk.minecraft.persistence;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBConnection {
    public static final Path DATABASE_LOCATION = Path
            .of(System.getProperty("user.home"), "JR_Custom_Tools", "CreateCalculator", "database.sqlite");
    public static final String DATABASE_URL = String.format("jdbc:sqlite:%s", DATABASE_LOCATION);

    /**
     * Gets a new Connection
     *
     * @return Connection to the database.
     */
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(DATABASE_URL);
            if (conn == null) throw new IllegalStateException("Could not get connection to Database.");
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
