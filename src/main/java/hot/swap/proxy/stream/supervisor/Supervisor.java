package hot.swap.proxy.stream.supervisor;

import hot.swap.proxy.base.Shutdown;
import hot.swap.proxy.cluster.Cluster;
import hot.swap.proxy.cluster.HuskaZkCluster;
import hot.swap.proxy.stream.Topology;
import hot.swap.proxy.stream.worker.Worker;
import hot.swap.proxy.stream.worker.WorkerData;
import hot.swap.proxy.utils.Utils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

import java.io.File;
import java.util.Collection;
import java.util.Map;

/**
 * Created by leeshine on 4/6/17.
 */

public class Supervisor implements Runnable,Shutdown{
    private SupervisorData supervisorData;
    private Map conf;

    private static final String Topology_Path = Cluster.TOPOLOGYS_SUBTREE;

    private PathChildrenCache childrenCache;
    private HuskaZkCluster huskaZkCluster;

    private Thread thread;

    public Supervisor(Map conf,SupervisorData supervisorData) throws Exception{
        this.conf = conf;
        this.supervisorData = supervisorData;

        init();

        thread = new Thread(this);
        thread.start();
    }

    public void init() throws Exception{
        initZk();

        childrenCache = new PathChildrenCache(huskaZkCluster.getClient(),Topology_Path,true);
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        childrenCache.getListenable().addListener(new SupervisorListener());
    }

    class SupervisorListener implements PathChildrenCacheListener{
        public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
            PathChildrenCacheEvent.Type eventType =  pathChildrenCacheEvent.getType();
            ChildData childData =  pathChildrenCacheEvent.getData();
            String path = childData.getPath();
            byte[] data = childData.getData();

            if(eventType.equals(PathChildrenCacheEvent.Type.CHILD_ADDED)){
                startNewTopology(data);
            }else if(eventType.equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)){
                updateCurrentTopology(data);
            }else{
                String names[] = path.split(File.separator);
                String topology_name = names[names.length-1];
                stopCurrentTopology(topology_name);
            }
        }
    }

    public void initZk() throws Exception{
        huskaZkCluster = new HuskaZkCluster(conf);
        supervisorData.setHuskaZkCluster(huskaZkCluster);
    }

    public void run() {
    }

    public void shutdown() {
        Collection<Worker> workers = supervisorData.getAllWorkers();
        for(Worker worker : workers)
            worker.shutdown();
    }

    public void startNewTopology(byte[] topology_data) throws Exception{
        Topology topology = (Topology)Utils.deserialize(topology_data);
        Worker worker = mkWorker(topology);
        supervisorData.addWorker(topology.getTopologyName(),worker);
    }

    public void updateCurrentTopology(byte[] topology_data){
        Topology topology = (Topology)Utils.deserialize(topology_data);
        String name = topology.getTopologyName();
        Collection<Worker> workerCollection = supervisorData.getWorkerByTopology(name);

        for(Worker worker : workerCollection){
            worker.updateCurrentTopology(topology);
        }
    }

    public void stopCurrentTopology(String topology_name){
        Collection<Worker> workerCollection = supervisorData.getWorkerByTopology(topology_name);
        for(Worker worker : workerCollection){
            worker.stopCurrentTopology();
        }
    }

    public Worker mkWorker(Topology topology) throws Exception{
        WorkerData workerData = new WorkerData(conf);
        Worker worker = new Worker(workerData);
        worker.startNewTopology(topology);

        return worker;
    }
}
