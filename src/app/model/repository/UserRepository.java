package app.model.repository;

import app.model.Hasher;
import app.model.Model;
import app.model.entity.User;
import hyggedb.insert.InsertionExecutor;
import hyggedb.select.Condition;
import hyggedb.select.Selection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ivoni on 1/14/2017.
 */
public class UserRepository extends AbstractRepository<User> {
    public UserRepository(Model model) {
        super(model);
    }

    @Override
    public User getById(int id) {
        User user = null;
        try {
            Selection selection = new Selection("user");
            selection.where("id=?", id);
            ResultSet rs = model.getSelectionExecutor().getResult(selection);
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
            user.setId(rs.getInt("id"));
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
            ResultSet rs = model.getSelectionExecutor().getResult(new Selection("user"));
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
    public Collection findBy(Condition condition) {
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
        InsertionExecutor insertionExecutor = model.getInsertionExecutor();
        Object[] objects;
        String sql;
        String hashedPassword;
        Hasher hasher = new Hasher();
        for (User persistedEntity : persistedEntities) {
            if (!identityMap.contains(persistedEntity)) {
                sql = "INSERT INTO 'user' ('name', 'surname', 'email', 'password', 'salt') VALUES (?,?,?,?,?)";
                objects = new Object[5];
                objects[0] = persistedEntity.getName();
                objects[1] = persistedEntity.getSurname();
                objects[2] = persistedEntity.getEmail();
                persistedEntity.setSalt(hasher.generateSalt());
                hashedPassword = hasher.hashPassword(persistedEntity.getPassword(), persistedEntity.getSalt());
                objects[3] = hashedPassword;
                objects[4] = persistedEntity.getSalt();
                int id = insertionExecutor.insert(sql, objects);
                persistedEntity.setId(id);

            } else {
                sql = "UPDATE user SET name=?, surname=?, email=?, password=? WHERE id=?";
                objects = new Object[5];
                objects[0] = persistedEntity.getName();
                objects[1] = persistedEntity.getSurname();
                objects[2] = persistedEntity.getEmail();
                hashedPassword = hasher.hashPassword(persistedEntity.getPassword(), persistedEntity.getSalt());
                objects[3] = hashedPassword;
                objects[4] = persistedEntity.getId();
                insertionExecutor.update(sql, objects);
            }
        }
    }
}
