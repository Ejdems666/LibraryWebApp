package model.repository;

import model.Connector;
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
public class ReviewRepository implements Repository<Review> {
    private QuerySelector querySelector;
    private ArrayList<Review> identiryMap = new ArrayList<>();
    private ArrayList<Review> persistedEntities = new ArrayList<>();
    private QueryExecutor queryExecutor;

    public ReviewRepository(Connection connection) {
        this.querySelector = new QuerySelector(connection);
        this.queryExecutor = new QueryExecutor(connection);
    }

    @Override
    public Review getById(int id) {
        Review review = null;
        try {
            ResultSet rs = querySelector.getResultSet("SELECT * FROM review WHERE id=" + id);

            review = mapReview(rs);
            identiryMap.add(review);

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
        }
        return review;
    }

    @Override
    public Collection<Review> findAll() {
        ArrayList<Review> reviews = null;
        try {
            ResultSet rs = querySelector.getResultSet("SELECT * FROM review");
            while (rs.next()) {
                Review review = mapReview(rs);
                identiryMap.add(review);
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public void persist(Review entity) {

    }

    @Override
    public void persistAndFlush(Review entity) {

    }

    @Override
    public void flush() {

    }
}
