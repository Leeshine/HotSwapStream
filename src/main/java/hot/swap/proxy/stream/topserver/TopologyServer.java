package hot.swap.proxy.stream.topserver;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by leeshine on 4/6/17.
 */
public class TopologyServer implements Runnable{
    private static Logger LOG = LoggerFactory.getLogger(TopologyServer.class);

    private Map conf;
    private ServerData serverData;
    public TopologyServer(Map conf, ServerData serverData){
        this.conf = conf;
        this.serverData = serverData;
    }

    public void run() {
    }
    //monitor,parser,adjust
}
