package com.devlopp.teq.sql;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestQueryCheck {

    @Test
    @DisplayName("test select query check")
    void testSelectQueryCheck() {
        String sql = "SELECT * FROM table_name";
        assertTrue(SQLDriver.isSelectQuery(sql));
    }

    @Test
    @DisplayName("test select query check with delete")
    void testSelectQueryCheckWithDelete() {
        String sql = "DELETE FROM table_name";
        assertFalse(SQLDriver.isSelectQuery(sql));
    }

    @Test
    @DisplayName("test select query check with create")
    void testSelectQueryCheckWithCreate() {
        String sql = "CREATE VIEW IF NOT EXISTS table_name";
        assertFalse(SQLDriver.isSelectQuery(sql));
    }

    @Test
    @DisplayName("test select query check with drop")
    void testSelectQueryCheckWithDrop() {
        String sql = "DROP TABLE IF NOT EXISTS table_name";
        assertFalse(SQLDriver.isSelectQuery(sql));
    }

    @Test
    @DisplayName("test select query check with insert")
    void testSelectQueryCheckWithInsert() {
        String sql = "INSERT INTO table_name (value) VALUES (value)";
        assertFalse(SQLDriver.isSelectQuery(sql));
    }

    @Test
    @DisplayName("test select query check with truncate")
    void testSelectQueryCheckWithTruncate() {
        String sql = "TRUNCATE table_name";
        assertFalse(SQLDriver.isSelectQuery(sql));
    }

    @Test
    @DisplayName("test select query check with update")
    void testSelectQueryCheckWithUpdate() {
        String sql = "UPDATE TABLE table_name SET value = 10";
        assertFalse(SQLDriver.isSelectQuery(sql));
    }
    
    @Test
    @DisplayName("test select query check with select and drop")
    void testSelectQueryCheckWithSelectAndDrop() {
        String sql = "SELECT * FROM table_name; UPDATE TABLE table_name SET value = 10";
        assertFalse(SQLDriver.isSelectQuery(sql));
    }

}
