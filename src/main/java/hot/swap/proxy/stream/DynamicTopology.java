package hot.swap.proxy.stream;

import java.io.Serializable;
import java.util.*;

/**
 * Created by leeshine on 4/13/17.
 */

public class DynamicTopology implements Serializable{
    private String topology_name;
    private boolean modify;
    private Set<String> newComponents;
    private Set<String> deleteComponents;
    private Map<String,List<String>> newInputList;
    private Map<String,List<String>> deleteInputList;
    //to do 单独处理需变化的订阅关系 or 全部重置订阅关系
    // 目前采用单独处理的方法

    public DynamicTopology(){
        modify = false;
        newComponents = new HashSet<String>();
        deleteComponents = new HashSet<String>();
        newInputList = new HashMap<String, List<String>>();
        deleteInputList = new HashMap<String, List<String>>();
    }

    public String getTopology_name(){
        return topology_name;
    }

    public void initWithTopologies(Topology pre_topology, Topology cur_topology){
        topology_name = pre_topology.getTopologyName();

        Map<String,Integer> indexMap1 = pre_topology.getComponentIndex();
        Map<String,Integer> indexMap2 = cur_topology.getComponentIndex();

        Set<String> componentSet1 = indexMap1.keySet();
        Set<String> componentSet2 = indexMap2.keySet();

        for(String component_name : componentSet2){
            if(componentSet1.contains(component_name)){
                componentSet1.remove(component_name);

                Set<String> inputSet1 = new HashSet(pre_topology.getNodeInputList(component_name));
                Set<String> inputSet2 = new HashSet<String>(cur_topology.getNodeInputList(component_name));

                List<String> newList = new ArrayList<String>();
                List<String> deleteList = new ArrayList<String>();

                for(String name : inputSet2){
                    if(inputSet1.contains(name)){
                        inputSet1.remove(name);
                    }else{
                        newList.add(name);
                    }
                }
                deleteList.addAll(inputSet1);

                if(newList.size() > 0) {
                    newInputList.put(component_name, newList);
                    modify = true;
                }
                if(deleteList.size() > 0) {
                    deleteInputList.put(component_name, deleteList);
                    modify = true;
                }
            }else{
                newComponents.add(component_name);
                newInputList.put(component_name,cur_topology.getNodeInputList(component_name));
                modify = true;
            }
        }
        deleteComponents.addAll(componentSet1);
        if(componentSet1.size() > 0)
            modify = true;

        // ?? 删除节点时统一删除订阅关系还是单独删除订阅关系
        //检测订阅关系的变化

    }

    public boolean needModify(){
        return modify;
    }

}
