package hot.swap.proxy.utils;

import java.util.Properties;

/**
 * Created by leeshine on 4/5/17.
 */
public class Constant {
    private static final String PROP_FILE = "conf.properties";
    public static final String zk_tmpDir;
    public static final String nm_tmpDir;
    public static final int zk_session;
    public static final int zk_connection;
    public static final String local_tmpdir;

    static{
        Properties prop = Utils.getProperties(PROP_FILE);
        local_tmpdir = prop.getProperty("local.tmpdir");
        zk_tmpDir = prop.getProperty("zookeeper.tmpdir");
        nm_tmpDir = prop.getProperty("nimbus.tmpdir");
        zk_session = Integer.parseInt(prop.getProperty("zookeeper.session.timeout","5000"));
        zk_connection = Integer.parseInt(prop.getProperty("zookeeper.connection.timeout","3000"));
    }

    public static final String STORM_LOCAL_DIR="storm.local.dir";
    public static final String STORM_CLUSTER_MODE = "storm.cluster.mode";
    public static final String LOCAL_MODE = "local";
    public static final String DISTRIBUTED_MODE = "distributed";

}
