package hot.swap.proxy.sproxy;

import hot.swap.proxy.base.Values;
import hot.swap.proxy.smodule.SwapModule;
import hot.swap.proxy.sproxy.interfaceutil.SwapControlInterface;
import hot.swap.proxy.utils.BehaviorInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by leeshine on 3/6/17.
 */

public class SwapProxy implements BehaviorInterface,SwapControlInterface{
    private SwapModule swapModule;
    private volatile Boolean swap_lock;
    private List<SwapProxy> proxyList;
    private String proxyName;

    public SwapProxy(SwapModule module){
        this.swapModule = module;
        proxyList = new ArrayList<SwapProxy>();
        Random random = new Random();
        proxyName = String.valueOf(random.nextInt())+"_"+getModuleName();
    }

    public void addProxy(SwapProxy proxy){
        proxyList.add(proxy);
    }

    public Values execute(Values values) {
        Values val = swapModule.execute(values);
        for(int i=0; i<proxyList.size(); ++i)
            proxyList.get(i).execute(val);
        return val;
    }

    public void setNewModule(SwapModule swapModule){
        this.swapModule = swapModule;
    }

    public String getModuleName(){
        return swapModule.getClass().getName();
    }

    public String getProxyName(){
        return proxyName;
    }
}
