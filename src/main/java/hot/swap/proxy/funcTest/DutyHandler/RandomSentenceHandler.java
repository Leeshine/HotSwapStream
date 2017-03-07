package hot.swap.proxy.funcTest.DutyHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by leeshine on 3/6/17.
 */
public class RandomSentenceHandler extends Handler{
    @Override
    public void handleRequest(List<String> lists) {
    }

    @Override
    public void handleRequest() {
        String[] sentences = new String[]{"the cow jumped over the moon", "an apple a day keeps the doctor away",
        "four score and seven years ago", "snow white and the seven dwarfs", "i am at two with nature"};
        Random random = new Random();
        try{
            while(true) {
                Thread.sleep(1000);
                String sentence = sentences[random.nextInt(sentences.length)];
                String[] sen = sentence.split(" ");
                List<String> candidates = new ArrayList<String>();
                for (int i = 0; i < sen.length; ++i)
                    candidates.add(sen[i]);
                getNextHandler().handleRequest(candidates);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
