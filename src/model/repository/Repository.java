package model.repository;

import model.entity.Entity;

/**
 * Created by adam on 12/01/2017.
 */
public interface Repository<K extends Entity> {
    public K getById();
}