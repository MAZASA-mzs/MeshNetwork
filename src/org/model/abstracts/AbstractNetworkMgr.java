package org.model.abstracts;

<<<<<<< HEAD
import org.model.ids.ConnectionID;
=======
import org.model.ids.connection.ConnectionID;
>>>>>>> 0f00acbc8245bc82bab1fbbd6837865ccdef38e5

/**
 * Abstract class managing network-level operations of a node.
 */
public abstract class AbstractNetworkMgr {

    /**
     * Update NetworkMgr state.
     */
    public abstract void tick();

    /**
     * Handles a connection request from a connection.
     *
     * @param connectionID The ID of new connection
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