package org.model.implementations.base.meshevents;

import org.controller.datacollection.MeshEvent;

public class StartOfIteration implements MeshEvent {
    private final int iterationNumber;

    public StartOfIteration(int iterationNumber) {
        this.iterationNumber = iterationNumber;
    };

    public String getEventType() {
        return "StartOfIteration";
    }

    public String getEventData() {
        return Integer.toString(iterationNumber);
    }
}
