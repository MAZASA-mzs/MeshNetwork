package org.model.ids;

import java.util.UUID;

import org.model.abstracts.AbstractID;

/**
 * Represents a unique identifier for a Node.
 */
public class NodeID extends AbstractID {

    public NodeID() {
        super();
    }

    public NodeID(UUID id) {
        super(id);
    }
}