package hot.swap.proxy.local;

import hot.swap.proxy.stream.StreamBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by leeshine on 4/5/17.
 */
public class LocalCluster implements ILocalCluster{
    private static final Logger LOG = LoggerFactory.getLogger(LocalCluster.class);

    private LocalClusterMap state;

    protected static LocalCluster instance = null;

    public static LocalCluster getInstance(){
        return instance;
    }

    public LocalCluster(){
        synchronized (LocalCluster.class){
            this.state = LocalUtils.prepareLocalCluster();
            if(this.state == null)
                throw new RuntimeException("prepare localCluster error!");
            instance = this;
        }
    }

    public void submitTopology(StreamBuilder builder, Map conf) {
        try{
            state.getNimbus().submitTopology(builder,conf);
        }catch (Exception e){
            LOG.error("failed to submit topology "+builder.getTopologyName(), e);
        }
    }

    public void updateTopology(StreamBuilder builder, Map conf) {
    }

    public void killTopology(String topologyName){
    }
}
