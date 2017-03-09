package hot.swap.proxy.sproxy.interfaceutil;

import hot.swap.proxy.smodule.SwapModule;

/**
 * Created by leeshine on 3/3/17.
 */
public interface SwapControlInterface {
    public void handleSwap(SwapModule swapModule);
    public void rollBack();
}
