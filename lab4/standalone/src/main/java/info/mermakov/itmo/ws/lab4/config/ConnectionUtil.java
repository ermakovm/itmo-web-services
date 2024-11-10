package info.mermakov.itmo.ws.lab4.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionUtil {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/ws_lab4";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private ConnectionUtil() {
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (SQLException exception) {
            Logger.getLogger(ConnectionUtil.class.getName()).log(Level.SEVERE, exception.getMessage(), exception);
            throw new RuntimeException(exception);
        }
    }
}
