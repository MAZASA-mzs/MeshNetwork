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

    private static Random random = new Random();
    private static int startNodes = 10;
    private static int newNodeTimer = 0;
    private static int minNewNodeTimer = Integer.MAX_VALUE-1;
    private static int maxNewNodeTimer = Integer.MAX_VALUE;
    
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
        return Integer.MAX_VALUE;
    }

    public static void update() {
        if (newNodeTimer <= 0) {
            NodeID nodeID = new NodeID();
            NetworkProtocol protocol = new SimpleProtocol(nodeID);
            UserTrafficBehavior behavior = new SimpleTrafficBehavior(nodeID);
            UserNetworkMgr networkMgr = new SimpleNetworkMgr(nodeID);
            Node newNode = new Node(nodeID, random.nextDouble(-100, 100), random.nextDouble(-100, 100),
                                    protocol, behavior, networkMgr);
            NodeStorage.add(newNode);
            newNodeTimer = random.nextInt(minNewNodeTimer, maxNewNodeTimer);
            return;
        }
        newNodeTimer -= 1;
    }    
}
