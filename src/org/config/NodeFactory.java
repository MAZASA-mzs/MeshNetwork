package org.config;

import java.util.Random;

import org.model.containers.NodeStorage;
import org.model.entity.Node;
import org.model.ids.NodeID;
import org.model.implementations.simple.SimpleTrafficBehavior;
import org.model.interfaces.UserNetworkMgr;
import org.model.interfaces.NetworkProtocol;
import org.model.interfaces.UserTrafficBehavior;
import org.model.implementations.simple.SimpleNetworkMgr;
import org.model.implementations.simple.SimpleProtocol;

public class NodeFactory {

    private static int startNodes = 30;
    private static int maxNewNodeTimer = 64;
    private static Random random = new Random();
    
    public static int initNodes() {
        int totalNodes = NodeFactory.startNodes;
        for (int i = 0; i < totalNodes; i++) {
            NodeID nodeID = new NodeID();
            NetworkProtocol protocol = new SimpleProtocol(nodeID);
            UserTrafficBehavior behavior = new SimpleTrafficBehavior(nodeID);
            UserNetworkMgr networkMgr = new SimpleNetworkMgr(nodeID);
            Node newNode = new Node(nodeID, random.nextDouble(-100, 100), random.nextDouble(-100, 100),
                                    protocol, behavior, networkMgr);
            NodeStorage.add(newNode);
        }    
        // return random.nextInt(maxNewNodeTimer);
        return Integer.MAX_VALUE;
    }

    public static int update() {
        NodeID nodeID = new NodeID();
        NetworkProtocol protocol = new SimpleProtocol(nodeID);
        UserTrafficBehavior behavior = new SimpleTrafficBehavior(nodeID);
        UserNetworkMgr networkMgr = new SimpleNetworkMgr(nodeID);
        Node newNode = new Node(nodeID, random.nextDouble(-100, 100), random.nextDouble(-100, 100),
                                protocol, behavior, networkMgr);
        NodeStorage.add(newNode);
        return random.nextInt(maxNewNodeTimer);
        // return Integer.MAX_VALUE;
    }    
}
