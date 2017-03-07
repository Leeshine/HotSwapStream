package hot.swap.proxy.base;

import hot.swap.proxy.message.IConnection;
import hot.swap.proxy.message.LocalConnection;
import hot.swap.proxy.message.MessageCenter;
import hot.swap.proxy.message.QueueManager;
import hot.swap.proxy.utils.BehaviorInterface;

/**
 * Created by leeshine on 3/7/17.
 */

public abstract class SComponent implements BehaviorInterface{
    private IConnection connection;
    private String id;
    private boolean Swapable;

    public SComponent(String _id, Boolean swapable){
        this.id = _id;
        Swapable = swapable;
    }

    public void init(QueueManager queueManager, MessageCenter messageCenter){
        connection = new LocalConnection(id,queueManager,messageCenter);
    }

    public Object recv(){
        return connection.recv();
    }

    public void send(Values values){
        connection.send(values);
    }

    public boolean checkSwapable(){
        return Swapable;
    }

    public abstract void startRun();
}
