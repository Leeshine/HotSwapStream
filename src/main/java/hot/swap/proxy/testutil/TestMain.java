package hot.swap.proxy.testutil;

/**
 * Created by leeshine on 3/9/17.
 */
public class TestMain {
    public static void main(String[] args) throws Exception{
       /* Boolean bs = false;
        Tests ts = new Tests(bs);
        Thread thread = new Thread(ts);
        thread.start();
        Thread.sleep(1000);
        System.out.println("begin to notify");
        synchronized (bs) {
            bs.notify();
        }*/
        TestClass testClass = new TestClass();
        testClass.test();
    }

    static class Tests implements Runnable{
        private  Boolean bs;
        public Tests(Boolean bs){
            this.bs = bs;
        }
        public void run() {
            while (true){
                try {
                    System.out.println("waiting");
                    synchronized (bs){
                        //bs.wait();
                        Thread.sleep(10000);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("wake up");
            }
        }
    }
}
