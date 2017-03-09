package hot.swap.proxy.smodule.interfaceutil;

import hot.swap.proxy.smodule.FunModuleIdentifier;

/**
 * Created by leeshine on 3/3/17.
 */

public interface SwapInterface {
    public FunModuleIdentifier getIdentifier();
    public String getState();
    public void checkState() throws Exception;
}
