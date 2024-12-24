import org.config.NodeFactory;
import org.controller.DataCollector;
import org.model.containers.ConnectionStorage;
import org.model.containers.NodeStorage;
public class App {
    private static final int deadTimer = 200;
    public static void main(String[] args) throws Exception {
        long startTime = System.nanoTime();

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
            if (globalTimer > App.deadTimer)
                break;
        }
        long elapsedTime = System.nanoTime() - startTime;
        double seconds = (double)elapsedTime / 1_000_000_000.0;
        System.err.printf("Execution time: %.6f seconds%n", seconds);
    }
}
