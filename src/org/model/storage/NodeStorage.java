<<<<<<<< HEAD:src/org/model/NodeStorage.java
package org.model;

import org.model.abstracts.AbstractStorage;
import org.model.entities.Node;
import org.model.ids.NodeID;
========
package org.model.storage;

import org.model.abstracts.AbstractStorage;
import org.model.entities.Node;
import org.model.ids.node.NodeID;
>>>>>>>> 0f00acbc8245bc82bab1fbbd6837865ccdef38e5:src/org/model/storage/NodeStorage.java

/**
 * Storage for managing nodes in the network.
 */
public class NodeStorage extends AbstractStorage<Node> {

    public Node getNodeById(NodeID nodeId) {
        for (Node node : getList()) {
            if (node.getId().equals(nodeId)) {
                return node;
            }
        }
        return null;
    }
}
