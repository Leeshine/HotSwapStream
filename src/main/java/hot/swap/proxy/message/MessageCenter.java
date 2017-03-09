package hot.swap.proxy.message;

import hot.swap.proxy.base.Values;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by leeshine on 3/7/17.
 */

public class MessageCenter {
    private Map<String,Set<String>> messageMapForTask; //src --> dst
    private Map<String,Set<String>> sourceMap; // dst --> srcs
    private Map<String,IConnection> connectionMap; //task --> connection
    private QueueManager queueManager;

    public MessageCenter(QueueManager queueManager){
        messageMapForTask = new HashMap<String, Set<String>>();
        sourceMap = new HashMap<String, Set<String>>();
        connectionMap = new HashMap<String, IConnection>();
        this.queueManager = queueManager;
    }

    public void registerModule(String taskId){
        connectionMap.put(taskId,new LocalConnection(taskId,queueManager,this));
    }

    public void init(){//to do ?? replace by handleInputList
    }

    public void addTaskPair(String src, String dst){
        if(!messageMapForTask.containsKey(src)){
            Set<String> list = new HashSet<String>();
            messageMapForTask.put(src,list);
        }
        messageMapForTask.get(src).add(dst);

        if(!sourceMap.containsKey(dst)){
            Set<String> ls = new HashSet<String>();
            sourceMap.put(dst,ls);
        }
        sourceMap.get(dst).add(src);
    }

    public void send(Values values, String taskId) throws Exception{
        if(!messageMapForTask.containsKey(taskId)) {
         //   throw new Exception("wrong task id!! " + taskId);
            return ; // ?? to do
        }

        Set<String> targetTaskIds = messageMapForTask.get(taskId);
        Iterator<String> iterator = targetTaskIds.iterator();
        while(iterator.hasNext()){
            String targetTaskId = iterator.next();
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

    public void changeTask(String oldId, String newId){
        //to do change connection taskid  ?? delete
        // proxyname = modulename  so don't need to remove
        connectionMap.get(oldId).changeTaskId(newId);
        connectionMap.put(newId,connectionMap.get(oldId));
        connectionMap.remove(oldId);

        queueManager.changeTaskId(oldId,newId);

        messageMapForTask.put(newId,messageMapForTask.get(oldId));
        messageMapForTask.remove(oldId);

        Set<String> sourceList = sourceMap.get(oldId);
        sourceMap.put(newId,sourceList);
        sourceList.remove(oldId);

        Iterator<String> iterator = sourceList.iterator();
        while(iterator.hasNext()){
            String source = iterator.next();
            Set<String> sets = messageMapForTask.get(source);
            sets.remove(oldId);
            sets.add(newId);
            messageMapForTask.put(source,sets);
        }
    }
}
