package hot.swap.proxy.base.dotparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leeshine on 3/20/17.
 */

public class Graph extends ArgumentBean {
    //private List<Node> nodes;
    private List<Edge> edges;
    private String graph_name;

    private Map<String,Node> nodeMap;

    public Graph(){
        edges = new ArrayList<Edge>();
        nodeMap = new HashMap<String, Node>();
    }

    public Graph(String name){
        edges = new ArrayList<Edge>();
        nodeMap = new HashMap<String, Node>();
        graph_name = name;
    }

    public void setGraph_name(String name){
        this.graph_name = name;
    }

    public Node getAndaddNode(String node_name){
        if(!nodeMap.containsKey(node_name))
            addNode(node_name);
        return nodeMap.get(node_name);
    }

    public Node getNode(String node_name){
        return nodeMap.get(node_name);
    }

    public void addEdge(Edge edge){
        edges.add(edge);
    }

    private void addNode(String node_name){
        Node node = new Node(node_name);
        nodeMap.put(node_name,node);
    }
}
