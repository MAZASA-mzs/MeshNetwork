package org.controller;

import org.model.ids.ConnectionID;
import org.model.ids.MessageID;
import org.model.ids.NodeID;
import org.model.ids.PacketID;

public class DataCollector {
    static void collect() {}

    public static void messageRecieved(NodeID id, MessageID messageID) {
        System.out.println("messageRecieved " + id.toString() + " " + messageID.toString());
    }

    public static void packetLost(ConnectionID connectionID, PacketID id) {
        System.out.println("packetLost " + connectionID.toString() + " " + id.toString());
    }

    public static void packetDied(PacketID id) {
        System.out.println("packetDied " + id.toString());
    }

    public static void endOfIteration(int globalTimer) {
        System.out.println("endOfIteration " + ((Integer)globalTimer).toString());
    }

    public static void nodePosition(NodeID id, double x, double y) {
        System.out.println("nodePosition " + id.toString() + " " + ((Double)x).toString() + " " + ((Double)y).toString());
    }

    public static void packetesInConnection(ConnectionID id, PacketID id2, int sendingProgress) {
        System.out.println("packetesInConnection " + id.toString() + " " + id2.toString() + " " + ((Integer)sendingProgress).toString());
    }
}
