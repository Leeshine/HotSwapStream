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
    private String id; // swapmodule's id only for swap , replaced by proxy's id otherwise
    private boolean Swapable;

    public SComponent(){
    }

    public void setId(String _id){
        this.id = _id;
    }

    public void setSwapable(boolean swapable){
        this.Swapable = swapable;
    }

    public SComponent(String _id, boolean swapable){
        this.id = _id;
        Swapable = swapable;
    }

    public void init(QueueManager queueManager, MessageCenter messageCenter){// for non-swap-module
        connection = new LocalConnection(id,queueManager,messageCenter);
    }

    public void init(IConnection connection, String proxyName){// for swap-module
        this.connection = connection;
    }

    public Object recv(){
        return connection.recv();
    }

    public void send(Values values){
        connection.send(values);
    }

    public String getId(){
        return id;
    }

    public boolean checkSwapable(){
        return Swapable;
    }

    public abstract void startRun();
}
