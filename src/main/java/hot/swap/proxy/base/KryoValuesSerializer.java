package hot.swap.proxy.base;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.IOException;
import java.util.List;

/**
 * Created by leeshine on 3/7/17.
 */
public class KryoValuesSerializer {
    Kryo kryo;
    ListDelegate _delegate;
    Output _kryoOut;
    Input _kryoInput;

    public KryoValuesSerializer(){
        kryo = new Kryo();
        _delegate = new ListDelegate();
        _kryoOut = new Output(2000, 2000000000);
        _kryoInput = new Input(1);
    }

    public void serializeInto(List<Object> values, Output out) throws IOException {
        _delegate.setDelegate(values);
        kryo.writeObject(out, _delegate);
    }

    public byte[] serialize(List<Object> values) throws IOException{
        _kryoOut.clear();
        serializeInto(values,_kryoOut);
        return _kryoOut.toBytes();
    }

    public byte[] serializeObject(Object object){
        _kryoOut.clear();
        kryo.writeClassAndObject(_kryoOut,object);
        return _kryoOut.toBytes();
    }

    public List<Object> deserializeFrom(Input input) {
        ListDelegate delegate = (ListDelegate) kryo.readObject(input, ListDelegate.class);
        return delegate.getDelegate();
    }

    public List<Object> deserialize(byte[] ser) throws IOException {
        _kryoInput.setBuffer(ser);
        return deserializeFrom(_kryoInput);
    }

    public Object deserializeObject(byte[] ser) throws IOException {
        _kryoInput.setBuffer(ser);
        return kryo.readClassAndObject(_kryoInput);
    }

}
