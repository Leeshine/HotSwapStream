package hot.swap.proxy.base.dotparser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leeshine on 3/20/17.
 */

public class Graph {
    private List<Node> nodes;
    private List<Edge> edges;
    private String graph_name;

    public Graph(String name){
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
        graph_name = name;
    }

    public void addEdge(Edge edge){
        edges.add(edge);
    }

    public void addNode(Node node){
        nodes.add(node);
    }

}
