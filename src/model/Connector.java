package model;

import java.sql.*;

/**
 * Created by Ejdems on 16/11/2016.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connector {

    private Connection conn = null;

    private static final String IP = "localhost";
    private static final int PORT = 3306;
    private static final String DATABASE = "cba_library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";


     public Connector() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE;
        this.conn = DriverManager.getConnection(url, USERNAME, PASSWORD);
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void close() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
