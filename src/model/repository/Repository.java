package model.repository;

import model.entity.Entity;

import java.util.Collection;

/**
 * Created by adam on 12/01/2017.
 */
public interface Repository<K extends Entity> {
    public K getById(int id);

    public Collection<K> findAll();
}
