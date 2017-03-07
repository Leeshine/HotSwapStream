package hot.swap.proxy.stream;

import hot.swap.proxy.base.SComponent;
import hot.swap.proxy.message.MessageCenter;
import hot.swap.proxy.message.QueueManager;
import hot.swap.proxy.smanager.SwapManager;
import hot.swap.proxy.sproxy.SwapProxy;

import java.util.List;
import java.util.Map;

/**
 * Created by leeshine on 3/7/17.
 */

public class StreamStarter {
    public static void submitTopology(StreamBuilder builder, String topologyName){
        //start MessageCenter QueueManager
        QueueManager queueManager = new QueueManager();
        MessageCenter messageCenter = new MessageCenter(queueManager);
        SwapManager swapManager = new SwapManager();

        //submit
        Map<String,SComponent> componentMap = builder.getComponentMap();
        Map<String,List<String>> inputList = builder.getInputList();

        messageCenter.handleInputList(inputList);

        for(Map.Entry<String,SComponent> entry : componentMap.entrySet()){
            SComponent component = entry.getValue();
            component.init(queueManager,messageCenter);

            if(component.checkSwapable()){//swapable
                SwapProxy swapProxy = new SwapProxy(component);
                swapProxy.startRun();
                swapManager.addProxy(component.getId(),swapProxy);
            }else{
                component.startRun();
            }
        }
    }
}
