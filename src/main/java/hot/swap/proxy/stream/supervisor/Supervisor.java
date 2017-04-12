package hot.swap.proxy.stream.supervisor;

import hot.swap.proxy.base.Shutdown;

import java.util.Map;

/**
 * Created by leeshine on 4/6/17.
 */
public class Supervisor implements Runnable,Shutdown{
    private SupervisorData supervisorData;
    private Map conf;

    private Thread thread;

    public Supervisor(Map conf,SupervisorData supervisorData){
        this.conf = conf;
        this.supervisorData = supervisorData;

        thread = new Thread(this);
        thread.start();
    }

    public void run() {
    }

    public void shutdown() {

    }
}
