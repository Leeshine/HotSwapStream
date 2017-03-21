package hot.swap.proxy.base.dotparser;


/**
 * Created by leeshine on 3/20/17.
 */

public class Node extends ArgumentBean{
    private final static String CLASSNAME = "class";
    private String node_name;
    private String class_name;

    public Node(String node_name){
        this.node_name = node_name;
        class_name = null;
    }

    public String getNode_name(){
        return node_name;
    }

    public String getClass_name(){
        return class_name;
    }

    public void setNode_name(String name){
        this.node_name = name;
    }

    public void setClass_name(String name){
        this.class_name = name;
    }

    public void addValue(String name, String value){
        super.addValue(name,value);

        if(name.endsWith(CLASSNAME))
            class_name = value;
    }
}
