package hot.swap.proxy.utils;

import com.sun.deploy.util.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by leeshine on 4/1/17.
 */
public class Utils {
    public static CuratorFramework newCurator(Map conf, List<String> servers, Integer port, String root) {
        List<String> serverPorts = new ArrayList<String>();
        for (String zkServer : (List<String>) servers) {
            serverPorts.add(zkServer + ":" + String.valueOf(port));
        }
        String zkStr = StringUtils.join(serverPorts, ",") + root;
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder();

        builder.connectString(zkStr).connectionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,3));


        return builder.build();
    }

    public static byte[] barr(byte v) {
        byte[] byteArray = new byte[1];
        byteArray[0] = v;

        return byteArray;
    }

    public static byte[] barr(Short v) {
        byte[] byteArray = new byte[Short.SIZE / 8];
        for (int i = 0; i < byteArray.length; i++) {
            int off = (byteArray.length - 1 - i) * 8;
            byteArray[i] = (byte) ((v >> off) & 0xFF);
        }
        return byteArray;
    }

    public static byte[] barr(Integer v) {
        byte[] byteArray = new byte[Integer.SIZE / 8];
        for (int i = 0; i < byteArray.length; i++) {
            int off = (byteArray.length - 1 - i) * 8;
            byteArray[i] = (byte) ((v >> off) & 0xFF);
        }
        return byteArray;
    }

    public static Properties getProperties(String file){
        Properties prop = new Properties();
        InputStream ins = Utils.class.getClassLoader().getResourceAsStream(file);
        try{
            prop.load(ins);
        }catch (Exception e){
            e.printStackTrace();
        }
        return prop;
    }


}
