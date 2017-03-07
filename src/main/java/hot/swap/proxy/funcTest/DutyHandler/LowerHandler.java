package hot.swap.proxy.funcTest.DutyHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leeshine on 3/6/17.
 */

public class LowerHandler extends Handler{
    @Override
    public void handleRequest(List<String> lists) {
        List<String> tmp = new ArrayList<String>();
        for(int i=0; i<lists.size(); ++i)
            tmp.add(lists.get(i).toLowerCase());
        getNextHandler().handleRequest(tmp);
    }

    @Override
    public void handleRequest() {

    }

}
