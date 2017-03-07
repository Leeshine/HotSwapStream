package hot.swap.proxy.funcTest.DutyHandler;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by leeshine on 3/6/17.
 */
public class SortHandler extends Handler{
    @Override
    public void handleRequest(List<String> lists) {
        Collections.sort(lists, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(lists);
    }

    @Override
    public void handleRequest() {

    }
}
