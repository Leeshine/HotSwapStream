package hot.swap.proxy.base.dotparser;


/**
 * Created by leeshine on 3/20/17.
 */

public class Node extends ArgumentBean{
    private String node_name;
    private String class_name;

    public Node(String node_name, String class_name){
        this.node_name = node_name;
        this.class_name = class_name;
    }

    public String getNode_name(){
        return node_name;
    }

    public String getClass_name(){
        return class_name;
    }
}
