package hot.swap.proxy.smanager;

import hot.swap.proxy.message.MessageCenter;
import hot.swap.proxy.message.QueueManager;
import hot.swap.proxy.smodule.SwapModule;
import hot.swap.proxy.sproxy.SwapProxy;
import hot.swap.proxy.trnascation.Vote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by leeshine on 3/6/17.
 */

public class SwapManager{
    private Map<String,SwapProxy> module2Proxy;

    private MessageCenter messageCenter;
    private Logger LOG = LoggerFactory.getLogger(SwapManager.class);
    //to do??
    public SwapManager(MessageCenter messageCenter){
        this.messageCenter = messageCenter;
        module2Proxy = new HashMap<String, SwapProxy>();
    }

    public void addProxy(String moduleName, SwapProxy proxy){
        module2Proxy.put(moduleName,proxy);
    }


    // to do need synchronized ???
    public void swapModule(String oldId, String newId, String newModule) throws Exception{
        SwapProxy proxy = findProxyByTaskId(oldId);
        String proxyName = proxy.getProxyName();

        //time service


        //proxy handle swap
        Vote res = proxy.handleSwap(newId,newModule);
        if(res == Vote.NO){
            LOG.info("from Task(%s) to Task(%s) has failed!!",oldId,newId);
        }else{
            module2Proxy.put(newModule,proxy);
            module2Proxy.remove(oldId);
            LOG.info("from Task(%s) to Task(%s) has succeed!!",oldId,newId);
        }
    }

    public SwapProxy findProxyByTaskId(String taskId){
        return module2Proxy.get(taskId);
    }

    public void notifyMessageCenter(String oldId, String newId){
        // proxy==modulename and do not change
        // so do not need to call this method
        messageCenter.changeTask(oldId,newId);
    }
}
