package hot.swap.proxy.smodule;

import hot.swap.proxy.base.KryoValuesSerializer;
import hot.swap.proxy.base.SComponent;
import hot.swap.proxy.base.Values;
import hot.swap.proxy.message.IConnection;
import hot.swap.proxy.smodule.interfaceutil.SwapInterface;
import hot.swap.proxy.transcation.Vote;
import hot.swap.proxy.utils.Pair;
import hot.swap.proxy.utils.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by leeshine on 3/6/17.
 */

// to do state safety
public class SwapModule extends SComponent implements  SwapInterface,Cloneable{
    private Logger LOG = LoggerFactory.getLogger(SwapModule.class);
    private String state;
    private String proxyName;

    private SwapModule(){// should not be used!!
        super(RandomUtil.RandomString(),true);
        state = ModuleState.INIT;
    }

    public SwapModule(String _id){
        super(_id,true);
        state = ModuleState.INIT;
    }

    public  void execute(Values values){
    }


    public FunModuleIdentifier getIdentifier() {
        return null;
    }

    public String getState() {
        return null;
    }

    @Override
    public void init(IConnection connection,String proxyName) {
        super.init(connection,proxyName);
        state = ModuleState.IDLE;
        this.proxyName = proxyName;
    }

    @Override
    public void startRun(){
    }

    public byte[] getSerilizer(){
        KryoValuesSerializer serializer = new KryoValuesSerializer();
        return serializer.serializeObject(this);
    }

    public Pair<Vote,SwapModule> prepareSwap(){
        try{
            checkState();
        }catch (Exception e){
            LOG.error(e.getMessage());
            state = ModuleState.ABORTED;
            byte[] bs = new byte[0];
            return new Pair(Vote.NO,bs);
        }

        byte[] serilizeClass = getSerilizer();
        state = ModuleState.PREPARED;

        return new Pair(Vote.YES,serilizeClass);
    }

    public void checkState() throws Exception{
        //synchronized (state){
           //state.wait();
            if(state != ModuleState.BLOCKED)
                throw new Exception("error state in prepared time");
            state = ModuleState.VOTING;
       // }
    }

    public void setState(String moduleState){
        this.state = moduleState;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        //to do deep clone??
        SwapModule cloneClass = null;
        cloneClass = (SwapModule)super.clone();

        return cloneClass;
    }
}
