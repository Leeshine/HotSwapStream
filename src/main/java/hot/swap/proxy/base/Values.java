package hot.swap.proxy.base;

import java.util.ArrayList;

/**
 * Created by leeshine on 3/6/17.
 */

public class Values extends ArrayList<Object> {
    public Values(){
    }

    public Values(Object... vals){
        super(vals.length);
        for(Object o : vals)
            add(o);
    }

    private int pos = 0;

    public Object get(){
        return get(pos++);
    }

    public String getString(int index){
        return (String)get(index);
    }

    public int getInt(int index){
        return (Integer)get(index);
    }
}
