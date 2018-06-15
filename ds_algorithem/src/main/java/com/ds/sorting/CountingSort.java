package com.ds.sorting;

import com.ds.util.PrintUtil;

public class CountingSort {
    
    public static int [] countingSort(int arr[]) {
        int max = arr[0], min = arr[0];
        int [] b = new int[arr.length];
        for(int num : arr) {
            if(num > max) {
                max = num;
            }
            if(num < min) {
                min = num;
            }
        }
        int k = max - min + 1;
        int [] c = new int[k];
        for(int i = 0; i < arr.length; ++i) {
            c[arr[i] - min] += 1;
        }
        System.out.print("print array c: ");
        PrintUtil.printList(c);
        
        for(int i = 1; i < c.length; ++i) {
            c[i] = c[i] + c[i-1];
        }
        System.out.print("print array c: ");
        PrintUtil.printList(c);
        
        for(int i = arr.length - 1; i >= 0; --i) {
            b[--c[arr[i] - min]] = arr[i];
        }
        return b;
    }

    public static void main(String[] args) {
        int[] demo = { 34, 8, 64, 51, 32, 21 };
        PrintUtil.printList(CountingSort.countingSort(demo));
    }

}
