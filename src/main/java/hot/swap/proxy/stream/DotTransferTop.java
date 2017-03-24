package hot.swap.proxy.stream;

import hot.swap.proxy.base.SComponent;
import hot.swap.proxy.base.dotparser.Edge;
import hot.swap.proxy.base.dotparser.GraphManager;
import hot.swap.proxy.base.dotparser.Node;
import hot.swap.proxy.base.dotparser.TopologyGraph;
import hot.swap.proxy.smodule.SwapModule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by leeshine on 3/23/17.
 */

public class DotTransferTop {
    public static StreamBuilder parserDotToBuilder(String dotFile){
        TopologyGraph graph = parserDotToGraph(dotFile);
        if(graph == null){
            return null;
        }

        StreamBuilder builder = null;
        try {
            builder = parserGraphToBuilder(graph);
        }catch (Exception e){
            e.printStackTrace();
        }
        return builder;
    }

    public static TopologyGraph parserDotToGraph(String dotFile){
        try {
            GraphManager manager = new GraphManager(dotFile);
            return manager.parserGraph();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static StreamBuilder parserGraphToBuilder(TopologyGraph graph) throws Exception{
        StreamBuilder builder = new StreamBuilder(graph.getGraph_name());

        Collection<Node> nodes = graph.getNode();
        Collection<Edge> edges = graph.getEdges();

        for(Node node : nodes){
            String node_name = node.getNode_name();
            String class_name = node.getClass_name();
            SComponent component = (SComponent)Class.forName(class_name).newInstance();
            component.setId(node_name);
            boolean swapable = (component instanceof SwapModule);
            component.setSwapable(swapable);
            builder.setModule(component);
        }

        for(Edge edge : edges){
            String head = edge.getHead();
            String tail = edge.getTail();
            builder.addGrouping(head,tail); // ??
        }

        return builder;
    }
}
