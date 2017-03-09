package hot.swap.proxy.sproxy;

import hot.swap.proxy.base.Values;
import hot.swap.proxy.message.IConnection;
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
    private RunClass runClass;
    private IConnection connection;

    public SwapProxy(String proxyName,SwapModule module, IConnection connection){
        this.proxyName = proxyName;
        this.swapModule = module;
        this.connection = connection;
        swap_lock = false;
        runClass = new RunClass();
    }

    public void execute(Values values) {
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

    public Vote handleSwap(String newId, String className){ // swap entrance
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
        newModule.init(connection);
        return newModule;
    }

    public void rollBack() {//only for swapModule
        swapModule.setState(ModuleState.IDLE);
    }

    private void blockCall(){
        synchronized (swap_lock){
            swap_lock = true;
        }
        swapModule.setState(ModuleState.BLOCKED);
    }

    private void unblockCall(){
        synchronized (swap_lock){
            swap_lock = false;
            swap_lock.notify();
        }
        swapModule.setState(ModuleState.IDLE);
    }

    class RunClass implements Runnable{
        public void run() {
            while (true){
                Values val = (Values)swapModule.recv();
                execute(val);
             //   if(swap_lock){// double if for too many swap_lock
                    synchronized (swap_lock){
                        if(swap_lock){
                            try {
                                swap_lock.wait();
                            }catch (Exception e){
                                LOG.error(e.getMessage());
                            }
                        }
                    }
              //  }
            }
        }
    }

    public void startRun(){
        new Thread(runClass).start();
    }
}
