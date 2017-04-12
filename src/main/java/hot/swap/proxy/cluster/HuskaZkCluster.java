package hot.swap.proxy.cluster;

import hot.swap.proxy.base.SComponent;
import hot.swap.proxy.stream.StreamBuilder;
import hot.swap.proxy.stream.Topology;
import hot.swap.proxy.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by leeshine on 4/6/17.
 * all huska in zk operation
 */
public class HuskaZkCluster {
    private static Logger LOG = LoggerFactory.getLogger(HuskaZkCluster.class);

    private ZookeeperCluster cluster;
    private Map<Object,Object> conf;

    public HuskaZkCluster(Map<Object,Object> conf) throws Exception{
        this.conf = conf;
        cluster = new ZookeeperCluster(conf);
    }

    public Object getObject(String path, boolean callback) throws Exception{
        byte[] data = cluster.get_data(path,callback);
        return Utils.deserialize(data);
    }

    public void deleteObject(String path) throws Exception{
        try{
            cluster.delete_node(path);
        }catch (Exception e){
            LOG.warn("failed to delete node "+path);
        }
    }

    public void setObject(String path, Object obj) throws Exception{
        if(obj instanceof byte[]){
            cluster.set_data(path,(byte[]) obj);
        }else if(obj instanceof  String){
            cluster.set_data(path,((String)obj).getBytes());
        }else{
            cluster.set_data(path,Utils.serialize(obj));
        }
    }

    public void setTempObject(String path, Object obj) throws Exception{
        if(obj instanceof byte[]){
            cluster.set_ephemeral_node(path,(byte[])obj);
        }else if(obj instanceof String){
            cluster.set_ephemeral_node(path,((String)obj).getBytes());
        }else{
            cluster.set_ephemeral_node(path,Utils.serialize(obj));
        }
    }

    public Topology getTopology(String topologyName) throws Exception{
        String path = Cluster.topology_path(topologyName);
        Object obj = getObject(path,false);
        return (Topology)obj;
    }

    /*public String getJarLocation(String component_class_name) throws Exception{
    }*/

    public SComponent getSComponentByClassName(String component_class_name) throws Exception{
        String path = Cluster.plugin_path(component_class_name);
        Object obj = getObject(path,false);
        return (SComponent)obj;
    }

    public void close(){
        cluster.close();
    }

}
