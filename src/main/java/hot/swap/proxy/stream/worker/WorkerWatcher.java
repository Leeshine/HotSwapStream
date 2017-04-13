package hot.swap.proxy.stream.worker;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.CuratorListener;

/**
 * Created by leeshine on 4/12/17.
 */

public class WorkerWatcher implements CuratorListener {
    public void eventReceived(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
        CuratorEventType eventType = curatorEvent.getType();
        if(eventType.equals(CuratorEventType.WATCHED)){
            return;
        }else {
            return;
        }
    }
}
