package hot.swap.proxy.smanager;

import hot.swap.proxy.message.MessageCenter;
import hot.swap.proxy.message.QueueManager;
import hot.swap.proxy.smodule.SwapModule;
import hot.swap.proxy.sproxy.SwapProxy;

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
        String proxyName = module2Proxy.get(oldId);
        SwapProxy proxy = proxyMap.get(proxyName);
        //SwapModule swapModule = (SwapModule) Class.forName(newModule).newInstance();
        Class newClas = Class.forName(newModule);
        Constructor constructor = newClas.getDeclaredConstructor(new Class[]{String.class});
        constructor.setAccessible(true);
        SwapModule swapModule = (SwapModule)constructor.newInstance(new Object[]{newId});

        handleChange(oldId,newId);
        swapModule.init(queueManager,messageCenter);

        module2Proxy.put(newModule,proxyName);
        proxy.readySwap(swapModule);
        //proxy.setNewModule(swapModule);
        //proxy.startRun(); ??new restart?
    }

    public void handleChange(String oldId, String newId){
        messageCenter.changeTask(oldId,newId);
    }
}
