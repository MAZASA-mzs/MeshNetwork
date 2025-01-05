package org.model.interfaces;

import java.util.Set;

import org.model.ids.ConnectionID;;

/**
 * Abstract class managing network-level operations of a node.
 */
public interface UserNetworkMgr {

    /**
     * Update NetworkMgr state.
     */
    public abstract void tick();

    /**
     * return a List of active Node Connections 
     */
    public abstract Set<ConnectionID> getConnectionsSet();

    /**
     * Handles a connection request from a other Node.
     *
     * @param id The ID of new connection
     * @return true if the connection is accepted, false otherwise
     */
    public abstract boolean connectionRequest(ConnectionID id);

    /**
     * Handles a disconnection event for a given connection.
     *
     * @param id The ID of the disconnected connection
     */
    public abstract void connectionBreak(ConnectionID id);

    public abstract String getType();

    public abstract String getState();
}
