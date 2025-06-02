package data;

import lombok.SneakyThrows;

import java.sql.*;

public class SQLHelper {
    private static final String dbUrl = System.getProperty("db.url");
    private static final String dbUser = System.getProperty("db.user");
    private static final String dbPass = System.getProperty("db.pass");

    @SneakyThrows
    public static String getLatestStatus() {
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;")) {
            return rs.next() ? rs.getString("status") : null;
        }
    }

    @SneakyThrows
    public static void cleanDatabase() {
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM payment_entity");
        }
    }
}
