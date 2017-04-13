package hot.swap.proxy.stream.topserver;

import hot.swap.proxy.cluster.HuskaZkCluster;

import java.util.Map;

/**
 * Created by leeshine on 4/12/17.
 */

public class ServerData {
    private Map<Object,Object> conf;
    private HuskaZkCluster huskaZkCluster;

    public ServerData(Map conf){
        this.conf = conf;
    }

}
