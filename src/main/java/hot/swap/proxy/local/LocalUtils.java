package hot.swap.proxy.local;

import hot.swap.proxy.stream.nimbus.NimbusData;
import hot.swap.proxy.stream.nimbus.NimbusServer;
import hot.swap.proxy.stream.nimbus.ServiceHandler;
import hot.swap.proxy.stream.supervisor.Supervisor;
import hot.swap.proxy.stream.supervisor.SupervisorData;
import hot.swap.proxy.stream.topserver.ServerData;
import hot.swap.proxy.stream.topserver.TopologyServer;
import hot.swap.proxy.utils.Constant;
import hot.swap.proxy.zk.Factory;
import hot.swap.proxy.zk.Zookeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

/**
 * Created by leeshine on 4/5/17.
 */

public class LocalUtils {
    private static Logger LOG = LoggerFactory.getLogger(LocalUtils.class);

    public static LocalClusterMap prepareLocalCluster(){
        LocalClusterMap state = new LocalClusterMap();
        try {
            List<String> tmpDirs = new ArrayList();

            String zkDir = getTmpDir();
            tmpDirs.add(zkDir);
            Factory zookeeper = startLocalZookeeper(zkDir);
            Map conf = getLocalConf(zookeeper.getZooKeeperServer().getClientPort());

            String nimbusDir = getTmpDir();
            tmpDirs.add(nimbusDir);
            Map nimbusConf = deepCopyMap(conf);
            nimbusConf.put(Constant.STORM_LOCAL_DIR, nimbusDir);
            ServiceHandler nimbus = new ServiceHandler(makeNimbusData(conf));

            Map supervisorConf = deepCopyMap(conf);
            String supervisorDir = getTmpDir();
            tmpDirs.add(supervisorDir);
            supervisorConf.put(Constant.STORM_LOCAL_DIR, supervisorDir);
            Supervisor supervisor = new Supervisor(supervisorConf,makeSupervisorData());

            String topserverDir = getTmpDir();
            tmpDirs.add(topserverDir);
            Map serverConf= deepCopyMap(conf);
            // ?? to do !!
            serverConf.put();
            TopologyServer topServer = new TopologyServer(serverConf,makeServerData());

            state.setNimbus(nimbus);
            state.setSupervisor(supervisor);
            state.setTopologyServer(topServer);
            state.setZookeeper(zookeeper);
            state.setConf(conf);
            state.setTmpDir(tmpDirs);
            return state;
        } catch (Exception e) {
            LOG.error("prepare cluster error!", e);
            state.clean();

        }
        return null;
    }

    public static Factory startLocalZookeeper(String tmpDir) {
        for (int i = 2000; i < 65535; i++) {
            try {
                return Zookeeper.mkInprocessZookeeper(tmpDir, i);
            } catch (Exception e) {
                LOG.error("fail to launch zookeeper at port: " + i, e);
            }
        }
        throw new RuntimeException("No port is available to launch an inprocess zookeeper.");
    }

    public static NimbusData makeNimbusData(Map conf){

    }

    public static SupervisorData makeSupervisorData(Map conf){

    }

    public static ServerData makeServerData(Map conf){

    }

    public static Map getLocalConf(){

    }

    public static String getTmpDir() {
        return Constant.local_tmpdir + File.separator + UUID.randomUUID();
    }


    private static Map deepCopyMap(Map map) {
        return new HashMap(map);
    }
}
