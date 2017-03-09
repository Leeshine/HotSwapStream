package hot.swap.proxy.sproxy;

import hot.swap.proxy.base.Values;
import hot.swap.proxy.smodule.ModuleState;
import hot.swap.proxy.smodule.SwapModule;
import hot.swap.proxy.sproxy.interfaceutil.SwapControlInterface;
import hot.swap.proxy.trnascation.Vote;
import hot.swap.proxy.utils.BehaviorInterface;
import hot.swap.proxy.utils.Pair;
import hot.swap.proxy.utils.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

/**
 * Created by leeshine on 3/6/17.
 */

public class SwapProxy implements BehaviorInterface,SwapControlInterface {
    private Logger LOG = LoggerFactory.getLogger(SwapProxy.class);
    private SwapModule swapModule;
    private volatile Boolean swap_lock;
    private String proxyName;
    private Thread thread;

    public SwapProxy(SwapModule module){
        this.swapModule = module;
        swap_lock = false;
        proxyName = RandomUtil.RandomString(6);
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

        swapModule.setState(ModuleState.BUSY);
        swapModule.execute(values);
        swapModule.setState(ModuleState.IDLE);
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
        blockCall();
        setNewModule(swapModule);
        unblockCall();
    }

    public Vote handleSwap(String newId, String className){
        blockCall();
        Vote res = swapTranscation(newId,className);
        unblockCall();
        return res;
    }

    public Vote swapTranscation(String newId, String className){
        Pair<Vote,SwapModule> res = swapModule.prepareSwap();
        if(res.getFirst() == Vote.NO){// prepare failed
            rollBack();
            LOG.info("swap transcation failed in prepared periord of Task : " + newId);
            return Vote.NO;
        }else{
            try{
                SwapModule newModule = commitTranscation(newId,className);
                setNewModule(newModule);
                return Vote.YES;
            }catch (Exception e){
                LOG.error(e.getMessage());
                LOG.info("swap transcation failed in commit periord of Task : "+newId);
                return Vote.NO;
            }
        }
    }

    public SwapModule commitTranscation(String newId, String className) throws Exception{
        Class newClass = Class.forName(className);
        Constructor constructor = newClass.getDeclaredConstructor(new Class[]{String.class});
        constructor.setAccessible(true);

        SwapModule newModule = (SwapModule)constructor.newInstance(new Object[]{newId});
        return newModule;
    }

    public void rollBack() {
        swapModule.setState(ModuleState.IDLE);
    }

    private void blockCall(){
        swapModule.setState(ModuleState.BLOCKED);
    }

    private void unblockCall(){
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
}
