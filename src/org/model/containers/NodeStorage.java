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
        return;
    }
}
