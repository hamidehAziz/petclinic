package org.springframework.samples.petclinic.migration.helper;

import org.springframework.samples.petclinic.migration.SQLiteConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SQLiteHelper {

    public static ResultSet fetchFindAllQuery(String tableName) throws SQLException {
        final String FIND_ALL = "SELECT * FROM "+ tableName +";";

        Connection connection = SQLiteConnector.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
        return preparedStatement.executeQuery();
    }

    public static ResultSet fetchFindByIdQuery(String tableName, Integer id) throws SQLException {
        final String FIND_BY_ID = "SELECT * FROM "+ tableName +" WHERE id =" + id;

        Connection connection =  SQLiteConnector.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
        return  preparedStatement.executeQuery();
    }

    public static ResultSet fetchFindByLastName(String tableName, String lastName) throws SQLException {
        final String FIND_BY_LAST_NAME = "SELECT * FROM "+ tableName +"WHERE last_name LIKE "+ lastName +";";

        Connection connection = SQLiteConnector.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LAST_NAME);
        return preparedStatement.executeQuery();
    }

}
