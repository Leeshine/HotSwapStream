package hot.swap.proxy.stream;

import hot.swap.proxy.base.SComponent;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by leeshine on 4/6/17.
 */

public class Topology implements Serializable{
    private String topologyName;

    private Map<String,SComponent> componentMap;
    private Map<String,List<String>> inputList;
    private Map<String,StreamBuilder.ComponentDeclarer> declarerMap;

    public Topology(String name){
        this.topologyName = name;
    }
}
