import java.util.List;
import java.util.LinkedList;

import org.model.entity.Node;
import org.model.implementation.simple.SimpleProtocol;
import org.model.structures.Message;

public class App {
    public static void main(String[] args) throws Exception {
        Node node = new Node(0, 0, null, null, null);
        SimpleProtocol protocol = new SimpleProtocol(node);
        List<Message> msgs = new LinkedList<Message>();
        for (int i = 0; i < 100; i++) {
            msgs.add(new Message(node.getID(), i));
        }
        protocol.sendMessages(msgs);
    }
}
