package storage;

import abstract.AbstractStorage;
import entities.Node;
import ids.NodeID;

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
