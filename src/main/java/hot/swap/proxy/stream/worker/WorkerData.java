package hot.swap.proxy.stream.worker;

import hot.swap.proxy.base.SComponent;
import hot.swap.proxy.cluster.HuskaZkCluster;
import hot.swap.proxy.message.MessageCenter;
import hot.swap.proxy.smanager.SwapManager;
import hot.swap.proxy.utils.RotatingMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by leeshine on 4/11/17.
 */

public class WorkerData {
    private static Logger LOG = LoggerFactory.getLogger(WorkerData.class);

    private Map<Object,Object> conf;
   // private RotatingMap<String,SComponent> componentRotatingMap;
    private HuskaZkCluster huskaZkCluster;
    private SwapManager manager;
    private MessageCenter messageCenter;

    private WorkerData(Map conf){
        this.conf = conf;
     //   componentRotatingMap = new RotatingMap<String, SComponent>(null);
    }

    public Map<Object, Object> getConf() {
        return conf;
    }

    public HuskaZkCluster getHuskaZkCluster() {
        return huskaZkCluster;
    }

    /*public RotatingMap<String, SComponent> getComponentRotatingMap() {
        return componentRotatingMap;
    }*/

    public SwapManager getManager() {
        return manager;
    }

    public MessageCenter getMessageCenter() {
        return messageCenter;
    }
}
