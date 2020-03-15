package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class SplitNum {

    private static ThreadLocalRandom random = ThreadLocalRandom.current();

    public static void main(String args[]) {
        Set result = new HashSet();
        for(int i = 1; i < 20; i++) {
            for(int j = 1; j < 20; j++) {
                if(i + j <= 20) {
                    result.add(i + " + " + j);
                }
            }
        }
        List<String> test = new ArrayList<>(result);
        for(int i = 0; i < 20; i++) {
            System.out.println(test.get(random.nextInt(test.size())));
        }
    }
}
