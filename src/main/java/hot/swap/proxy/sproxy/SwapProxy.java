package hot.swap.proxy.sproxy;

import hot.swap.proxy.base.SComponent;
import hot.swap.proxy.base.Values;
import hot.swap.proxy.smodule.SwapModule;
import hot.swap.proxy.sproxy.interfaceutil.SwapControlInterface;
import hot.swap.proxy.utils.BehaviorInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Created by leeshine on 3/6/17.
 */

public class SwapProxy implements BehaviorInterface,SwapControlInterface {
    private Logger LOG = LoggerFactory.getLogger(SwapProxy.class);
    private SComponent swapModule;
    private volatile Boolean swap_lock;
    private String proxyName;
    private Thread thread;

    public SwapProxy(SComponent module){
        this.swapModule = module;
        Random random = new Random();
        swap_lock = false;
        proxyName = String.valueOf(random.nextInt())+"_"+getModuleName();
        thread = new Thread(new RunClass());
    }

    public void execute(Values values) {
        /*while(true) {//to do
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
        }*/

        swapModule.execute(values);
    }

    private void setNewModule(SwapModule swapModule){
        this.swapModule = swapModule;
    }
    public String getModuleName(){
        return swapModule.getClass().getName();
    }

    public String getProxyName(){
        return proxyName;
    }

    public void handleSwap(SwapModule swapModule) {
        //blockCall();
        setNewModule(swapModule);
        //unblockCall();
    }

    private void blockCall(){
        synchronized (thread) {
            try {
                thread.wait();
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
        }
    }

    private void unblockCall(){
        synchronized (thread) {
            try {
                thread.notify();
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
        }
    }

    public void blockNewCall(){
    }

    public boolean checkModuleState(){
    }

    public void getInternalState(){
    }

    class RunClass implements Runnable{
        public void run() {
            while (true){
                Values val = (Values)swapModule.recv();
                execute(val);
            }
        }
    }

    public void startRun(){
        thread.start();
    }

    public void rollBack(){

    }
}
