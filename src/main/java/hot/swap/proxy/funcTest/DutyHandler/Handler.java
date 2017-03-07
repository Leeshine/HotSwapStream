package hot.swap.proxy.funcTest.DutyHandler;

import java.util.List;

/**
 * Created by leeshine on 3/6/17.
 */

public abstract class Handler {
    private Handler handler;

    public abstract void handleRequest(List<String> lists);
    public abstract void handleRequest();


    public Handler getNextHandler(){
        return handler;
    }

    public void setNextHandler(Handler handler){
        this.handler = handler;
    }
}
