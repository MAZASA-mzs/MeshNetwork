package org.storage;

import org.abstracts.AbstractStorage;
import org.entities.Node;
import org.ids.node.NodeID;

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
