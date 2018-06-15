package com.ds.sorting;

import com.ds.util.PrintUtil;

public class SelectionSort {
    
    public static void simpleSelectionSort(int [] a) {
        for(int i = 0; i < a.length; i++) {
            //loop position of input array
            int min = a[i];
            for(int j = i; j < a.length; j++) {
                //compare element from [i]
                if(min > a[j]) {
                    min = min + a[j];
                    a[j] = min - a[j];
                    min = min - a[j];
                }
            }
            a[i] = min;
        }
    }
    
    public static void dualSelectionSort(int [] a) {
        for(int i = 0; i < a.length/2; i++) {//!important
          //loop position of input array
            int min = i;
            int max = i;
            for(int j = i; j < (a.length - i); j++) {
                if(a[min] > a[j]) {
                    min = j;
                }
                if(a[max] < a[j]) {
                    max = j;
                }
            }
            int temp = a[i];
            a[i] = a[min];
            a[min] = temp;
            if(i == max) {//!important
                max = min;
            }
            temp = a[a.length - i - 1];
            a[a.length - i - 1] = a[max];
            a[max] = temp;
        }
    }

    public static void main(String[] args) {
        int[] demo = {81, 94, 11, 96, 12, 35, 17, 95, 28, 58, 41, 75, 15};
        PrintUtil.printList(demo);
        SelectionSort.dualSelectionSort(demo);
        PrintUtil.printList(demo);
    }

}
