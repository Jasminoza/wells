package org.example.util;

import com.typesafe.config.ConfigFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtils {
    private static Connection connectionToSQLite;

    private static final String DB_DRIVER = "db.driver";
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";

    private JDBCUtils() {
    }

    public static synchronized PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }

    private static synchronized Connection getConnection() {
        if (connectionToSQLite == null) {
            try {
                String jdbcDriver = ConfigFactory.load().getString(DB_DRIVER);
                String databaseUrl = ConfigFactory.load().getString(DB_URL);
                String user = ConfigFactory.load().getString(DB_USER);
                String password = ConfigFactory.load().getString(DB_PASSWORD);
                Class.forName(jdbcDriver);
                connectionToSQLite = DriverManager.getConnection(databaseUrl, user, password);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return connectionToSQLite;
    }
}
