package hot.swap.proxy.cluster;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by leeshine on 4/11/17.
 */
public class Cluster {
    private static Logger LOG = LoggerFactory.getLogger(Cluster.class);

    public static final String ZK_SEPRATOR = "/";
    public static final String HUSKA_NAMESPACE = "huska";

    public static final String PLUGINS_ROOT = "plugin";
    public static final String TOPOLOGYS_ROOT = "topology";
    public static final String TASK_ROOT = "task";
    public static final String DYNAMIC_ROOT = "dynamic";

    public static final String PLUGINS_SUBTREE = ZK_SEPRATOR + PLUGINS_ROOT;
    public static final String TOPOLOGYS_SUBTREE = ZK_SEPRATOR + TOPOLOGYS_ROOT;
    public static final String TASK_SUBTREE = ZK_SEPRATOR + TASK_ROOT;
    public static final String DYNAMIC_SUBTREE = ZK_SEPRATOR + DYNAMIC_ROOT;

    public static String plugin_path(String id){
        return PLUGINS_SUBTREE + ZK_SEPRATOR + id;
    }

    public static String topology_path(String id){
        return TOPOLOGYS_SUBTREE + ZK_SEPRATOR + id;
    }

    public static String dynamic_path(String id){
        return DYNAMIC_SUBTREE + ZK_SEPRATOR + id;
    }

    public static String task_path(String id){
        return TASK_SUBTREE + ZK_SEPRATOR + id;
    }

    public static HuskaZkCluster mk_huska_cluster_state(Map cluster_state_spec) throws Exception{
        return new HuskaZkCluster(cluster_state_spec);
    }
}
