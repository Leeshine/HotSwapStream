package hot.swap.proxy.utils;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by leeshine on 3/6/17.
 */
public class StringSerializer {
    private static final Charset charSet = Charset.forName("UTF-8");
    private static final CharsetDecoder decoder = charSet.newDecoder();

    public static final void StringToBuffer(final ByteBuffer buf, final String string){
       if(string == null){
            buf.putInt(Integer.MAX_VALUE);
            return;
        }
        final byte[] bytes = string.getBytes();
        final int len = bytes.length;
        buf.putInt(len);
        buf.put(bytes);
    }


    public static final String BufferToString(final ByteBuffer buffer){
        buffer.flip();
        final int len = buffer.getInt();
        if(len == Integer.MIN_VALUE)
            return null;
        final byte[] bytes = new byte[len];
        buffer.get(bytes,0,len);
        return new String(bytes,0,len,charSet);
    }
}
