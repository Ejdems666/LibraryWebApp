package app.model.repository;

import app.model.Model;
import app.model.entity.Entity;

import java.util.ArrayList;

/**
 * Created by adam on 18/01/2017.
 */
public abstract class AbstractRepository<K extends Entity> implements Repository<K> {
    protected Model model;
    protected ArrayList<K> identityMap = new ArrayList<K>();
    protected ArrayList<K> persistedEntities = new ArrayList<K>();

    public AbstractRepository(Model model) {
        this.model = model;
    }
}
