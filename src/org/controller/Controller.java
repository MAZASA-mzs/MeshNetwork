package org.controller;

<<<<<<< HEAD
import org.model.ConnectionStorage;
import org.model.NodeStorage;
import org.model.entities.Connection;
import org.model.entities.Node;
import org.model.ids.ConnectionID;
import org.model.ids.NodeID;
=======
import org.model.entities.Node;
import org.model.ids.connection.ConnectionID;
import org.model.ids.node.NodeID;
import org.model.storage.NodeStorage;
import org.model.entities.Connection;
import org.model.storage.ConnectionStorage;
>>>>>>> 0f00acbc8245bc82bab1fbbd6837865ccdef38e5

/**
 * Controller class for managing the network system.
 */
public class Controller {

    private NodeStorage nodeStorage;
    private ConnectionStorage connectionStorage;

    public Controller(NodeStorage nodeStorage, ConnectionStorage connectionStorage) {
        this.nodeStorage = nodeStorage;
        this.connectionStorage = connectionStorage;
    }

    public void update() {
        // Update nodes and connections
    }

    public void addNode(Node node) {
        nodeStorage.addItem(node);
    }

    public void removeNode(NodeID nodeId) {
        nodeStorage.removeItem(nodeId);
    }

    public void addConnection(Connection connection) {
        connectionStorage.addItem(connection);
    }

    public void removeConnection(ConnectionID connectionId) {
        connectionStorage.removeItem(connectionId);
    }

    public String getViewData() {
        // Returns the data to be viewed in the GUI
        return null;
    }
}
