package hot.swap.proxy.message;

import hot.swap.proxy.base.Values;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by leeshine on 3/7/17.
 */
public class QueueManager {
    //to do thread safe
    private List<LinkedBlockingQueue<Values>> queueList;
    private ConcurrentHashMap<String,Integer> queueMap;
    private Integer queueCount;

    public QueueManager(){
        queueList = new ArrayList<LinkedBlockingQueue<Values>>();
        queueMap = new ConcurrentHashMap<String, Integer>();
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

    public void changeTaskId(String oldId, String newId){
        if(!queueMap.containsKey(oldId)){
            getQueueByTaskId(newId);
        }else{
            queueMap.put(newId,queueMap.get(oldId));
            queueMap.remove(oldId);
        }
    }
}
