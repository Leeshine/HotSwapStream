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
    private Map<String,SComponent> componentMap = new HashMap<String, SComponent>();
    private Map<String,List<String>> inputList = new HashMap<String, List<String>>();

    public ComponentDeclarer setModule(String id, SComponent component){
        checkComponentId(id);
        componentMap.put(id,component);

        return  new ComponentDeclarer(id);
    }

    public void checkComponentId(String id){
        if(componentMap.containsKey(id))
            throw new IllegalArgumentException("component has been already declared for id: " + id);
    }

    class ComponentDeclarer{
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

}
