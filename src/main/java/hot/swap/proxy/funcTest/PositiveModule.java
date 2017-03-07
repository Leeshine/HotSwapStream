package hot.swap.proxy.funcTest;

import hot.swap.proxy.base.Values;
import hot.swap.proxy.smodule.SwapModule;

/**
 * Created by leeshine on 3/6/17.
 */
public class PositiveModule extends SwapModule{
    @Override
    public Values execute(Values values) {
        Values newValues = new Values();
        for(int i=0; i<values.size(); ++i){
            int value = values.getInt(i);
            if(value < 0)
                value *= -1;
            newValues.add(value);
        }

        return newValues;
    }
}
