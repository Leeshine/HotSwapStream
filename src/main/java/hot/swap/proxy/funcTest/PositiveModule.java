package hot.swap.proxy.funcTest;

import hot.swap.proxy.base.Values;
import hot.swap.proxy.smodule.SwapModule;

/**
 * Created by leeshine on 3/6/17.
 */
public class PositiveModule extends SwapModule{
    public PositiveModule(String _id) {
        super(_id);
    }

    @Override
    public void execute(Values values) {
        for(int i=0; i<values.size(); ++i){
            int value = values.getInt(i);
            if(value < 0)
                value *= -1;
            send(new Values(value));
        }
    }
}
