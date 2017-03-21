package hot.swap.proxy.testutil;

import hot.swap.proxy.base.dotparser.Graph;
import hot.swap.proxy.base.dotparser.GraphManager;

/**
 * Created by leeshine on 3/21/17.
 */
public class TestGraphParser {
    public static void main(String[] args) throws Exception{
        String inputFile = "/home/leeshine/MasterPaper/HotSwapProxy" +
                "/src/main/java/hot/swap/proxy/testutil/testgraph.dot";
        GraphManager manager = new GraphManager(inputFile);
        Graph graph = manager.parserGraph();

        System.out.println(graph);
    }
}
