package hot.swap.proxy.funcTest;

import hot.swap.proxy.base.Values;
import hot.swap.proxy.smodule.SwapModule;

/**
 * Created by leeshine on 3/6/17.
 */

public class LowerModule extends SwapModule{
    public LowerModule(String _id) {
        super(_id);
    }

    @Override
    public void execute(Values values) {
        Values newValues = new Values();

        for(int i=0; i<values.size(); ++i){
            String str = values.getString(i);
            send(new Values(str.toLowerCase()));
        }
    }
}
