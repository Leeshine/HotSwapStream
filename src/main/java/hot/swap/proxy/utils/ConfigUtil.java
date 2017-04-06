package hot.swap.proxy.utils;

import java.util.Map;

/**
 * Created by leeshine on 4/6/17.
 */
public class ConfigUtil {
    public static boolean local_mode(Map conf){
        String mode = (String)conf.get(Constant.STORM_CLUSTER_MODE);
        if(mode != null){
            if(mode.equals(Constant.LOCAL_MODE))
                return true;

            if(mode.equals(Constant.DISTRIBUTED_MODE))
                return false;

        }
        throw new IllegalArgumentException("Illegal cluster mode in conf :" + mode);
    }
}
