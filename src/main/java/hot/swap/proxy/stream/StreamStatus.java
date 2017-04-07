package hot.swap.proxy.stream;

import java.io.Serializable;

/**
 * Created by leeshine on 4/6/17.
 */
public class StreamStatus implements Serializable{
    private static final long serialVersionUID = -3013095336395395123L;

    private StatusType type;
    private StreamStatus oldStatus = null;

    public StreamStatus(StatusType type){
        this(type,null);
    }

    public StreamStatus(StatusType type, StreamStatus oldStatus){
        this.type = type;
        this.oldStatus = oldStatus;
    }

    public StatusType getStatusType(){
        return type;
    }

    public StreamStatus getOldStatus(){
        return  oldStatus;
    }

    public boolean equals(Object base){
        if((base instanceof StreamStatus) == false)
            return false;

        StreamStatus check = (StreamStatus)base;
        if(check.getStatusType().equals(this.getStatusType()))
            return true;
        return false;
    }

    public int hashCode(){
        return this.getStatusType().hashCode();
    }
}
