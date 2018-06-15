package com.ds.util;

public interface PrintUtil {
    
    static void printList(int[] array) {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i : array) {
            str.append(i);
            str.append(",");
        }
        str.deleteCharAt(str.length() -1);
        str.append("]");
        System.out.println("array: " + str.toString());
    }

}
