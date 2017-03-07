package hot.swap.proxy;

import hot.swap.proxy.base.Values;
import hot.swap.proxy.funcTest.*;
import hot.swap.proxy.smanager.SwapManager;
import hot.swap.proxy.sproxy.SwapProxy;
import java.util.Random;

/**
 * Created by leeshine on 3/3/17.
 */

public class ClientMain {
    public static void main(String[] args) throws Exception {
        SwapManager manager = new SwapManager();

        SwapProxy caseProxy = new SwapProxy(new UpperModule());
        SwapProxy printProxy = new SwapProxy(new PrintModule());

        manager.addProxy(UpperModule.class.getName(),caseProxy);
        manager.addProxy(PrintModule.class.getName(),printProxy);

        int cnt = 0;
        String str = "lv shan chun IS a XieXie ni";
        String[] words = str.split(" ");
        Random random = new Random();

        while(true){
            String word = words[random.nextInt(words.length)];

            caseProxy.execute(new Values(word));

            Thread.sleep(1000);
            cnt++;

            if(cnt == 5)
                manager.swapModule(UpperModule.class.getName(), LowerModule.class.getName());
        }
    }
}
