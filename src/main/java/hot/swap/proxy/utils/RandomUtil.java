package hot.swap.proxy.utils;

import java.util.Random;

/**
 * Created by leeshine on 3/8/17.
 */
public class RandomUtil {
    private static Random random = new Random();
    private static String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static int charLength = characters.length();
    private static int defaultStringLength = 6;
    private static int defaultIntLimit = 100000;

    public static int RandomInt(int limit){
        return random.nextInt(limit);
    }

    public static int RandomInt(){
        return random.nextInt(defaultIntLimit);
    }

    public static String RandomString(){
        return RandomString(defaultStringLength);
    }

    public static String RandomString(int length){
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<length; ++i){
            int index = RandomInt(charLength);
            sb.append(characters.substring(index,index+1));
        }
        return sb.toString();
    }

}
