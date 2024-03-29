package DAO;
/**
 * This is the class to hold the Database Queries
 * @author Antonio Peza
 *
 * */

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;

/**
 * This class is used to construct all the statements to send to the database
 */
public class DatabaseQuery {
    private static String query;
    private static Statement statement;
    private static ResultSet result;

    /*
    * Sets statement object to use SQL methods
    * @param connection
    * */
    public static void setStatement(Connection connection) throws SQLException {
        statement = connection.createStatement();
    }

    /*
    * Returns the statement method created
    * @return statement
    * */
    public static Statement getStatement(){
        return statement;
    }
}