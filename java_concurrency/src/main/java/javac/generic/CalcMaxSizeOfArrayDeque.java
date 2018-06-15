package javac.generic;

import java.util.concurrent.TimeUnit;

public class CalcMaxSizeOfArrayDeque {

    public static void main(String[] args) {
        int size = 1;
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            size = size << 1;
            System.out.println(size);
        }
    }

}
