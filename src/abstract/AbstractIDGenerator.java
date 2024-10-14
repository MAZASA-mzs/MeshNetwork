package abstract;

import java.util.UUID;

/**
 * Abstract class for generating unique IDs.
 */
public abstract class AbstractIDGenerator {

    /**
     * Generates a new UUID-based ID.
     * @return a new UUID.
     */
    public UUID generate() {
        return UUID.randomUUID();
    }
}
