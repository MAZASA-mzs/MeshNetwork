package org.config;

import java.util.Random;

import org.model.abstracts.AbstractBehavior;
import org.model.abstracts.AbstractNetworkMgr;
import org.model.abstracts.AbstractProtocol;
import org.model.containers.NodeStorage;
import org.model.entity.Node;
import org.model.ids.NodeID;
import org.model.implementation.simple.SimpleBehavior;
import org.model.implementation.simple.SimpleNetworkMgr;
import org.model.implementation.simple.SimpleProtocol;

public class NodeFactory {

    private static int startNodes = 40;
    private static Random random = new Random();
    
    public static int initNodes() {
        for (int i = 0; i < NodeFactory.startNodes; i++) {
            NodeID nodeID = new NodeID();
            AbstractProtocol protocol = new SimpleProtocol(nodeID);
            AbstractBehavior behavior = new SimpleBehavior(nodeID);
            AbstractNetworkMgr networkMgr = new SimpleNetworkMgr(nodeID);
            Node newNode = new Node(nodeID, random.nextDouble(-100, 100), random.nextDouble(-100, 100),
                                    protocol, behavior, networkMgr);
            NodeStorage.add(newNode);
        }
        return Integer.MAX_VALUE;
    }

    public static int update() {
        return Integer.MAX_VALUE;
    }
    
}
