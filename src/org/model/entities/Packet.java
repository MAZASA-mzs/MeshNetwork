package org.model.entities;

<<<<<<< HEAD
import org.model.ids.PacketID;
=======
import org.model.ids.packet.PacketID;
>>>>>>> 0f00acbc8245bc82bab1fbbd6837865ccdef38e5

/**
 * Represents a data packet being transmitted between nodes.
 */
public class Packet {

    private final PacketID id;
    private String destination;
    private int size;

    public Packet() {
        this.id = new PacketID(); // Generates a new PacketID
    }

    public PacketID getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    // Getters and Setters for destination, size, etc.
}
