<<<<<<<< HEAD:src/org/model/ids/MessageID.java
package org.model.ids;
========
package org.model.ids.message;
>>>>>>>> 0f00acbc8245bc82bab1fbbd6837865ccdef38e5:src/org/model/ids/message/MessageID.java

import java.util.UUID;

import org.model.abstracts.AbstractID;

/**
 * Represents a unique identifier for a Message.
 */
public class MessageID extends AbstractID {

    public MessageID() {
        super();
    }

    public MessageID(UUID id) {
        super(id);
    }
}
