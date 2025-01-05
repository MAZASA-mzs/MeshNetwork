package org.controller.datacollection;

import java.util.ArrayList;
import java.util.List;

import org.model.entity.Connection;
import org.model.entity.Node;

public class DataCollector {
    private static final List<MeshEventListener> listeners = new ArrayList<>();

    public static void addListener(MeshEventListener listener) {
        DataCollector.listeners.add(listener);
    }

    public static void collectMeshState(List<Node> nodes, List<Connection> connections) {
        for (MeshEventListener listener : listeners)
            listener.getState(nodes, connections);;
    }

    public static void collect(MeshEvent event) {
        for (MeshEventListener listener : listeners)
            listener.getEvent(event);
    }
}
