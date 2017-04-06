package hot.swap.proxy.cluster;

import hot.swap.proxy.zk.WatcherCallBack;
import hot.swap.proxy.zk.Zookeeper;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by leeshine on 4/6/17.
 */

public class ZookeeperCluster {
    private Logger LOG = LoggerFactory.getLogger(ZookeeperCluster.class);

    private Zookeeper zkobj = new Zookeeper();
    private CuratorFramework zk;
    private WatcherCallBack watcher;

    private Map<Object,Object> conf;

    public ZookeeperCluster(Map<Object,Object> conf){
        this.conf = conf;


    }

    public CuratorFramework mkZk(){
        return zkobj.mkClient();
    }

}
