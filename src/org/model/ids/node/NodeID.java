<<<<<<<< HEAD:src/org/model/ids/NodeID.java
package org.model.ids;
========
package org.model.ids.node;
>>>>>>>> 0f00acbc8245bc82bab1fbbd6837865ccdef38e5:src/org/model/ids/node/NodeID.java

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
