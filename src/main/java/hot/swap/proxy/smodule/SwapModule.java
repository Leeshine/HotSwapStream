package hot.swap.proxy.smodule;

import hot.swap.proxy.base.SComponent;
import hot.swap.proxy.base.Values;
import hot.swap.proxy.smodule.interfaceutil.SwapInterface;
import hot.swap.proxy.utils.RandomUtil;

/**
 * Created by leeshine on 3/6/17.
 */

public class SwapModule extends SComponent implements  SwapInterface{
    private ModuleState state;

    private SwapModule(){// should not be used!!
        super(RandomUtil.RandomString(),true);
        state = ModuleState.INIT;
    }

    public SwapModule(String _id){
        super(_id,true);
        state = ModuleState.INIT;
    }

    public  void execute(Values values){
    }


    public FunModuleIdentifier getIdentifier() {
        return null;
    }

    public String getState() {
        return null;
    }

    public void checkState() {
    }

    @Override
    public void startRun(){
    }
}
