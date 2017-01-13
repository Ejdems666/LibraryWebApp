package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by adam on 13/01/2017.
 */
public class SqlIO {
    private Connection connection;

    public SqlIO(Connection connection) {
        this.connection = connection;
    }

    public ResultSet getResultSet(String query)
            throws SQLException {

        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(query);
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (stmt != null) { stmt.close(); }
        }
        return resultSet;
    }
}
