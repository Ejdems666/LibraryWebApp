package model.repository;

import model.QueryExecutor;
import model.QuerySelector;
import model.entity.Review;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by adam on 18/01/2017.
 */
public class ReviewRepository extends AbstractRepository<Review> {

    public ReviewRepository(Connection connection) {
        super(connection);
    }

    @Override
    public Review getById(int id) {
        Review review = null;
        try {
            ResultSet rs = querySelector.getResultSet("SELECT * FROM review WHERE id=" + id);
            review = mapReview(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review;
    }

    private Review mapReview(ResultSet rs) throws SQLException {
        Review review = null;
        if (rs.next()) {
            review.setTimestamp(rs.getDate("timestamp"));
            review.setValue(rs.getInt("value"));
            review.setId(rs.getInt("id"));
            review.setUserId(rs.getInt("user_id"));
            review.setItemId(rs.getInt("item_id"));
            identityMap.add(review);
        }
        return review;
    }

    @Override
    public Collection<Review> findAll() {
        ArrayList<Review> reviews = null;
        try {
            ResultSet rs = querySelector.getResultSet("SELECT * FROM review");
            Review review = mapReview(rs);
            while (review != null) {
                reviews.add(review);
                review = mapReview(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public Collection<Review> findBy(String[] conditions, Object[] attributes) {
        return null;
    }

    @Override
    public void persist(Review entity) {
        persistedEntities.add(entity);
    }

    @Override
    public void persistAndFlush(Review entity) {
        persist(entity);
        flush();
    }

    @Override
    public void flush() {
        QueryExecutor queryExecutor = new QueryExecutor(connection);
        Object[] objects = new Object[3];
        String sql;
        for (Review persistedEntity : persistedEntities) {
            if(!identityMap.contains(persistedEntity)) {
                sql = "INSERT INTO review(value,timestamp,user_id,item_id) VALUES(?,?,?,?)";
                objects[0] = persistedEntity.getValue();
                objects[1] = persistedEntity.getTimestamp();
                objects[2] = persistedEntity.getUserId();
                objects[3] = persistedEntity.getItemId();
                int id = queryExecutor.insert(sql,objects);
                persistedEntity.setId(id);
            } else {
                sql = "UPDATE frame SET value=?,timestamp=?,user_id=?,item_id=? WHERE id=?";
                objects[0] = persistedEntity.getValue();
                objects[1] = persistedEntity.getTimestamp();
                objects[2] = persistedEntity.getUserId();
                objects[3] = persistedEntity.getItemId();
                objects[4] = persistedEntity.getId();
                queryExecutor.update(sql,objects);
            }
        }
    }
}
