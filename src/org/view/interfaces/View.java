package org.view.interfaces;

import java.util.List;

import org.model.entity.Node;
import org.controller.datacollection.MeshEvent;
import org.model.entity.Connection;

public interface View {
    public void showNodes(List<Node> nodes);
    public void showConnections(List<Connection> connections);
    public void showEvent(MeshEvent event);
}
