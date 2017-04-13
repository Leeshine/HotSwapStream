package hot.swap.proxy.cluster;

import hot.swap.proxy.base.Config;
import hot.swap.proxy.utils.Constant;
import hot.swap.proxy.utils.PathUtils;
import hot.swap.proxy.zk.DefaultWatcherCallBack;
import hot.swap.proxy.zk.WatcherCallBack;
import hot.swap.proxy.zk.Zookeeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by leeshine on 4/6/17.
 * all zk interface
 */

public class ZookeeperCluster {
    private static Logger LOG = LoggerFactory.getLogger(ZookeeperCluster.class);

    private Zookeeper zkobj = new Zookeeper();
    private CuratorFramework zk;

    private Map<Object,Object> conf;

    public ZookeeperCluster(Map<Object,Object> conf,WatcherCallBack watcherCallBack) throws Exception{
        this.conf = conf;

        CuratorFramework _zk = mkZk();
        //String path = String.valueOf(conf.get(Config.STORM_ZOOKEEPER_ROOT));
        String path = String.valueOf(Constant.zk_root);

        zkobj.mkdirs(_zk,path);
        _zk.close();

        zk = null;
        zk = mkZk(watcherCallBack);
    }

    public CuratorFramework getClient(){
        return  zk;
    }

    public ZookeeperCluster(Map<Object,Object> conf) throws Exception{
        this(conf,new DefaultWatcherCallBack());
    }

    public void addListener(CuratorListener listener){
        zk.getCuratorListenable().addListener(listener);
    }

    public CuratorFramework mkZk(){
        return zkobj.mkClient(conf,(List<String>) conf.get(Config.STORM_ZOOKEEPER_SERVERS),(Integer) conf.get(Config.STORM_ZOOKEEPER_PORT),Constant.zk_root);
    }

    public CuratorFramework mkZk(WatcherCallBack watcher){
        return zkobj.mkClient(conf,(List<String>) conf.get(Config.STORM_ZOOKEEPER_SERVERS),(Integer) conf.get(Config.STORM_ZOOKEEPER_PORT),Constant.zk_root,watcher);
    }

    public void close(){
        zk.close();
    }

    public void delete_node(String path) throws Exception{
        zkobj.deletereRcursive(zk,path);
    }

    public List<String> get_children(String path, boolean watch) throws Exception{
        return zkobj.getChildren(zk,path,watch);
    }

    public byte[] get_data(String path,boolean watch) throws Exception{
        return zkobj.getData(zk,path,watch);
    }

    public void mkdirs(String path) throws Exception{
        zkobj.mkdirs(zk,path);
    }

    public void set_data(String path,byte[] data) throws Exception{
        if(zkobj.exists(zk,path,false)){
            zkobj.setData(zk,path,data);
        }else{
            zkobj.mkdirs(zk, PathUtils.parent_path(path));
            zkobj.createNode(zk,path,data, CreateMode.PERSISTENT);
        }
    }

    public boolean node_existed(String path, boolean watch) throws Exception {
        return zkobj.exists(zk,path,watch);
    }

    public void set_ephemeral_node(String path, byte[] data) throws Exception{
        zkobj.mkdirs(zk,PathUtils.parent_path(path));
        if(zkobj.exists(zk,path,false)){
            zkobj.setData(zk,path,data);
        }else{
            zkobj.createNode(zk,path,data,CreateMode.EPHEMERAL);
        }
    }
}
