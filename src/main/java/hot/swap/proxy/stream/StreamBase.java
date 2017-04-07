package hot.swap.proxy.stream;

import java.io.Serializable;

/**
 * Created by leeshine on 4/6/17.
 */
public class StreamBase implements Serializable{
    private static final long serialVersionUID = -2113095336395395213L;

    private String streamName;
    private StreamStatus status;

    public StreamBase(String streamName, StreamStatus status){
        this.streamName = streamName;
        this.status = status;
    }

    public String getStreamName(){
        return streamName;
    }

    public void setStreamName(String streamName){
        this.streamName = streamName;
    }

    public StreamStatus getStatus(){
        return status;
    }

    public void setStatus(StreamStatus status){
        this.status = status;
    }

    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((status==null) ? 0 : status.hashCode());
        result = prime * result + ((streamName==null) ? 0 : streamName.hashCode());
        return result;
    }

    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(this.getClass() != obj.getClass())
            return false;

        StreamBase base = (StreamBase)obj;
        if(streamName == null){
            if(base.streamName != null)
                return false;
        }else if(!base.streamName.equals(this.streamName)){
            return false;
        }

        if(status == null){
            if(base.status != null)
                return false;
        }else if(!base.status.equals(this.status)){
            return false;
        }
        return true;
    }
}
