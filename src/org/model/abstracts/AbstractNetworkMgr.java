package org.model.abstracts;

import org.model.ids.connection.ConnectionID;

/**
 * Abstract class managing network-level operations of a node.
 */
public abstract class AbstractNetworkMgr {

    /**
     * Simulates a single time unit in the mesh network simulation.
     */
    public abstract void tick();

    /**
     * Handles a connection request from another node.
     *
     * @param connectionID The ID of the connection being requested
     * @return true if the connection is accepted, false otherwise
     */
    public abstract boolean connectionRequest(ConnectionID connectionID);

    /**
     * Handles a disconnection event for a given connection.
     *
     * @param connectionID The ID of the disconnected connection
     */
    public abstract void connectionBreak(ConnectionID connectionID);
}