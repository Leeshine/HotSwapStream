package hot.swap.proxy.stream.nimbus;

import hot.swap.proxy.stream.StreamBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * Created by leeshine on 4/6/17.
 */
public class ServiceHandler {
    private final static Logger LOG = LoggerFactory.getLogger(ServiceHandler.class);
    private NimbusData data;
    private Map<Object,Object> conf;

    public ServiceHandler(NimbusData data){
        this.data = data;
        this.conf = data.getConf();
    }

    public void shutdown(){
    }

    public void submitTopology(StreamBuilder builder, Map conf) throws Exception{
        String topologyName = builder.getTopologyName();
        synchronized (data){
            Set<String> pendingTopologys = data.getPendingSubmitTopology().keySet();
            if(pendingTopologys.contains(topologyName)){
                throw new Exception(topologyName + "were submitted");
            }

            data.getPendingSubmitTopology().put(topologyName,null);
        }

        try{
            //local

            //zookeeper

        }catch (Exception e){

        }
    }

    public void updateTopology(StreamBuilder builder, Map conf) throws Exception{
        String topologyName = builder.getTopologyName();
    }

    public void makeAssignMent(String topologyName, String topologyId){

    }

    public void killTopology(String name) throws Exception{
    }
}
