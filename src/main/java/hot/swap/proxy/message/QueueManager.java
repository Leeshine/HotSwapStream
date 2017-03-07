package hot.swap.proxy.message;

import hot.swap.proxy.base.Values;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by leeshine on 3/7/17.
 */
public class QueueManager {
    private List<LinkedBlockingQueue<Values>> queueList;
    private Map<String,Integer> queueMap;
    private Integer queueCount;

    public QueueManager(){
        queueList = new ArrayList<LinkedBlockingQueue<Values>>();
        queueMap = new HashMap<String, Integer>();
        queueCount = 0;
    }

    public LinkedBlockingQueue getQueueByTaskId(String takid){
        if(!queueMap.containsKey((takid))){
            LinkedBlockingQueue<Values> queue = new LinkedBlockingQueue<Values>();
            queueList.add(queue);
            queueMap.put(takid,queueCount++);
        }

        return queueList.get(queueMap.get(takid));
    }
}
