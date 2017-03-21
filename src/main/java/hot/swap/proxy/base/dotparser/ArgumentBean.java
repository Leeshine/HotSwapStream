package hot.swap.proxy.base.dotparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leeshine on 3/20/17.
 */

public abstract class ArgumentBean {
    private List<String> arg_names;
    private Map<String,String> arg_values;

    public ArgumentBean(){
        arg_names = new ArrayList<String>();
        arg_values = new HashMap<String, String>();
    }

    public List<String> getArgNames(){
        return arg_names;
    };

    public Map<String,String> getArgValues() {
        return arg_values;
    }

    public String getValue(String name){
        if(arg_values.containsKey(name)){
            return arg_values.get(name);
        }
        else
            return null;
    }

    public void addValue(String name, String value){
        if(!arg_values.containsKey(value)){
            arg_names.add(name);
        }
        arg_values.put(name,value);
    }
}
