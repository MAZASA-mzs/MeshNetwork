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
}
