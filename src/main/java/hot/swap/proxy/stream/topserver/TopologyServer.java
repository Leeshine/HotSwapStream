package hot.swap.proxy.stream.topserver;

import hot.swap.proxy.base.SComponent;
import hot.swap.proxy.base.dotparser.TopologyGraph;
import hot.swap.proxy.cluster.HuskaZkCluster;
import hot.swap.proxy.stream.DotTransferTop;
import hot.swap.proxy.stream.Topology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by leeshine on 4/6/17.
 */

public class TopologyServer implements Runnable{
    private static Logger LOG = LoggerFactory.getLogger(TopologyServer.class);

    private Map conf;
    private ServerData serverData;
    private HuskaZkCluster huskaZkCluster;
    private Object plugin_lock;
    private Object topology_lock;

    public TopologyServer(ServerData serverData) throws Exception{
        this.plugin_lock = new Object();
        this.topology_lock = new Object();

        this.serverData = serverData;
        this.conf = serverData.getConf();

        initZK();
    }

    private void initZK() throws Exception{
        huskaZkCluster = new HuskaZkCluster(conf);
        serverData.setHuskaZkCluster(huskaZkCluster);
    }

    public void run() {
    }

    //monitor,parser,adjust
    public void submitTopologyFile(String topology_file) throws Exception{
        TopologyGraph graph = DotTransferTop.parserDotToGraph(topology_file);
        Topology topology = new Topology(graph);

        if(validateTopology(topology) == true){
            handleValidTopology(topology);
        }else{
            throw new IllegalArgumentException(topology.getTopologyName() + " : invalid topology");
        }
    }

    public boolean validateTopology(Topology topology) throws Exception{
        List<String> nodeList = topology.getComponentNames();
        for(String node : nodeList){
            if(huskaZkCluster.SComponentExist(node) == false){
                return false;
            }
        }

        return true;
    }

    public void handleValidTopology(Topology topology){
        String name = topology.getTopologyName();
    }

    public void submitNewTopology(Topology topology) throws Exception{
        huskaZkCluster.addTopology(topology);
    }

    public void updateCurrentTopology(Topology topology)throws Exception{
        String topology_name = topology.getTopologyName();

        Topology pre_topology = huskaZkCluster.getTopology(topology_name);
    }

    public void stopCurrentTopology(String topology_name) throws Exception{
    }

    public void installPlugin(String class_name, SComponent component) throws Exception{
        synchronized (plugin_lock) {
            if (huskaZkCluster.SComponentExist(class_name) == true) {
                LOG.error("plugin[" + class_name + "] : exist !");
                return;
            } else {
                huskaZkCluster.addSComponent(class_name, component);
            }
        }
    }

    public void updatePlugin(String class_name, SComponent component) throws Exception{
        synchronized (plugin_lock) {
            if (huskaZkCluster.SComponentExist(class_name) == true) {
                LOG.info("update plugin[" + class_name + "] !");
            } else {
                LOG.info("plugin[" + class_name + "] doesn't exist, will install !");
                huskaZkCluster.addSComponent(class_name, component);
            }
        }
    }

    public void uninstallPlugin(String class_name, SComponent component){
    }
}
