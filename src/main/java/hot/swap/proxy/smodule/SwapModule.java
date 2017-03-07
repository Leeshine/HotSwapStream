package hot.swap.proxy.smodule;

import hot.swap.proxy.base.SComponent;
import hot.swap.proxy.base.Values;
import hot.swap.proxy.smodule.interfaceutil.SwapInterface;
import hot.swap.proxy.utils.BehaviorInterface;

/**
 * Created by leeshine on 3/6/17.
 */

public class SwapModule extends SComponent implements  SwapInterface{
    private ModuleState state;

    public SwapModule(String _id){
        super(_id);
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

}
