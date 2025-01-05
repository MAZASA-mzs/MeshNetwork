package org.model.implementations.base.meshevents;

import org.controller.datacollection.MeshEvent;

public class EndOfIteration implements MeshEvent{
    private final int iterationNumber;

    public EndOfIteration(int iterationNumber) {
        this.iterationNumber = iterationNumber;
    };

    public String getEventType() {
        return "EndOfIteration";
    }

    public String getEventData() {
        return Integer.toString(iterationNumber);
    }
}
