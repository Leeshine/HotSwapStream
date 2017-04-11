package hot.swap.proxy.utils;

import com.sun.deploy.util.StringUtils;
import org.apache.commons.io.input.ClassLoaderObjectInputStream;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.*;
import java.net.URLClassLoader;
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

    public static byte[] serialize(Object obj){
        return javaSerialize(obj);
    }

    public static byte[] javaSerialize(Object obj){
        if(obj instanceof  byte[]){
            return (byte[])obj;
        }

        try{
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.close();
            return bos.toByteArray();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static Object deserialize(byte[] serialized){
        return javaDeserialize(serialized);
    }

    public static Object javaDeserialize(byte[] serialized){
        Object res = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(serialized);
            ObjectInput ois = new ObjectInputStream(bis);
            res = ois.readObject();
            ois.close();
        }catch (IOException ioe){
            throw new RuntimeException(ioe);
        }catch (ClassNotFoundException classe){
            throw new RuntimeException(classe);
        }
        return res;
    }

    /*public static <T> T deserialize(byte[] serialized, Class<T> clazz){
    }

    public static Object javaDeserialize(byte[] serialized){
        return javaDeserializeWithClassLoader(serialized,)
    }

    public static Object javaDeserializeWithClassLoader(byte[] serialized, URLClassLoader loader){
        try{
            ByteArrayInputStream bis = new ByteArrayInputStream(serialized);
            Object res = null;
            if(loader != null){
                ClassLoaderObjectInputStream cis = new ClassLoaderObjectInputStream(loader,bis);
                res = cis.readObject();
                cis.close();
            }else{
                ObjectInputStream ois = new ObjectInputStream(bis);
                res = ois.readObject();
                ois.close();
            }
            return res;
        }catch (IOException ioe){
            throw new RuntimeException(ioe);
        }catch (ClassNotFoundException cfe){
            throw new RuntimeException(cfe);
        }
    }*/

}
