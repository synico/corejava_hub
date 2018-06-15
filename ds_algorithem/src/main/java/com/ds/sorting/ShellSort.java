package com.ds.sorting;

import com.ds.util.PrintUtil;

public class ShellSort {
    
    public static int[] shellSort1(int[] array) {
        int j;
        for(int gap = array.length/2; gap > 0;gap /= 2) {
            psg("GAP: " + gap);
            for(int i = gap; i < array.length; i++) {
                int temp = array[i];
                for(j = i; j >= gap && temp < array[j -gap]; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }
        return array;
    }
    
    private static void psg(String msg) {
        System.out.println(msg);
    }
    
    public static void shellSorting(int [] a) {
        int delta;//define delta
        for(delta = a.length/2; delta > 0; delta = delta/2) {
            //start to loop list
            for(int p = delta; p < a.length ; p++) {
                int temp = a[p];
                int i;
                for(i = p; i > delta -1  && temp < a[i-delta] ;i = i -delta) {
                    a[i] = a[i - delta];
                }
                a[i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] demo = {81, 94, 11, 96, 12, 35, 17, 95, 28, 58, 41, 75, 15};
        PrintUtil.printList(demo);
//        int[] sortedDemo = ShellSort.shellSort1(demo);
//        PrintUtil.printList(sortedDemo);
        ShellSort.shellSorting(demo);
        PrintUtil.printList(demo);
    }

}
