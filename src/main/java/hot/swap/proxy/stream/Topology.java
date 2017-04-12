package hot.swap.proxy.stream;

import hot.swap.proxy.base.dotparser.Edge;
import hot.swap.proxy.base.dotparser.Node;
import hot.swap.proxy.base.dotparser.TopologyGraph;

import java.io.Serializable;
import java.util.*;

/**
 * Created by leeshine on 4/6/17.
 */

public class Topology implements Serializable{
    private String topologyName;

    private List<String> componentNames;
    private int[][] componentMatrix;
    private Map<String,Integer> componentIndex;
    private int index = 0;

    public Topology(){
        componentNames = new ArrayList<String>();
        componentIndex = new HashMap<String, Integer>();
    }


    public void parserGraph(TopologyGraph graph){
        topologyName = graph.getGraph_name();
        Collection<Node> nodes = graph.getNode();
        for(Node node: nodes){ // class_name ?? node_name
            String class_name = node.getClass_name();
            componentNames.add(class_name);
            componentIndex.put(class_name,index++);
        }
        componentMatrix = new int[index][index];
        for(int i=0; i<index; ++i){
            for(int j=0; j<index; ++j){
                componentMatrix[i][j] = 0;
            }
        }

        Collection<Edge> edges = graph.getEdges();
        for(Edge edge : edges){
            String head_name = edge.getHead();
            String tail_name = edge.getTail();

            String head_class_name = graph.getNode(head_name).getClass_name();
            String tail_class_name = graph.getNode(tail_name).getClass_name();

            int head_index = componentIndex.get(head_class_name);
            int tail_index = componentIndex.get(tail_class_name);

            componentMatrix[head_index][tail_index] = 1;
        }
    }

    public Map<String,List<String>> getInputList(){
        Map<String,List<String>> inputList = new HashMap<String, List<String>>();
        for(String name : componentNames){
            List<String> list = new ArrayList<String>();
            int in = componentIndex.get(name);
            for(int j=0; j<index; ++j){
                if(componentMatrix[in][j] == 1){
                    list.add(componentNames.get(j));
                }
            }
            inputList.put(name,list);
        }

        return inputList;
    }

    public String getTopologyName() {
        return topologyName;
    }

    public int[][] getComponentMatrix() {
        return componentMatrix;
    }

    public List<String> getComponentNames() {
        return componentNames;
    }

    public Map<String, Integer> getComponentIndex() {
        return componentIndex;
    }
}
