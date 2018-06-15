package com.ds.sorting;

import com.ds.util.PrintUtil;

public class MergeSort {
    
    private static void mergeSort(int [] a, int [] temp, int left, int right) {
        psg("left: " + left + ", right: " + right + ", center: " + (left + right)/2);
        if(left < right) {
            int center = (left + right)/2;
            mergeSort(a, temp, left, center);
            psg("@left: " + left + ", right: " + right + ", center: " + center);
            mergeSort(a, temp, center + 1, right);
            psg("$left: " + left + ", right: " + right + ", center: " + center);
            merge(a, temp, left, center + 1, right);
            PrintUtil.printList(a);
        }
    }
    
    private static void merge(int [] a, int [] temp, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        psg("   start to merge ==== leftPos: " + leftPos + ", rightPos: " + rightPos + ", rightEnd: " + rightEnd + ", numElements: " + numElements);
        
        //Main Loop
        while(leftPos <= leftEnd && rightPos <= rightEnd) {
            if(a[leftPos] <= a[rightPos]) {
                temp[tmpPos++] = a[leftPos++];
                psg("       tmpPos: " + tmpPos + ", leftPos: " + leftPos);
            } else {
                temp[tmpPos++] = a[rightPos++];
                psg("       tmpPos: " + tmpPos + ", rightPos: " + rightPos);
            }
        }
        
        while(leftPos <= leftEnd) {//copy rest of first half
            temp[tmpPos++] = a[leftPos++];
            psg("       copy rest of first half ==== tmpPos: " + tmpPos + ", leftPos: " + leftPos);
        }
        
        while(rightPos <= rightEnd) {//copy rest of right half
            temp[tmpPos++] = a[rightPos++];
            psg("       copy rest of right half ==== tmpPos: " + tmpPos + ", rightPos: " + rightPos);
        }
        
        //Copy temp
        for(int i = 0; i < numElements; i++, rightEnd--) {
            a[rightEnd] = temp[rightEnd];
        }
    }
    
    public static void mergeSort(int [] a) {
        int [] temp = new int[a.length];
        mergeSort(a, temp, 0, a.length - 1);
    }
    
    public static void main(String[] args) {
        int[] demo = {24, 13, 26, 1, 2, 27, 38, 15};
        int[] demo1 = {49, 38, 65, 97, 76, 13, 27};
//        PrintUtil.printList(demo);
        MergeSort.mergeSort(demo1);
//        PrintUtil.printList(demo);
    }
    
    private static void psg(String msg) {
        System.out.println(msg);
    }

}
