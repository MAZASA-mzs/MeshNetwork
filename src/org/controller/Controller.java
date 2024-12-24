package org.controller;

import org.model.containers.ConnectionStorage;
import org.model.containers.NodeStorage;

import org.model.entity.Connection;
import org.model.entity.Node;

import org.model.ids.ConnectionID;
import org.model.ids.NodeID;

/**
 * Controller class for managing the network system.
 */
public class Controller {
    public static void update() {
        // Update nodes and connections
    }

    public static void addNode(Node node) {
        NodeStorage.add(node);
    }

    public static void removeNode(NodeID nodeId) {
        NodeStorage.remove(nodeId);
    }

    public static void addConnection(Connection connection) {
        ConnectionStorage.add(connection);
    }

    public static void removeConnection(ConnectionID connectionId) {
        ConnectionStorage.remove(connectionId);
    }

    public static String getViewData() {
        // Returns the data to be viewed in the GUI
        return null;
    }
}
