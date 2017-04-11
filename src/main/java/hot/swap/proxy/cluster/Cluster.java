package hot.swap.proxy.cluster;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by leeshine on 4/11/17.
 */
public class Cluster {
    private static Logger LOG = LoggerFactory.getLogger(Cluster.class);

    public static final String ZK_SEPRATOR = "/";
    public static final String HUSKA_NAMESPACE = "huska";

    public static final String PLUGINS_ROOT = "plugin";
    public static final String TOPOLOGYS_ROOT = "topology";

    public static String PLUGINS_SUBTREE = ZK_SEPRATOR + PLUGINS_ROOT;
    public static String TOPOLOGYS_SUBTREE = ZK_SEPRATOR + TOPOLOGYS_ROOT;

    public static String plugin_path(String id){
        return PLUGINS_SUBTREE + ZK_SEPRATOR + id;
    }

    public static String topology_path(String id){
        return TOPOLOGYS_SUBTREE + ZK_SEPRATOR + id;
    }

}
