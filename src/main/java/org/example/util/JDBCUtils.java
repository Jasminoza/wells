package org.example.util;

import com.typesafe.config.ConfigFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtils {
    private static Connection connectionToMySQLite;

    private JDBCUtils() {
    }

    public static synchronized PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }

    private static synchronized Connection getConnection() {
        if (connectionToMySQLite == null) {
            try {
                String jdbcDriver = ConfigFactory.load().getString("db.driver");
                String databaseUrl = ConfigFactory.load().getString("db.url");
                String user = ConfigFactory.load().getString("db.user");
                String password = ConfigFactory.load().getString("db.password");
                Class.forName(jdbcDriver);
                connectionToMySQLite = DriverManager.getConnection(databaseUrl, user, password);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return connectionToMySQLite;
    }
}
