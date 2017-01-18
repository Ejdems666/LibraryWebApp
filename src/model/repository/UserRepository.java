package model.repository;

import model.Connector;
import model.QueryExecutor;
import model.QuerySelector;
import model.entity.Item;
import model.entity.User;
import model.entity.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ivoni on 1/14/2017.
 */
public class UserRepository implements Repository {
    private QuerySelector querySelector;
    private QueryExecutor queryExecutor;
    private ArrayList<User> identityMap = new ArrayList<>();
    private ArrayList<User> persistedEntities = new ArrayList<>();

    public UserRepository(Connector connector) {
        querySelector = new QuerySelector(connector.getConnection());
        queryExecutor = new QueryExecutor(connector.getConnection());
    }


    @Override
    public Entity getById(int id) {

        User user = null;

        try {
            ResultSet rs = querySelector.getResultSet("SELECT * FROM user WHERE id = " + id);
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
        identityMap.add(user);
        return user;
    }

    @Override
    public Collection<User> findAll() {

        ArrayList<User> users = new ArrayList<User>();

        try {
            ResultSet rs = querySelector.getResultSet("SELECT * FROM user");
            while (rs.next()) {
                int user_id = Integer.parseInt(rs.getString("user_id"));
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String salt = rs.getString("salt");
                User user = new User(user_id, name, surname, email, password, salt);
                identityMap.add(user);
                users.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void persist(Entity entity) {
        persistedEntities.add(((User) entity));
    }

    @Override
    public void persistAndFlush(Entity entity) {
        persist(entity);
        flush();
    }

    @Override
    public void flush() {
        Object[] objects;
        String sql;
        int id;
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
                id = queryExecutor.insert(sql, objects);
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
            }
        }
    }
}
