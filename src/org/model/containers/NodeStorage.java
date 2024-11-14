package org.model.containers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.model.entity.Node;
import org.model.ids.NodeID;

/**
 * Storage for managing nodes in the network.
 */
public class NodeStorage {
    private Map<NodeID, Node> data = new HashMap<NodeID, Node>();

    public Node get(NodeID NodeId) {
        return this.data.get(NodeId);
    }

    public void add(Node item) {
        NodeID id = new NodeID();
        data.put(id, item);
    }

    public void remove(NodeID id) {
        data.remove(id);
    }

    public Set<NodeID> getAllIds() {
        return data.keySet();
    }

    public void update() {
        return;
    }
}
