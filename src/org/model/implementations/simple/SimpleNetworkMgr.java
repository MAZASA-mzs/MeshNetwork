package org.model.implementations.simple;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.config.ConnectionFactory;
import org.model.containers.ConnectionStorage;
import org.model.containers.NodeStorage;
import org.model.entity.Connection;
import org.model.entity.Node;
import org.model.ids.ConnectionID;
import org.model.ids.NodeID;
import org.model.interfaces.UserNetworkMgr;


public class SimpleNetworkMgr implements UserNetworkMgr {
    private static Random random = new Random();
    private static int connectionAccept = 10;
    private static int minConnectionRequestTimer = 5;
    private static int maxConnectionRequestTimer = 10;
    private static int nextConnectionRequestTimer = 0;
    private static int maxConnectionRadius = 256;
    private Set<ConnectionID> connections;
    private final NodeID nodeID;

    public SimpleNetworkMgr(NodeID nodeID) {
        this.connections = new HashSet<ConnectionID>();
        this.nodeID = nodeID;
    }

    @Override
    public void tick() {
        if (nextConnectionRequestTimer <= 0) {
            for (NodeID nodeID : NodeStorage.getIDs()) {
                if (nodeID.equals(this.nodeID))
                    continue;
                boolean isNodeConnected = false;
                for (ConnectionID connectionID : this.connections) {
                    Connection connection = ConnectionStorage.get(connectionID);
                    if (connection.getConnectedNodesID().contains(nodeID)) {
                        isNodeConnected = true;
                        break;
                    }
                }
                if (isNodeConnected) continue;
                Node otherNode = NodeStorage.get(nodeID);
                Node thisNode  = NodeStorage.get(this.nodeID);

                if ((Math.pow(thisNode.getX()-otherNode.getX(),2) +
                     Math.pow(thisNode.getY()-otherNode.getY(),2)
                    ) > Math.pow((double)maxConnectionRadius,2))
                        continue;
                ConnectionID newConnectionID = ConnectionFactory.newPtPConnection();
                boolean isSuccessfulConnection = otherNode.connectionRequest(newConnectionID);
                if (!isSuccessfulConnection) {
                    ConnectionStorage.remove(newConnectionID);
                    continue;
                }
                ConnectionStorage.get(newConnectionID).addNodeID(this.nodeID);
                this.connections.add(newConnectionID);

            }
            nextConnectionRequestTimer = random.nextInt(minConnectionRequestTimer, maxConnectionRequestTimer);
            return;
        }
        nextConnectionRequestTimer -= 1;
    }

    @Override
    public Set<ConnectionID> getConnectionsSet() {
        return Collections.unmodifiableSet(this.connections);
    }

    @Override
    public boolean connectionRequest(ConnectionID id) {
        if (random.nextInt(1000) <= connectionAccept) {
            this.connections.add(id);
            ConnectionStorage.get(id).addNodeID(this.nodeID);
            return true;
        }
        return false;
    }

    @Override
    public void connectionBreak(ConnectionID id) {
        ConnectionStorage.get(id).removeNodeID(this.nodeID);
    }

    @Override
    public String getType() {
        return "SimpleNetworkMgr";
    }

    @Override
    public String getState() {
        return this.getConnectionsSet().toString() + " " + Integer.toString(nextConnectionRequestTimer);
    }

}
