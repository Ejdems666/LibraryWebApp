package model.repository;

import model.Connector;
import model.SqlIO;
import model.entity.User;
import model.entity.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by ivoni on 1/14/2017.
 */
public class UserRepository implements Repository {
    private SqlIO sqlIO;

    public UserRepository(Connector connector) {
        sqlIO = new SqlIO(connector.getConnection());
    }


    @Override
    public Entity getById(int id) {

        User user = null;

        try {
            ResultSet rs = sqlIO.getResultSet("SELECT * FROM user WHERE id = " + id);
            if (rs.next()) {
                user.setId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password")); // Not sure, if this one needs to be here
                user.setSalt(rs.getString("salt")); // Don't know about that one either
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public Entity[] findAll() {

        ArrayList<User> users = new ArrayList<User>();

        try {
            ResultSet rs = sqlIO.getResultSet("SELECT * FROM user");
            while (rs.next()) {
                int user_id = Integer.parseInt(rs.getString("user_id"));
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String salt = rs.getString("salt");
                users.add(new User(user_id, name, surname, email, password, salt));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User[] users1 = new User[users.size()];
        return users1;
    }
}
