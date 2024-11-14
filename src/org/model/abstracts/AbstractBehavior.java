package org.model.abstracts;

import java.util.List;

import org.model.structures.Message;

/**
 * Abstract class representing types of user behaviour.
 */
public abstract class AbstractBehavior {
    
    /**
     * Update Behavior state.
     */
    public abstract void tick();

    /**
     * Returns a List of new Messages to pass to Node.protocol.sendMessages().
     * 
     * @return List<Message> A list of new Messages that require processing
     */
    public abstract List<Message> getNewMessages();
}