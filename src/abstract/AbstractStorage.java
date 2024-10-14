package abstract;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract storage class for storing items in the network.
 * @param <T> the type of items being stored.
 */
public abstract class AbstractStorage<T> {

    private List<T> items = new ArrayList<>();

    public void update() {
        // Update logic
    }

    public List<T> getList() {
        return items;
    }

    public void addItem(T item) {
        items.add(item);
    }

    public T getItem(String id) {
        // Find and return item by ID
        return null;
    }

    public void removeItem(String id) {
        // Remove item by ID
    }
}
