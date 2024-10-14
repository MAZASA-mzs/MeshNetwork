package org.model.abstracts;

import java.util.UUID;

/**
 * Abstract class representing an object ID.
 */
public abstract class AbstractID {

    public final UUID id;

    /**
     * Constructor for generating a new ID.
     */
    public AbstractID() {
        this.id = UUID.randomUUID();
    }

    public AbstractID(UUID id) {
        this.id = id;
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

    public abstract AbstractID copy();
}
