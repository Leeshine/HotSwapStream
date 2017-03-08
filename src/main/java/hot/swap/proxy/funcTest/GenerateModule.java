package hot.swap.proxy.funcTest;

import hot.swap.proxy.base.Values;
import hot.swap.proxy.nonswapmodule.NonSwapModule;

import java.util.Random;

/**
 * Created by leeshine on 3/8/17.
 */
public class GenerateModule extends NonSwapModule{
    public GenerateModule(String _id) {
        super(_id);
    }

    @Override
    public void startRun() {
        //super.startRun();
        Runclass runclass = new Runclass();
        new Thread(runclass).start();
    }

    class Runclass implements Runnable{
        String words = "lv shanchun Is Xinazai CHUN GE gG";
        String cadiates[] = words.split(" ");
        Random random = new Random();

        public void run() {
            while(true){
                String word = cadiates[random.nextInt(cadiates.length)];
                send(new Values(word));
                try{
                    Thread.sleep(1000);
                }catch (Exception e){

                }
            }
        }
    }
}
