package hot.swap.proxy.funcTest;

import hot.swap.proxy.base.Values;
import hot.swap.proxy.smodule.SwapModule;

/**
 * Created by leeshine on 3/6/17.
 */
public class UpperModule extends SwapModule{
    @Override
    public Values execute(Values values) {
        Values newValue = new Values();
        for(int i=0; i<values.size(); ++i){
            String str = values.getString(i);
            newValue.add(str.toUpperCase());
        }

        return newValue;
    }
}
