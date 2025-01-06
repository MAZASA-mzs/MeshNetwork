package org.view.implementations;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.controller.datacollection.MeshEvent;
import org.model.entity.Connection;
import org.model.entity.Node;
import org.model.ids.NodeID;
import org.view.interfaces.View;

public class FullStdOutView implements View {
    public FullStdOutView() {}

    @Override
    public void showNodes(List<Node> nodes) {
        System.out.println("NodesListing");
        for (Node node : nodes) {
            System.out.println("NodeState");
            System.out.println(node.getID());
            System.out.println(Double.toString(node.getX()) + " " + Double.toString(node.getY()));
            System.out.println(node.getProtocolType() + " " + node.getProtocolState());
            System.out.println(node.getNetworkMgrType() + " " + node.getNetworkMgrState());
            System.out.println(node.getBehaviorType() + " " + node.getBehaviorState());
        }
    }

    @Override
    public void showConnections(List<Connection> connections) {
        System.out.println("ConnectionListing");
        for (Connection connection : connections) {
            System.out.println("ConnectionState");
            System.out.println(connection.getID());
            System.out.println(connection.isP2P());

            StringJoiner joiner = new StringJoiner(" ");
            for (NodeID nodeID : connection.getConnectedNodesID()) {
                joiner.add(nodeID.toString());
            }
            System.out.println(joiner.toString());

            System.out.println("PacketsState");
            System.out.println(connection.getPacketsState());
        }
    }

    @Override
    public void showEvent(MeshEvent event) {
        System.out.println(event.getEventType() + "\t" + event.getEventData());
    }
}
