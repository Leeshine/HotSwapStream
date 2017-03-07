package hot.swap.proxy.nonswapmodule;

import hot.swap.proxy.base.SComponent;
import hot.swap.proxy.base.Values;
import hot.swap.proxy.message.MessageCenter;
import hot.swap.proxy.message.QueueManager;

/**
 * Created by leeshine on 3/7/17.
 */

public class NonSwapModule extends SComponent{

    public NonSwapModule(String _id){
        super(_id,false);
    }

    public void init(QueueManager queueManager, MessageCenter messageCenter){
        super.init(queueManager,messageCenter);
    }


    public void execute(Values values) {
    }

    class RunClass implements Runnable{
        public void run() {
            while(true){
                Values val = (Values)recv();
                execute(val);
            }
        }
    }

    @Override
    public void startRun() {
        RunClass runClass = new RunClass();
        new Thread(runClass).start();
    }
}
