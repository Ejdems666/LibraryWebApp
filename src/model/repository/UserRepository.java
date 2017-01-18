package model.repository;

import model.Connector;
import model.QueryExecutor;
import model.QuerySelector;
import model.entity.Entity;
import model.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ivoni on 1/14/2017.
 */
public class UserRepository extends AbstractRepository<User> {
    public UserRepository(Connection connection) {
        super(connection);
    }


    @Override
    public User getById(int id) {
        User user = null;
        try {
            ResultSet rs = querySelector.getResultSet("SELECT * FROM user WHERE id = " + id);
            user = mapUser(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        identityMap.add(user);
        return user;
    }

    private User mapUser(ResultSet rs) throws SQLException {
        User user = new User();
        if (rs.next()) {
            user.setId(rs.getInt("user_id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password")); // Not sure, if this one needs to be here
            user.setSalt(rs.getString("salt")); // Don't know about that one either
            identityMap.add(user);
        }
        return user;
    }

    @Override
    public Collection<User> findAll() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            ResultSet rs = querySelector.getResultSet("SELECT * FROM user");
            User user = mapUser(rs);
            while (user != null) {
                users.add(user);
                user = mapUser(rs);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Collection findBy(String[] conditions, Object[] attributes) {
        return null;
    }

    @Override
    public void persist(User entity) {
        persistedEntities.add(entity);
    }

    @Override
    public void persistAndFlush(User entity) {
        persist(entity);
        flush();
    }

    @Override
    public void flush() {
        QueryExecutor queryExecutor = new QueryExecutor(connection);
        Object[] objects;
        String sql;
        for (User persistedEntity : persistedEntities) {
            if (!identityMap.contains(persistedEntity)) {
                sql = "INSERT INTO 'user' ('name', 'surname', 'email', 'password', 'salt')" +
                        "VALUES (?,?,?,?,?)";
                objects = new Object[5];
                objects[1] = persistedEntity.getName();
                objects[2] = persistedEntity.getSurname();
                objects[3] = persistedEntity.getEmail();
                objects[4] = persistedEntity.getPassword();
                objects[5] = persistedEntity.getSalt();
                int id = queryExecutor.insert(sql, objects);
                persistedEntity.setId(id);

            } else {
                sql = "UPDATE order SET name=?, surname=?, email=?, password=? salt=? WHERE id=?";
                objects = new Object[6];
                objects[0] = persistedEntity.getName();
                objects[1] = persistedEntity.getSurname();
                objects[2] = persistedEntity.getEmail();
                objects[3] = persistedEntity.getPassword();
                objects[4] = persistedEntity.getSalt();
                objects[5] = persistedEntity.getId();
                queryExecutor.update(sql,objects);
            }
        }
    }
}
