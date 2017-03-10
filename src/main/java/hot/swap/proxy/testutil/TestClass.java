package hot.swap.proxy.testutil;

import hot.swap.proxy.base.Values;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leeshine on 3/9/17.
 */
public class TestClass{
    private List<String> ls;
    private int pos;
    Values values;
    private volatile Boolean bs;

    public TestClass(){
        ls = new ArrayList<String>();
        pos = -1;
        values = new Values("abc",123);
        bs = false;
    }

    public void add(String str){
        ls.add(str);
    }

    public String get() throws Exception{
        if(pos>=ls.size()){
            throw new Exception("wrong pos");
        }
        return ls.get(++pos);
    }

    public void test(){
        synchronized (bs){
            bs = true;
        }
        synchronized (bs){
            bs = false;
            bs.notify();
        }
        System.out.println("dasdsa");
    }

}
