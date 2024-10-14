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
