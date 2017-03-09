package hot.swap.proxy.testutil;

import hot.swap.proxy.base.KryoValuesSerializer;
import hot.swap.proxy.testutil.TestClass;

/**
 * Created by leeshine on 3/9/17.
 */
public class TestMain {
    public static void main(String[] args) throws Exception{
        TestClass testClass = new TestClass();
        testClass.add("abc");
        testClass.add("def");

        KryoValuesSerializer valuesSerializer = new KryoValuesSerializer();
        byte[] bs = valuesSerializer.serializeObject(testClass);


        TestClass ls = (TestClass) valuesSerializer.deserializeObject(bs);
        System.out.println(ls.get());
        ls.test();
    }
}
