package hot.swap.proxy.message;

import hot.swap.proxy.base.Values;

/**
 * Created by leeshine on 3/7/17.
 */

public interface IConnection {
    public Object recv();

    public void send(Values values);

    public void close();
}
