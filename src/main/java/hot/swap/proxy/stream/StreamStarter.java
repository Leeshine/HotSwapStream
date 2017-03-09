package hot.swap.proxy.stream;

import hot.swap.proxy.base.SComponent;
import hot.swap.proxy.message.IConnection;
import hot.swap.proxy.message.LocalConnection;
import hot.swap.proxy.message.MessageCenter;
import hot.swap.proxy.message.QueueManager;
import hot.swap.proxy.smanager.SwapManager;
import hot.swap.proxy.smodule.SwapModule;
import hot.swap.proxy.sproxy.SwapProxy;
import hot.swap.proxy.utils.RandomUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by leeshine on 3/7/17.
 */

public class StreamStarter {
    public static SwapManager submitTopology(StreamBuilder builder, String topologyName){
        //start MessageCenter QueueManager
        QueueManager queueManager = new QueueManager();
        MessageCenter messageCenter = new MessageCenter(queueManager);
        SwapManager swapManager = new SwapManager(messageCenter);

        //submit
        Map<String,SComponent> componentMap = builder.getComponentMap();
        Map<String,List<String>> inputList = builder.getInputList();

        messageCenter.handleInputList(inputList);

        for(Map.Entry<String,SComponent> entry : componentMap.entrySet()){
            SComponent component = entry.getValue();
            component.init(queueManager,messageCenter);

            //to do order by start thread
            // name stategy
            if(component.checkSwapable()){//swapable
                String proxyName = component.getId(); //proxyname = modulename ??
                IConnection connection = new LocalConnection(proxyName,queueManager,messageCenter);
                SwapProxy swapProxy = new SwapProxy(proxyName,(SwapModule)component,connection);
                swapProxy.startRun();
                swapManager.addProxy(component.getId(),swapProxy);
            }else{
                component.init(queueManager,messageCenter);
                component.startRun();
            }
        }

        return swapManager;
    }
}
