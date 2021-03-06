package hot.swap.proxy.stream.nimbus;

import hot.swap.proxy.cluster.Cluster;
import hot.swap.proxy.cluster.HuskaZkCluster;
import hot.swap.proxy.cluster.ZookeeperCluster;
import hot.swap.proxy.utils.ConfigUtil;
import hot.swap.proxy.utils.RotatingMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by leeshine on 4/6/17.
 */

public class NimbusData {
    private static final Logger LOG = LoggerFactory.getLogger(NimbusData.class);

    private Map<Object,Object> conf;
    private final boolean localMode;

    private RotatingMap<String,Object> pendingSubmitTopology;

    private HuskaZkCluster huskaZkCluster;

    public NimbusData(Map conf) throws Exception{
        this.conf = conf;

        localMode = ConfigUtil.local_mode(conf);

        pendingSubmitTopology = new RotatingMap<String, Object>(null);

        huskaZkCluster = Cluster.mk_huska_cluster_satte(conf);
    }

    public Map<Object,Object> getConf(){
        return conf;
    }

    public RotatingMap<String,Object> getPendingSubmitTopology(){
        return pendingSubmitTopology;
    }

    public boolean isLocalMode(){
        return localMode;
    }

    public HuskaZkCluster getHuskaZkCluster(){
        return huskaZkCluster;
    }
}
