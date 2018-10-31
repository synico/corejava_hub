package com.ds.hash;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @date 2018/10/31
 */

public class BloomFilterUsage {

    private static final int INSERT_AMOUNT = 1000000;

    public static void main(String[] args) {

        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), INSERT_AMOUNT, 0.001);

        Set<String> set = new HashSet<String>(INSERT_AMOUNT);
        List<String> list = new ArrayList<String>(INSERT_AMOUNT);

        for(int i = 0; i < INSERT_AMOUNT; i++) {
            String uuid = UUID.randomUUID().toString();
            bloomFilter.put(uuid);
            set.add(uuid);
            list.add(uuid);
        }

        int wrong = 0;
        int right = 0;

        for(int i = 0; i < 10000; i++) {
            String str = i % 100 == 0 ? list.get(i / 100) : UUID.randomUUID().toString();
            if(bloomFilter.mightContain(str)) {
                if(set.contains(str)) {
                    right++;
                } else {
                    wrong++;
                }
            }
        }

        System.out.println("right: " + right);

        System.out.println("wrong: " + wrong);
    }
}
