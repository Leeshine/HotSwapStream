package hot.swap.proxy;

import hot.swap.proxy.funcTest.GenerateModule;
import hot.swap.proxy.funcTest.LowerModule;
import hot.swap.proxy.funcTest.PrintModule;
import hot.swap.proxy.funcTest.UpperModule;
import hot.swap.proxy.stream.StreamBuilder;
import hot.swap.proxy.stream.StreamStarter;

/**
 * Created by leeshine on 3/3/17.
 */

public class ClientMain {
    public static void main(String[] args) throws Exception {
        StreamBuilder builder = new StreamBuilder();
        GenerateModule generateModule = new GenerateModule("generate");
        UpperModule upperModule = new UpperModule("upper");
        LowerModule lowerModule = new LowerModule("lower");
        PrintModule printModule = new PrintModule("print");

        builder.setModule(generateModule);
        builder.setModule(upperModule).grouping(generateModule.getId());
        builder.setModule(lowerModule).grouping(generateModule.getId());
        builder.setModule(printModule).grouping(upperModule.getId()).grouping(lowerModule.getId());

        StreamStarter.submitTopology(builder,"test");
    }
}
