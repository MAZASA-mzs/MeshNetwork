package org.model.abstracts;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


/**
 * Abstract storage class for storing items.
 * @param <T> the type of items being stored.
 */
public abstract class AbstractStorage<T_ID, T> {
    
    private Map<AbstractID, T> items = new HashMap<>();

    public void update() {
        // Update logic
    }

    public List<T> getList() {
        return new ArrayList<>(items.values());
    }

    public void addItem(T item, AbstractID id) {
        items.put(id, item);
    }

    public T getItem(AbstractID id) {
        return items.get(id);
    }

    public void removeItem(AbstractID id) {
        items.remove(id);
    }
}
