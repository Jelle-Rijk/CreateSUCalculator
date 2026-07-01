package org.jellerijk.mccreatecalc.data.database;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBConnection {
    public static final Path DATABASE_LOCATION = Path
            .of(System.getProperty("user.home"), "JR_Custom_Tools", "CreateCalculator", "database.sqlite");
    public static final String DATABASE_URL = String.format("jdbc:sqlite:%s", DATABASE_LOCATION);


    /**
     * @param autocommit Whether the connection should autocommit or not.
     * @return Connection to the database.
     */
    public static Connection getConnection(boolean autocommit) {
        try {
            Connection conn = DriverManager.getConnection(DATABASE_URL);
            if (conn == null)
                throw new IllegalStateException("Could not get connection to Database.");
            conn.setAutoCommit(autocommit);
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets a new Connection with autocommit set to true.
     *
     * @return Connection to the database.
     */
    public static Connection getConnection() {
        return getConnection(true);
    }
}
