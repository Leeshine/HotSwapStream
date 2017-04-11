package hot.swap.proxy.cluster;

import hot.swap.proxy.callback.ClusterStateCallback;
import hot.swap.proxy.zk.WatcherCallBack;
import hot.swap.proxy.zk.Zookeeper;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

/**
 * Created by leeshine on 4/10/17.
 */
public class DistributedClusterState implements ClusterState{
    private static Logger LOG = LoggerFactory.getLogger(DistributedClusterState.class);

    private Zookeeper zkobj = new Zookeeper();
    private CuratorFramework zk;
    private WatcherCallBack watcher;

    public void set_ephemeral_node(String path, byte[] data) throws Exception {

    }

    public void delete_node(String path) throws Exception {

    }

    public void set_data(String path, byte[] data) throws Exception {

    }

    public byte[] get_data(String path, boolean watch) throws Exception {
        return new byte[0];
    }

    public byte[] get_data_sync(String path, boolean watch) throws Exception {
        return new byte[0];
    }

    public List<String> get_children(String path, boolean watch) throws Exception {
        return null;
    }

    public void mkdirs(String path) throws Exception {

    }

    public void tryToBeLeader(String path, byte[] host) throws Exception {

    }

    public void close() {

    }

    public UUID register(ClusterStateCallback callback) {
        return null;
    }

    public ClusterStateCallback unregister(UUID id) {
        return null;
    }

    public boolean node_existed(String path, boolean watch) throws Exception {
        return false;
    }

    public Integer get_version(String path, boolean watch) throws Exception {
        return null;
    }
}
