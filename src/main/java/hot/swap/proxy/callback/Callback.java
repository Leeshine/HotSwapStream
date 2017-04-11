package hot.swap.proxy.callback;

/**
 * Created by leeshine on 4/10/17.
 */
public interface Callback {
    public<T> Object execute(T... args);
}
