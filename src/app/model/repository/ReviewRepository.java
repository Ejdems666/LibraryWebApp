package app.model.repository;

import hyggedb.insert.InsertionExecutor;
import hyggedb.select.Condition;
import hyggedb.select.Selection;
import app.model.Model;
import app.model.QueryExecutor;
import app.model.entity.Review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by adam on 18/01/2017.
 */
public class ReviewRepository extends AbstractRepository<Review> {

    public ReviewRepository(Model model) {
        super(model);
    }

    @Override
    public Review getById(int id) {
        Review review = null;
        try {
            Selection selection = new Selection("review");
            selection.where("id=?",id);
            ResultSet rs = model.getSelectionExecutor().getResult(selection);
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
            ResultSet rs = model.getSelectionExecutor().getResult(new Selection("review"));
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
    public Collection<Review> findBy(Condition condition) {
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
        InsertionExecutor insertionExecutor = model.getInsertionExecutor();
        Object[] objects = new Object[3];
        String sql;
        for (Review persistedEntity : persistedEntities) {
            if(!identityMap.contains(persistedEntity)) {
                sql = "INSERT INTO review(value,timestamp,user_id,item_id) VALUES(?,?,?,?)";
                objects[0] = persistedEntity.getValue();
                objects[1] = persistedEntity.getTimestamp();
                objects[2] = persistedEntity.getUserId();
                objects[3] = persistedEntity.getItemId();
                int id = insertionExecutor.insert(sql,objects);
                persistedEntity.setId(id);
            } else {
                sql = "UPDATE frame SET value=?,timestamp=?,user_id=?,item_id=? WHERE id=?";
                objects[0] = persistedEntity.getValue();
                objects[1] = persistedEntity.getTimestamp();
                objects[2] = persistedEntity.getUserId();
                objects[3] = persistedEntity.getItemId();
                objects[4] = persistedEntity.getId();
                insertionExecutor.update(sql,objects);
            }
        }
    }
}
