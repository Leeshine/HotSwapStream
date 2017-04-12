package hot.swap.proxy.stream.nimbus;

import hot.swap.proxy.stream.Topology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by leeshine on 4/11/17.
 */

public class TopologyAssign implements Runnable{
    private static Logger LOG = LoggerFactory.getLogger(TopologyAssign.class);

    private TopologyAssign(){

    }

    private static TopologyAssign instance = null;

    public static TopologyAssign getInstance(){
        synchronized (TopologyAssign.class){
            if(instance == null)
                instance = new TopologyAssign();
        }
        return instance;
    }

    private NimbusData nimbusData;
    private Thread thread;

    public void init(NimbusData data){
        this.nimbusData = data;

        thread = new Thread(this);
        thread.setName("TopologyAssign");
        thread.setDaemon(true);
        thread.start();
    }

    public void run() {
    }
}
