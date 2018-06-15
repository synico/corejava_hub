package com.ds.sorting;

import com.ds.util.PrintUtil;

public class BubbleSort {
    
    public static int [] bubbleSort(int a[]) {
        int counter = 0;
        for(int i = a.length; i >= 0; i--) {
            for(int j = 0; j < i - 1; j++) {
                counter++;
                if(a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        System.out.println("counter: " + counter);
        return a;
    }
    
    public static int [] bubbleSort1(int a[]) {
        for(int i = 0; i < a.length; i++) {
            int temp;
            for(int j = 0; j < a.length - i - 1; j++) {
              if(a[j] > a[j + 1]) {
                temp = a[j];
                a[j] = a[j + 1];
                a[j + 1] = temp;
              }
            }
          }
        return a;
    }
    
    public static int [] bubleSortImproved(int a[]) {
        int counter = 0;
        boolean swapped = false;
        for(int i = a.length; i >= 0; i--) {
            swapped = false;
            for(int j = 0; j < i -1; j++) {
                counter++;
                if(a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    swapped = true;
                }
            }
            if(!swapped) {
                break;
            }
        }
        System.out.println("counter: " + counter);
        return a;
    }

    public static void main(String[] args) {
        int[] demo = {81, 94, 11, 96, 12, 35, 17, 95, 28, 58, 41, 75, 15};
        PrintUtil.printList(demo);
//        BubbleSort.bubleSortImproved(demo);
        BubbleSort.bubbleSort1(demo);
        PrintUtil.printList(demo);
    }

}
