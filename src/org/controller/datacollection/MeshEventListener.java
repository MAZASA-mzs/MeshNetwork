package org.controller.datacollection;

import java.util.List;

import org.model.entity.Connection;
import org.model.entity.Node;
import org.view.interfaces.View;

public class MeshEventListener {
    private View view;

    public MeshEventListener(View view) {
        this.view = view;
    }

    public void getState(List<Node> nodes, List<Connection> connections) {
        view.showNodes(nodes);
        view.showConnections(connections);
    }

    public void getEvent(MeshEvent event) {
        view.showEvent(event);
    }
}
