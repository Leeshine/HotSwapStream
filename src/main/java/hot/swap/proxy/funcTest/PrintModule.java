package hot.swap.proxy.funcTest;

import hot.swap.proxy.base.Values;
import hot.swap.proxy.smodule.SwapModule;


/**
 * Created by leeshine on 3/6/17.
 */

public class PrintModule extends SwapModule {
    private  int cnt;
    public PrintModule(String _id) {
        super(_id);
        cnt = 0;
    }

    @Override
    public void execute(Values values) {
        for(int i=0; i<values.size(); ++i)
            System.out.println(values.get(i)+ " "+(++cnt));
    }
}
