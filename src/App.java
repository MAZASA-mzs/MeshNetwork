import org.config.NodeFactory;
import org.controller.datacollection.DataCollector;
import org.controller.datacollection.MeshEvent;
import org.controller.datacollection.MeshEventListener;
import org.model.containers.ConnectionStorage;
import org.model.containers.NodeStorage;
import org.model.implementations.base.meshevents.EndOfIteration;
import org.model.implementations.base.meshevents.StartOfIteration;
import org.view.implementations.FullStdOutView;

public class App {
    private static final int deadTimer = 200;
    public static void main(String[] args) throws Exception {
        long startTime = System.nanoTime();
        DataCollector.addListener(new MeshEventListener(new FullStdOutView()));
        NodeFactory.initNodes();

        int globalTimer = 0;
        while (true) {
            DataCollector.collect((MeshEvent)(new StartOfIteration(globalTimer)));
            DataCollector.collectMeshState(NodeStorage.getNodesList(), ConnectionStorage.getConnectionsList());
            NodeStorage.update();
            ConnectionStorage.update();
            NodeFactory.update();
            if (globalTimer > App.deadTimer)
                break;
            DataCollector.collect((MeshEvent)(new EndOfIteration(globalTimer)));
            globalTimer += 1;
        }
        long elapsedTime = System.nanoTime() - startTime;
        double seconds = (double)elapsedTime / 1_000_000_000.0;
        System.err.printf("Execution time: %.6f seconds%n", seconds);
    }
}
