package model.repository;

import model.entity.Entity;

/**
 * Created by adam on 12/01/2017.
 */
public interface Repository<K extends Entity> {
    public K getById(int id);

    public K[] findAll();

    public void persist(K entity);

    public void persistAndFlush(K entity);

    public void flush();
}
