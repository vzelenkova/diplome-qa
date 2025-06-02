java
package db;

import data.SQLHelper;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.*;

public class DBTest {
    @Test
    void testMySQLContainer() {
        try (MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8")) {
            mysql.start();
            System.setProperty("db.url", mysql.getJdbcUrl());
            System.setProperty("db.user", mysql.getUsername());
            System.setProperty("db.pass", mysql.getPassword());
            SQLHelper.cleanDatabase();
            assertNull(SQLHelper.getLatestStatus());
        }
    }

    @Test
    void testPostgresContainer() {
        try (PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")) {
            postgres.start();
            System.setProperty("db.url", postgres.getJdbcUrl());
            System.setProperty("db.user", postgres.getUsername());
            System.setProperty("db.pass", postgres.getPassword());
            SQLHelper.cleanDatabase();
            assertNull(SQLHelper.getLatestStatus());
        }
    }
}
