package hot.swap.proxy.base.dotparser;

import java.util.*;

/**
 * Created by leeshine on 3/20/17.
 */

public class TopologyGraph extends ArgumentBean {
    //private List<Node> nodes;
    private List<Edge> edges;
    private String graph_name;

    private Map<String,Node> nodeMap;

    public TopologyGraph(){
        edges = new ArrayList<Edge>();
        nodeMap = new HashMap<String, Node>();
    }

    public TopologyGraph(String name){
        edges = new ArrayList<Edge>();
        nodeMap = new HashMap<String, Node>();
        graph_name = name;
    }

    public void setGraph_name(String name){
        this.graph_name = name;
    }

    public String getGraph_name(){
        return  graph_name;
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

    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append(graph_name);
        sb.append(":\n");
        sb.append(nodeMap.toString());
        sb.append("\n");
        sb.append(edges.toString());
        sb.append("\n");
        return sb.toString();
    }

    public Collection<Node> getNode(){
        return nodeMap.values();
    }

    public List<Edge> getEdges(){
        return edges;
    }
}
