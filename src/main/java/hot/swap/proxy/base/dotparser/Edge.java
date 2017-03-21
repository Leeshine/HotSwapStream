package hot.swap.proxy.base.dotparser;

/**
 * Created by leeshine on 3/20/17.
 */

public class Edge extends ArgumentBean{
    private String head_node;
    private String tail_node;

    public Edge(String nameA, String nameB){
        head_node = nameA;
        tail_node = nameB;
    }

    public String getHead(){
        return head_node;
    }

    public String getTail(){
        return tail_node;
    }
}
