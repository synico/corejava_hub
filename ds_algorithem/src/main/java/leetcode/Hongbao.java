package leetcode;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Hongbao {

    public List<Integer> hongbao1(int totalAmount, int totalNumber) {
        List<Integer> list = new ArrayList<>();
        if(totalAmount <= 0 || totalNumber <= 0) {
            return list;
        }
        for(int i = totalNumber; i >= 2; i--) {
            int x = (totalAmount << 1) / i;
            int random = ThreadLocalRandom.current().nextInt(1, x);
            list.add(random);
            totalAmount -= random;
        }
        list.add(totalAmount);
        return list;
    }

    public List<Integer> hongbao2(int totalAmount, int totalNumber) {
        List<Integer> list = new ArrayList<>();
        if(totalAmount <= 0 || totalNumber <= 0) {
            return list;
        }
        Set<Integer> set = new HashSet<>();
        while(set.size() < totalNumber - 1) {
            int random = ThreadLocalRandom.current().nextInt(1, totalAmount);
            set.add(random);
        }

        Integer[] amounts = set.toArray(new Integer[0]);
        Arrays.sort(amounts);
        list.add(amounts[0]);
        for(int i = 1; i < amounts.length; i++) {
            list.add(amounts[i] - amounts[i - 1]);
        }
        list.add(totalAmount - amounts[amounts.length - 1]);

        return list;
    }

    public static void main(String args[]) {
        Hongbao instance = new Hongbao();
        System.out.println(instance.hongbao2(10, 5));
    }
}
