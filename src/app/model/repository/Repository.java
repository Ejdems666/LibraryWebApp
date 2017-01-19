package app.model.repository;

import hyggedb.select.Condition;
import app.model.entity.Entity;
import hyggedb.select.Selection;

import java.util.Collection;

/**
 * Created by adam on 12/01/2017.
 */
public interface Repository<K extends Entity> {
    public K getById(int id);

    public Collection<K> findAll();

    public Collection<K> findBy(Condition condition);

    public void persist(K entity);

    public void persistAndFlush(K entity);

    public void flush();


}
