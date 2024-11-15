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

    public void update() {
        // Update nodes and connections
    }

    public void addNode(Node node) {
        NodeStorage.add(node);
    }

    public void removeNode(NodeID nodeId) {
        NodeStorage.remove(nodeId);
    }

    public void addConnection(Connection connection) {
        ConnectionStorage.add(connection);
    }

    public void removeConnection(ConnectionID connectionId) {
        ConnectionStorage.remove(connectionId);
    }

    public String getViewData() {
        // Returns the data to be viewed in the GUI
        return null;
    }
}
