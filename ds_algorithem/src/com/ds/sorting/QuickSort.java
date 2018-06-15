package com.ds.sorting;

import com.ds.util.PrintUtil;

public class QuickSort {
    
    public static void bubbleSort(int [] a) {
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
    }
    
    public static void quicksort(int [] a) {
        if(a.length <= 1) {
            return;
        } else {
            quicksort(a, 0, a.length - 1);
        }
    }
    
    private static int partition(int [] a, int low, int high) {
        int pivot = low;
        while(low < high) {
            while(low < high && a[high] > a[pivot]) {
                high--;
            }
            while(low < high && a[++low] < a[pivot]) {
            }
            swapReferences(a, low, high);
        }
        swapReferences(a, pivot, low);
        return low;
    }
    
    private static void quicksort(int [] a, int low, int high) {
        System.out.print("low: " + low + ", high: " + high);
        if(low < high) {
            int pivotloc = partition(a, low, high);
            System.out.println(" , pivotloc: " + pivotloc);
            quicksort(a, low, pivotloc - 1);
            quicksort(a, pivotloc + 1, high);
        }
    }
    
    private static int median3(int [] a, int left, int right) {
        int center = (left + right) / 2;
        if(a[center] < a[left]) {
            swapReferences(a, left, center);
        }
        if(a[right] < a[left]) {
            swapReferences(a, left, right);
        }
        if(a[right] < a[center]) {
            swapReferences(a, center, right);
        }
        swapReferences(a, center, right - 1);
        return a[right - 1];
    }
    
    private static void swapReferences(int [] a, int left, int right) {
        int temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }

    public static void main(String[] args) {
        int[] demo = {13, 81, 92, 43, 65, 31, 57, 26, 75, 0};
        int[] demo1 = {8, 1, 4, 9, 6, 3, 5, 2, 7, 0};
        int[] demo2 = {2, 8, 7, 1, 3, 5, 6, 4};
        PrintUtil.printList(demo1);
        QuickSort.quicksort(demo1);
        PrintUtil.printList(demo1);
    }

}
