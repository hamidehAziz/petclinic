package org.springframework.samples.petclinic.migration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.springframework.util.StringUtils.isEmpty;

public class SQLiteConnector {
    private static final String DB_NAME = "petclinic";
    private static Connection connection;

    private SQLiteConnector(){}

    public static Connection getDBConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME + ".sqlite");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return connection;
    }
}
