package hot.swap.proxy.stream;

import hot.swap.proxy.base.SComponent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leeshine on 3/7/17.
 */

public class StreamBuilder {
    private String topologyName;
    private Map<String,SComponent> componentMap;
    private Map<String,List<String>> inputList;
    private Map<String,ComponentDeclarer> declarerMap;

    public StreamBuilder(String name){
        topologyName = name;
        componentMap = new HashMap<String, SComponent>();
        inputList = new HashMap<String, List<String>>();
        declarerMap = new HashMap<String, ComponentDeclarer>();
    }

    public ComponentDeclarer setModule(SComponent component){
        String id = component.getId();
        checkComponentId(id,component);
        componentMap.put(id,component);

        ComponentDeclarer declarer = new ComponentDeclarer(id);
        declarerMap.put(id,declarer);
        return declarer;
    }

    public void addGrouping(String srcComponentId, String dstComponentId){
        declarerMap.get(dstComponentId).grouping(srcComponentId);
    }


    public void checkComponentId(String id,SComponent component){
        if(componentMap.containsKey(id))
            throw new IllegalArgumentException("component has been already declared for id: " + id);
    }

    public class ComponentDeclarer{
        private String _id;

        public ComponentDeclarer(String id){
            this._id = id;
        }

        public ComponentDeclarer grouping(String componentId){
            if(!inputList.containsKey(_id)){
                inputList.put(_id,new ArrayList<String>());
            }

            inputList.get(_id).add(componentId);

            return this;
        }
    }

    public Map<String,SComponent> getComponentMap(){
        return componentMap;
    }

    public Map<String,List<String>> getInputList(){
        return inputList;
    }

    public void setTopologyName(String name){
        topologyName = name;
    }

    public Topology createTopology(){
        Topology topology = new Topology(topologyName);

        return topology;
    }


    public String getTopologyName(){
        return topologyName;
    }

}
