package hot.swap.proxy;

import java.util.HashMap;

/**
 * Created by leeshine on 4/6/17.
 */
public class Config extends HashMap<String,Object>{
    public static final String STORM_LOCAL_DIR="storm.local.dir";
    public static final String STORM_CLUSTER_MODE = "storm.cluster.mode";
    public static final String LOCAL_MODE = "local";
    public static final String DISTRIBUTED_MODE = "distributed";
    public static final String STORM_ZOOKEEPER_SERVERS = "storm.zookeeper.server";
    public static final String STORM_ZOOKEEPER_PORT = "storm.zookeeper.port";
    public static final String STORM_ZOOKEEPER_ROOT = "storm.zookeeper.root";
    public static final String WORKER_JAR_LOCATION = "worker.jar.localtion";
    public static final String WORKER_TOPOLOGY_NAME = "worker.topology.name";

}
