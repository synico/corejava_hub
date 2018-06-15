package javac.singleton;

import java.util.concurrent.CyclicBarrier;

public class ClassInitSequence {

    public static void main(String[] args) throws Exception {
        System.out.println(Sub.taxi);
        String s1 = new String("iByteCode");
        System.out.println(s1);
        CyclicBarrier barrier = new CyclicBarrier(1);
        barrier.await();
    }

}

class Super {
    static {
        System.out.println("initialize class Super");
    }
    static int taxi = 1729;
}

class Sub extends Super {
    static {
        System.out.println("initialize class Sub");
    }
}