package hot.swap.proxy.smodule;

import hot.swap.proxy.base.Values;
import hot.swap.proxy.smodule.interfaceutil.SwapInterface;
import hot.swap.proxy.utils.BehaviorInterface;


/**
 * Created by leeshine on 3/6/17.
 */

public class SwapModule implements BehaviorInterface,SwapInterface{
    private ModuleState state;

    public SwapModule(){
        state = ModuleState.INIT;
    }

    public  Values execute(Values values){
        return null;
    }


    public FunModuleIdentifier getIdentifier() {
        return null;
    }

    public String getState() {
        return null;
    }
}
