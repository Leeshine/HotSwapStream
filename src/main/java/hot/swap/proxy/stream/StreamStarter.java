package hot.swap.proxy.stream;

import hot.swap.proxy.base.SComponent;
import hot.swap.proxy.message.MessageCenter;
import hot.swap.proxy.message.QueueManager;

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

        //submit
        Map<String,SComponent> componentMap = builder.getComponentMap();
        Map<String,List<String>> inputList = builder.getInputList();

        for(Map.Entry entry : componentMap.entrySet()){
            SComponent component = (SComponent)entry.getValue();
            component.init(queueManager,messageCenter);
            if(component.checkSwapable()){//swapable

            }else{
            }
        }

    }
}
