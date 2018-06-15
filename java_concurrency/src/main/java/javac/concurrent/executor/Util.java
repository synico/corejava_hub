package javac.concurrent.executor;

import java.nio.ByteBuffer;

public class Util {

    public static int transmogrify(int data) {
        /**
         * data ^ ' ' to transform characters between lower case and upper case
         */
        return Character.isLetter(data) ? data ^ ' ' : data;
    }

    public static void transmogrify(ByteBuffer buf) {
        buf.flip();
        for (int i = 0; i < buf.limit(); i++) {
            buf.put(i, (byte) transmogrify(buf.get(i)));
        }
    }
}
