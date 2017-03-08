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

public class MessageCenter {
    private Map<String,List<String>> messageMapForTask;
    private QueueManager queueManager;

    public MessageCenter(QueueManager queueManager){
        messageMapForTask = new HashMap<String, List<String>>();
        this.queueManager = queueManager;
    }

    public void init(){//to do ?? replace by handleInputList
    }

    public void addTaskPair(String src, String dst){
        if(!messageMapForTask.containsKey(src)){
            List<String> list = new ArrayList<String>();
            messageMapForTask.put(src,list);
        }
        messageMapForTask.get(src).add(dst);
    }

    public void send(Values values, String taskId) throws Exception{
        if(!messageMapForTask.containsKey(taskId))
            throw new Exception("wrong task id!!");

        List<String> targetTaskIds = messageMapForTask.get(taskId);
        for(int i=0; i<targetTaskIds.size(); ++i){
            String targetTaskId = targetTaskIds.get(i);
            LinkedBlockingQueue targetQueue = queueManager.getQueueByTaskId(targetTaskId);
            targetQueue.put(values);
        }
    }

    public void handleInputList(Map<String,List<String>> inputList){
        for(Map.Entry<String,List<String>> entry : inputList.entrySet()){
            String dst = entry.getKey();
            List<String> srcs = entry.getValue();
            for(int i=0; i<srcs.size(); ++i){
                String src = srcs.get(i);
                addTaskPair(src,dst);
            }
        }
    }
}
