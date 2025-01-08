package org.model.implementations.base.ids;

import java.util.UUID;

import org.model.ids.NodeID;

public class BroadcastAddress extends NodeID {
    // Static instance variable
    private static volatile BroadcastAddress instance;

    // Private constructor
    private BroadcastAddress() {
        super(UUID.fromString("ffffffff-ffff-ffff-ffff-ffffffffffff"));
    }

    // Public method to get the instance
    public static BroadcastAddress getInstance() {
        if (instance == null) {
            synchronized (BroadcastAddress.class) {
                if (instance == null) {
                    instance = new BroadcastAddress();
                }
            }
        }
        return instance;
    }

    // Static method for equality check
    public static boolean equals(NodeID id) {
        return id.equals(BroadcastAddress.getInstance());
    }
}
