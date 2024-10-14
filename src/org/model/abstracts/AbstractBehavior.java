package org.model.abstracts;

import java.util.List;
import org.model.entities.Message;

/**
 * Abstract class representing the behavior of a node in a mesh network.
 * This class defines the interface for all concrete implementations of node behaviors.
 */
public abstract class AbstractBehavior {
    
    /**
     * Simulates a single time unit in the mesh network simulation.
     */
    public abstract void tick();

    /**
     * Returns a List of new Messages that need to be processed by the node.
     * 
     * @return List<Message> A list of new Messages that require processing.
     */
    public abstract List<Message> getNewMessages();
}