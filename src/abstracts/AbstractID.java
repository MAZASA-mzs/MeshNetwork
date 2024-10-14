package abstracts;

import java.util.UUID;

/**
 * Abstract class representing an object ID.
 */
public abstract class AbstractID {

    private final UUID id;

    /**
     * Constructor for generating a new ID.
     */
    public AbstractID() {
        this.id = UUID.randomUUID();
    }

    /**
     * Constructor for creating an ID from an existing UUID.
     * @param id the UUID to use.
     */
    public AbstractID(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AbstractID that = (AbstractID) obj;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
