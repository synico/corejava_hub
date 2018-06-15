package javac.generic;

import java.util.Date;

public class PairTest {

    public static void main(String[] args) {
        Date d1 = new Date();
        Date d2 = new Date();
        DateInterval interval = new DateInterval();
        Pair pair = interval;
        Object d3 = new Date();
        pair.setSecond(d3);

        int j = 0;
        for (int i = 0; i < 100; i++) {
            j = j++;
        }
        // j = 0
        System.out.println(j);
    }

}
