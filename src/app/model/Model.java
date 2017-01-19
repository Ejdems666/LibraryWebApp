package app.model;

import app.model.repository.*;
import hyggedb.insert.InsertionExecutor;
import hyggedb.select.SelectionExecutor;

/**
 * Created by Ejdems on 16/11/2016.
 */
public class Model {
    private final Connector db;
    private SelectionExecutor selectionExecutor;
    private InsertionExecutor insertionExecutor;
    private AttributeRepository attributeRepository = null;
    private CategoryRepository categoryRepository = null;
    private ItemAttributeRepository itemAttributeRepository = null;
    private ItemRepository itemRepository = null;
    private ReviewRepository reviewRepository = null;
    private UserRepository userRepository = null;

    // made as singleton, this way it can only be instanced one time
    private static Model instance = null;

    public static Model getInstance() {
        if(instance == null) {
            try {
                instance = new Model();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private Model() throws Exception {
        db = new Connector();
        selectionExecutor = new SelectionExecutor(db.getConnection());
        insertionExecutor = new InsertionExecutor(db.getConnection());
    }

    public void close() {
        db.close();
    }

    public SelectionExecutor getSelectionExecutor() {
        return selectionExecutor;
    }

    public InsertionExecutor getInsertionExecutor() {
        return insertionExecutor;
    }

    public AttributeRepository getAttributeRepository() {
        if(attributeRepository == null) {
            attributeRepository = new AttributeRepository(this);
        }
        return attributeRepository;
    }

    public CategoryRepository getCategoryRepository() {
        if(categoryRepository == null) {
            categoryRepository = new CategoryRepository(this);
        }
        return categoryRepository;
    }

    public ItemAttributeRepository getItemAttributeRepository() {
        if(itemAttributeRepository == null) {
            itemAttributeRepository = new ItemAttributeRepository(this);
        }
        return itemAttributeRepository;
    }

    public ItemRepository getItemRepository() {
        if(itemRepository == null) {
            itemRepository = new ItemRepository(this);
        }
        return itemRepository;
    }

    public ReviewRepository getReviewRepository() {
        if(reviewRepository == null) {
            reviewRepository = new ReviewRepository(this);
        }
        return reviewRepository;
    }

    public UserRepository getUserRepository() {
        if(userRepository == null) {
            userRepository = new UserRepository(this);
        }
        return userRepository;
    }
}
