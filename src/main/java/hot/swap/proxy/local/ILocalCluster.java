package hot.swap.proxy.local;

import hot.swap.proxy.stream.StreamBuilder;

import java.util.Map;

/**
 * Created by leeshine on 4/1/17.
 */
public interface ILocalCluster {
    void submitTopology(StreamBuilder builder, Map conf);

    void updateTopology(StreamBuilder builder, Map conf);

    void killTopology(String topologyName);
}
