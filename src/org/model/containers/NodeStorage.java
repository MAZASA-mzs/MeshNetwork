package org.model.containers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.model.entity.Node;
import org.model.ids.NodeID;

/**
 * Storage for managing nodes in the network.
 */
public class NodeStorage {
    private static Map<NodeID, Node> data = new HashMap<NodeID, Node>();

    private NodeStorage() {
        throw new UnsupportedOperationException("This class cannot be instantiated, it's static.");
    }

    public static Node get(NodeID nodeID) {
        return data.get(nodeID);
    }

    public static void add(Node item) {
        data.put(item.getID(), item);
    }

    public static void remove(NodeID nodeID) {
        data.remove(nodeID);
    }

    public static Set<NodeID> getIDs() {
        return data.keySet();
    }

    public static void update() {
        for (NodeID nodeID : NodeStorage.getIDs()) {
            NodeStorage.get(nodeID).tick();
        }
        return;
    }

    public static List<Node> getNodesList() {
        List<Node> nodes = new LinkedList<>();
        for (NodeID nodeID : NodeStorage.data.keySet())
            nodes.add(NodeStorage.get(nodeID));
        return nodes;
    }
}
