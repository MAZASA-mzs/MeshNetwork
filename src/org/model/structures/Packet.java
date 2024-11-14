package org.model.structures;


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
