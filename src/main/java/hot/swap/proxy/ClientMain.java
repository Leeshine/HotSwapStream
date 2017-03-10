package hot.swap.proxy;

import hot.swap.proxy.funcTest.GenerateModule;
import hot.swap.proxy.funcTest.LowerModule;
import hot.swap.proxy.funcTest.PrintModule;
import hot.swap.proxy.funcTest.UpperModule;
import hot.swap.proxy.smanager.SwapManager;
import hot.swap.proxy.stream.StreamBuilder;
import hot.swap.proxy.stream.StreamStarter;

/**
 * Created by leeshine on 3/3/17.
 */

public class ClientMain {
    public static void main(String[] args) throws Exception {
        System.setProperty("log4j.configuration","/home/leeshine/MasterPaper/HotSwapProxy/src/" +
                "main/resources/log4j.properties");
        StreamBuilder builder = new StreamBuilder();
        GenerateModule generateModule = new GenerateModule("generate");
        //UpperModule upperModule = new UpperModule("upper");
        LowerModule lowerModule = new LowerModule("lower");
        PrintModule printModule = new PrintModule("print");

        builder.setModule(generateModule);
        builder.setModule(lowerModule).grouping(generateModule.getId());
        //builder.setModule(lowerModule).grouping(generateModule.getId());
        builder.setModule(printModule).grouping(lowerModule.getId());

        SwapManager swapManager = StreamStarter.submitTopology(builder,"test");
        Thread.sleep(1000);
        swapManager.swapModule(lowerModule.getId(),"upper",UpperModule.class.getName());
    }
}
