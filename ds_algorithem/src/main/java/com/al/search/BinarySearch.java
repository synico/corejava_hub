package com.al.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinarySearch {

    private static final Logger log = LoggerFactory.getLogger(BinarySearch.class);

    private void binarySearch() {

    }

    public static void main(String[] args) {
        System.out.println("binary search");
        String str = "11:00-11:30";
        String sp[] = str.split("[:|-]");
        log.info("{}", sp.length);
    }
}
