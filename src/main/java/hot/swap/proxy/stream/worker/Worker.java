package hot.swap.proxy.stream.worker;

import hot.swap.proxy.Config;
import hot.swap.proxy.base.SComponent;
import hot.swap.proxy.base.Shutdown;
import hot.swap.proxy.cluster.HuskaZkCluster;
import hot.swap.proxy.message.IConnection;
import hot.swap.proxy.message.LocalConnection;
import hot.swap.proxy.message.MessageCenter;
import hot.swap.proxy.message.QueueManager;
import hot.swap.proxy.smanager.SwapManager;
import hot.swap.proxy.smodule.SwapModule;
import hot.swap.proxy.sproxy.SwapProxy;
import hot.swap.proxy.stream.Topology;
import hot.swap.proxy.utils.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leeshine on 4/11/17.
 */

public class Worker implements Runnable,Shutdown{
    private static Logger LOG = LoggerFactory.getLogger(Worker.class);

    private Map conf;
    private WorkerData workerData;
    private HuskaZkCluster huskaZkCluster;
    private String topologyName;

    private String workerName;
    private Thread thread;

    private Map<String,SComponent> componentMap;
    private SwapManager swapManager;

    public Worker(Map conf, WorkerData workerData){
        this.conf = conf;
        this.workerData = workerData;
        this.huskaZkCluster = workerData.getHuskaZkCluster();
        this.componentMap = new HashMap<String, SComponent>();

        topologyName = (String)conf.get(Config.WORKER_TOPOLOGY_NAME);
        Topology topology = getTopology();
        init(topology);

        thread = new Thread(this);
        workerName = "worker_"+RandomUtil.RandomString(3);
        thread.setName(workerName);
        thread.start();
    }

    public SwapManager getSwapManager(){
        return swapManager;
    }

    private void init(Topology topology){
        List<String> class_names = topology.getComponentNames();
        try {
            for (String class_name : class_names) {
                SComponent component = huskaZkCluster.getSComponentByClassName(class_name);
                componentMap.put(class_name,component);
            }
        }catch (Exception e){
            LOG.error(e.getMessage());
        }

        QueueManager queueManager = new QueueManager();
        MessageCenter messageCenter = new MessageCenter(queueManager);
        swapManager = new SwapManager(messageCenter);

        Map<String,List<String>> inputList = topology.getInputList();

        messageCenter.handleInputList(inputList);

        for(Map.Entry<String,SComponent> entry : componentMap.entrySet()){
            SComponent component = entry.getValue();
            component.init(queueManager,messageCenter);

            //to do order by start thread
            // name stategy
            if(component.checkSwapable()){//swapable
                String proxyName = component.getId(); //proxyname = modulename ??
                IConnection connection = new LocalConnection(proxyName,queueManager,messageCenter);
                SwapProxy swapProxy = new SwapProxy(proxyName,(SwapModule)component,connection);
                swapProxy.startRun();
                swapManager.addProxy(component.getId(),swapProxy);
            }else{
                component.init(queueManager,messageCenter);
                component.startRun();
            }
        }
    }


    public String getWorkerName(){
        return workerName;
    }

    public Topology getTopology(){
        Topology topology = null;
        try{
            topology = huskaZkCluster.getTopology(topologyName);
        }catch (Exception e){
            LOG.error(e.getMessage());
        }
        return topology;
    }

    public void run() {
    }

    public void shutdown() {
        huskaZkCluster.close();
    }
}
