package hot.swap.proxy.message;

import hot.swap.proxy.base.Values;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by leeshine on 3/7/17.
 */

public class LocalConnection implements IConnection{
    private String taskId;
    private LinkedBlockingQueue queue;
    private MessageCenter messageCenter;

    public LocalConnection(String taskId, QueueManager queueManager, MessageCenter messageCenter){
        this.taskId = taskId;
        this.queue = queueManager.getQueueByTaskId(taskId);
        this.messageCenter = messageCenter;
    }

    public Object recv(){//??
        try{
            return queue.take();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void send(Values values){
        try {
            messageCenter.send(values, taskId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void changeTaskId(String newId){
        this.taskId = newId;
    }

    public void close() {
    }
}
