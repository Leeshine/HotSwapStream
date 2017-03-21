package hot.swap.proxy.base.dotparser;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by leeshine on 3/21/17.
 */

public class GraphManager {
    private InputStream inputStream;

    public GraphManager(String inputFile) throws FileNotFoundException{
        InputStream stream = new FileInputStream(inputFile);
    }

    public Graph parserGraph(){
        return null;
    }
}
