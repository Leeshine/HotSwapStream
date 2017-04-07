package hot.swap.proxy.cluster;

import hot.swap.proxy.stream.StreamBuilder;

import java.util.Map;

/**
 * Created by leeshine on 4/6/17.
 */
public class HuskaZkCluster {
    private ZookeeperCluster cluster;
    private Map<Object,Object> conf;

    public HuskaZkCluster(Map<Object,Object> conf) throws Exception{
        this.conf = conf;

        cluster = new ZookeeperCluster(conf);
    }

    public void updateTopology(StreamBuilder builder){
    }
}
