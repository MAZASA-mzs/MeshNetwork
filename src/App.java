import org.config.NodeFactory;
import org.controller.DataCollector;
import org.model.containers.ConnectionStorage;
import org.model.containers.NodeStorage;
public class App {
    public static void main(String[] args) throws Exception {
        int globalTimer = 0;
        int nextNodeFactoryTimer = NodeFactory.initNodes();
        while (true) {
            NodeStorage.update();
            ConnectionStorage.update();
            if (nextNodeFactoryTimer <= 0)
                nextNodeFactoryTimer = NodeFactory.update();
            else
                nextNodeFactoryTimer -=1;
            globalTimer += 1;
            DataCollector.endOfIteration(globalTimer);
            if (globalTimer > 50)
                break;
        }
    }
}
