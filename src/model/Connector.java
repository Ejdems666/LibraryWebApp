package model;

import java.sql.*;

/**
 * Created by Ejdems on 16/11/2016.
 */
public class Connector {
    private static final String IP = "wm62.wedos.net";
    private static final int PORT = 3306;
    private static final String DATABASE = "d73332_library";
    private static final String USERNAME = "w73332_library";
    private static final String PASSWORD = "KDCpsaxQ";

    private Connection conn = null;

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
