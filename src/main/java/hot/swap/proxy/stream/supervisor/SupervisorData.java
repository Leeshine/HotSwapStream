package hot.swap.proxy.stream.supervisor;

import hot.swap.proxy.cluster.HuskaZkCluster;
import hot.swap.proxy.stream.worker.Worker;
import hot.swap.proxy.utils.RotatingMap;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by leeshine on 4/11/17.
 */
public class SupervisorData {
    private static Logger LOG = LoggerFactory.getLogger(SupervisorData.class);

    private Map<Object,Object> conf;
    private HuskaZkCluster huskaZkCluster;
    private RotatingMap<String,Collection<Worker>> workerRotatingMap;

    public SupervisorData(Map conf){
        this.conf = conf;

        workerRotatingMap = new RotatingMap<String, Collection<Worker>>(null);
    }

    public Map<Object,Object> getConf(){
        return conf;
    }

    private HuskaZkCluster getHuskaZkCluster(){
        return huskaZkCluster;
    }

    private RotatingMap<String,Collection<Worker>> getWorkerRotatingMap(){
        return workerRotatingMap;
    }

    public void addWorker(String topology_name, Worker worker){
        if(workerRotatingMap.containsKey(topology_name) == false){
            workerRotatingMap.put(topology_name,new ArrayList<Worker>());
        }
        Collection<Worker> workerCollection = workerRotatingMap.get(topology_name);
        workerCollection.add(worker);

        workerRotatingMap.put(topology_name,workerCollection);
    }

    public Collection<Worker> getWorkerByTopology(String topology_name){
        if(workerRotatingMap.containsKey(topology_name) == false)
            return null;
        return workerRotatingMap.get(topology_name);
    }

    public Collection<Worker> getAllWorkers(){
        Collection<Worker> workers = new ArrayList<Worker>();

        Set<String> keySet = workerRotatingMap.keySet();
        for(String name : keySet){
            workers.addAll(workerRotatingMap.get(name));
        }

        return workers;
    }

    public void setHuskaZkCluster(HuskaZkCluster huskaZkCluster){
        this.huskaZkCluster = huskaZkCluster;
    }
}
