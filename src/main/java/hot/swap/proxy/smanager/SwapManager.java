package hot.swap.proxy.smanager;

import hot.swap.proxy.smodule.SwapModule;
import hot.swap.proxy.sproxy.SwapProxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by leeshine on 3/6/17.
 */

public class SwapManager{
    private Map<String,SwapProxy> proxyMap = new HashMap<String, SwapProxy>();
    private Map<String,String> module2Proxy = new HashMap<String, String>();

    public void addProxy(String moduleName, SwapProxy proxy){
        proxyMap.put(proxy.getProxyName(),proxy);
        module2Proxy.put(moduleName,proxy.getProxyName());
    }

    public void swapModule(String oldModule, String newModule) throws Exception{
        String proxyName = module2Proxy.get(oldModule);
        SwapProxy proxy = proxyMap.get(proxyName);
        SwapModule swapModule = (SwapModule) Class.forName(newModule).newInstance();
        proxy.setNewModule(swapModule);
        module2Proxy.put(newModule,proxyName);
    }
}
