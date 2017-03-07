package hot.swap.proxy;

import hot.swap.proxy.smodule.FunModuleIdentifier;
import hot.swap.proxy.smodule.interfaceutil.SwapInterface;
import hot.swap.proxy.utils.BehaviorInterface;

/**
 * Created by leeshine on 3/3/17.
 */

public  abstract class FunModel implements BehaviorInterface,SwapInterface {
    public Object execute() {
        return null;
    }

    public FunModuleIdentifier getIdentifier() {
        return null;
    }

    public String getState() {
        return null;
    }
}
