package hot.swap.proxy.stream.supervisor;

import hot.swap.proxy.cluster.HuskaZkCluster;
import hot.swap.proxy.stream.worker.Worker;
import hot.swap.proxy.utils.RotatingMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by leeshine on 4/11/17.
 */
public class SupervisorData {
    private static Logger LOG = LoggerFactory.getLogger(SupervisorData.class);

    private Map<Object,Object> conf;
    private RotatingMap<String,Worker> workerRotatingMap;
    private HuskaZkCluster huskaZkCluster;

    public SupervisorData(Map conf){
        this.conf = conf;

        workerRotatingMap = new RotatingMap<String, Worker>(null);
    }

    public Map<Object,Object> getConf(){
        return conf;
    }

    private HuskaZkCluster getHuskaZkCluster(){
        return huskaZkCluster;
    }

    private RotatingMap<String,Worker> getWorkerRotatingMap(){
        return workerRotatingMap;
    }

    public void addWorker(Worker worker){
        workerRotatingMap.put(worker.getWorkerName(),worker);
    }
}
