package hot.swap.proxy.local;

import hot.swap.proxy.stream.StreamBuilder;

/**
 * Created by leeshine on 4/1/17.
 */
public interface ILocalCluster {
    void submitTopology(StreamBuilder builder);
    void updateTopology(StreamBuilder builder);
}
