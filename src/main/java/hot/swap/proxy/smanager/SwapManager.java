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
    private Map<String,SwapProxy> proxyMap = new HashMap<String, SwapProxy>();
    private Map<String,String> module2Proxy = new HashMap<String, String>();

    private MessageCenter messageCenter;
    private QueueManager queueManager;
    private Logger LOG = LoggerFactory.getLogger(SwapManager.class);
    //to do??
    public SwapManager(QueueManager queueManager, MessageCenter messageCenter){
        this.messageCenter = messageCenter;
        this.queueManager = queueManager;
    }

    public void addProxy(String moduleName, SwapProxy proxy){
        proxyMap.put(proxy.getProxyName(),proxy);
        module2Proxy.put(moduleName,proxy.getProxyName());
    }

    public void swapModule(String oldId, String newId, String newModule) throws Exception{
        SwapProxy proxy = findProxyByTaskId(oldId);
        String proxyName = proxy.getProxyName();

        //time service


        //proxy handle swap
        Vote res = proxy.handleSwap(newId,newModule);
        if(res == Vote.NO){
            LOG.info("from Task(%s) to Task(%s) has failed!!",oldId,newId);
        }else{
            handleChange(oldId,newId); // should be here safe transcatin??
            module2Proxy.put(newModule,proxyName);
            module2Proxy.remove(oldId);
            LOG.info("from Task(%s) to Task(%s) has succeed!!",oldId,newId);
        }
    }

    public SwapProxy findProxyByTaskId(String taskId){
        String proxyName = module2Proxy.get(taskId);
        return proxyMap.get(proxyName);
    }

    public void handleChange(String oldId, String newId){
        messageCenter.changeTask(oldId,newId);
    }
}
