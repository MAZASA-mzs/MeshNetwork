package org.view.implementations;

import java.util.List;

import org.controller.datacollection.MeshEvent;
import org.model.entity.Connection;
import org.model.entity.Node;
import org.view.interfaces.View;

public class FullStdOutView implements View{
    public FullStdOutView() {}

    @Override
    public void showNodes(List<Node> nodes) {
        System.out.println("NodesListing");
        for (Node node : nodes) {
            System.out.println("NodeState");
            System.out.println(node.getID());
            System.out.println(Double.toString(node.getX()) + " " + Double.toString(node.getY()));
            System.out.println(node.getProtocolState());
            System.out.println(node.getNetworkMgrState());
            System.out.println(node.getBehaviorState());
        }
    }

    @Override
    public void showConnections(List<Connection> connections) {
        System.out.println("ConnectionListing");
        for (Connection connection : connections) {
            System.out.println("ConnectionState");
            System.out.println(connection.getID());
            System.out.println(connection.isP2P());
            System.out.println(connection.getConnectedNodesID());
            System.out.println("PacketsState");
            System.out.println(connection.getPacketsState());
        }
    }

    @Override
    public void showEvent(MeshEvent event) {
        System.out.println(event.getEventType() + "\t" + event.getEventData());
    }
}
