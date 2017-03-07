package hot.swap.proxy.sproxy;

import hot.swap.proxy.base.Values;
import hot.swap.proxy.smodule.SwapModule;
import hot.swap.proxy.sproxy.interfaceutil.SwapControlInterface;
import hot.swap.proxy.utils.BehaviorInterface;
import java.util.Random;

/**
 * Created by leeshine on 3/6/17.
 */

public class SwapProxy implements BehaviorInterface,SwapControlInterface {
    private SwapModule swapModule;
    private volatile Boolean swap_lock;
    private String proxyName;

    public SwapProxy(SwapModule module){
        this.swapModule = module;
        Random random = new Random();
        proxyName = String.valueOf(random.nextInt())+"_"+getModuleName();
    }

    public void execute(Values values) {
        while(true) {//to do
            synchronized (swap_lock) {
                if (swap_lock) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else break;
            }
        }

        swapModule.execute(values);
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

    public void blockNewCall(){
    }

    public boolean checkModuleState(){
    }

    public void getInternalState(){
    }
}
