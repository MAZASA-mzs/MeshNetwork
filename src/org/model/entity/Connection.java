package org.model.entity;

import java.util.List;
import java.util.Set;

import org.model.ids.ConnectionID;
import org.model.ids.NodeID;
import org.model.structures.Packet;

public interface Connection {

    ConnectionID getID();

    boolean isP2P();

    void removeNodeID(NodeID nodeID);

    void addNodeID(NodeID nodeID);

    Set<NodeID> getConnectedNodesID();

    void tick();

    void sendPackets(List<Packet> packets);

    String getPacketsState();

}