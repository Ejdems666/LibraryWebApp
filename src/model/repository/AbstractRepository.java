package model.repository;

import model.QuerySelector;
import model.entity.Entity;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by adam on 18/01/2017.
 */
public abstract class AbstractRepository<K extends Entity> implements Repository<K> {
    protected Connection connection;
    protected QuerySelector querySelector;
    protected ArrayList<K> identityMap = new ArrayList<K>();
    protected ArrayList<K> persistedEntities = new ArrayList<K>();

    public AbstractRepository(Connection connection) {
        connection = connection;
        querySelector = new QuerySelector(connection);
    }
}
